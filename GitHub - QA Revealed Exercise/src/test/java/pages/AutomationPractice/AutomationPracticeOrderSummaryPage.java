package pages.AutomationPractice;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AutomationPracticeOrderSummaryPage extends BaseHelper {
    @FindBy(className = "button-medium")
    WebElement buttonConfirm;


    WebDriver driver;

    public AutomationPracticeOrderSummaryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private void confirmPay() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("button-medium")));
        js.executeScript("arguments[0].click();", buttonConfirm);
    }

    public void finalConfirmPay() {
        confirmPay();
    }

}
