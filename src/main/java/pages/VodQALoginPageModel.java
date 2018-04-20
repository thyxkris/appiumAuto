package pages;


import io.appium.java_client.MobileBy;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by makri on 4/09/2017.
 */
public class VodQALoginPageModel extends BasePageModel {
    public VodQALoginPageModel(ScenarioContext scenarioContext) throws Exception {
        super(scenarioContext, "login");
        //Why outdoor? - BuyThisSpace
    }

    public void clikLogin() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId("login"))).click();
    }

}
