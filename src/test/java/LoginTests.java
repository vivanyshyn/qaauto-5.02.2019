import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void successfulLoginTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\WZHIVY\\IdeaProjects\\qaauto-5.02.2019\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.linkedin.com/");

    }
}
