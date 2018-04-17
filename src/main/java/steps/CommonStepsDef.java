package steps;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.MobilePageModel;

import java.io.IOException;

/**
 * Created by makri on 6/09/2017.
 */
public class CommonStepsDef extends BaseStepsDef {

    private MobilePageStepsDef mobilePageStepsDef;


    public CommonStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
    }

    @Override
    public void IShouldBeOnThePage() throws Exception {

    }

    @Before
    public void setUp(Scenario scenario) throws Throwable {

        super.setup(scenario);

    }


    @After
    public void tearUp() throws IOException {
        super.tearUp();
    }


}