package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Herrokuapp.HerrokuappLoginPage;


public class HerrokuappTest extends BaseTest {
    @Test
    public void HerrokuappValidLoginTest() {

        String username = "tomsmith";
        String password = "SuperSecretPassword!";

        HerrokuappLoginPage loginpage = new HerrokuappLoginPage(driver);
        loginpage.loginPage(username, password);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
        WebElement loginResults = driver.findElement(By.id("flash"));
        WebElement logoutButton = driver.findElement(By.className("button"));
        Assert.assertTrue("Invalid login", loginResults.getText().contains("You logged into a secure area!"));
        Assert.assertTrue("There is not logout button", logoutButton.isDisplayed());
    }

    @Test
    public void HerrokuappInvalidLoginTest() {

        String username = "petar";
        String password = "petar!";

        HerrokuappLoginPage loginpage = new HerrokuappLoginPage(driver);
        loginpage.loginPage(username, password);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
        WebElement loginResults = driver.findElement(By.id("flash"));
        Assert.assertTrue("Negative Test, valid login", loginResults.getText().contains("Your username is invalid!"));

    }
}
