package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

	// constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// locators
	WebElement email = driver.findElement(By.xpath("//input[@id='input-email']"));
	WebElement pwd = driver.findElement(By.xpath("//input[@id='input-password']"));
	WebElement btn_login = driver.findElement(By.xpath("//input[@value='Login']"));

	// Action Methods
	public void setEmail(String val) {
		email.sendKeys(val);
	}

	public void setPassword(String val) {
		pwd.sendKeys(val);
	}

	public void clickLoginButton() {
		btn_login.click();
	}

}