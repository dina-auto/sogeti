package framework;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * @author Dineshkumar
 *
 */
public class DriverManager {

	protected static WebDriver driver=null;
	
	public WebDriver getDriver() {
		driver = new ChromeDriver();
		driver.get("https://www.sogeti.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public static WebDriver getDriverInstance() {
		return driver;
	}
}
