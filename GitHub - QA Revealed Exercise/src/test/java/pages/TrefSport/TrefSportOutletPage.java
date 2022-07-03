package pages.TrefSport;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrefSportOutletPage extends BaseHelper
{
    @FindBy(id="product-list")
    WebElement fieldAllProducts;

    public int ordinalNumCycle = 0;
    public int twoFive;

    public ArrayList<Integer> listRandomNumSelect = new ArrayList<>();
    public ArrayList<Double> listOfCalculatedPrices = new ArrayList<>();
    public ArrayList<Double> listOfNewPrices = new ArrayList<>();

    WebDriver driver;
    public TrefSportOutletPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private void rndNumTwoFive() { twoFive = new Random().nextInt(4) + 2; }
    private void rndNumSelectedItems() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-item")));
        List<WebElement> listOfItems = fieldAllProducts.findElements(By.className("product-item"));
        for (int iCount = 0; iCount<twoFive; iCount++){
            int randomNumber =  new Random().nextInt(listOfItems.size());
            listRandomNumSelect.add(randomNumber);
              }
    }
     private void getPrices() {
         List<WebElement> listOfItems = fieldAllProducts.findElements(By.className("product-item"));
         int ordinalNumItemList;
         while (ordinalNumCycle<twoFive)
         {
             ordinalNumItemList = listRandomNumSelect.get(ordinalNumCycle);
             WebElement selectedItem = listOfItems.get(ordinalNumItemList);

             String oldPriceString = selectedItem.findElement(By.className("old-price")).getText();
             String discountPercentString = selectedItem.findElement(By.className("sticker--percent")).getText();
             String newPriceString = selectedItem.findElement(By.className("product-item__price")).getText();

             double oldPriceInt = Double.parseDouble(oldPriceString.replace(",00 RSD", "").replace(",20 RSD", "").replace(".", "").trim());
             int discountPercentInt = 100-Integer.parseInt(discountPercentString.replace("-", "").replace("%", ""));

             double calculatedPrice = oldPriceInt * 0.01 * discountPercentInt;
             double newPriceInt = Double.parseDouble(newPriceString.replace(",00 RSD", "").replace(",20 RSD", "").replace(".", "").trim());

             listOfCalculatedPrices.add(calculatedPrice);
             listOfNewPrices.add(newPriceInt);
             ordinalNumCycle++;
         }
    }

    public void checkPricesOutletOffer()
    {
        rndNumTwoFive();
        rndNumSelectedItems();
        getPrices();
    }
}
