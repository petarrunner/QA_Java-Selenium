package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.ePismonosa.ePismonosaHomePage;

import java.util.Locale;

public class ePismonosaTest extends BaseTest{
    @Test
    public void checkNavigationLinkToInstagram(){

        ePismonosaHomePage homepage = new ePismonosaHomePage(driver);
        homepage.publicMethod();
        String text = driver.findElement(By.id("cs_social_widget-2")).findElement(By.tagName("h2")).getText();
        Assert.assertTrue("Greska",text.toLowerCase().contains("mreže"));

        System.out.println("Text: "+text);
        System.out.println("Final: "+text.toLowerCase());
        try {
            Thread.sleep(3000); // ostavljeno zbog vizuelne potvrde
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
