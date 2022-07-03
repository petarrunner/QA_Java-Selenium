package pages.AutomationPractice;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AutomationPracticeShoppingCartSumaryPage extends BaseHelper
{
    WebDriver driver;
    public AutomationPracticeShoppingCartSumaryPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy (className="button-plus")
    WebElement buttonPlus;
    @FindBy (id="total_price")
    WebElement totalPrice;

    @FindBy (className="standard-checkout")
    WebElement buttonProceed;

    public String totalProductString;
    public String totalShippingString;
    public String totalPriceString;


    private void clickPlus()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("button-plus")));
        buttonPlus.click();
        buttonPlus.click();
        buttonPlus.click();
        wdWait.until(ExpectedConditions.textToBePresentInElementValue(By.className("cart_quantity_input"), "4"));
    }
    private void setTotalProductsValue()
    {
        WebElement totalProduct = driver.findElement(By.id("total_product"));
        totalProductString = totalProduct.getText();
        System.out.println(totalProductString);
    }
    private void setTotalShippingValue()
    {
        WebElement totalShipping = driver.findElement(By.id("total_shipping"));
        totalShippingString = totalShipping.getText();
    }
    private void setTotalPriceValue()
    {
        WebElement totalPrice = driver.findElement(By.id("total_price"));
        totalPriceString = totalPrice.getText();
    }
    private void clickProceedToCheckout()
    {
        buttonProceed.click();
    }



        public void shoppingCartSummaryCheck()
        {
            clickPlus();
            setTotalProductsValue();
            setTotalPriceValue();
            setTotalShippingValue();
            clickProceedToCheckout();
        }




}
