package pages.Donesi;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DonesiConfirmAddressPage extends BaseHelper
{
    @FindBy (className="button-map-submit")
    WebElement buttonContinue;

    WebDriver driver;
    public DonesiConfirmAddressPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void ClickOnButtonContinue()
    {

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("button-map-submit")));
        buttonContinue.click();

    }

}
