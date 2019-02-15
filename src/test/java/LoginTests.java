import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTests {

    @Test
    public void successfulLoginTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\WZHIVY\\IdeaProjects\\qaauto-5.02.2019\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        //options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.linkedin.com/");

        String email="sslava543@gmail.com";
        String password="qwerty12345";
        String searchItem="Viacheslav";

        WebElement loginField = driver.findElement(By.xpath("//input[@class='login-email reg-field__input']"));
        loginField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.xpath("//input[@class='login-password reg-field__input']"));
        passwordField.sendKeys(password);
        WebElement submitButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
        submitButton.click();

        Thread.sleep(1000);

        WebElement leftProfileBlock = driver.findElement(By.xpath("//div[@class='feed-identity-module__actor-meta profile-rail-card__actor-meta break-words']"));

        Boolean testBoolean = leftProfileBlock.getText().toLowerCase().contains(searchItem.toLowerCase());
        Assert.assertTrue (testBoolean, searchItem + " not found");
        //driver.close();

    }
}
