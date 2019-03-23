package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;


public class LoginTests extends BaseTest {

    @DataProvider
    public Object[][] validData() {
        return new Object[][]{
                {"sslava543@gmail.com", "qwerty12345"},
                {"sslava543@GMAIL.com", "qwerty12345"},
                {" sslava543@gmail.com ", "qwerty12345"},
        };
    }

    @DataProvider
    public Object[][] invalidData() {
        return new Object[][]{
                {"12345sslava543@gmail.com", "qwerty12345",
                        "Hmm, we don't recognize that email. Please try again.",
                        ""},
                {"sslava543@gmail.com", "qwerty123456",
                        "",
                        "Hmm, that's not the right password. Please try again or request a new one."},

        };
    }


    @Test(dataProvider="validData")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        //page.HomePage homePage = landingPage.login2(userEmail, userPassword, page.HomePage.class);

        HomePage homePage = landingPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not loaded.");
    }

    @Test (dataProvider="invalidData")
    public void negativeLoginTest (String userEmail,
                                   String userPassword,
                                   String emailValidationMessage,
                                   String passwordValidationMessage) {

        LoginSubmitPage loginSubmitPage = landingPage.login(userEmail, userPassword, LoginSubmitPage.class);

        //page.LoginSubmitPage loginSubmitPage = landingPage.login(userEmail, userPassword);


        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Submit Page is not loaded");
        Assert.assertEquals(loginSubmitPage.getUserEmailValidationText(),
                emailValidationMessage,
                "userEmail validation message text is wrong");
        Assert.assertEquals(loginSubmitPage.getUserPasswordValidationText(),
                passwordValidationMessage,
                "userEmail validation message text is wrong");
    }

}
