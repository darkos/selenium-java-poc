package org.webplease.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
	
	WebDriver browser;
	
	public LoginTest(WebDriver driver) {
		this.browser = driver;
	}
	
	public void logInToMezzanineAsAdmin(String adminUrl, String userName, String password) {
		browser.get(adminUrl);
		browser.manage().window().maximize();
		WebElement usernameText = browser.findElement(By.id("id_username"));
		WebElement passwordText = browser.findElement(By.id("id_password"));
		WebElement submitButton = browser.findElement(By.xpath("//input[@type='submit']"));
		usernameText.sendKeys(userName);
		passwordText.sendKeys(password);
		submitButton.click();
	}
	
	public void logout() {
		WebElement logOutButton = browser.findElement(By.linkText("Log out"));
		logOutButton.click();
	}
	
	public void gotoUsersPage() {
		WebDriverWait wait = new WebDriverWait(browser, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Users")));
		WebElement usersPageLink = browser.findElement(By.linkText("Users"));
		usersPageLink.click();
	}
	
	public void addUser(String userName, String password, String firstName, String lastName, String emailAddress) {
		WebElement addUserLink = browser.findElement(By.linkText("Add user"));
		addUserLink.click();
		WebElement usernameText = browser.findElement(By.id("id_username"));
		WebElement passwordText1 = browser.findElement(By.id("id_password1"));
		WebElement passwordText2 = browser.findElement(By.id("id_password2"));
		WebElement save = browser.findElement(By.name("_save"));
		
		usernameText.sendKeys(userName);
		passwordText1.sendKeys(password);
		passwordText2.sendKeys(password);
		save.click();
		
		WebElement fName = browser.findElement(By.id("id_first_name"));
		WebElement lName = browser.findElement(By.id("id_last_name"));
		WebElement email = browser.findElement(By.id("id_email"));
		
		fName.sendKeys(firstName);
		lName.sendKeys(lastName);
		email.sendKeys(emailAddress);
		
		save = browser.findElement(By.name("_save"));
		save.click();
	}
	
	public void deleteUser(String userName) {
		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(userName)));
//		WebElement userLink = browser.findElement(By.linkText(userName));
//		userLink.click();
		
//		WebElement element = driver.findElement(By("element_path"));
		WebElement userLink = browser.findElement(By.linkText(userName));
//		userLink.sendKeys(Keys.RETURN); 
//		System.out.println(userLink.getLocation());
//		System.out.println(userLink.getSize());
//		System.out.println(userLink.getAttribute("outerHTML"));
//		Dimension d = userLink.getSize();
//		int xOffset = d.getWidth()/2;
//		int yOffset = d.getHeight()/2;
		
		Actions actions = new Actions(browser);
//		actions.moveToElement(userLink, xOffset, yOffset).click().perform();
//		actions.moveToElement(userLink).click().perform();
		actions.moveToElement(userLink).sendKeys(Keys.RETURN).perform();
//		userLink.sendKeys(Keys.RETURN); 
		
		
//		Actions builder = new Actions(browser);   
//		builder.moveToElement(userLink, 10, 25).click().build().perform();
		
		
		WebDriverWait wait = new WebDriverWait(browser, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Delete")));
		WebElement deleteLink = browser.findElement(By.linkText("Delete"));
//		deleteLink.click();
		deleteLink.sendKeys(Keys.RETURN);
		WebElement iAmSure = browser.findElement(By.xpath("//input[@type='submit']"));
		iAmSure.click();
	}
	
}
