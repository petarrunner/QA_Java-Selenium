package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Blic.BlicArticlePage;
import pages.Blic.BlicPage;
import pages.Blic.BlicResultsPage;


public class BlicTest extends BaseTest {
    @Test
    public void blicSearchTest() throws InterruptedException {

        String term = "comtrade";

        BlicPage searchpage = new BlicPage(driver);
        searchpage.blicSearch(term);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("news-box")));
        WebElement searchResults = driver.findElement(By.className("news-box"));
        Assert.assertTrue("Term is not found on search result page", searchResults.getText().toLowerCase().contains(term));
        Thread.sleep(3000); // ostavljeno zbog vizuelne potvrde
    }

    @Test
    public void BlicResultsPageOpen() throws InterruptedException {
        String term = "comtrade";
        BlicPage searchpage = new BlicPage(driver);
        searchpage.blicSearch(term);

        int pageNo = 3;
        BlicResultsPage results = new BlicResultsPage(driver);
        results.articleGetTitle(pageNo);

        BlicArticlePage article = new BlicArticlePage(driver);
        article.articleHeaderText();

        String articleTitle = results.selectedArticleText;
        String articleHeader2 = article.articleHeader;
        System.out.println("T - " + articleTitle);
        System.out.println("H - " + articleHeader2);

        Assert.assertTrue("Wrong page is opened", articleTitle.contains(articleHeader2));
        Thread.sleep(3000); //ostavljeno zbog vizuelne potvrde
    }
}

