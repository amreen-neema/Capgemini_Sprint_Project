package PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	 WebDriver driver;

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    By username = By.id("user-name");
	    By password = By.id("password");
	    By loginButton = By.id("login-button");

	    public void enterUsername(String usernameValue) {
	        driver.findElement(username).clear();
	        driver.findElement(username).sendKeys(usernameValue);
	    }

	    public void enterPassword(String passwordValue) {
	        driver.findElement(password).clear();
	        driver.findElement(password).sendKeys(passwordValue);
	    }

	    public void clickLogin() {
	        driver.findElement(loginButton).click();
	    }

	    public boolean isLoginSuccessful() {
	        return driver.getCurrentUrl().contains("inventory.html");
	    }
}
