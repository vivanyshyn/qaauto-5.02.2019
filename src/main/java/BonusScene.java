import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BonusScene {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\WZHIVY\\IdeaProjects\\qaauto-5.02.2019\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://bonus.eximb.com/");

        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Мій аккаунт")));

        element = driver.findElement(By.linkText("Мій аккаунт"));
        // put "Selenium" into search field
        element.click();

        element = driver.findElement(By.id("LoginForm_login"));
        element.sendKeys("aaa");

        element = driver.findElement(By.id("LoginForm_password"));
        element.sendKeys("bbb");
        element.sendKeys(Keys.ENTER);

        //next part
        driver.get("https://bonus.eximb.com/account/catalog/market");


        List<WebElement> allOptions = driver.findElements(By.xpath("//h2|//div[@class='catalog-item__right']"));
        for (WebElement option : allOptions) {
            System.out.println(option.getText());
        }

    }
}

