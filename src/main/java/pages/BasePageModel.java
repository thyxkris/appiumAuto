package pages;

import infrastructure.KAppiumDriver;
import libraries.infrastructure.ScenarioContext;
import org.apache.logging.log4j.Logger;

/**
 * Created by makri on 17/05/2017.
 */
public class BasePageModel extends BaseModel {

    protected static Logger logger;
    protected KAppiumDriver driver;
    // protected HeaderBar headerBar;

    public BasePageModel(ScenarioContext scenarioContext, String accessibilityId) throws Exception {
        super(scenarioContext, accessibilityId, null);
        setup(scenarioContext);

    }

    public BasePageModel(ScenarioContext scenarioContext, String accessibilityId, String url) throws Exception {
        super(scenarioContext, accessibilityId, url);
        setup(scenarioContext);
    }


    public void setup(ScenarioContext scenarioContext) throws Exception {

        waitForPageLoad();
        this.scenarioContext = scenarioContext;
        this.driver = scenarioContext.getAppiumDriver();
        //components initilizations
        //    this.headerBar = new HeaderBar(scenarioContext);
        if (logger == null) {
            logger = scenarioContext.getLogger();
        }
        logger.info(this.getClass() + " finish to initialize  after super's setup");
        waitForPageLoad();
    }


}
