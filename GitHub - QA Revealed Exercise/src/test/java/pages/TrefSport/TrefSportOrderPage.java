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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TrefSportOrderPage extends BaseHelper {
    @FindBy(id = "product-list")
    WebElement fieldOrderItems;

    public String availableSizes = "";
    public ArrayList<Integer> listOfPricesAllAfter = new ArrayList<>();
    public int minPrice;
    public int maxPrice;

    WebDriver driver;

    public TrefSportOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
        if (listOrderListLink.size() > 0) {

            for (WebElement item : listOrderListLink) {
//                List<WebElement> listaDsc = item.findElements(By.className("sticker--percent"));
//                if (listaDsc.size() > 0) {
//                    String price = item.findElement(By.className("old-price")).getText();
//                    int priceInt = priceStringToInteger(price);
//                    listOfPricesAllAfter.add(priceInt);
//                } else {
//                }
                String price2 = item.findElement(By.className("product-item__price")).getText();
                int priceInt2 = priceStringToInteger(price2);
                listOfPricesAllAfter.add(priceInt2);
            }
        } else {
            TrefSportFirstPage firstPage = new TrefSportFirstPage(driver);
            firstPage.goToOrder();
            selectPriceRange();

        }
        System.out.println("Size of itemList => " + listOrderListLink.size());

    }

    private void selectOrderedSize(String size) {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("divSize")));
        List<WebElement> listSizes = driver.findElement(By.id("divSize")).findElements(By.className("custom-check"));
        for (WebElement el : listSizes) {
            if (size.contains(el.findElement(By.tagName("label")).getText())) {
                el.click();
                break;
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkAvailableSizes() {
        List<WebElement> listAvailableSizes = driver.findElements(By.className("product-item"));
        int randomNumber = new Random().nextInt(listAvailableSizes.size());
        WebElement randomItem = listAvailableSizes.get(randomNumber);

        Actions mousehover = new Actions(driver);
        mousehover.moveToElement(randomItem).build().perform();

        List<WebElement> randomItemListP = randomItem.findElement(By.className("velicine")).findElements(By.tagName("p"));

        String itemText = randomItemListP.get(randomItemListP.size() - 1).getText();
        availableSizes = itemText;


    }

    private void setRandomRangePrice() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("filter__item")));
        System.out.println("URL => " + driver.getCurrentUrl());

        WebElement priceField = driver.findElement(By.id("PageContentPlaceHolder_priceList"));
        js.executeScript("arguments[0].click();", priceField.findElement(By.className("filter__item-title")));

        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("custom-check")));
        List<WebElement> listSizeOptions = priceField.findElements(By.tagName("input"));
        int randomNumber = new Random().nextInt(listSizeOptions.size());
        js.executeScript("arguments[0].click();", listSizeOptions.get(randomNumber));
        String priceRange = priceField.findElements(By.tagName("label")).get(randomNumber).getText();
        separatePricesToList(priceRange);
    }

    private void separatePricesToList(String price) {
        String priceString = price.replace(" RSD", "").replace(".", "");
        List<String> listStrings = Arrays.asList(priceString.split(" - "));
        minPrice = Integer.parseInt(listStrings.get(0));
        maxPrice = Integer.parseInt(listStrings.get(1));

        System.out.println("1 => " + minPrice + " between " + maxPrice);

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

    public void selectTShirt(String size) {

        selectOrderedSize(size);
        checkAvailableSizes();
    }

    public void selectPriceRange() {
        setRandomRangePrice();
        takeAllPricesAfter();
    }
}
