package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class VodQADragDropPageStepsDef extends BaseStepsDef {


    public VodQADragDropPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
       // scenarioContext.getAppiumDriver().getAppiumDriver().getPageSource();
       // PageFactory.initElements(new AppiumFieldDecorator((WebDriver)scenarioContext.getAppiumDriver().getAppiumDriver()), launchPageModel);
    }

    @Override
    @Then("^I should be on the Drag Drop page$")
    public void IShouldBeOnThePage() throws Throwable {
        takeScreenshotInReportsAndSaveOnDisk();
        vodQADragDropPageModel.isExpectedPage();
    }




    @Then("^I drag the button 'Drag me!' to 'Drop here'$")
    public void iDragTheButtonDragMeToDropHere() throws Throwable {
        vodQADragDropPageModel.DragMe();
    }



    @Then("^I should see the text  \"([^\"]*)\"$")
    public void iShouldSeeTheText(String text) throws Throwable {
        assertEquals("sucess text", text, vodQADragDropPageModel.getSuccessResult());
    }
}
