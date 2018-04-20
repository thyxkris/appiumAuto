package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class VodQALoginPageStepsDef extends BaseStepsDef {


    public VodQALoginPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
       // scenarioContext.getAppiumDriver().getAppiumDriver().getPageSource();
       // PageFactory.initElements(new AppiumFieldDecorator((WebDriver)scenarioContext.getAppiumDriver().getAppiumDriver()), launchPageModel);
    }

    @Override
    @Given("^I should be on the VOA Login page$")
    public void IShouldBeOnThePage() throws Throwable {
        takeScreenshotInReportsAndSaveOnDisk();
    }


    @When("^I click login button$")
    public void iClickLoginButton() throws Throwable {
        vodQALoginPageModel.clikLogin();
    }
}
