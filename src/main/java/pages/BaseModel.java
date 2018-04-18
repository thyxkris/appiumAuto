package pages;

import infrastructure.Base;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 29/06/2017.
 */
public class BaseModel extends Base{

    protected ScenarioContext scenarioContext;


    public BaseModel(ScenarioContext scenarioContext, String accessibilityId) {
        super(scenarioContext, accessibilityId);
        this.scenarioContext = scenarioContext;
    }

    public BaseModel(ScenarioContext scenarioContext, String accessibilityId, String url) {
        super(scenarioContext, accessibilityId, url);
        this.scenarioContext = scenarioContext;

    }

}
