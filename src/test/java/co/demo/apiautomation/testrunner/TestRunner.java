package co.demo.apiautomation.testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"html:TestReports/API/CucumberReports/CucumberReport.html",
				"co.demo.apiautomation.utils.TestListener"
		}
		,features= {"src/test/java/co/demo/apiautomation/features"}
		,glue = {"co.demo.apiautomation.stepdefinitions"}
		//,dryRun = true
		,monochrome = true
		,snippets = SnippetType.CAMELCASE
		,tags = "@addBookUsingJson"
		//,publish = true
		)
public class TestRunner {

}
