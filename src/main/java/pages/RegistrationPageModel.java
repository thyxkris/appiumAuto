package pages;


import infrastructure.KMobileElement;
import io.appium.java_client.MobileBy;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class RegistrationPageModel extends BasePageModel {
    public RegistrationPageModel(ScenarioContext scenarioContext) throws Exception {
        super(scenarioContext, "LatitudeApp.RegistrationView");
        //wait for the moving
        Thread.sleep(10000);
        //Why outdoor? - BuyThisSpace
    }


    //By COOL_TITLE = By.xpath("//XCUIElementTypeStaticText[@name=\"Cool accessibilityId\"]");
    final String IMG_EMAIL = "email";
    final String IMG_PASSWORD = "password";


    public void clickEmail() throws InterruptedException {

        driver.clickButton(IMG_EMAIL);

        //int count = driver.findElements(MobileBy.ByAccessibilityId.partialLinkText())

       // this.scenarioContext.getScenario().write(" how many text field = "+ count);
        KMobileElement kMobileElement = driver.findElementByAccessibilityId(IMG_EMAIL);
        driver.waitForElementInvisible(kMobileElement.getMobileElement());
       // Thread.sleep(1500);
        while(kMobileElement.getMobileElement().isDisplayed())
        {
            Thread.sleep(1000);
            logger.info("kMobileElement.getMobileElement().isDisplayed() "+kMobileElement.getMobileElement().isDisplayed());
        }

    }

    public void clickPassword() throws InterruptedException {

        driver.clickButton(IMG_PASSWORD);



    }


//    public String getTextFromCoolTitle() throws InterruptedException {
//        int waitTime = 0;
//        while (driver.findElements(COOL_TITLE).size() == 0 && ConfigHelper.getElementWaitTime() > waitTime) {
//            Thread.sleep(1000);
//            waitTime += 1000;
//        }
//        return driver.findElement(COOL_TITLE).getText();
//    }
}
