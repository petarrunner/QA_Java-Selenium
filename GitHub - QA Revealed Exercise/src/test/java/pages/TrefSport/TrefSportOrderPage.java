package pages.TrefSport;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrefSportOrderPage extends BaseHelper {
    @FindBy(id = "product-list")
    WebElement fieldOrderItems;
    public ArrayList<Double> listOfPricesAllAfter = new ArrayList<>();

    WebDriver driver;

    public TrefSportOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public double priceStringToDouble(String priceString) {
        double priceDouble = Double.parseDouble(priceString.replace(",00 RSD", "").replace(",20 RSD", "").replace(".", "").replace(">",""));
        return priceDouble;
    }


    private void repeatOrder() {
        TrefSportFirstPage fpage = new TrefSportFirstPage(driver);
        fpage.goToOrder();
        clickOnRandomItem();
    }

    private void clickOnRandomItem() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-list")));
        List<WebElement> listOrderListLink = fieldOrderItems.findElements(By.className("product-item"));
        System.out.println("List size items: " + listOrderListLink.size());
        if (listOrderListLink.size() > 0) {
            int numRnd = new Random().nextInt(listOrderListLink.size());
            WebElement selectedItem = listOrderListLink.get(numRnd);
            System.out.println("Random number: " + numRnd);
            System.out.println("Random item: " + listOrderListLink.get(numRnd).getText());
            Actions mousehover = new Actions(driver);
            mousehover.moveToElement(selectedItem).build().perform();

            if (selectedItem.findElement(By.className("velicine")).findElement(By.tagName("p")).getText().contains("Dostupne veličine:")) {
                selectedItem.click();
            } else {
                repeatOrder();
            }
        } else {
            repeatOrder();
        }
    }


    private void checkListTwoAndMore() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-list")));
        List<WebElement> listOrderListLink = fieldOrderItems.findElements(By.className("product-item"));
        if (listOrderListLink.size() < 2) {
            searchAgain();
        }
    }

    private void searchAgain() {
        TrefSportFirstPage fpage = new TrefSportFirstPage(driver);
        fpage.goToOrder();
        checkListTwoAndMore();
    }

    private void clickSortButton() {
        WebElement buttonSort = driver.findElement(By.xpath("/html/body/form/main/div[1]/div/div[1]/div/div/a"));
        buttonSort.click();
    }

    private void selectSortOption() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("active-result")));
        List<WebElement> listSortOptions = driver.findElements(By.className("active-result"));
        listSortOptions.get(3).click();
    }

    private void takeAllPricesAfter() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> listOrderListLink = fieldOrderItems.findElements(By.className("product-item"));
        for (WebElement item : listOrderListLink) {
            List<WebElement> listaDsc = item.findElements(By.className("sticker--percent"));
            if (listaDsc.size() > 0) {
                String price = item.findElement(By.className("old-price")).getText();
                double priceInt = priceStringToDouble(price);
                listOfPricesAllAfter.add(priceInt);
            } else {
                String price2 = item.findElement(By.className("product-item__price")).getText();
                double priceInt2 = priceStringToDouble(price2);
                listOfPricesAllAfter.add(priceInt2);
            }
        }
    }

    public void sortingAscendingFinal() {
        checkListTwoAndMore();
        clickSortButton();
        selectSortOption();
        takeAllPricesAfter();
    }

    public void orderItemsFinal() {
        clickOnRandomItem();
    }
}