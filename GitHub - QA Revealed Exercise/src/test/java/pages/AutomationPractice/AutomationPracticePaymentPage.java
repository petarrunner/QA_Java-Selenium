package pages.AutomationPractice;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AutomationPracticePaymentPage extends BaseHelper
{
    @FindBy (className="bankwire")
    WebElement payByBank;


    WebDriver driver;
    public AutomationPracticePaymentPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private void agreeTermsOfService()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("bankwire")));
        payByBank.click();
    }

    public void confirmPayByBank()
    {
        agreeTermsOfService();
    }

}
