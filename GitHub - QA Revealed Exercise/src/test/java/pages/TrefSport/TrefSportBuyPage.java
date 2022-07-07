package pages.TrefSport;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class TrefSportBuyPage extends BaseHelper {
    @FindBy(id = "PageContentPlaceHolder_SpanPrice")
    WebElement priceSpan;
    @FindBy(id = "hlAddToCart")
    WebElement buttonDodajUKorpu;
    @FindBy(id = "aProductAddedToCartCancel")
    WebElement buttonNastaviKupovinu;
    public double countingSumCart = 0.0;

    WebDriver driver;

    public TrefSportBuyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    TrefSportFirstPage fpage = new TrefSportFirstPage(driver);
    TrefSportOrderPage opage = new TrefSportOrderPage(driver);

    private void getPrice() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("PageContentPlaceHolder_SpanPrice")));
        String price = priceSpan.getText();
        double priceD = opage.priceStringToInteger(price);
        countingSumCart = countingSumCart + priceD;
    }

    private void checkAndSelectSize() {
        WebElement fieldSizes = driver.findElement(By.className("check-product-size"));
        List<WebElement> listSizes = fieldSizes.findElements(By.className("available"));
        if (listSizes.size() > 0) {
            int numRnd = new Random().nextInt(listSizes.size());
            WebElement selectedSize = listSizes.get(numRnd);
            js.executeScript("arguments[0].click();", selectedSize);
        }
    }

    private void clickDodajUKorpu() {
        WebElement fieldSizes = driver.findElement(By.className("check-product-size"));
        List<WebElement> listSizes = fieldSizes.findElements(By.className("available"));
        WebElement text = driver.findElement(By.id("spanSizeLable"));
        if (text.getText().equals("ODABIR VELIČINE") && listSizes.size() == 0) {
            fpage.goToOrder();
            opage.orderItemsFinal();
            getPrice();
            checkAndSelectSize();
            clickDodajUKorpu();
        } else {
            buttonDodajUKorpu.click();
        }
    }

    private void clickNastaviKupovinu() {
        wdWait.until(ExpectedConditions.visibilityOf(buttonNastaviKupovinu));
        buttonNastaviKupovinu.click();
    }

    private void clickBtnCart() {
        WebElement buttonCart = driver.findElement(By.className("js-quick-cart"));
        wdWait.until(ExpectedConditions.visibilityOf(buttonCart));
        buttonCart.click();
    }

    private void clickBtnConfirmCart() {
        WebElement buttonConfirmCart = driver.findElement(By.id("hlConfirmOrder"));
        wdWait.until(ExpectedConditions.visibilityOf(buttonConfirmCart));
        buttonConfirmCart.click();
    }

    public void clickOnBuyButton() {
        getPrice();
        checkAndSelectSize();
        clickDodajUKorpu();
        clickNastaviKupovinu();
    }

    public void goToCart() {
        clickBtnCart();
        clickBtnConfirmCart();
    }
}
