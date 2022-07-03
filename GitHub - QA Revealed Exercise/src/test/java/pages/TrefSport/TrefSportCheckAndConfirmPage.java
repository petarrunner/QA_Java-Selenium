package pages.TrefSport;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TrefSportCheckAndConfirmPage extends BaseHelper {
    @FindBy(className = "bill__value--highlight")
    WebElement finalPriceSpan;
    public double finalPrice;

    WebDriver driver;

    public TrefSportCheckAndConfirmPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    TrefSportOrderPage opage = new TrefSportOrderPage(driver);

    private void getFinalPrice() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("bill__value--highlight")));
        finalPrice = opage.priceStringToDouble(finalPriceSpan.getText());
//        finalPrice = Double.parseDouble(finalPriceSpan.getText().replace(",00 RSD", "").replace(",20 RSD", "").replace(".", ""));
    }

    public void checkAndConfirm() {
        getFinalPrice();
    }
}
