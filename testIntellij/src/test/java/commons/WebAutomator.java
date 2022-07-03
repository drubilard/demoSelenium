package commons;

import java.net.URL;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebAutomator {
	private WebDriver driver;
	private WebDriverWait ewait;
	private Actions action;
	private JavascriptExecutor jse;

	private void setDriver(WebDriver normalDriver) {
		EventFiringWebDriver edriver = new EventFiringWebDriver(normalDriver);
		edriver.register(new EventListener());
		driver = edriver;
		action = new Actions(normalDriver);
		jse = (JavascriptExecutor) normalDriver;

	}

	private WebDriver createLocalDriver(Browser browser) throws Exception {
		System.out.println("Levantando browser " + browser + " para pruebas");
		WebDriver normalDriver = null;
		switch (browser) {
			case CHROME:
				normalDriver = Configuration.createChromeDriver();
				break;
			case FIREFOX:
				normalDriver = Configuration.createFirefoxDriver();
				break;
			case HTMLUNITDRIVER:
				normalDriver = Configuration.createHtmlUnitDriver();
				break;
			case CHROMEHEADLESS:
				normalDriver = Configuration.createChromeDriverHeadless();
				break;
			case EXPLORER:
				normalDriver = Configuration.createIEDriver();
				break;
			case REMOTE:
				ChromeOptions opt = new ChromeOptions();
				normalDriver = new RemoteWebDriver(new URL(Configuration.HOST_URL), opt);
				break;
			default:
				throw new Exception("Unsupported browser: " + browser);
		}

		return normalDriver;
	}

	public WebAutomator(Browser browser) throws Exception {
		WebDriver normalDriver = createLocalDriver(browser);
		this.setDriver(normalDriver);

	}

	public void type(WebElement element, String text) {
		element.sendKeys(text);
	}

	public void typeClear(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	public boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSelected(WebElement element) {
		try {
			return element.isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	public void type(By locator, String text) {
		find(locator).sendKeys(text);
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	public void visit(String url) {
		driver.get(url);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public WebDriverWait getWaiter() {
		return this.ewait;
	}

	public void closeAll() {
		driver.quit();
	}

	public WebElement getIndx(List<WebElement> WebElements, int indice) {
		return WebElements.get(indice);
	}

	public String getAttribute(WebElement element, String attribute) {
		try {
			return element.getAttribute(attribute);
		} catch (Exception e) {
			return null;
		}
	}

	public WebElement find(By locator) {
		return driver.findElement(locator);
	}

	public WebElement find(WebElement element, By locator) {
		return element.findElement(locator);
	}

	public WebElement find(By locator, Integer timeout) {
		WebElement element = new WebDriverWait(driver, timeout)
				.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	public int SizeElements(By locator) {
		return driver.findElements(locator).size();

	}

	public void click(By locator) {
		find(locator).click();
	}

	public void click(WebElement element) {
		element.click();
	}

	public void click(By locator, Integer timeout) throws Exception {
		waitElementToBeClickable(locator, timeout);
		find(locator).click();
	}

	public void click(WebElement element, Integer timeout) throws Exception {
		waitElementToBeClickable(element, timeout);
		element.click();
	}

	public void clickThroughJavaScript(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getText(By locator) {
		return find(locator).getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	/* Wait methods */

	public WebElement waitPresenceOfElementLocated(By locator, int timeout) throws Exception {
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));

		} catch (Exception e) {
			SeleniumUtils.takeScreenShot(driver, "ErrorwaiPresenceOfElementLocated");
			return null;
		}
	}

	public WebElement waitVisibilityOf(WebElement element, int timeout) throws Exception {
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			SeleniumUtils.takeScreenShot(driver, "ErrorwaitVisibilityOf");
			return null;
		}

	}

	public WebElement waitVisibilityOf(List<WebElement> elements, int timeout) throws Exception {
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elements.get(0)));
		} catch (Exception e) {
			SeleniumUtils.takeScreenShot(driver, "ErrorwaitVisibilityOf");
			return null;
		}

	}

	public Boolean waitTextToBePresentInElement(WebElement element, int timeout, String text) throws Exception {
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.textToBePresentInElement(element, text));

		} catch (Exception e) {
			SeleniumUtils.takeScreenShot(driver, "ErrorwaitTextToBePresentInElement");
			return null;
		}

	}

	public WebElement waitElementToBeClickable(By locator, int timeout) throws Exception {
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));

		} catch (Exception e) {
			SeleniumUtils.takeScreenShot(driver, "ErrorwaitElementToBeClickable");
			return null;
		}
	}

	public WebElement waitElementToBeClickable(WebElement element, int timeout) throws Exception {
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));

		} catch (Exception e) {
			SeleniumUtils.takeScreenShot(driver, "ErrorwaitElementToBeClickable");
			return null;
		}

	}

	public Boolean waitElementToBeSelected(WebElement element, int timeout) throws Exception {
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeSelected(element));

		} catch (Exception e) {
			SeleniumUtils.takeScreenShot(driver, "ErrorwaitElementToBeSelected");
			return false;
		}

	}

	public WebDriverWait waitForAlert(int timeout) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.alertIsPresent());
			return wait;
		} catch (Exception e) {
			SeleniumUtils.takeScreenShot(driver, "ErrorwaitForAlert");
			return null;
		}
	}

	public void submit(WebElement element) {
		element.submit();
	}

	public void submit(By locator) {
		find(locator).submit();
	}

	public String url() {
		return driver.getCurrentUrl();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public void switchTo(String tab) {
		driver.switchTo().window(tab);
	}

	// ACTION

	public void actionTab() {
		action.sendKeys(Keys.TAB).build().perform();
	}

	public void actionEnter() {
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public void movetoElementClick(WebElement element) {
		action.moveToElement(element).click().perform();
	}

	public void copiarPortapapeles(WebElement element) {
		action.keyDown(element, Keys.CONTROL).sendKeys(element, "c").keyUp(element, Keys.CONTROL).build().perform();
	}

	public void pegarPortapapeles(WebElement element) {
		action.keyDown(element, Keys.CONTROL).sendKeys(element, "v").keyUp(element, Keys.CONTROL).build().perform();
	}

	// IFRAME
	public void switchToIframe(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchTodefaultContent() {
		driver.switchTo().defaultContent();
	}

	// JS
	public void clickJs(WebElement element) {
		jse.executeScript("arguments[0].click();", element);
	}

	public void openNewTabJS() {
		jse.executeScript("window.open()");
	}

}
