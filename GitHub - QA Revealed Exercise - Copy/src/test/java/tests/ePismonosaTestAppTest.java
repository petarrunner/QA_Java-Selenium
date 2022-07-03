package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.ePismonosaTestApp.ePismonosaTestAppHomePage;

public class ePismonosaTestAppTest extends BaseTest {

    @Test
    public void ePismonosaInvalidLoginTest() {
//    public void login_InvalidCredentials_ShouldNotBeLoggedInAndDisplayedAlertMessage() {
        String username = "Petar";
        String password = "123";

        ePismonosaTestAppHomePage homepage = new ePismonosaTestAppHomePage(driver);
        homepage.publicMethod(username, password);

        Assert.assertEquals("Pogrešni kredencijali", homepage.alertMessage);

        try {
            Thread.sleep(3000); // ostavljeno zbog vizuelne potvrde
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


