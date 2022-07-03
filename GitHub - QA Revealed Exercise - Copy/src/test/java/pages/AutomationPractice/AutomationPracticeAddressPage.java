package pages.AutomationPractice;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AutomationPracticeAddressPage extends BaseHelper
{
    @FindBy (name="processAddress")
            WebElement buttonProceed;

    WebDriver driver;
    public AutomationPracticeAddressPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String streetAddress;

    private void checkAddressAndCity()
    {
        streetAddress = driver.findElement(By.id("address_delivery")).findElement(By.className("address_address1")).getText();
    }
    private void clickProceed()
    {
        buttonProceed.click();
    }

    public void signIn()
    {
        checkAddressAndCity();
        clickProceed();
    }

}
