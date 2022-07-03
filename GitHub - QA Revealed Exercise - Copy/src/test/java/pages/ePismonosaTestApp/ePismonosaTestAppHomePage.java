package pages.ePismonosaTestApp;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ePismonosaTestAppHomePage extends BaseHelper {

    WebDriver driver;
    public ePismonosaTestAppHomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    public String alertMessage = "";

    private void navigateToHomePage(){
        driver.get("https://test-app.epismonosa.rs");
    }
    private void clickButtonLoginWithNameAndPassword(){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("register-page-container")));
        List<WebElement> listInputs = driver.findElements(By.className("login-radio-large"));
        listInputs.get(0).click();
    }
    private void enterCredentials( String username,String password){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("register-form")));
        List<WebElement> usernameAndPassword = driver.findElement(By.className("register-form")).findElements(By.tagName("input"));
        usernameAndPassword.get(0).sendKeys(username);
        usernameAndPassword.get(1).sendKeys(password);
    }
    private void clickButtonConfirm(){
        WebElement buttonConfirm = driver.findElement(By.className("register-form")).findElement(By.tagName("button"));
        buttonConfirm.click();
    }
    private void checkAlertMessage(){
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MuiAlert-message")));
        List<WebElement> listAlert = driver.findElements(By.className("MuiAlert-message"));
        if(listAlert.size()>0){
           alertMessage = listAlert.get(0).getText();
        }
    }
    public void publicMethod(String username,String password){
        navigateToHomePage();
        clickButtonLoginWithNameAndPassword();
        enterCredentials( username, password);
        clickButtonConfirm();
        checkAlertMessage();
    }
}
