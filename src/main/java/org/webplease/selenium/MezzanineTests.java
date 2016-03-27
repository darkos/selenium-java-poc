package org.webplease.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MezzanineTests {

	private String baseUrl = "http://www.webplease.org";
	private String baseUrlAdmin = "http://www.webplease.org/admin/login/?next=/admin/";
	private WebDriver browser;
	
	public static void main(String[] args) {
		MezzanineTests tests = new MezzanineTests();		
		tests.runTests();	
	}
	
	public MezzanineTests() {
//		this.browser = new FirefoxDriver();
		this.browser = getFirefoxRemoteDriver();
//		this.browser = getChromeRemoteDriver();
	}
	
	public RemoteWebDriver getFirefoxRemoteDriver() {
		RemoteWebDriver driver = null;
		URL url = null;
		DesiredCapabilities cap = new DesiredCapabilities().firefox();
		cap.setBrowserName("firefox");
		cap.setPlatform(Platform.LINUX);
		try {
			url = new URL("http://159.203.75.45:5555/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver = new RemoteWebDriver(url, cap);
		return driver;
	}
	
	public RemoteWebDriver getChromeRemoteDriver() {
		RemoteWebDriver driver = null;
		URL url = null;
		DesiredCapabilities cap = new DesiredCapabilities().chrome();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.LINUX);
		try {
			url = new URL("http://159.203.75.45:5556/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver = new RemoteWebDriver(url, cap);
		return driver;
	}
	
	public void runTests() {
//		logInTest();
//		addUsers();
		deleteUsers();
//		browser.close();
	}
	
	public void logInTest() {
		LoginTest loginTest = new LoginTest(this.browser);
		loginTest.logInToMezzanineAsAdmin(baseUrlAdmin, "admin", "admintest");
		loginTest.logout();
	}
	
	public void addUsers() {
		LoginTest loginTest = new LoginTest(this.browser);
		loginTest.logInToMezzanineAsAdmin(baseUrlAdmin, "admin", "admintest");
		loginTest.gotoUsersPage();
		loginTest.addUser("darko", "darkotest", "Darko", "Stefanovic", "stefanovic.darko@gmail.com");
		loginTest.addUser("webplease", "webpleasetest", "Web", "Please", "webplease@gmail.com");
		loginTest.logout();
	}
	
	public void deleteUsers() {
		LoginTest loginTest = new LoginTest(this.browser);
		loginTest.logInToMezzanineAsAdmin(baseUrlAdmin, "admin", "admintest");
		loginTest.gotoUsersPage();
		loginTest.deleteUser("darko");
		loginTest.deleteUser("webplease");
		loginTest.logout();
	}

}
