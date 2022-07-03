package Test;

import Pages.LoginPage;
import commons.Browser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected static LoginPage loginpage;
    @BeforeClass
    @Parameters({"browser"})
    static void setUpBeforeClass(String browser) throws Exception {
        if (browser.toLowerCase().equals("chrome")) {
            loginpage = new LoginPage(Browser.CHROME);
        } else if (browser.equals("firefox")) {
            loginpage = new LoginPage(Browser.FIREFOX);

        }else {
            System.out.println("browser no válido, se usará htmlunit driver por defecto");
            loginpage = new LoginPage(Browser.HTMLUNITDRIVER);
        }
        loginpage.get();
        loginpage.getAutomator().maximize();
        //loginpage.loginClick();
    }

}
