package testBase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumGrid {

	public static void main(String[] args) throws MalformedURLException {
		String hubURL = "http://localhost:4444";
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setPlatform(Platform.WIN11);
		cap.setBrowserName("chrome");
		
		WebDriver driver = new RemoteWebDriver(new URL(hubURL), cap);
		
		driver.get("https://www.google.com");
		System.out.println(driver.getTitle());
		driver.quit();

	}

}
