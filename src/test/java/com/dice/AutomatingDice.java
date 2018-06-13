package com.dice;

import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomatingDice {

	public static void main(String[] args) {
		// set up chrome driver path
		WebDriverManager.chromedriver().setup();
		
		// invoke selenium webDriver
		WebDriver driver = new ChromeDriver();
		
		// full screen
		driver.manage().window().maximize();
		
		// set wait universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Step1.  Launch browser and navigate to http://dice.com
		String url = "https://dice.com";
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Pass, Dice.com homeoage successfuly loaded");
		}else {
			System.out.println("Fail: ");
			System.out.println("Expected: "+ expectedTitle);
			System.out.println("Actual: "+ actualTitle);
			throw new RuntimeException("Fail while testing");
		}
		
		String keyword = "java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "15205";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		//ensure count is more than 0
		
		int countResult = Integer.parseInt(count.replaceAll(",", ""));
		
		if(countResult>0) {
			System.out.println("Keyword: "+ keyword+ " search returned "+ countResult + "results in "+ location);
		}
		
		
		driver.close();
		
		
		
		
	}
}
