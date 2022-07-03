package pages.Blic;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BlicPage extends BaseHelper {
    @FindBy(id = "search-open")
    WebElement searchIcon;
    @FindBy(id = "search-input")
    WebElement searchField;
    @FindBy(id = "search")
    WebElement formSearch;

    WebDriver driver;

    public BlicPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToBlicHomepage() {
        driver.get("https://www.blic.rs/");
    }

    private void clickOnMagnifyingGlassIcon() {
        searchIcon.click();
    }

    private void enterTermSearchField(String term) {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-input")));
        searchField.sendKeys(term);
    }

    private void clickOnTraziIcon() {
        formSearch.findElement(By.className("icon")).click();
    }

    public void blicSearch(String term) {
        navigateToBlicHomepage();
        clickOnMagnifyingGlassIcon();
        enterTermSearchField(term);
        clickOnTraziIcon();
    }
}
