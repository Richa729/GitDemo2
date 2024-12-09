package com.rahulshettyacademy.pageobjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

	public AndroidDriver driver;
	public CartPage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(id ="com.androidsample.generalstore:id/termsButton")
	private WebElement termsAndCondition;

	@AndroidFindBy(id ="android:id/button1")
	private WebElement closeButton;

	@AndroidFindBy(xpath ="//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
	private WebElement checkBox;

	@AndroidFindBy(id ="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;

	@AndroidFindBy(id ="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement amountLabel;

	@AndroidFindBy(id ="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;


	public double getProductSum()
	{

		int count = productList.size();
		double totalsum =0;
		for(int i = 0 ;i < count ; i++ ) 
		{
			String amount = productList.get(i).getText();
			Double price = Double.parseDouble((amount).substring(1));
			totalsum= totalsum + price ;

		}
		return totalsum;
	}

	public Double getTotalAmountDisplayed()
	{
		String displaySum= amountLabel.getText();
		return getFormattedAount(displaySum);


	}
	public void acceptTermAndConditions()
	{

		longPressAction(termsAndCondition);
		closeButton.click();

	}

	public void submitOrder()
	{

		checkBox.click();
		proceed.click();


	}

}
