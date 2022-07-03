package pages.AutomationPractice;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AutomationPracticeShippingPage extends BaseHelper {
    @FindBy(id = "cgv")
    WebElement checkTermsOfService;
    @FindBy(name = "processCarrier")
    WebElement buttonProceed;

    WebDriver driver;

    public AutomationPracticeShippingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void agreeTermsOfService() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("cgv")));
        checkTermsOfService.click();
    }

    private void clickProceed() {
        buttonProceed.click();
    }

    public void confirmShipping() {
        agreeTermsOfService();
        clickProceed();
    }

}
