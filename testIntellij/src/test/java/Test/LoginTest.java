package Test;
import commons.Configuration;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void login() throws Exception {
        loginpage.loginClick();
        loginpage.enterUserInput(Configuration.USER);
        loginpage.enterPasswordInput(Configuration.PASSWORD);
        loginpage.clickLoginSubmit();
        loginpage.loginSuccessfully();

    }

}
