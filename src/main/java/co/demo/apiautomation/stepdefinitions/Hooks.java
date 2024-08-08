package co.demo.apiautomation.stepdefinitions;

import co.demo.apiautomation.utils.ER;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	@Before
	public void testStart(Scenario scenario) {
		ER.Info("*****************************************************************************************");
		ER.Info("	Scenario: "+scenario.getName());
		ER.Info("*****************************************************************************************");
	}
	
	@AfterStep
	public void afterStep(Scenario scenario) {
		ER.Info("-----------------------------------------------------------------------------------------");
	}
}
