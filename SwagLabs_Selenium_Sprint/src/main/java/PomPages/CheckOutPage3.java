package PomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage3 {
	 WebDriver driver;
	    WebDriverWait wait;

	    public CheckOutPage3(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    By firstName = By.id("first-name");
	    By continueButton = By.id("continue");

	    By errorMessage = By.cssSelector("[data-test='error']");

	    public void clickContinue() {

	        wait.until(
	                ExpectedConditions.elementToBeClickable(continueButton)
	        ).click();
	    }

	    public boolean isCheckoutPageDisplayed() {

	        try {
	            return wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(firstName)
	            ).isDisplayed();

	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public boolean isErrorDisplayed() {

	        try {
	            return wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(errorMessage)
	            ).isDisplayed();

	        } catch (Exception e) {
	            return false;
	        }
	    }
	}


