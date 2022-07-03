package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.Donesi.DonesiConfirmAddressPage;
import pages.Donesi.DonesiFoundedObjectsPage;
import pages.Donesi.DonesiHomePage;
import pages.Donesi.DonesiRestaurantPage;


public class DonesiTest extends BaseTest {

    @Test
    public void donesiOrderTest() throws InterruptedException {

        String streetAddress = "Beogradska 10";
        int orderNoList = 3;

        DonesiHomePage donesiHomePage = new DonesiHomePage(driver);
        donesiHomePage.donesiOrderCheck(streetAddress);

        DonesiConfirmAddressPage confirmpage = new DonesiConfirmAddressPage(driver);
        confirmpage.ClickOnButtonContinue();

        DonesiFoundedObjectsPage foundedpage = new DonesiFoundedObjectsPage(driver);
        foundedpage.saljiTotalPodatke(orderNoList);


        int totalNumberOfObjects = Integer.parseInt(foundedpage.totalNumberOfObjects);
        int selectedNumberOfObjects = Integer.parseInt(foundedpage.selectedNumberOfObjects);
        int numberOfRecommendedObjects = foundedpage.numberOfRecommendedObjects;

        Assert.assertTrue("There is not any founded object", totalNumberOfObjects > 0);
        Assert.assertTrue("Number isn't smaller", selectedNumberOfObjects < totalNumberOfObjects);
        Assert.assertEquals("Numbers are not equals", selectedNumberOfObjects, numberOfRecommendedObjects);

        System.out.println("Number of objects - total: " + totalNumberOfObjects);
        System.out.println("Number of objects - selected: " + selectedNumberOfObjects);
        System.out.println("Number of recommended objects from list: " + numberOfRecommendedObjects);

        Thread.sleep(3000); // Ostavljeno zbog vizuelne potvrde
    }

    @Test

    public void orderDishesRandom() throws InterruptedException {
        DonesiRestaurantPage restaurantpage = new DonesiRestaurantPage(driver);
        restaurantpage.finalOrder();

        double food1 = restaurantpage.firstFoodPriceDouble;
        double food2 = restaurantpage.secondFoodPriceDouble;

        double foodOnePrice = restaurantpage.totalFirstFood;
        double foodTwoPrice = restaurantpage.totalSecondFood;
        double totalSum = restaurantpage.totalSum;
        double minPrice = restaurantpage.minPriceDeliveryDouble;

        System.out.println("ASSERT");
        System.out.println("food 1 - " + foodOnePrice);
        System.out.println("food 2 - " + foodTwoPrice);
        System.out.println("total - " + totalSum);
        System.out.println("min price - " + minPrice);

        Assert.assertEquals("Price for the first food  is not correct", food1 * 2, foodOnePrice, 0.01);
        Assert.assertEquals("Price for the second food one is not correct", food2, foodTwoPrice, 0.01);
        Assert.assertEquals("Total sum is not correct", foodOnePrice + foodTwoPrice, totalSum, 0.01);
        Assert.assertTrue("Total sum is lower than min price", totalSum > minPrice);

        Thread.sleep(3000); // Ostavljeno zbog vizuelne potvrde

    }
}