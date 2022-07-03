package pages.Blic;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BlicArticlePage extends BaseHelper {
    public String articleHeader;

    WebDriver driver;

    public BlicArticlePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void articleTitle() {
        System.out.println("TextArtHeader1 - " + articleHeader);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("article__header")));
        articleHeader = driver.findElement(By.className("article__header")).findElement(By.tagName("h1")).getText();
        System.out.println("TextArtHeader2 - " + articleHeader);
    }

    public void articleHeaderText() {
        articleTitle();
    }
}
