package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeScenarios {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/");
	}

	@Test
	public void wrongCredentialsTest() {
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("mandaha0618@gmail.com" +Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("123456" +Keys.ENTER);
		driver.findElement(By.id("SubmitLogin")).click();
		String str1= driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		//Assert.assertTrue(true,"Authentication failed.");
		Assert.assertEquals(str1, ("Authentication failed."));
	}
	@Test
	public void invalidEmailTest() {
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("mandaha@ya." +Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("12345678" +Keys.ENTER);
		driver.findElement(By.id("SubmitLogin")).click();
		String str2 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
	
		Assert.assertEquals(str2, ("Invalid email address."));
	}
	@Test
	public void blankEmailTest() {
		driver.findElement(By.className("login")).click();
		
		driver.findElement(By.id("passwd")).sendKeys("12345678" +Keys.ENTER);
		driver.findElement(By.id("SubmitLogin")).click();
		String str3 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
	
		Assert.assertEquals(str3, ("An email address required."));
	}
	@Test 
	public void blankPasswordTest() {
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("mandaha0618@gmail.com" +Keys.ENTER);
		
		driver.findElement(By.id("SubmitLogin")).click();
		String str4 =driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		
		Assert.assertEquals(str4, ("Password is required."));
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		
	}
}
