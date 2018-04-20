package pages;


import io.appium.java_client.MobileBy;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by makri on 4/09/2017.
 */
public class VodQASampleListPageModel extends BasePageModel {
    public VodQASampleListPageModel(ScenarioContext scenarioContext) throws Exception {
        super(scenarioContext, "dragAndDrop");
        //Why outdoor? - BuyThisSpace
        Thread.sleep(5000);
    }

    public void clickDragAndDrop() {
        driver.findElementByAccessibilityId("dragAndDrop").click();
    }

}
