package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String path=System.getProperty("user.dir")+"//reports//";
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Test Results"+System.currentTimeMillis());;
		reporter.config().setDocumentTitle("Web Automation for RSA");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Arch");
		return extent;
		
	}

}
