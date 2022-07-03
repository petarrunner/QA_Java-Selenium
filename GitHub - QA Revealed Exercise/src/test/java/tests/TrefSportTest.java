package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.TrefSport.*;

import java.util.ArrayList;
import java.util.Random;

public class TrefSportTest extends BaseTest {
    @Test
    public void trefSportValidLoginTest() {
        String email = "petarvlatkovic@yahoo.com";
        String password = "Petar123";
        String userFullName = "PV Zemun";

        TrefSportFirstPage firstpage = new TrefSportFirstPage(driver);
        firstpage.signIn(email, password);
        firstpage.checkUserName();
        String userNameText = firstpage.checkUsersFullName();

        Assert.assertEquals(userFullName, userNameText);
        try {
            Thread.sleep(5000); // Ostavljeno zbog vizuelne potvrde
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void trefSportInvalidLoginTest() {
        String email = "ppp@gmail.com";
        String password = "Petar123";

        TrefSportFirstPage firstpage = new TrefSportFirstPage(driver);
        firstpage.signIn(email, password);

        String errorMsgText = driver.findElement(By.id("messagePlaceholder")).getText();

        Assert.assertEquals("Email ili lozinka nisu ispravni!", errorMsgText);
        try {
            Thread.sleep(5000); // Ostavljeno zbog vizuelne potvrde
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkDiscountedPricesInOutletOffer() {
        TrefSportFirstPage firstpageoutlet = new TrefSportFirstPage(driver);
        firstpageoutlet.goToOutletOffer();

        TrefSportOutletPage outletpage = new TrefSportOutletPage(driver);
        outletpage.checkPricesOutletOffer();

        int twoFiveInt = outletpage.twoFive;
        for (int iCount = 0; iCount < twoFiveInt; iCount++) {
            double numCalc = outletpage.listOfCalculatedPrices.get(iCount);
            double numNew = outletpage.listOfNewPrices.get(iCount);
            System.out.println(iCount + 1 + ".  =>  " + numNew + " / " + numCalc + "  =  " + numNew / numCalc);
            Assert.assertTrue(numNew >= numCalc && numNew <= 1.061 * numCalc);
        }
    }

    @Test
    public void checkOrderItems() {
        String email = "petarvlatkovic@yahoo.com";
        String password = "Petar123";
        int twoFive = new Random().nextInt(4) + 2;

        TrefSportFirstPage firstpage = new TrefSportFirstPage(driver);
        TrefSportOrderPage orderpage = new TrefSportOrderPage(driver);
        TrefSportBuyPage buypage = new TrefSportBuyPage(driver);
        TrefSportDeliveryAndPaymentPage paymentpage = new TrefSportDeliveryAndPaymentPage(driver);
        TrefSportCheckAndConfirmPage checkconfirmpage = new TrefSportCheckAndConfirmPage(driver);

        System.out.println("*** first page ***");
        firstpage.signIn(email, password);
        for (int i = 1; i <= twoFive; i++) {
            System.out.println("* Item " + i + " *");
            firstpage.goToOrder();
//            System.out.println("*** order page  ***");
            orderpage.orderItemsFinal();
//            System.out.println("*** buy page ***");
            buypage.clickOnBuyButton();
        }
        buypage.goToCart();
        System.out.println("*** payment page ***");
        paymentpage.confirmAndProgress();
        System.out.println("*** check confirm page ***");
        checkconfirmpage.checkAndConfirm();

        double sumPrices = buypage.countingSumCart;
        double finalPrice = checkconfirmpage.finalPrice;
        System.out.println(sumPrices + " : " + finalPrice);
        try {
            Thread.sleep(5000); // Ostavljeno zbog vizuelne potvrde
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("ERROR - Total sum is not correct!", sumPrices, finalPrice, 0.0);
        System.out.println(sumPrices + " : " + finalPrice);
    }

    @Test
    public void sortingAscendingPrice() {

        TrefSportFirstPage firstpageoutlet = new TrefSportFirstPage(driver);
        TrefSportOrderPage orderpage = new TrefSportOrderPage(driver);

        firstpageoutlet.justNavigate();
        firstpageoutlet.goToOrder();
        orderpage.sortingAscendingFinal();

        ArrayList<Double> listPrices = orderpage.listOfPricesAllAfter;
        int listSize = orderpage.listOfPricesAllAfter.size();
        for (int i = 1; i < listSize; i++) {
            System.out.println("List after: " + listPrices.get(i));
            Assert.assertTrue(listPrices.get(i - 1) <= listPrices.get(i));
            System.out.println(listPrices.get(i - 1) + " <= " + listPrices.get(i));
        }
        try {
            Thread.sleep(5000); // Ostavljeno zbog vizuelne potvrde
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}