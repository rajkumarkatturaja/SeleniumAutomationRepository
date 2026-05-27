package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testBase.BaseTest;

public class MyAccountPage extends BasePage {

	// constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	// locators
	WebElement msg = driver.findElement(By.xpath("//h2[normalize-space()='My Orders']"));
	WebElement logout = driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']"));

	// Action Methods

	public String getConfirmation() {

		return msg.getText();

	}
	
	public void clickLogout()
	{
		logout.click();
	}

}
