package pages.Donesi;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DonesiHomePage extends BaseHelper
{
    @FindBy (className="cookie-consent-accept-all")
    WebElement acceptCookiesButton;
    @FindBy (className="address-form-component-search-input")
    WebElement insertAddress;
    @FindBy (className="address-form-component-list")
    WebElement offerList;
    @FindBy (xpath="/html/body/div[2]/main/div/section[1]/div[2]/div/div/div/div/div[2]/form/div[2]/div[1]/div[2]/button")
    WebElement buttonOrder;

    WebDriver driver;
    public DonesiHomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    private void navigateToPageDonesi()
    {
        driver.get("https://www.donesi.com/");
    }

    private void  acceptCookie()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("cookie-consent-accept-all")));
        List<WebElement> popup = driver.findElements(By.className("cookie-consent-accept-all"));
        if(popup.size()>0)
        {
            popup.get(0).click();
        }
    }

    private void insertStreetAddress(String streetAddress)
    {
        insertAddress.sendKeys(streetAddress);
    }

    private void createListAndSelectFirst()
    {

        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("address-form-component-list"), ""));
        List<WebElement> listOfAddresses = offerList.findElements(By.tagName("li"));
        WebElement firstAddress = listOfAddresses.get(0);
        js.executeScript("arguments[0].click();",firstAddress);
    }
    private void clickOrderButton()
    {
        js.executeScript("arguments[0].click();",buttonOrder);
    }


    public void donesiOrderCheck(String streetAddress) {
        navigateToPageDonesi();
        acceptCookie();
        insertStreetAddress(streetAddress);
        createListAndSelectFirst();
        clickOrderButton();
    }
}