package Test;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;



import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class TestAppium {

	private static RemoteWebDriver driver;
	private static Properties capabilitiesValues;
	private boolean portraitmodecheck = true;
	
	
	@Test
	public void setUp() throws Exception {

		DesiredCapabilities capabilities=DesiredCapabilities.android();
		capabilitiesValues  = new Properties();
		capabilitiesValues.load(new FileInputStream("testdata/capabilities.properties"));
		capabilities.setCapability(CapabilityType.BROWSER_NAME,"");

		// Set android deviceName desired capability. Set it Android Emulator.
		capabilities.setCapability("deviceName", "emulator-5554");

		// Set android platformVersion desired capability. Set your emulator's
		// android version.
		capabilities.setCapability("platformVersion", "5.1");

		// Set android platformName desired capability. It's Android in our case
		// here.
		capabilities.setCapability("platformName", "Android");

		// Set android appPackage desired capability. It is
		// om.kayak.android for Kayak application.
		// Set your application's appPackage if you are using any other app.
		capabilities.setCapability("appPackage", "com.kayak.android");

		// Set android appActivity desired capability. It is
		// com.facebook.FacebookActivity for calculator application.
		// Set your application's appPackage if you are using any other app.
		capabilities.setCapability("appActivity", "com.facebook.FacebookActivity");

		// Created object of RemoteWebDriver will all set capabilities.
		// Set appium server address and port number in URL string.
		// It will launch calculator app in emulator.
	    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		//closeButton();
		clickLooksGoodBtn();
		checkIfHomeScreen();
		
		System.out.println("*--Current screen orientation Is : " + ((AndroidDriver) driver).getOrientation());
		checkIfAllElementsExists();
		((AndroidDriver) driver).rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
		System.out.println("*--Current screen orientation Is : " + ((AndroidDriver) driver).getOrientation());
		checkIfAllElementsExists();
		
		driver.quit();

	}

	private void checkIfAllElementsExists() throws NoSuchElementException {
		HashMap<String, Boolean> elements = new HashMap<String, Boolean>();
		
		String drawerlayout = "com.kayak.android:id/navigation_drawer_activity_drawer_layout";
		WebElement drawerElement = driver.findElementById(drawerlayout);
		if(drawerElement.isDisplayed()){
			elements.put("navigation_drawer_activity_drawer_layout", true);
		} else {
			elements.put("navigation_drawer_activity_drawer_layout", false);
		}
		
		String toolbarlayout = "com.kayak.android:id/toolbar";
		WebElement toolbarElement = driver.findElementById(toolbarlayout);
		if(toolbarElement.isDisplayed()){
			elements.put("toolbar", true);
		} else {
			elements.put("toolbar", false);
		}
		
		String destinationlayout = "com.kayak.android:id/destinationLayout";
		WebElement destinationElement = driver.findElementById(toolbarlayout);
		if(toolbarElement.isDisplayed()){
			elements.put("destinationLayout", true);
		} else {
			elements.put("destinationLayout", false);
		}
		
		String dateslayout = "com.kayak.android:id/datesRow";
		WebElement datesElement = driver.findElementById(dateslayout);
		if(toolbarElement.isDisplayed()){
			elements.put("datesRow", true);
		} else {
			elements.put("datesRow", false);
		}
		
		String roomGuestslayout = "com.kayak.android:id/roomsGuestsRow";
		WebElement roomGuestsElement = driver.findElementById(dateslayout);
		if(toolbarElement.isDisplayed()){
			elements.put("roomsGuestsRow", true);
		} else {
			elements.put("roomsGuestsRow", false);
		}
		
		String searchButtonlayout = "com.kayak.android:id/searchButton";
		WebElement searchButtonElement = driver.findElementById(searchButtonlayout);
		if(toolbarElement.isDisplayed()){
			elements.put(searchButtonElement.getText(), true);
		} else {
			elements.put(searchButtonElement.getText(), false);
		}
		//System.out.println(elements);
		for(Entry<String, Boolean> entry: elements.entrySet()) {
		    System.out.println(entry.getKey() + " : " + entry.getValue());
		    if(entry.getValue() == false){
		    	portraitmodecheck  = false;
		    }
		}
		System.out.println();
			elements.clear();
	}
	
	
	private void clickLooksGoodBtn() throws NoSuchElementException{
		String looksGoodBtn = "android:id/button1";
		WebElement looksGoodView = driver.findElement((By.id(looksGoodBtn)));
		if(looksGoodView.isDisplayed()){
			System.out.println("LooksGood present");
			looksGoodView.click();
		} else{
			System.out.println("LooksGood absent");
		}
	}
	
	

	

	private void checkIfHomeScreen() throws NoSuchElementException {
		String homeScreenToolbar = "com.kayak.android:id/toolbar";
		WebElement homeToolBar = driver.findElement((By.id(homeScreenToolbar)));
		if(homeToolBar.isDisplayed()){
			System.out.println("HomeScreen present");
		} else{
			System.out.println("HomeScreen absent");
		}		
	}

	private void closeButton() {
		String crossimgbtn = "com.kayak.android:id/closeBtn";
		WebElement crsbtn = null;
		try {
			crsbtn = driver.findElement((By.id(crossimgbtn)));
		} catch (NoSuchElementException nse){
			System.out.println("No close button found. Throwing ==> "+nse);
		}
		
		if(crsbtn.isDisplayed()){
			System.out.println("Crossbtn present");
			crsbtn.click();
			} else{
				System.out.println("Crossbtn absent");
			}
		}
}