package Pages;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import commons.Browser;
import commons.Configuration;
import commons.WebAutomator;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class LoginPage extends LoadableComponent<LoginPage> {

    private WebAutomator automator;

    public LoginPage(Browser browser) throws Exception {
        automator = new WebAutomator(browser);
        PageFactory.initElements(automator.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id=\"single-spa-application:@nbx/authentication-register-mfe\"]/section/div/div[1]/div/p[2]/a")
    private WebElement loginButton;

    @FindBy(id = "email")
    private WebElement userInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(id = "init-integration-button")
    private WebElement integrationButton;

    public WebAutomator getAutomator() {
        return automator;
    }

    public void loginClick() throws Exception {
        automator.waitElementToBeClickable(loginButton,10);
        automator.clickJs(loginButton);
    }
    public void enterUserInput(String user) throws Exception {
        automator.waitVisibilityOf(userInput,10);
        automator.type(userInput,user);
    }
    public void enterPasswordInput(String pass) throws Exception {
        automator.waitVisibilityOf(passwordInput,10);
        automator.type(passwordInput,pass);
    }
    public void clickLoginSubmit() throws Exception {
        automator.waitElementToBeClickable(submitButton,10);
        automator.clickJs(submitButton);
    }
    public void loginSuccessfully() throws Exception {
        Assert.assertNotNull(automator.waitVisibilityOf(integrationButton,10));
    }


    @Override
    protected void load() {
        automator.visit(Configuration.APP_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        Assertions.assertEquals("Pyme", automator.getTitle());
    }
}
