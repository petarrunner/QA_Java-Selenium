package pages.TrefSport;

import helpers.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TrefSportDeliveryAndPaymentPage extends BaseHelper
{
    @FindBy (id="lblAddressBuyer1")
    WebElement labelDeliveryHomeAddress;
    @FindBy (id="lbtnToCheckout2")
    WebElement buttonConfirm;

    WebDriver driver;
    public TrefSportDeliveryAndPaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void selectDeliveryHomeAddress() {js.executeScript("arguments[0].click();",labelDeliveryHomeAddress); }
    private void clickConfirm() { buttonConfirm.click(); }
    public void confirmAndProgress()
    {
    selectDeliveryHomeAddress();
    clickConfirm();
    }
}
