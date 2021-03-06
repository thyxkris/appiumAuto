package steps;

import cucumber.api.Scenario;
import helpers.ConfigHelper;
import infrastructure.DriversFactory;
import libraries.helpers.TestConstantData;
import libraries.infrastructure.ScenarioContext;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;

import pages.*;

import pages.BaseModel;
import pages.BasePageModel;
import pages.LaunchPageModel;
import pages.RegistrationPageModel;


import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;


/**
 * Created by makri on 18/05/2017.
 */


public abstract class BaseStepsDef {

    //declare page models, everytime when adding a new page, must declare it here
    protected LaunchPageModel launchPageModel;
    protected RegistrationPageModel registrationPageModel;

    protected VodQALoginPageModel vodQALoginPageModel;
    protected VodQASampleListPageModel vodQASampleListPageModel;
    protected VodQADragDropPageModel vodQADragDropPageModel;
    protected MobilePageModel mobilePageModel;


    //below no need to modify
    protected Logger logger;
    protected ScenarioContext scenarioContext;

    //here is generic, should be put into another super: BaseStep
    public BaseStepsDef(ScenarioContext scenarioContext) throws Throwable {
        this.scenarioContext = scenarioContext;
        logger = scenarioContext.getLogger();
        logger.info(registerClass(this.getClass().getName()));

        logger.info("BaseStepsDef/registerClass " + this.getClass() + "end setup ");
    }

    public <T extends BaseModel> BaseModel registerClass(String T) throws Exception {
        logger.info("registerClass; " + this.getClass() + "start setup ");


        if (T.equals("steps.CommonStepsDef")) {
            return null;
        }
        T = T.replace("steps.", "pages.").replace("StepsDef", "Model");

        Class<?> c = Class.forName(T);
        Constructor<?> cons = c.getConstructor(ScenarioContext.class);

        for (Object field : this.getClass().getSuperclass().getDeclaredFields()) {
            if (((Field) field).getType().equals(Class.forName(T))) {
                ((Field) field).setAccessible(true);
                ((Field) field).set(this, cons.newInstance(scenarioContext));

                return scenarioContext.setCurrentPage((T) ((Field) field).get(this));
            }
        }

        return null;
    }

    public BasePageModel getCurrentPage() {
        return scenarioContext.getCurrentPage();
    }

    abstract public void IShouldBeOnThePage() throws Throwable;


    //below is project-related,
    public void setup(Scenario scenario) throws IOException {

        //initialize logger
        scenarioContext.setScenario(scenario);
        logger = scenarioContext.getLogger(getScenarioId());
        logger.info("starting -" + scenario.getName());
        //scenarioContext.setLogger(logger);

        //initialize appium driver
        scenarioContext.setAppiumDriver(DriversFactory.getDriver());
        scenarioContext.getAppiumDriver().setLogger(logger);

        outputConfigInfo();
        launchApp();
        scenarioContext.setContextNames(scenarioContext.getAppiumDriver().getContextHandles());
        // scenarioContext.switchToNative();
        //scenarioContext.switchToWebView();


    }

    public String getScenarioId() {
        for (Object o : scenarioContext.getScenario().getSourceTagNames().toArray()) {
            if (o.toString().contains("@id")) {
                return o.toString();
            }
        }
        throw new IllegalArgumentException("no scenario id defined !");
    }

