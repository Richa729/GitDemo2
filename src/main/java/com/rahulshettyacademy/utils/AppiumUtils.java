package com.rahulshettyacademy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	public AppiumDriverLocalService service;
	AppiumDriver driver;
	public AppiumUtils(AndroidDriver driver)
	{
		this.driver = driver;
	}
	
	public Double getFormattedAount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	public void waitForElementToAppear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(ele, "text", "Cart"));
	}

		public List<HashMap<String, String>> getJsonDataToMap(String jsonPath) throws IOException {
			//read json to string
			String jsonContent=FileUtils.readFileToString(new File(jsonPath),StandardCharsets.UTF_8);
			//convert string to hasmap dependency - jackson data bin
			
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
			return data;
		

}
		
		public AppiumDriverLocalService startAppiumServer(String ipAddress , int port)
		{
			service =new AppiumServiceBuilder()
					.withAppiumJS(new File("C:\\Users\\richa\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					.withIPAddress(ipAddress).usingPort(port).build(); 
			   service.start();
			   return service;
		}
		public String getScreenshotPath(String testcaseName , AppiumDriver driver) throws IOException
		{
		 File source = driver.getScreenshotAs(OutputType.FILE);
		 String destinationFile = System.getProperty("user.dir")+"\\reports\\"+ testcaseName + ".png";
		 FileUtils.copyFile(source, new File(destinationFile));
		 return destinationFile;
		}
}
