package pages;


import infrastructure.KMobileElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by makri on 4/09/2017.
 */
public class MobilePageModel extends BasePageModel {
    public MobilePageModel(ScenarioContext scenarioContext) throws Exception {
        super(scenarioContext,"");
        //Why outdoor? - BuyThisSpace
    }
    By ALERT_BTN= By.xpath("(//XCUIElementTypeButton[@name=\"AX error -25205\"])[1]");

    @iOSFindBy(xpath = "(//XCUIElementTypeButton[@name=\"AX error -25205\"])[1]")
    public MobileElement buttonAlert;


    public void clickAlert() throws InterruptedException {
        //buttonAlert.click();
        driver.clickButton(buttonAlert);
    }

    public void getTextAlertButton() throws InterruptedException {
        // driver.findElements(ALERT_BTN).get(1).click();

    }
}
