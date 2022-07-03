package pages.Donesi;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.Double.parseDouble;

public class DonesiRestaurantPage extends BaseHelper
{
    @FindBy (id="menu-list-content")
            WebElement fieldPopularDishes;
    @FindBy (className="cc-controls-quantity-increase")
            WebElement buttonPlus;
    @FindBy (className="cc-modal-footer-controls-submit__button")
            WebElement buttonAddToCart;
    @FindBy (className="cookie-consent-accept-all")
            WebElement buttonAcceptCookie;
    @FindBy (xpath="/html/body/div[2]/main/section[2]/div/div/div/div/div/div[1]/div[3]/div[2]/div[1]/span")
            WebElement fieldMinDeliveryPrice;
    @FindBy (xpath="/html/body/div[2]/main/section[2]/div/div/div/div/div/div[1]/div[3]/div[2]/div[3]/span")
            WebElement fieldPriceShipping;

    public String minDeliveryPriceString;
    public String priceShippingString;
    public String firstFoodPriceString;
    public String secondFoodPriceString;

    public double minPriceDeliveryDouble;
    public double priceShippingDouble;
    public double firstFoodPriceDouble;
    public double secondFoodPriceDouble;

    public double totalFirstFood;
    public double totalSecondFood;
    public double totalSum;

    WebDriver driver;
    public DonesiRestaurantPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

   private void navigationToPageDonesi() {
        driver.get("https://www.donesi.com/shops?address=Beogradska%2010%2C%2011000%20Beograd%2C%20Vra%C4%8Dar&city=Vra%C4%8Dar&county=Beograd&latitude=44.8034646&longitude=20.4674129&nomap=0&street=Beogradska&street_no=10&zip=11000&area=Vra%C4%8Dar&city_transliterated=&slug=%2F&deliverytype=0&scope=personal&t=1617033263142&lo=%2F&kitchens=italijanska-hrana");
   }
    private void navigateToRestaurantPage()
    {
        //driver.get("https://www.donesi.com/dostava/beograd/stari-grad/st.-mark-s-place");
        driver.get("https://www.donesi.com/dostava/beograd/bigpizza-centar");
    }
    private void cookiesAccept()
    {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cookie-consent-accept-all")));
        js.executeScript("arguments[0].click();", buttonAcceptCookie);
    }
    private void takeMinDeliveryPrice()
    {
        minDeliveryPriceString = fieldMinDeliveryPrice.getText();
        minPriceDeliveryDouble = Integer.parseInt(minDeliveryPriceString.replace(".","").replace("RSD","").trim());
        System.out.println("Min Price String - "+minDeliveryPriceString);
        System.out.println("Min Price Double - "+ minPriceDeliveryDouble);
    }
    private void takePriceShipping()
    {
        priceShippingString = fieldPriceShipping.getText();
        priceShippingDouble = Integer.parseInt(priceShippingString.replace(".","").replace("RSD","").trim());
        System.out.println("Price Shipping String - "+priceShippingString);
        System.out.println("Price Shipping Double - "+ priceShippingDouble);
    }

    private void orderPopularDish() {
        System.out.println("FOOD");
        wdWait.until(ExpectedConditions.visibilityOf(fieldPopularDishes));
        List<WebElement> listPopularDishes = fieldPopularDishes.findElements(By.tagName("li"));

        int numberRandom = new Random().nextInt(listPopularDishes.size());
        System.out.println("INT Random Number - "+numberRandom);

        WebElement orderedFood = listPopularDishes.get(numberRandom);

        WebElement foodOrderedLink = orderedFood.findElement(By.className("shop-profile-menu-list-item-name"));
        js.executeScript("arguments[0].click();", foodOrderedLink);
    }
    private void takeFoodPrice() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cc-modal-header__total")));
        String foodOrderedPriceString = driver.findElement(By.className("cc-modal-header__total")).getText();
        String foodOrderedNameString = driver.findElement(By.className("cc-modal-header__name")).getText();

        if (Objects.equals(firstFoodPriceString, null)){
            firstFoodPriceString=foodOrderedPriceString;
            firstFoodPriceDouble= parseDouble(firstFoodPriceString.replace(".","").replace("RSD","").replace(",", ".").trim());
        } else {
            secondFoodPriceString=foodOrderedPriceString;
            secondFoodPriceDouble= parseDouble(secondFoodPriceString.replace(".","").replace("RSD","").replace(",", ".").trim());
        }

        System.out.println("1  Name - "+foodOrderedNameString);
        System.out.println("1  String - "+firstFoodPriceString);
        System.out.println("1  Double - "+firstFoodPriceDouble);
        System.out.println("2  String - "+secondFoodPriceString);
        System.out.println("2  Double - "+secondFoodPriceDouble);
    }
    private void addOnePortion() {
        wdWait.until(ExpectedConditions.visibilityOf(buttonPlus));
        buttonPlus.click();
    }

    private void confirmAddToCart()
    {
        wdWait.until(ExpectedConditions.visibilityOf(buttonAddToCart));
        buttonAddToCart.click();
    }

    private void calculateTotalSum()
    {
        wdWait.until(ExpectedConditions.invisibilityOf(buttonAddToCart));
        List<WebElement> listTotals = driver.findElements(By.className("cart-product-list-item-price"));
        String totalFoodString1 = listTotals.get(0).getText().replace(".","").replace("RSD","").replace(",", ".").trim();
        String totalFoodString2 = listTotals.get(1).getText().replace(".","").replace("RSD","").replace(",", ".").trim();

        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("cart-total-price"), listTotals.get(0).getText()));
        String totalString = driver.findElement(By.className("cart-total-price")).getText().replace(".","").replace("RSD","").replace(",", ".").trim();

        System.out.println("PRICE STRING");
        System.out.println("Price 1 - "+totalFoodString1);
        System.out.println("Price 2 - "+totalFoodString2);
        System.out.println("Price total- "+totalString);

        totalFirstFood = parseDouble(totalFoodString1);
        totalSecondFood= parseDouble(totalFoodString2);
        totalSum= parseDouble(totalString);

        System.out.println("PRICE DOUBLE");
        System.out.println("Price 1 - "+totalFirstFood);
        System.out.println("Price 2 - "+totalSecondFood);
        System.out.println("total- "+totalSum);
    }
    public void finalOrder()
    {
        navigationToPageDonesi();
        navigateToRestaurantPage();
        cookiesAccept();
        takeMinDeliveryPrice();
        takePriceShipping();
        orderPopularDish();
        takeFoodPrice();
        addOnePortion();
        confirmAddToCart();
        orderPopularDish();
        takeFoodPrice();
        confirmAddToCart();
        calculateTotalSum();
    }
}
