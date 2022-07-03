package pages.Donesi;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DonesiFoundedObjectsPage extends BaseHelper
{
    @FindBy (className="shops-listing-counter")
    WebElement stringNumberOfObjects;

    @FindBy (className="shops-listings-shops-list")
        WebElement fieldRecommendedFoods;

    public String totalNumberOfObjects;
    public String selectedNumberOfObjects;
    public int numberOfRecommendedObjects;

    WebDriver driver;
    public DonesiFoundedObjectsPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    private void checkNumberOfObjects()
    {
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("shops-listing-counter"), ""));
        totalNumberOfObjects = stringNumberOfObjects.getText();
    }

    private void selectTypeFood(int orderNoList)
    {
        WebElement boxListTypeFood = driver.findElement(By.className("cuisine-filter-list"));
        List<WebElement> listTypeFood = boxListTypeFood.findElements(By.tagName("li"));
        WebElement selectedElement = listTypeFood.get(orderNoList-1);
        selectedElement.click();

        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("shops-listing-counter"), totalNumberOfObjects));
        selectedNumberOfObjects = stringNumberOfObjects.getText();
    }

    private void checkRecommendedFoods()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("shops-listings-shops-list")));
        List<WebElement> listRecommendedFoods = fieldRecommendedFoods.findElements(By.className("shops-listings-shops-list-item"));
        numberOfRecommendedObjects = listRecommendedFoods.size();
    }

    public void saljiTotalPodatke(int orderNoList)
    {
        checkNumberOfObjects();
        selectTypeFood(orderNoList);
        checkRecommendedFoods();

    }



}