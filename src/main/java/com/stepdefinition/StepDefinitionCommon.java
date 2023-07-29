package com.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import framework.DriverManager;


/**
 * @author Dineshkumar
 *
 */
public class StepDefinitionCommon {

	public static String scenarioName;

	@Before
	public void beforeClass(Scenario scenario) throws Exception {
		System.out.println("********************************************************************************");
		System.out.println("***** Scenario Name : " + scenario.getName() + " *****");
		System.out.println("********************************************************************************");
	}

	@After
	public void tearDown(Scenario scenario) throws Exception {
		if (DriverManager.getDriverInstance() != null)

			DriverManager.getDriverInstance().quit();
	}

}
