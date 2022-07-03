package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.CoinMarketCap.CoinMarketCapPage;

public class CoinMarketCapTest extends BaseTest
{
    @Test
    public void checkTop100()
    {
        CoinMarketCapPage homepage = new CoinMarketCapPage(driver);
        homepage.checkListFinal();

        int number = homepage.checkList();
        System.out.println(number);
        Assert.assertEquals(100,number);
    }

    @Test
    public void checkWatchlist() throws InterruptedException
    {
        String lowerPrice = "$49,850";
//        String lowerPrice = "$59,850";
        String higherPrice = "$99,999";
        CoinMarketCapPage homepage = new CoinMarketCapPage(driver);
        homepage.checkWatchListFinal(lowerPrice,higherPrice);

        Thread.sleep(2000);

    }
}
