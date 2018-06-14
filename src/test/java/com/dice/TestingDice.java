package com.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestingDice {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		
		FileReader reader = new FileReader("JobTitles.txt");
		BufferedReader bufReader = new BufferedReader(reader);
		//System.out.println(bufReader.readLine());
		
		List<String> list = new ArrayList<String>();
		
		for(int i=0; i<20;i++) {
			list.add(bufReader.readLine());
		}
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String url = "https://dice.com";
		driver.get(url);
		
		String location = "10170";
		List<String> newList = new ArrayList<String>();
		
		for(int i=0; i<list.size(); i++) {
			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(list.get(i));
			
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);
			
			driver.findElement(By.id("findTechJobs")).click();
			
			newList.add(list.get(i)+"-" + driver.findElement(By.id("posiCountId")).getText());
			
			driver.navigate().back();
			
		}
		
		driver.close();
		
		for (String string : newList) {
			System.out.println(string.toString()+ " positions available");
		}
		
		
		
		
		
		
		
		
	}
}
