package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.AutomationPractice.*;

import static org.junit.Assert.assertEquals;


public class AutomationPracticeTest extends BaseTest {

    @Test
    public void automationPracticeOrderTest() throws InterruptedException {

        AutomationPracticeHomePage homepage = new AutomationPracticeHomePage(driver);
        homepage.goToBestSellersPage();

        AutomationPracticeProductPage productpage = new AutomationPracticeProductPage(driver);
        productpage.addToCart();

        AutomationPracticeShoppingCartSumaryPage shoppingcartpage = new AutomationPracticeShoppingCartSumaryPage(driver);
        shoppingcartpage.shoppingCartSummaryCheck();

        String totalProduct = shoppingcartpage.totalProductString.replace("$", "").trim();
        String shipping = shoppingcartpage.totalShippingString.replace("$", "").trim();
        String totalPrice = shoppingcartpage.totalPriceString.replace("$", "").trim();

        double totalProductNumber = Double.parseDouble(totalProduct);
        double totalShippingNumber = Double.parseDouble(shipping);
        double totalPriceNumber = Double.parseDouble(totalPrice);

        System.out.println("Price: " + totalProductNumber);
        System.out.println("Shipping: " + totalShippingNumber);
        System.out.println("Total price + shipping: " + totalPriceNumber);
        Thread.sleep(3000);
        assertEquals("Total sum is not correct!", totalProductNumber + totalShippingNumber, totalPriceNumber, 0.01);

        String email = "rafoti8004@bombaya.com";
        String password = "petar123";
        String cityName = "Beograd";

        AutomationPracticeAuthenticationPage AuthenticationPage = new AutomationPracticeAuthenticationPage(driver);
        AuthenticationPage.signIn(email, password);

        AutomationPracticeAddressPage signinpage = new AutomationPracticeAddressPage(driver);
        signinpage.signIn();

        String streetAddress = signinpage.streetAddress;

        System.out.println(cityName);
        System.out.println(streetAddress);

        Assert.assertTrue("Address does not contains name of the city!", streetAddress.contains(cityName));

        AutomationPracticeShippingPage shippingpage = new AutomationPracticeShippingPage(driver);
        shippingpage.confirmShipping();

        AutomationPracticePaymentPage paymentpage = new AutomationPracticePaymentPage(driver);
        paymentpage.confirmPayByBank();
        AutomationPracticeOrderSummaryPage confirmpaypage = new AutomationPracticeOrderSummaryPage(driver);
        confirmpaypage.finalConfirmPay();
        System.out.println("FINAL");

        Thread.sleep(3000); // Ostavljeno zbog vizuelne potvrde
    }
}
