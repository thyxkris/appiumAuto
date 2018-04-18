package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class LaunchPageStepsDef extends BaseStepsDef {


    public LaunchPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
       // scenarioContext.getAppiumDriver().getAppiumDriver().getPageSource();
       // PageFactory.initElements(new AppiumFieldDecorator((WebDriver)scenarioContext.getAppiumDriver().getAppiumDriver()), launchPageModel);
    }

    @Override
    public void IShouldBeOnThePage() throws Throwable {
        takeScreenshotInReportsAndSaveOnDisk();
    }



    @Given("^I should be on the launch page$")
    public void iShouldBeOnTheLaunchPage() throws Throwable {
        IShouldBeOnThePage();
    }

    @Then("^I click 'get started' button$")
    public void iClickGetStartedButton() throws Throwable {
        launchPageModel.clickGetStarted();
    }


//
}
