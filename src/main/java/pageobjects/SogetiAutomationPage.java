package pageobjects;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import framework.DriverManager;

/**
 * @author Dineshkumar
 *
 */
public class SogetiAutomationPage extends DriverManager {

	// Services and Automation link highlights are captured using the xpath
	public static final By SERVICES_LINK_HIGHLIGHTED = By.xpath(
			"//*[@id='main-menu']//*[contains(@class, 'selected has')]/div[@class='wrapper']/span[text()='Services']");
	public static final By AUTOMATION_LINK_HIGHLIGHTED = By
			.xpath("//*[@id='main-menu']//*[@class='selected  current expanded']/a[contains(text(), 'Automation')]");
	public static final By AUTOMATION_TEXT = By.xpath("//div/h1");
	// Automation Page Contact us form elements
	public static final By FIRST_NAME_TEXTBOX = By.xpath("//label[contains(text(), 'First')]/following-sibling::input");
	public static final By LAST_NAME_TEXTBOX = By.xpath("//label[contains(text(), 'Last')]/following-sibling::input");
	public static final By EMAIL_TEXTBOX = By.xpath("//label[contains(text(), 'Email')]/following-sibling::input");
	public static final By PHONE_TEXTBOX = By.xpath("//label[contains(text(), 'Phone')]/following-sibling::input");
	public static final By COMPANY_TEXTBOX = By.xpath("//label[contains(text(), 'Company')]/following-sibling::input");
	public static final By MESSAGE_TEXTBOX = By
			.xpath("//label[contains(text(), 'Message')]/following-sibling::textarea");
	public static final By COUNTRY_DROPDOWN = By.xpath("//div/select");
	public static final By AGREE_CHECKBOX = By.xpath("//span/input");
	public static final By CAPTCHA_CHECKBOX = By.xpath("//*[@id='recaptcha-anchor']");
	public static final By SUBMIT_BUTTON = By.xpath("//div/button[@type='submit']");

	public SogetiAutomationPage() {
		driver = DriverManager.getDriverInstance();
	}

	public String verifyAutomationText() {
		String actual = driver.findElement(AUTOMATION_TEXT).getText();
		return actual;
	}

	public boolean verifyHighlightedElements() {
		if (driver.findElement(SERVICES_LINK_HIGHLIGHTED).isDisplayed() == true) {
			System.out.println("Services - Element highlighted is validated using the xpath class attribute ");
		}
		if (driver.findElement(AUTOMATION_LINK_HIGHLIGHTED).isDisplayed() == true) {
			System.out.println("Automation - Element highlighted is validated using the xpath class attribute ");
		}
		boolean services_display = driver.findElement(SERVICES_LINK_HIGHLIGHTED).isDisplayed();
		System.out.println("Services - Element highlighted is validated using the xpath class attribute. Display is "
				+ services_display);
		boolean automation_display = driver.findElement(AUTOMATION_LINK_HIGHLIGHTED).isEnabled();
		System.out.println("Automation - Element highlighted is validated using the xpath class attribute. Display is  "
				+ automation_display);

		return services_display && automation_display ? true : false;
	}

	public void fillContactusForm() {
		String phoneNumber = generateRandomPhoneNumber();
		String eMail = generateRandomEmail();
		driver.findElement(FIRST_NAME_TEXTBOX).sendKeys("Dinesh");
		driver.findElement(LAST_NAME_TEXTBOX).sendKeys("Eswaran");
		driver.findElement(EMAIL_TEXTBOX).sendKeys(eMail);
		driver.findElement(COMPANY_TEXTBOX).sendKeys("Sogeti");
		driver.findElement(PHONE_TEXTBOX).sendKeys(phoneNumber);
		WebElement countryDD = driver.findElement(COUNTRY_DROPDOWN);
		Select s = new Select(countryDD);
		s.selectByVisibleText("Germany");
		driver.findElement(MESSAGE_TEXTBOX).sendKeys("This is Test Message for validation");

	}

	public void clickAgreeAndIamNotRobotCheckboxes() {
		WebElement checkbox = driver.findElement(AGREE_CHECKBOX);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", checkbox);
		driver.switchTo().frame(0);
		WebElement captcha = driver.findElement(CAPTCHA_CHECKBOX);
		js.executeScript("arguments[0].click()", captcha);
		driver.switchTo().defaultContent();
		System.out.println("The flow completed till the Captcha validation");
		System.out.println(
				"* For this test case I am able to go to the last step of selecting the check box of Captcha. But after selecting the\r\n"
						+ "	 Captcha check box a new image validation displayed dynamically which cannot be automated. \r\n"
						+ "	 Image-Based Challenges - Captcha often involve visual challenges, like distorted characters or images that are difficult \r\n"
						+ "	 for automated tools to interpret accurately. \r\n"
						+ "	 The purpose of Captcha is to detect and block bot-like behavior ");
	}

	public static String generateRandomPhoneNumber() {
		Random random = new Random();
		StringBuilder phoneNumber = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int digit = random.nextInt(10);
			phoneNumber.append(digit);
		}
		return phoneNumber.toString();
	}

	public static String generateRandomEmail() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int length = 10; // Length of the email address we can change it for the value we want
		StringBuilder randomLocalPart = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			char randomChar = characters.charAt(index);
			randomLocalPart.append(randomChar);
		}
		String domain = "sogeti.com";
		return randomLocalPart.toString() + "@" + domain;
	}
}
