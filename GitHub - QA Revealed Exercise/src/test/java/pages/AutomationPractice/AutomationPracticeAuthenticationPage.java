package pages.AutomationPractice;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AutomationPracticeAuthenticationPage extends BaseHelper
{
    WebDriver driver;
    public AutomationPracticeAuthenticationPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="email")
    WebElement emailAddressInput;
    @FindBy(id="passwd")
    WebElement passwordInput;
    @FindBy(id="SubmitLogin")
    WebElement buttonSignIn;

    private void enterEmailAddress(String email)
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailAddressInput.sendKeys(email);
    }
    private void enterPassword(String password)
    {
        passwordInput.sendKeys(password);
    }
    private void clickSignIn()
    {
        buttonSignIn.click();
    }

    public void signIn(String email,String password)
    {
        enterEmailAddress(email);
        enterPassword(password);
        clickSignIn();
    }

}
