package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;

import base.BaseTest;
import utils.ExtentReporterNG;

public class TestListener extends BaseTest implements ITestListener {
	
	ExtentReports extent= ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> test=new ThreadLocal();
	
	@Override
	public void onStart(ITestContext context) {

		log.info("Test Execution Started");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
	//log.info("Test Started: ");
		ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//log.info("Test Passed: "+result.getMethod().getMethodName());
		test.get().pass("TestPassed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {

	    //log.error("Test Failed: " + result.getMethod().getMethodName());
		test.get().fail(result.getThrowable());

	    String testCaseName = result.getMethod().getMethodName();

	    try {
	        driver = (WebDriver) result.getTestClass()
	                .getRealClass()
	                .getField("driver")
	                .get(result.getInstance());

	        log.info("Driver fetched successfully");

	    } catch (Exception e) {
	        log.error("Driver fetch FAILED");
	        e.printStackTrace();
	    }

	    try {
	        String path= getScreenshot(testCaseName, driver);
	        log.info("Screenshot captured");
	        
	        test.get().addScreenCaptureFromPath(path,testCaseName);

	    } catch (Exception e) {
	        log.error("Screenshot FAILED");
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		//log.info("Test Skipped: "+result.getMethod().getMethodName());
		test.get().skip("Test Skipped");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		//log.info("Test Execution Finished");
		extent.flush();
	}

}
