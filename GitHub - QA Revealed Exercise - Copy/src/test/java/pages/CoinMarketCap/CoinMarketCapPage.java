package pages.CoinMarketCap;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CoinMarketCapPage extends BaseHelper {
    @FindBy(className = "cmc-cookie-policy-banner__close")
    WebElement buttonExitCookie;
    @FindBy(className = "table-control-filter")
    WebElement buttonFilter;
    @FindBy(className = "container___QEYqH")
    WebElement fieldFilterHidden;
    @FindBy(className = "popup-content")
    WebElement fieldFilterPopUp;
    WebDriver driver;

    public CoinMarketCapPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToHomePage() {
        driver.get("https://coinmarketcap.com/");
    }

    private void exitCookie() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cmc-cookie-policy-banner__close")));
        buttonExitCookie.click();
    }

    private void login() {
        WebElement fieldNavbar = driver.findElement(By.className("sc-1q2q8hd-0"));
        List<WebElement> listNavbar = fieldNavbar.findElements(By.tagName("a"));
        listNavbar.get(7).click();
    }

    private void enterEmail() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("Button__ButtonPrimary-sc-16xgzp2-0")));
        List<WebElement> listInputs = driver.findElements(By.tagName("input"));
        listInputs.get(0).sendKeys("mekew53129@yncyjs.com");
        wdWait.until(ExpectedConditions.visibilityOf(listInputs.get(1)));
        listInputs.get(1).sendKeys("petar123");
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/form/button")));
        driver.findElement(By.xpath("/html/body/div/main/form/button")).click();
    }

    public int checkList() {
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tr")));
        List<WebElement> listTop100 = driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        listTop100.removeIf(n -> (n.getAttribute("class").contains("CLASSNAME")));

        return listTop100.size();
    }

    private void clickButtonFilter() {
        js.executeScript("arguments[0].click();", buttonFilter);
        wdWait.until(ExpectedConditions.visibilityOf(fieldFilterHidden));
    }

    private void selectAddFilters() {
        List<WebElement> listButtonsFilter = fieldFilterHidden.findElements(By.tagName("li"));
        listButtonsFilter.get(4).click();
    }

    private void clickPrice() {
        wdWait.until(ExpectedConditions.visibilityOf(fieldFilterPopUp));
        List<WebElement> listMoreFilters = fieldFilterPopUp.findElements(By.className("cmc-filter-button"));
        listMoreFilters.get(2).click();
    }

    private void insertPrices(String lowerPrice, String higherPrice) {
        List<WebElement> listPriceRangeFields = fieldFilterPopUp.findElement(By.className("cmc-input-row")).findElements(By.tagName("input"));
        listPriceRangeFields.get(0).sendKeys(lowerPrice);
        listPriceRangeFields.get(1).sendKeys(higherPrice);

        WebElement buttonApplyFilter = fieldFilterPopUp.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[3]/div/div/div[2]/div[1]/div[5]/div/div/div[2]/div/div[2]/div/button[1]"));
        buttonApplyFilter.click();
    }

    public void addToWatchlist() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("tbody")));
        List<WebElement> listRest = driver.findElement(By.tagName("tbody")).findElements(By.className("icon-Star"));

        js.executeScript("arguments[0].click();", listRest.get(0));
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("eXSuNU")));
        driver.findElement(By.className("eXSuNU")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.className("eXSuNU")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.className("eXSuNU")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.className("eXSuNU")).click();
        // wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("eXSuNU")));
        //  driver.findElement(By.className("eXSuNU")).click();
        js.executeScript("arguments[0].click();", listRest.get(2));
        js.executeScript("arguments[0].click();", listRest.get(4));
        js.executeScript("arguments[0].click();", listRest.get(6));
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clickShowResults() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div/div[3]/div/div/div[2]/div[2]/button[1]")));
        WebElement buttonShowResults = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[3]/div/div/div[2]/div[2]/button[1]"));
        buttonShowResults.click();

    }

    private void exitWatchlistUpdate() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("titleWrapper___-jN1T")));
        WebElement fieldExitWListUpd = driver.findElement(By.className("titleWrapper___-jN1T"));
        WebElement buttonExitWListUpd = fieldExitWListUpd.findElement(By.tagName("svg"));
//        buttonExitWListUpd.click();
        js.executeScript("arguments[0].click();", buttonExitWListUpd);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkListFinal() {
        navigateToHomePage();
        exitCookie();
        checkList();
    }

    public void checkWatchListFinal(String lowerPrice, String higherPrice) {
        navigateToHomePage();
        exitCookie();
        login();
        enterEmail();
        //checkList();
        //clickButtonFilter();
        //selectAddFilters();
        //clickPrice();
        //insertPrices(lowerPrice,higherPrice);
        //clickShowResults();
        //checkList();
        addToWatchlist();
//        exitWatchlistUpdate();
    }
}
