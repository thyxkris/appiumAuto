package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.KMobileElement;
import infrastructure.KWebElement;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.By;

/**
 * Created by makri on 4/09/2017.
 */
public class RegistrationPageStepsDef extends BaseStepsDef {


    public RegistrationPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
       // scenarioContext.getAppiumDriver().getAppiumDriver().getPageSource();
       // PageFactory.initElements(new AppiumFieldDecorator((WebDriver)scenarioContext.getAppiumDriver().getAppiumDriver()), launchPageModel);
    }

    @Override
    public void IShouldBeOnThePage() throws Throwable {
        registrationPageModel.isExpectedPage();
        takeScreenshotInReportsAndSaveOnDisk();
    }



    @Then("^I should be on the registration page$")
    public void iShouldBeOnTheRegistrationPage() throws Throwable {
        IShouldBeOnThePage();
    }

    @When("^I click the email image$")
    public void iClickTheEmailImage() throws Throwable {
       this.registrationPageModel.clickEmail();
    }

    @Then("^I should see the email input field show up$")
    public void iShouldSeeTheEmailInputFieldShowUp() throws Throwable {
        KMobileElement kMobileElement = this.scenarioContext.getAppiumDriver().findElementByAccessibilityId("email");
        this.scenarioContext.getAppiumDriver().waitForElementInvisible(kMobileElement.getMobileElement());
        By byTmp= By.xpath("//XCUIElementTypeApplication[@name=\"Latitude\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeTextField/XCUIElementTypeTextField");
        assertEquals("text field number > 0",1, this.scenarioContext.getAppiumDriver().findElements(byTmp).size());
    }
//
}
