import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class For_ex {
        public static void main(String[] args) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\WZHIVY\\IdeaProjects\\qaauto-5.02.2019\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);
            options.addArguments("--start-maximized");
            WebDriver driver = new ChromeDriver(options);
            driver.get("https://www.ukr.net/");

            List<WebElement> newsList= driver.findElements(By.xpath("//ul[@class='feed__section--top']/li"));
            for (WebElement oneNews : newsList) {
                System.out.println(oneNews.getText());
            }







        }
}

