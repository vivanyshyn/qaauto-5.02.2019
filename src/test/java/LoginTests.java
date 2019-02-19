import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\WZHIVY\\IdeaProjects\\qaauto-5.02.2019\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        //options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test(priority = 1)
    public void successfulLoginTest() throws InterruptedException {
        WebElement userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("sslava543@gmail.com");
        userPasswordField.sendKeys("qwerty12345");
        signInButton.click();

        Thread.sleep(500);

        WebElement profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));

        Assert.assertTrue(profileMenuItem.isDisplayed(),
                "profileMenuItem is not displayed on Home page.");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/feed/",
                "Home page URL is incorrect");
    }

    @Test(priority = 2)
    public void incorrectEmailLoginTest() throws InterruptedException {
        WebElement userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("wrongMail1564@gmail.com");
        userPasswordField.sendKeys("qwerty12345");
        signInButton.click();

        Thread.sleep(500);

        WebElement errorForUserName = driver.findElement(By.xpath("//div[@id='error-for-username']"));
        
        Assert.assertTrue(errorForUserName.isDisplayed(),
                "Error message is not displayed");
        Assert.assertEquals(errorForUserName.getText(),
                "Hmm, we don't recognize that email. Please try again.",
                "Error message for incorrect username is missing");
    }

    @Test(priority = 3)
    public void incorrectPasswordLoginTest() {
        WebElement userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("sslava543@gmail.com");
        userPasswordField.sendKeys("12345");
        signInButton.click();

        WebElement errorForPassword = driver.findElement(By.xpath("//div[@id='error-for-password']"));

        Assert.assertTrue(errorForPassword.isDisplayed(),
                "Error message is not displayed");

        Assert.assertEquals(errorForPassword.getText(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Error message for incorrect password is missing");
    }

    @Test(priority = 4)
    public void emptyCredentialsLoginTest() {
        WebElement userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("");
        userPasswordField.sendKeys("");
        signInButton.click();

        Assert.assertFalse(signInButton.isEnabled(),
                "SignIn Button is not disabled (email and password fields are empty both)");


        userEmailField.sendKeys("sslava543@gmail.com");
        userPasswordField.sendKeys("");
        signInButton.click();

        Assert.assertFalse(signInButton.isEnabled(),
                "SignIn Button is not disabled (password field is empty)");

        userEmailField.clear();
        userEmailField.sendKeys("");
        userPasswordField.sendKeys("aaa");
        signInButton.click();

        Assert.assertFalse(signInButton.isEnabled(),
                "SignIn Button is not disabled (email field is empty)");
    }

}
