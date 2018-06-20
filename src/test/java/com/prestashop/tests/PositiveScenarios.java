package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.PhoneNumber;

import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.Address;

public class PositiveScenarios {

	WebDriver driver;
	Faker faker = new Faker();
	String email = faker.internet().emailAddress();
	String password = faker.internet().password();
	String name = faker.name().firstName();
	String lastName = faker.name().lastName();
	String addressS = faker.address().streetAddress();
	String addressC = faker.address().city();
    String phone= faker.phoneNumber().cellPhone();
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/");
		driver.findElement(By.className("login")).click();

	}
	@Test
	public void loginTest() throws InterruptedException {
		
		String winHandleBefore = driver.getWindowHandle();
        //creating account
        driver.findElement(By.id("email_create")).sendKeys(email);
        driver.findElement(By.id("SubmitCreate")).click();
        //popup window
        Thread.sleep(2000);
        for(String winHandle : driver.getWindowHandles()){
               driver.switchTo().window(winHandle);
            }
        driver.findElement(By.id("uniform-id_gender1")).click();
		driver.findElement(By.cssSelector("input[data-validate='isName']")).sendKeys(name);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.id("address1")).sendKeys(addressS);
		driver.findElement(By.id("city")).sendKeys(addressC);
		driver.findElement(By.id("id_state")).sendKeys("VA");
		driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).sendKeys(phone);
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys(email);
		driver.findElement(By.id("submitAccount")).click();
		driver.findElement(By.className("logout")).click();
		
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.id("SubmitLogin")).click();
		Assert.assertTrue(true, name+lastName);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		
	}
	

}
