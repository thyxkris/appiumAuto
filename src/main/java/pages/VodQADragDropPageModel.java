package pages;


import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

/**
 * Created by makri on 4/09/2017.
 */
public class VodQADragDropPageModel extends BasePageModel {
    public VodQADragDropPageModel(ScenarioContext scenarioContext) throws Exception {
        super(scenarioContext, "dragMe");
        //Why outdoor? - BuyThisSpace
        Thread.sleep(5000);
    }

   public void DragMe()
   {
       MobileElement dragMe = (MobileElement) new WebDriverWait(driver.getAppiumDriver(), 30)
               .until(ExpectedConditions
                       .elementToBeClickable(MobileBy.AccessibilityId("dragMe")));
       new TouchAction(driver.getAppiumDriver()).press(dragMe).waitAction(Duration.ofMillis(3000))
               .moveTo(driver.findElementByAccessibilityId("dropzone").getMobileElement()).release().perform();


   }

   public String getSuccessResult()
   {
       String expected = driver.findElementByAccessibilityId("success").getText();
       return expected;
   }

}
