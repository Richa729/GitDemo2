package com.rahulshettyacademy.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;
import com.rahulshettyacademy.pageobjects.android.FormPage;
import com.rahulshettyacademy.utils.AppiumUtils;

import dev.failsafe.internal.util.Durations;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends AppiumUtils {

	public BaseTest(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	public FormPage formPage ;
	
	@BeforeClass(alwaysRun = true)
	public void configAppium() throws URISyntaxException, IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rahulshettyacademy\\testData\\data.properties");
		String address = prop.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		prop.load(fis);
		prop.load(fis);
		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String androidDeviceName=prop.getProperty("AndroidDeviceName");
		service = startAppiumServer(address ,Integer.parseInt(port));

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(androidDeviceName);

		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\com\\rahulshettyacademy\\resource\\General-Store.apk");

		driver = new AndroidDriver(service.getUrl(),options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);


	}



	@AfterClass
	public void tearDown()
	{
		driver.quit();
		service.close();

	}

}
