package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class VodQASampleListPageStepsDef extends BaseStepsDef {


    public VodQASampleListPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
       // scenarioContext.getAppiumDriver().getAppiumDriver().getPageSource();
       // PageFactory.initElements(new AppiumFieldDecorator((WebDriver)scenarioContext.getAppiumDriver().getAppiumDriver()), launchPageModel);
    }

    @Override
    @Then("^I should be on the Sample List page$")
    public void IShouldBeOnThePage() throws Throwable {
        takeScreenshotInReportsAndSaveOnDisk();
        vodQASampleListPageModel.isExpectedPage();
    }


    @When("^I click drag and drop button$")
    public void iClickLoginButton() throws Throwable {
        vodQASampleListPageModel.clickDragAndDrop();
    }



}
