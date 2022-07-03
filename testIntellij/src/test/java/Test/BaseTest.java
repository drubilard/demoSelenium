package Test;

import Pages.LoginPage;
import Pages.SolicitarFirmasPage;
import commons.Browser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected static LoginPage loginpage;
    protected static SolicitarFirmasPage solicitarfirmas;

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
        solicitarfirmas = new SolicitarFirmasPage(loginpage.getAutomator());
        loginpage.get();
        loginpage.getAutomator().maximize();
        loginpage.login();
    }

}
