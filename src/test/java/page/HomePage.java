package page;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileMenuItem;

    @FindBy(xpath = "//form[@id='extended-nav-search']//input")
    private WebElement searchField;

    public HomePage (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return profileMenuItem.isDisplayed() &&
                driver.getCurrentUrl().contains("/feed") &&
                driver.getTitle().contains("LinkedIn");
    }

    public SearchPage search(String searchTerm) throws InterruptedException {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        return new SearchPage(driver);

    }
}
