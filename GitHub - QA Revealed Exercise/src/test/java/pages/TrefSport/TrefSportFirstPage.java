package pages.TrefSport;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class TrefSportFirstPage extends BaseHelper {
    @FindBy(id = "HyperLink4")
    WebElement buttonAcceptCookie;
    @FindBy(id = "txbEmailHeader")
    WebElement inputEmail;
    @FindBy(id = "txbPasswordHeader")
    WebElement inoutPassword;
    @FindBy(id = "btnLoginHeader")
    WebElement btnLogIn;
    @FindBy(className = "header__menu-list")
    WebElement fieldNavbar;

    WebDriver driver;

    public TrefSportFirstPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToHomePage() {
        driver.get("https://www.trefsport.com/");
    }

    private void acceptCookie() {
        if (buttonAcceptCookie.isDisplayed()) {
            buttonAcceptCookie.click();
        }
    }

    private void clickButtonSignIn() {
        WebElement buttonLogIn = returnElementAttValue("data-js", "controls-dropdown-trigger");
        js.executeScript("arguments[0].click();", buttonLogIn);
    }

    private void enterEmail(String email) {
        inputEmail.sendKeys(email);
    }

    private void enterPassword(String password) {
        inoutPassword.sendKeys(password);
    }

    private void clickLogIn() {
        btnLogIn.click();
    }

    public String checkUsersFullName() {

        wdWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("btnLoginHeader")));
        WebElement userName = returnElementAttValue("data-js", "controls-dropdown-trigger");
        return userName.getText();
    }

    private void clickOnFieldOutlet() {
        List<WebElement> listNavbarElements = fieldNavbar.findElements(By.className("header__menu-list-item"));
        WebElement fieldOutlet = listNavbarElements.get(7);
        fieldOutlet.click();
    }

    private void createNavbarListAndClick() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header__menu-list-item")));
        List<WebElement> listNavbar = driver.findElements(By.className("header__menu-list-item"));
        int randomNvbHover = new Random().nextInt(6);
        Actions mousehover = new Actions(driver);
        mousehover.moveToElement(listNavbar.get(randomNvbHover)).build().perform();
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("header__menu-dropdown")));
        WebElement activeDropdown = listNavbar.get(randomNvbHover);
        List<WebElement> listHoverElements = activeDropdown.findElements(By.className("header__menu-dropdown-col"));
        listHoverElements.remove(0);
        listHoverElements.removeIf(n -> (n.getText().contains("Obuća")) || (n.getText().contains("Odeća")) || (n.getText().contains("OPREMA")) || (n.getText().contains("LOPTE")) || (n.getText().contains("Pištaljke")));
        int rndNmb = new Random().nextInt(listHoverElements.size());
        js.executeScript("arguments[0].click();", listHoverElements.get(rndNmb).findElement(By.tagName("a")));
    }

    private void selectOptionNavbar(int numberOption, String itemGroup) {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header__menu-list-item")));
        List<WebElement> listNavbar = driver.findElements(By.className("header__menu-list-item"));
        Actions mousehover = new Actions(driver);
        mousehover.moveToElement(listNavbar.get(numberOption)).build().perform();
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("header__menu-dropdown")));
        WebElement activeDropdown = listNavbar.get(numberOption);
        List<WebElement> listHoverElements = activeDropdown.findElements(By.className("header__menu-dropdown-col"));
        listHoverElements.remove(0);
        listHoverElements.removeIf(n -> (n.getText().contains("Obuća")) || (n.getText().contains("Odeća")) || (n.getText().contains("OPREMA")) || (n.getText().contains("LOPTE")) || (n.getText().contains("Pištaljke")));
        for (WebElement el :
                listHoverElements)
            if (itemGroup.contains(el.getText())) {
                js.executeScript("arguments[0].click();", el.findElement(By.tagName("a")));
                break;
            }
    }

    public void signIn(String email, String password) {
        navigateToHomePage();
        acceptCookie();
        clickButtonSignIn();
        enterEmail(email);
        enterPassword(password);
        clickLogIn();
    }

    public void checkUserName() {
        checkUsersFullName();
    }

    public void goToOutletOffer() {
        navigateToHomePage();
        acceptCookie();
        clickOnFieldOutlet();
    }

    public void justNavigate() {
        navigateToHomePage();
        acceptCookie();
    }

    public void goToOrder() {
        createNavbarListAndClick();
    }

    public void selectOrderedOption(int numberOption, String itemGroup) {
        navigateToHomePage();
        acceptCookie();
        selectOptionNavbar(numberOption, itemGroup);
    }
}