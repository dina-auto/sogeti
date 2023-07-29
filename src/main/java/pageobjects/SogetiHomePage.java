package pageobjects;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.DriverManager;

/**
 * @author Dineshkumar
 *
 */
public class SogetiHomePage extends DriverManager {

	public static final By SERVICES_LINK = By.xpath("(//span[contains(text(),'Services')])[1]");
	public static final By AUTOMATION_LINK = By.xpath("//*[@id='main-menu']//li/a[contains(text(),'Automation')]");
	public static final By ACCECPT_COOKIES = By.xpath("//div/button[@class='acceptCookie']");
	public static final By COUNTRY_LIST = By.xpath("//div[@id='country-list-id']/ul/li/a");
	public static final By WORLDWIDE_LINK = By.xpath("//span[@aria-label='Worldwide']");

	public SogetiHomePage() {
		driver = DriverManager.getDriverInstance();
	}

	public void acceptCookies() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ACCECPT_COOKIES));
		driver.findElement(ACCECPT_COOKIES).click();
	}

	public void hoverOverServicesLink() {
		Actions actions = new Actions(driver);
		Action action = actions.moveToElement(driver.findElement(SERVICES_LINK)).build();
		action.perform();
	}

	public void clickAutomationLink() {
		driver.findElement(AUTOMATION_LINK).click();
	}

	public void clickWorldwideLink() {
		driver.findElement(WORLDWIDE_LINK).click();
	}

	public boolean verifyCountrySpecificLinks() {
		boolean countrySpecificLinksSucess = true;
		List<WebElement> countriesList = driver.findElements(COUNTRY_LIST);

		System.out.println("Total countries are " + countriesList.size());

		for (int i = 0; i < countriesList.size(); i++) {

			WebElement element = countriesList.get(i);
			String url = element.getAttribute("href");
			String countryName = element.getText();
			boolean urlSucessfullyLoaded = verifyLinkActive(url, countryName);
			if (!urlSucessfullyLoaded) {
				countrySpecificLinksSucess = false;
				return countrySpecificLinksSucess;
			}
		}
		return countrySpecificLinksSucess;
	}

	public static boolean verifyLinkActive(String linkUrl, String countryName) {
		boolean urlFound = true;
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(3000);
			httpURLConnect.connect();
			Assert.assertEquals(200, httpURLConnect.getResponseCode());
			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println("Country Name is - " + countryName + " - URL is - " + linkUrl
						+ " - Response code of URL is -" + httpURLConnect.getResponseCode()
						+ "- and Response message is " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
				urlFound = false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			urlFound = false;
		}
		return urlFound;
	}
}
