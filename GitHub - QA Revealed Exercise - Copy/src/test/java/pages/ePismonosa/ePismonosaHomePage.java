package pages.ePismonosa;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ePismonosaHomePage extends BaseHelper {

WebDriver driver;
public ePismonosaHomePage(WebDriver driver)
{
    this.driver=driver;
    PageFactory.initElements(driver,this);
}

private void navigateToHomePage(){
    driver.get("https://epismonosa.rs/");
}
private  void clickInstagramIcon(){
    wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("social-instagram")));
    WebElement iconInstagram = driver.findElement(By.className("social-instagram"));
    iconInstagram.click();
    }

    public void publicMethod(){
    navigateToHomePage();
    clickInstagramIcon();

    }
}
