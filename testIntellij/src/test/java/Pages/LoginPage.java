package Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import commons.Browser;
import commons.Configuration;
import commons.WebAutomator;
import static org.testng.Assert.assertEquals;

public class LoginPage extends LoadableComponent<LoginPage> {

    private WebAutomator automator;

    public LoginPage(Browser browser) throws Exception {
        automator = new WebAutomator(browser);
        PageFactory.initElements(automator.getDriver(), this);
    }

    @FindBy(css = "ul>li>a.item-link-button")
    private WebElement loginButton;

    @FindBy(id = "signin-email")
    private WebElement emailInput;

    @FindBy(id = "signin-password")
    private WebElement pswInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginSubmit;

    public WebAutomator getAutomator() {
        return automator;
    }

    public void login() throws Exception {
        automator.waitVisibilityOf(loginButton, Configuration.timeout);
        automator.clickJs(loginButton);
        automator.waitElementToBeClickable(loginSubmit, Configuration.timeout);
        automator.type(emailInput, Configuration.USER);
        automator.type(pswInput, Configuration.PASSWORD);
        automator.clickJs(loginSubmit);
    }

    @Override
    protected void load() {
        automator.visit(Configuration.APP_URL);

    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals("Rev-U", automator.getTitle());

    }

}
