package pages.AutomationPractice;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AutomationPracticeHomePage extends BaseHelper
{
    @FindBy (className="blockbestsellers")
           WebElement buttonBestSellers;
    @FindBy (id="blockbestsellers")
        WebElement blockBestSellers;

    WebDriver driver;
    public AutomationPracticeHomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }



    private void navigateToHomePage()
    {
        driver.get("http://automationpractice.com/index.php");
    }

    private void clickBestSellers()
    {
        buttonBestSellers.click();
    }

    private void selectProduct()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("blockbestsellers")));
        List<WebElement> listOfProducts = blockBestSellers.findElements(By.tagName("li"));
        WebElement thirdElement = listOfProducts.get(2).findElement(By.tagName("a"));
        js.executeScript("arguments[0].click();",thirdElement);
    }


    public void goToBestSellersPage()
    {
        navigateToHomePage();
        clickBestSellers();
        selectProduct();
    }
}
