package othersPages;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.BaseTest;


public class BrowserTest extends BaseTest

{
    @Test
    public void googleSearchTest() throws InterruptedException
    {
        // otici na pocetnu stranicu Google-a
        driver.get("https://www.google.com/");
        // u polje za pretragu otkucati "QA Revealed"
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("QA Revealed");
        wdWait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
        // kliknuti na dugme "Google Search"
        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchButton.click();
        // proveriti da postoji pojam "QA Revealed" na rezultatima pretrage
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("hlcw0c")));
        WebElement searchResults = driver.findElement(By.className("hlcw0c"));
        Assert.assertTrue("Term is not found on search result page",searchResults.getText().contains("QA Revealed"));
        Thread.sleep(3000); // ostavljeno zbog vizuelne potvrde
    }

    @Test
    public void YahooSearchTest() throws InterruptedException
    {
        // otici na pocetnu stranicu Yahoo-a
        driver.get("https://www.yahoo.com/");

        // u polje za pretragu otkucati "QA Revealed"
        WebElement searchBox = driver.findElement(By.id("ybar-sbq"));
        searchBox.sendKeys("QA Revealed");

        // kliknuti na dugme "Yahoo Search"
        WebElement searchButton = driver.findElement(By.id("ybar-search"));
        searchButton.click();

        // proveriti da postoji pojam "QA Revealed" na rezultatima pretrage
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("web")));
        WebElement searchResults = driver.findElement(By.id("web"));
        Assert.assertTrue("Term is not found on search result page", searchResults.getText().contains("QA Revealed"));
        Thread.sleep(3000); // ostavljeno zbog vizuelne potvrde
    }
}
