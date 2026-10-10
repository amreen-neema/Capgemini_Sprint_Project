package PomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCartPage {

	 WebDriver driver;
	    WebDriverWait wait;

	    public AddToCartPage(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }

	    By checkoutButton = By.id("checkout");

	    public void clickCheckout() {

	        wait.until(
	                ExpectedConditions.elementToBeClickable(checkoutButton)
	        ).click();

	        System.out.println("Checkout button clicked");
	    }
}
