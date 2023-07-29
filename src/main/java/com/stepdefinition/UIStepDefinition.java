package com.stepdefinition;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.DriverManager;

import org.junit.Assert;
import pageobjects.SogetiAutomationPage;
import pageobjects.SogetiHomePage;


/**
 * @author Dineshkumar
 *
 */
public class UIStepDefinition {

	private SogetiHomePage sogetiHomePage;
	private SogetiAutomationPage sogetiAutomationPage;

	@Given("^I want to launch Sogeti home page$")
	public void i_want_to_launch_Sogeti_home_page() throws Throwable {

		DriverManager driverManager = new DriverManager();
		driverManager.getDriver();
	}

	@When("^I navigate to Services and Automation link$")
	public void i_navigate_to_Services_and_Automation_link() throws Throwable {

		if (sogetiHomePage == null)
			sogetiHomePage = new SogetiHomePage();
		sogetiHomePage.acceptCookies();
		sogetiHomePage.hoverOverServicesLink();
		sogetiHomePage.clickAutomationLink();

	}

	@Then("^I verify Automation text in Automation page$")
	public void i_verify_Automation_text_in_Automation_page() throws Throwable {

		if (sogetiAutomationPage == null)
			sogetiAutomationPage = new SogetiAutomationPage();
		String autoText = sogetiAutomationPage.verifyAutomationText();
		Assert.assertEquals("Automation", autoText);
		System.out.println("The text--- " + autoText + " ---is validated successfully in Automation Screen");
	}

	@Then("^I verify the highlight of Services and Automation links$")
	public void i_verify_the_highlight_of_Services_and_Automation_links() throws Throwable {
		if (sogetiAutomationPage == null)
			sogetiAutomationPage = new SogetiAutomationPage();
		Assert.assertEquals(true, sogetiAutomationPage.verifyHighlightedElements());
	}

	@When("^I enter the details of Contact us Form$")
	public void i_enter_the_details_of_Contact_us_Form() throws Throwable {
		if (sogetiAutomationPage == null)
			sogetiAutomationPage = new SogetiAutomationPage();
		sogetiAutomationPage.fillContactusForm();
	}

	@When("^I click the checkboxes of I agree and I am not Robot$")
	public void i_click_the_checkboxes_of_I_agree_and_I_am_not_Robot() throws Throwable {
		if (sogetiAutomationPage == null)
			sogetiAutomationPage = new SogetiAutomationPage();
		sogetiAutomationPage.clickAgreeAndIamNotRobotCheckboxes();
	}

	@When("^I click Worldwide link$")
	public void i_click_Worldwide_link() throws Throwable {
		if (sogetiHomePage == null)
			sogetiHomePage = new SogetiHomePage();
		sogetiHomePage.acceptCookies();
		sogetiHomePage.clickWorldwideLink();
	}

	@Then("^I verify Country specific links$")
	public void i_verify_Country_specific_links() throws Throwable {
		if (sogetiHomePage == null)
			sogetiHomePage = new SogetiHomePage();
		Assert.assertEquals(true, sogetiHomePage.verifyCountrySpecificLinks());
	}
}
