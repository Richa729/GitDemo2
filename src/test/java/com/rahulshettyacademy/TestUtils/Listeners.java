package com.rahulshettyacademy.TestUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rahulshettyacademy.utils.AppiumUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Listeners extends AppiumUtils implements ITestListener{
	AppiumDriver driver;
	
	public Listeners(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS , "Test Pass");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test.fail(result.getThrowable());
		try
		{
		
		driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		try {
			
		test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver), null);
		
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}

}