import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static java.lang.Thread.sleep;

public class BadCodeExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\WZHIVY\\IdeaProjects\\qaauto-5.02.2019\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com/");

        String searchItem = "Selenium";

        // find search field identifier
        WebElement element = driver.findElement(By.xpath("//input[@name='q']"));
        // put "Selenium" into search field
        element.sendKeys(searchItem);
        // press Enter;
        element.sendKeys(Keys.ENTER);

        List<WebElement> searchResultElements = driver.findElements(By.xpath("//div[@class='g']"));
        // for each WebElement in searchResultElements print text
        for (WebElement searchResultElement : searchResultElements) {
            System.out.println(searchResultElement.getText());

            String searchResultElementText = searchResultElement.getText();

            if (searchResultElementText.toLowerCase().contains(searchItem.toLowerCase())) {
                System.out.println(searchItem + " found");
            } else {
                System.out.println(searchItem + " not found");
            }
            System.out.println(" ");
        }
        System.out.println("Results count: " + searchResultElements.size());
        driver.close();
    }
}
