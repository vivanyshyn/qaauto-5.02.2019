import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.*;


public class LoginTests {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\WZHIVY\\IdeaProjects\\qaauto-5.02.2019\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        //options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterMethod() { driver.quit(); }

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


    @Test(dataProvider="validData", priority=1)
    public void successfulLoginTest(String userEmail, String userPassword) {

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");
        landingPage.login(userEmail, userPassword);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not loaded.");
    }

    @Test (dataProvider="invalidData")
    public void negativeLoginTest (String userEmail, String userPassword, String emailValidationMessage, String passwordValidationMessage) throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.login(userEmail, userPassword);

        SubmitPage submitPage = new SubmitPage(driver);

        Assert.assertTrue(submitPage.isPageLoaded(), "Submit Page is not loaded");

        if (emailValidationMessage !="") {
        Assert.assertTrue(submitPage.isEmailValidationMessageDisplayed(emailValidationMessage), "Email Validation message on SubmitPage is missing or incorrect");
        } else {
        Assert.assertTrue(submitPage.isPasswordValidationMessageDisplayed(passwordValidationMessage), "Password Validation message on SubmitPage is missing or incorrect");
        }
    }

}
