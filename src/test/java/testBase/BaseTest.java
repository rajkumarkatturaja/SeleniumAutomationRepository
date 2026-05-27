package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@BeforeClass(groups = { "Smoke", "Regression", "Master", "DDT" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws InterruptedException, IOException {

		// Loading config.properties
		FileReader file = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);

		logger = LogManager.getLogger(this.getClass());

		if (prop.getProperty("execution_env").equals("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setPlatform(Platform.WIN11);

			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "edge":
				cap.setBrowserName("edge");
				break;
			default:
				cap.setBrowserName("chrome");
				break;

			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444"), cap);

		} else if (prop.getProperty("execution_env").equals("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				driver = new ChromeDriver();
				break;

			}
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		// driver.get("https://tutorialsninja.com/demo/");
		driver.get(prop.getProperty("appURL"));
		Thread.sleep(1000);

	}

	@AfterClass(groups = { "Smoke", "Regression", "Master", "DDT" })
	public void tearDown() {
		driver.close();
	}

	public String randomString() {
		String str = RandomStringUtils.randomAlphabetic(5);
		return str;

	}

	public static String captureScreen(String testName) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timeStamp + ".png";

		try {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File target = new File(path);

			source.renameTo(target);
			return path;

		} catch (Exception e) {
			throw new RuntimeException("Screenshot capture failed", e);
		}

	}

}
