package pages.Blic;


import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BlicResultsPage extends BaseHelper
{
    public String selectedArticleText;

    WebDriver driver;
    public BlicResultsPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private void articlesGetTitle(int pageNo)
    {
        System.out.println("Text1 - "+selectedArticleText);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("search__results")));
        WebElement searchResults = driver.findElement(By.className("search__results"));
        List<WebElement> listOfArticles = searchResults.findElements(By.className("content-wrapper"));
        WebElement selectedArticle = listOfArticles.get(pageNo-1);
        selectedArticleText= selectedArticle.getText();
        System.out.println("Text2 - "+selectedArticleText);
    }
    private void articlesClick(int pageNo)
    {
        List<WebElement> listOfArticles = driver.findElement(By.className("search__results")).findElements(By.className("content-wrapper"));
        WebElement selectedArticleLink = listOfArticles.get(pageNo-1).findElement(By.tagName("a"));
        js.executeScript("arguments[0].click();",selectedArticleLink);
    }


    public void articleGetTitle(int pageNo)
    {
        articlesGetTitle(pageNo);
        articlesClick(pageNo);
    }



}