    public org.openqa.selenium.Dimension setResolution() {

        org.openqa.selenium.Dimension resolution = null;
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            resolution = new org.openqa.selenium.Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
            logger.info("resolution is set W: " + (int) screenSize.getWidth() + " H: " + (int) screenSize.getHeight());
        } catch (HeadlessException e) {
            logger.info(e.toString());
            try {
                String[] res = ConfigHelper.getString("window.resolution").toLowerCase().split("x");
                logger.info("resolution is set W: " + Integer.valueOf(res[0]) + " H: " + Integer.valueOf(res[1]));
                resolution = new org.openqa.selenium.Dimension(Integer.valueOf(res[0]), Integer.valueOf(res[1]));
            } catch (Exception e1) {

                logger.info(e1.toString());
                resolution = new org.openqa.selenium.Dimension(1024, 768);
            }
        }
        return resolution;
    }

    public void outputConfigInfo() {

        logger.info("DriverType - " + helpers.ConfigHelper.getDriverType());
        logger.info("platformName - " + helpers.ConfigHelper.getPlatformVersion());
        logger.info("getAutomationName - " + helpers.ConfigHelper.getAutomationName());
        logger.info("getAppPath - " + helpers.ConfigHelper.getAppPath());
        logger.info("getAppiumVersion - " + helpers.ConfigHelper.getAppiumVersion());
        logger.info("getDeviceName - " + helpers.ConfigHelper.getDeviceName());

        logger.info("getCurrentWorkingDir - " + helpers.ConfigHelper.getCurrentWorkingDir());
        logger.info("getTemplateDir - " + helpers.ConfigHelper.getTemplateDir());
        logger.info("getTestResourcesFolderPath - " + helpers.ConfigHelper.getTestResourcesFolderPath());
        logger.info("getUserDir - " + helpers.ConfigHelper.getUserDir());
        logger.info("getTestEnv - " + helpers.ConfigHelper.getTestEnv());
        logger.info("implicit wait time - " + helpers.ConfigHelper.getImplicitWaitTimeString());
        logger.info("element wait time - " + helpers.ConfigHelper.getElementWaitTimeString());
        logger.info("pageload wait time - " + helpers.ConfigHelper.getPageLoadWaitTimeString());
        logger.info("selenium grid url - " + helpers.ConfigHelper.getSeleniumGridURL());
        logger.info("attempts.times - " + helpers.ConfigHelper.getAttemptCount());

    }

    public void launchApp() throws IOException {

        //String url = libraries.helpers.ConfigHelper.getStartingURL();
        //scenarioContext.getAppiumDriver().navigate().to(url);
        scenarioContext.getAppiumDriver().manage().timeouts().implicitlyWait(TestConstantData.NORMAL_WAIT_INTERNAL, TimeUnit.MILLISECONDS);
        //takeScreenshotInReportsAndSaveOnDisk();

    }


    public void tearUp() throws IOException {
        if (!scenarioContext.getScenario().getStatus().toLowerCase().contains("pass")) {
            writeToRerun(ConfigHelper.getTestResourcesFolderPath() + File.separator + "rerunTags.txt", getScenarioId());
        }
        takeScreenshotInReportsAndSaveOnDisk();

        logger.info(scenarioContext.getScenario().getName() + scenarioContext.getScenario().getSourceTagNames() + scenarioContext.getScenario().getStatus());

        scenarioContext.getScenario().write(scenarioContext.getScenario().getStatus() + scenarioContext.getScenario().getName() + scenarioContext.getScenario().getSourceTagNames());
        scenarioContext.dispose();
        scenarioContext.getAppiumDriver().quit();

    }

    public void writeToRerun(String rerunFile, String data) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            File file = new File(rerunFile);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
                data = "--tags " + data;
            } else {
                data = "," + data;
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write(data);


        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }

    }


    public ScenarioContext ResetTestingData() {
        return (ScenarioContext) scenarioContext.resetTestData();
    }


    //default, only take screenshots when failing.
    void assertEquals(String message, String expected, String actual) throws Throwable {
        assertEquals(message, expected, actual, false);
    }

    void assertEquals(String message, Integer expected, Integer actual) throws Throwable {
        assertEquals(message, expected, actual, false);
    }

    void assertEquals(String message, String expected, String actual, boolean takeScreenShotWhenPassed) throws Throwable {

        scenarioContext.getScenario().write(" actual = " + actual);
        scenarioContext.getScenario().write(" expected = " + expected);
        if (takeScreenShotWhenPassed) {
            takeScreenshotInReportsAndSaveOnDisk();
        }

        if (expected == null)//bypass the test
        {
            logger.info(" assertEquals skipped - expected has no input");
            return;
        }

        if (expected.equals("NULL"))//on purpose, want to check if the value is null
            expected = null;
        try {
            Assert.assertEquals(message, expected, actual);
            logger.info(" assertEquals pass - " + expected + " == " + actual);

        } catch (AssertionError e) {
            logger.info(" assertEquals fail - " + expected + " != " + actual);
            logger.info(e.toString());
            throw e;
        }
    }

    void assertEquals(String message, int expected, int actual, boolean takeScreenShotWhenPassed) throws Throwable {

        scenarioContext.getScenario().write(" actual = " + actual);
        scenarioContext.getScenario().write(" expected = " + expected);
        // scenarioContext.getScenario().write(" thread count = " + scenarioContext.getThreadId());
        if (takeScreenShotWhenPassed) {
            takeScreenshotInReportsAndSaveOnDisk();
        }


        try {
            Assert.assertEquals(message, expected, actual);
            logger.info(" assertEquals pass - " + expected + " == " + actual);

        } catch (AssertionError e) {
            logger.info(" assertEquals fail - " + expected + " != " + actual);
            logger.info(e.toString());
            throw e;
        }
    }

    void assertTrue(String message, boolean actual, boolean takeScreenShotWhenPassed) throws Throwable {

        scenarioContext.getScenario().write("actual = " + actual);
        scenarioContext.getScenario().write("expected = " + true);
        scenarioContext.getScenario().write("thread count = " + scenarioContext.getThreadId());
        if (takeScreenShotWhenPassed) {
            takeScreenshotInReportsAndSaveOnDisk();
        }

        try {
            Assert.assertTrue(message, actual);
            logger.info("assertEquals pass - " + actual + " == " + true);
        } catch (AssertionError e) {
            logger.info("assertEquals fail - " + actual + " != " + true);
            logger.info(e.toString());
            throw e;
        }
    }

    void assertTrue(boolean actual, boolean takeScreenShotWhenPassed) throws Throwable {
        assertTrue("", actual, takeScreenShotWhenPassed);
    }

    void assertTrue(boolean actual) throws Throwable {
        assertTrue("", actual, false);
    }

    void assertFalse(String message, boolean actual, boolean takeScreenShotWhenPassed) throws Throwable {

        scenarioContext.getScenario().write("actual = " + actual);
        scenarioContext.getScenario().write("expected = " + false);
        scenarioContext.getScenario().write("thread count = " + scenarioContext.getThreadId());
        if (takeScreenShotWhenPassed) {
            takeScreenshotInReportsAndSaveOnDisk();
        }

        try {
            Assert.assertFalse(message, actual);
            logger.info("assertEquals pass - " + actual + " == " + false);
        } catch (AssertionError e) {
            logger.info("assertEquals fail - " + actual + " != " + false);
            logger.info(e.toString());
            throw e;
        }
    }

    void assertFalse(boolean actual, boolean takeScreenShotWhenPassed) throws Throwable {
        assertFalse("", actual, takeScreenShotWhenPassed);
    }

    void assertFalse(boolean actual) throws Throwable {
        assertFalse("", actual, false);
    }

    void takeScreenshotInReportsAndSaveOnDisk() throws IOException {

        try {
            final byte[] foto = scenarioContext.getAppiumDriver().getScreenshotAs(OutputType.BYTES);
            //  scenarioContext.getScenario().write(takeScreenShot());
            scenarioContext.getScenario().embed(foto, "image/png");
        } catch (Exception e) {
            scenarioContext.getScenario().write(" take screen shot fails due to " + e.toString());
        }
    }


}
