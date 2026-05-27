package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseTest;



public class ExtendReports implements ITestListener
{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repo;
	
	public void onStart(ITestContext context) {
		
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repo = "Execution_Report_" + timestamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+ repo);
		sparkReporter.config().setDocumentTitle("Dashboard--");
		sparkReporter.config().setReportName("Execution Summary");
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
	
		//String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", "Windows");
		
		//String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", "Chrome");
		
			
		System.out.println("Test Execution started..");
	}

	public void onTestStart(ITestResult result) {
		
		
		System.out.println("Test Method Execution satrted..");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS,result.getName());
		
		System.out.println("Test Method Execution Passed..");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL,result.getName());
		test.log(Status.FAIL,result.getThrowable());
		System.out.println("Test Method Execution Failed..");
		
		try {
		String imgPath = new BaseTest().captureScreen(result.getTestName());
		test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,result.getName());
		System.out.println("Test Method Execution Skipped..");
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		System.out.println("....Test Execution Finished.");
	}


}
