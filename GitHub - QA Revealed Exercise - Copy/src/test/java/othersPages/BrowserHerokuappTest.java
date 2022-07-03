package othersPages;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.BaseTest;

import java.util.List;


public class BrowserHerokuappTest extends BaseTest
{
    @Test
    public void herokuappCorrectLoginTest() throws InterruptedException
    {
    //Go to page " https://the-internet.herokuapp.com/login"
        driver.get("https://the-internet.herokuapp.com/login");

        //Enter "tomsmith" for the username.
        WebElement usernameBox = driver.findElement(By.id("username"));
        usernameBox.sendKeys("tomsmith");

        // Enter "SuperSecretPassword!" for the password.
        WebElement passwordBox = driver.findElement(By.id("password"));
        passwordBox.sendKeys("SuperSecretPassword!");

        // Click on the Login button.
        WebElement loginButton = driver.findElement(By.className("fa-sign-in"));
        loginButton.click();
        // There are message "You logged into a secure area!" and logout button.
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
        WebElement loginResults = driver.findElement(By.id("flash"));
        WebElement logoutButton = driver.findElement(By.className("button"));
        Assert.assertTrue("Invalid login",loginResults.getText().contains("You logged into a secure area!"));
        Assert.assertTrue("There is not logout button",logoutButton.isDisplayed());

        Thread.sleep(3000); // ostavljeno zbog vizuelne potvrde
    }
    @Test
    public void herokuappIncorrectLoginTest() throws InterruptedException
    {
        //Go to page " https://the-internet.herokuapp.com/login"
        driver.get("https://the-internet.herokuapp.com/login");

        //Enter "petar" for the username.
        WebElement usernameBox = driver.findElement(By.id("username"));
        usernameBox.sendKeys("petar");

        // Enter "SuperSecretPassword!" for the password.
        WebElement passwordBox = driver.findElement(By.id("password"));
        passwordBox.sendKeys("petar!");

        // Click on the Login button.
        WebElement loginButton = driver.findElement(By.className("fa-sign-in"));
        loginButton.click();
        // There are message "You logged into a secure area!" and logout button.
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
        WebElement loginResults = driver.findElement(By.id("flash"));
        Assert.assertTrue("Negative Test, valid login",loginResults.getText().contains("Your username is invalid!"));
        Thread.sleep(3000); // ostavljeno zbog vizuelne potvrde
    }

@Test
    public void blicSearchTest() throws InterruptedException
{
    //Go to page www.blic.rs.
         driver.get("https://www.blic.rs/");

    //Click on the magnifying-glass icon.
        WebElement magnifierIcon = driver.findElement(By.className("search__open"));
        magnifierIcon.click();

    // Enter term "comtrade" in search field.
        //WebElement searchField = driver.findElement(By.id("search-field-head"));
        WebElement searchForm = driver.findElement(By.id("search-form-head"));
        //wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-field-head")));
        wdWait.until(ExpectedConditions.visibilityOf(searchForm));
        WebElement searchField = searchForm.findElement(By.tagName("input"));
        searchField.sendKeys("comtrade");






    //Click on the button "Traži".
        // WebElement buttonSearch = driver.findElement(By.xpath("/html/body/header/div[1]/div/div[3]/form/button"));
        WebElement searchButton = searchForm.findElement(By.tagName("button"));
        //WebElement buttonSearch = searchForm.findElement(By.tagName("button"));
        searchButton.click();

    //In the results there are texts with searched term.
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("search__results")));
        WebElement searchResults= driver.findElement(By.className("search__results"));

    //System.out.print(resultsField.getText());
        Assert.assertTrue("Term is not find!", searchResults.getText().toLowerCase().contains("comtrade"));



        List<WebElement> articles = searchResults.findElements(By.tagName("article"));
        System.out.println("Articles: " + articles.size());


                WebElement thirdArticle = articles.get(2);
    System.out.println("Third article:" + thirdArticle.getText());

        int brojac = 0;
        for (WebElement article:articles)
        {
            brojac++;
            System.out.println(brojac);
            System.out.println(article.getText());
        }


    for(WebElement article:articles) {
            if (article.getText().contains("eljeni pogled")) {
                System.out.println(article.getText());
            }
            Thread.sleep(2000);
        }

}


}
