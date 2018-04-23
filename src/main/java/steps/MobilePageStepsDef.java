package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by makri on 4/09/2017.
 */
public class MobilePageStepsDef extends BaseStepsDef {


    public MobilePageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
        scenarioContext.getAppiumDriver().getAppiumDriver().getPageSource();
        PageFactory.initElements(new AppiumFieldDecorator((WebDriver)scenarioContext.getAppiumDriver().getAppiumDriver()), mobilePageModel);
    }

    @Override
    public void IShouldBeOnThePage() throws Throwable {
        takeScreenshotInReportsAndSaveOnDisk();
    }

    @Then("^I should be on the mobile page$")
    public void iShouldBeOnTheMobilePage() throws Throwable {
        IShouldBeOnThePage();
    }

    @Then("^I click alert button$")
    public void iClickAlertButton() throws Throwable {
        mobilePageModel.clickAlert();
    }

    @Then("^I should see a pop up window with title \"([^\"]*)\"$")
    public void iShouldSeeAPopUpWindowWithTitle(String arg0) throws Throwable {
        assertEquals("to check the title","Cool title",mobilePageModel.getTextFromCoolTitle());
    }
//
}