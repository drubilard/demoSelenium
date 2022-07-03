package Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTest {
	WebDriver driver;
	public DesiredCapabilities cap = new DesiredCapabilities();

	@BeforeClass
	@Parameters({ "URL", "browser" })
	public void beforeClass(String url, String browser) throws MalformedURLException {

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().version("101").setup();
			driver= new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.get(url);
		System.out.println("Browser title: " + browser + "   " + driver.getTitle());


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void searchProduct() {
		/*
		 * WebElement searchBoxElement = driver.findElement(By.name("q"));
		 * searchBoxElement.sendKeys("quality-stream.com"); searchBoxElement.submit();
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver,30);
		 * wait.until(ExpectedConditions.titleContains("quality-stream.com"));
		 * 
		 * assertEquals("quality-stream.com - Google Search", driver.getTitle());
		 */

		WebElement searchBoxElement = driver.findElement(By.name("s"));
		searchBoxElement.sendKeys("watches");
		searchBoxElement.submit();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));

		String header1 = driver.findElement(By.tagName("h1")).getText();

		System.out.println("The header 1 <h1> says: " + header1);
		assertEquals(header1, "Search results: “watches”");

	}

}
