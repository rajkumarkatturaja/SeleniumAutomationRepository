package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

	// constructor
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	// locators

	WebElement fname = driver.findElement(By.xpath("//input[@id='input-firstname']"));
	WebElement lname = driver.findElement(By.xpath("//input[@id='input-lastname']"));
	WebElement email = driver.findElement(By.xpath("//input[@id='input-email']"));
	WebElement phone = driver.findElement(By.xpath("//input[@id='input-telephone']"));
	WebElement pwd = driver.findElement(By.xpath("//input[@id='input-password']"));
	WebElement conpwd = driver.findElement(By.xpath("//input[@id='input-confirm']"));
	WebElement accept = driver.findElement(By.xpath("//input[@name='agree']"));
	WebElement conti = driver.findElement(By.xpath("//input[@value='Continue']"));

	// ActionMethods
	public void setFName(String first) {
		fname.sendKeys(first);
	}

	public void setLName(String last) {
		lname.sendKeys(last);
	}

	public void setEmail(String val) {
		email.sendKeys(val);
	}

	public void setPhone(String val) {
		phone.sendKeys(val);
	}

	public void setPassword(String val) {
		pwd.sendKeys(val);
	}

	public void setConPassword(String val) {
		conpwd.sendKeys(val);
	}

	public void clickAccept() {
		accept.click();
	}

	public void clickContinue() {
		conti.click();
	}

	public String getConfirmation() {
		WebElement msg = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']"));
		return msg.getText();

	}

	public String getUserName() {

		return email.getText();
	}

	public String getPwd() {

		return pwd.getText();
	}

}
