package pages.AutomationPractice;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AutomationPracticeProductPage extends BaseHelper
{
    @FindBy (id="group_1")
    WebElement buttonSelect;
    @FindBy (id="color_8")
    WebElement selectWhiteColor;

    WebDriver driver;
    public AutomationPracticeProductPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    private void setSize()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("quantity_wanted")));
        buttonSelect.click();
        List<WebElement> listOfSizes =buttonSelect.findElements(By.tagName("option"));
        listOfSizes.get(2).click();
    }
    private void selectWhiteColor()
    {
        selectWhiteColor.click();
    }

    private void clickButtonAddToCart()
    {
        WebElement buttonAddToCart = driver.findElement(By.id("add_to_cart")).findElement(By.tagName("button"));
        buttonAddToCart.click();
    }
    private void proceedToCheckout()
    {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
        WebElement buttonProceed = driver.findElement(By.className("button-container")).findElement(By.tagName("a"));
        js.executeScript("arguments[0].click();",buttonProceed);
    }

    public void addToCart()
    {
        setSize();
        selectWhiteColor();
        clickButtonAddToCart();
        proceedToCheckout();
    }




}
