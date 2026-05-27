package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

//constructor
	public LandingPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	By btn_myAcc = By.xpath("//span[text()='My Account']");
	By btn_register = By.xpath("//a[text()='Register']");

	By btn_login = By.xpath("//a[normalize-space()='Login']");

	// Action Methods

	public void clickMyAccount() {
		driver.findElement(btn_myAcc).click();
	}

	public void clickRegister() {
		driver.findElement(btn_register).click();
	}

	public void clickLogin() {
		driver.findElement(btn_login).click();
	}

}
