package runner;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
					monochrome = true,
					features = "src/main/resources/featurefiles",  					
  					tags = {"@Sogeti"},
					plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report1.html"},
					glue = {"com.stepdefinition"}
					)
public class TestRunner {	
	
}	
