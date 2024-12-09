package com.rahulshettyacademy;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rahulshettyacademy.TestUtils.BaseTest;
import com.rahulshettyacademy.pageobjects.android.CartPage;
import com.rahulshettyacademy.pageobjects.android.FormPage;
import com.rahulshettyacademy.pageobjects.android.ProductCatalogue;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_tc_4_Hybrid extends BaseTest {

	public eCommerce_tc_4_Hybrid(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Test(dataProvider= "getData" , groups = {"Smoke"})
	public void fillForm(HashMap<String , String> input) throws Exception
	{
		//form page
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get(input.get("country")));	

		//product catalogue
		ProductCatalogue productCatalogue = formPage.submitForm();	
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);

		//cart page
		CartPage cartPage  = productCatalogue.goToCartpage();
		Thread.sleep(3000);
		double totalSum = cartPage.getProductSum();
		double displayFormattedAmount =cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedAmount);

		cartPage.acceptTermAndConditions();
		cartPage.submitOrder();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void preSetup()
	{
		formPage.setActivity();
	}
	@DataProvider
	public Object[][] getData() throws Exception
	{
		List<HashMap<String , String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\com\\rahulshettyacademy\\testdata\\eCommerce.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}

	
	

}
