package commons;

import java.io.File;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Configuration {
	public static final String downloadFilePath = "";
	public static final String uploadTramiteFilePath = System.getProperty("user.dir") + File.separator + "tramites"
			+ File.separator;
	public static final int timeout = 10;
	public static final String APP_URL = "http://test.revu.tuxidev.cl:8980/"; // url de aplicacion revu
	public static final String USER = "drubilar@getnada.com";
	public static final String PASSWORD = "123456Aa";
	public static final String NOMBRENUEVOFIRMANTE = "Felipe Vargas";
	public static final String RUTMANDANTE = "11.111.111-1";
	public static final String RUTMANDATARIO = "22.222.222-2";
	public static final String RUTNUEVOFIRMANTE = "16198385-3";

	public static String DRIVER_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
			+ File.separator + "resources" + File.separator + "drivers" + File.separator; // ruta del recurso para
																							// setear la property (como
																							// cuando se busca el driver
																							// de chrome)
	public static final String IE_DRIVER = DRIVER_DIR + "IEDriverServer"; // setei de ruta para firefox driver
	public static final String CHROME_DRIVER = DRIVER_DIR + "chromedriver"; // seteo de ruta para chrome driver
	public static final String GECKO_DRIVER = DRIVER_DIR + "geckodriver"; // setei de ruta para firefox driver
	public static final String HOST_URL = "http://localhost:4444"; // url docker
	public static final Logger LOGGER = (Logger) LogManager.getLogger("CSE");

	private static String modifyifWindows(String inPath) { // funcion para identificar SO paras saber que extencino
		// poner
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			return inPath + ".exe";
		} else {
			return inPath;
		}
	}

	public static WebDriver createChromeDriver() { // funcion que genera la property adecuada apra chrome
		// System.setProperty("webdriver.chrome.driver",
		// modifyifWindows(CHROME_DRIVER));
		WebDriverManager.chromedriver().version("102").setup();
		return new ChromeDriver();
	}

	public static WebDriver createIEDriver() { // funcion que genera la property adecuada para IE
		System.setProperty("webdriver.ie.driver", modifyifWindows(IE_DRIVER));
		InternetExplorerOptions caps = new InternetExplorerOptions();
		caps.setCapability("ignoreZoomSetting", true);
		return new InternetExplorerDriver(caps);
	}

	public static WebDriver createHtmlUnitDriver() { // funcion que genera htmilUnitDriver
		return new HtmlUnitDriver();
	}

	public static WebDriver createFirefoxDriver() { // funcion que genera la property adecuada para firefox
		// System.setProperty("webdriver.gecko.driver", modifyifWindows(GECKO_DRIVER));
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}

	public static WebDriver createChromeDriverWithOptions(String downloadFilePath) {

		// System.setProperty("webdriver.chrome.driver",
		// modifyifWindows(CHROME_DRIVER));
		WebDriverManager.chromedriver().version("101").setup();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilePath);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);

		return new ChromeDriver(options);

	}

	public static WebDriver createChromeDriverHeadless() { // funcion que genera la property adecuada para chrome en
		// modo headless

		// System.setProperty("webdriver.chrome.driver",
		// modifyifWindows(CHROME_DRIVER));
		WebDriverManager.chromedriver().version("101").setup();
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("window-size=1400,600");
		options.setHeadless(true);
		return new ChromeDriver(options);
	}

}
