package commons;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static io.qameta.allure.Allure.addAttachment;


public class SeleniumUtils {
	public static void printElementInfo(String name, WebElement element) {
		String marker = "------------------------------";
		System.out.println(marker);
		System.out.println("Element meta-data: " + name);
		System.out.println(marker);
		System.out.println("WebElement object:: " + element);
		System.out.println("Outer HTML:: " + element.getAttribute("outerHTML"));
		System.out.println("Inner HTML:: " + element.getAttribute("innerHTML"));
		System.out.println("Text:: " + element.getText());
		System.out.println("Value::" + element.getAttribute("value"));
		System.out.println("Tag:: " + element.getTagName());
		System.out.println("Displayed?:: " + element.isDisplayed());
		System.out.println("Selected?:: " + element.isSelected());
		System.out.println("Enabled?:: " + element.isEnabled());
		System.out.println(marker);
	}

	public static void takeScreenShot(WebDriver driver, String nombreFile) throws Exception {
		/**TakesScreenshot screenShot = (TakesScreenshot) driver;

		File file2 = screenShot.getScreenshotAs(OutputType.FILE);
		String basePath = System.getProperty("user.dir") + File.separator + "error" + File.separator;
		String path = String.format(basePath + nombreFile + System.currentTimeMillis() + ".png");

		FileUtils.copyFile(file2, new File(path));**/
	}

	public static String IdentifySecondTab(String actualityTab, WebDriver driver) {
		String secondWindowTab = "";
		for (String windowHandler : driver.getWindowHandles()) {
			if (!windowHandler.equals(actualityTab)) {
				secondWindowTab = windowHandler;
				System.out.println("secondwindow:" + secondWindowTab);
			}
		}
		return secondWindowTab;
	}

	public static void SwitchWindowTab(String secondWindowTab, WebDriver driver) {
		System.out.println("cambiando de tab");
		driver.switchTo().window(secondWindowTab);
	}

	public static void SwitchAndCloseWindowTab(String tab, WebDriver driver) throws InterruptedException {
		System.out.println("cambiando de tab");
		driver.close();
		Thread.sleep(500);
		SwitchWindowTab(tab, driver);
	}

	public static String getEnvironmentVariable(String variable){
		return System.getenv(variable);
	}

	public static void takeAllureScreenShot(WebDriver driver) throws InterruptedException {
		try {
		  File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
		} catch (IOException | NoSuchSessionException e) {
		  System.err.println(e.toString());
		}
	  }
}
