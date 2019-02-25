import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubmitPage {
    WebDriver driver;

    WebElement errorForUserName;
    WebElement errorForPassword;

    public SubmitPage (WebDriver driver) {
        this.driver=driver;
        initElements();

    }

    private void initElements () {
        errorForUserName =  driver.findElement(By.xpath("//div[@id='error-for-username']"));
        errorForPassword = driver.findElement(By.xpath("//div[@id='error-for-password']"));
    }

    public boolean isPageLoaded () {
        return (errorForUserName.isDisplayed() ||
                errorForPassword.isDisplayed()) &&
                driver.getCurrentUrl().contains("login-submit?loginSubmitSource=GUEST_HOME") &&
                driver.getTitle().contains("Sign In to LinkedIn");
    }

    public boolean isEmailValidationMessageDisplayed (String emailValidationMessage){
        return errorForUserName.isDisplayed() && errorForUserName.getText().contains(emailValidationMessage);
    }

    public boolean isPasswordValidationMessageDisplayed (String passwordValidationMessage){
        return errorForPassword.isDisplayed() && errorForPassword.getText().contains(passwordValidationMessage);
    }
}
