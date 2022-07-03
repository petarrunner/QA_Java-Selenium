package pages.Herrokuapp;

import helpers.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HerrokuappLoginPage extends BaseHelper {
    @FindBy(id = "username")
    WebElement usernameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(className = ("fa-sign-in"))
    WebElement loginButton;

    WebDriver driver;

    public HerrokuappLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToHerokuappLoginpage() {
        driver.get(" https://the-internet.herokuapp.com/login");
    }

    private void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    private void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    private void clickOnLoginButton() {
        loginButton.click();
    }

    public void loginPage(String username, String password) {
        navigateToHerokuappLoginpage();
        enterUsername(username);
        enterPassword(password);
        clickOnLoginButton();
    }
}
