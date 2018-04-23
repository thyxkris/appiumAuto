package pages;
import infrastructure.KMobileElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import libraries.helpers.ConfigHelper;
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
        super(scenarioContext, "show alert");
        //Why outdoor? - BuyThisSpace
    }

    //By ALERT_BTN= By.xpath("(//XCUIElementTypeButton[@name=\"AX error -25205\"])[1]");
    // By ALERT_BTN=
    By COOL_TITLE = By.xpath("//XCUIElementTypeStaticText[@name=\"Cool title\"]");

    @iOSFindBy(accessibility = "show alert")
    public MobileElement buttonAlert;
    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Cool title\"]")
    public MobileElement popUpTitle;

    public void clickAlert() throws InterruptedException {
        //buttonAlert.click();
        //driver.clickButton(buttonAlert);

        int waitTime = 0;
        while (driver.findElementsByAccessibilityId("show alert").size() == 0 && ConfigHelper.getElementWaitTime() > waitTime) {
            Thread.sleep(1000);
            waitTime += 1000;
        }

        //MobileElement mobileElement = ((AppiumDriver<MobileElement>)driver.getAppiumDriver()).findElementsByAccessibilityId("show alert").get(0);
        // driver.findElementsByAccessibilityId("show alert");
        // mobileElement.click();
        driver.clickButton(driver.findElementByAccessibilityId("show alert"));
        //
    }


    public String getTextFromCoolTitle() throws InterruptedException {
        int waitTime = 0;
        while (driver.findElements(COOL_TITLE).size() == 0 && ConfigHelper.getElementWaitTime() > waitTime) {
            Thread.sleep(1000);
            waitTime += 1000;
        }
        return driver.findElement(COOL_TITLE).getText();
    }
}
