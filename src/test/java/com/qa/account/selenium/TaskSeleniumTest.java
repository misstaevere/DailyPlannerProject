package com.qa.account.selenium;

import static org.junit.Assert.assertEquals;

import org.hibernate.engine.spi.ExtendedSelfDirtinessTracker;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TaskSeleniumTest {

	private RemoteWebDriver driver;
//	private static ExtentReports report;
//	
//	@BeforeClass // runs once
//	public void setUp() {
//		report = new ExtentReports();
//		ExtentHtmlReporter reporter = new ExtentHtmlReporter("test-output/html/extentReport.html");
//		reporter.config().setAutoCreateRelativePathMedia(true); // allows screenshots
//		report.attachReporter(reporter);
//	}

	@Before // runs several times
	public void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	@Test
	public void test() throws InterruptedException {

//		ExtentTest test = report.createTest("my test");

		driver.get("http://www.bing.com"); // goes to that website and maximises it
		WebElement search = driver.findElementById("sb_form_q");
		// search = driver.findElementByXPath(""); other options
		// search = driver.findElementByCssSelector("");
		search.sendKeys("turtles" + Keys.ENTER);
		Thread.sleep(1000L); // slows it down

		search = driver.findElementById("sb_form_q");
		assertEquals("turtles", search.getAttribute("value"));

		WebElement home = driver.findElementByXPath("//*[@id=\"sb_form\"]/a/h1");
		home.click();
		Thread.sleep(1000L);
	}

	@After
	public void tearDown() {
		this.driver.quit();
	}

}
