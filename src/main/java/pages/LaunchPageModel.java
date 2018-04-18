package pages;


import libraries.helpers.ConfigHelper;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class LaunchPageModel extends BasePageModel {
    public LaunchPageModel(ScenarioContext scenarioContext) throws Exception {
        super(scenarioContext, "Welcome to Latitude");
        //Why outdoor? - BuyThisSpace
    }


    //By COOL_TITLE = By.xpath("//XCUIElementTypeStaticText[@name=\"Cool accessibilityId\"]");
    final String BTN_GET_STARTED = "Get Started";
    final String BTN_SIGN_IN = "Sign In";


    public void clickGetStarted() throws InterruptedException {

        driver.clickButton(BTN_GET_STARTED);

    }

    public void clickSignIn() throws InterruptedException {

        driver.clickButton(BTN_SIGN_IN);

    }


//    public String getTextFromCoolTitle() throws InterruptedException {
//        int waitTime = 0;
//        while (driver.findElements(COOL_TITLE).size() == 0 && ConfigHelper.getElementWaitTime() > waitTime) {
//            Thread.sleep(1000);
//            waitTime += 1000;
//        }
//        return driver.findElement(COOL_TITLE).getText();
//    }
}
