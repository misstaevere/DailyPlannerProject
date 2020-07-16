package com.qa.account.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	private RemoteWebDriver driver;

	@Before
	public void init() {
		ChromeOptions opts = new ChromeOptions();
		opts.setHeadless(true);
		this.driver = new ChromeDriver(opts);
//		this.driver.manage().window().maximize();

	}

	@Test
	public void test() throws InterruptedException {

		final String USER = "user1";
		final String PASS = "pass1";

		final String TASK_NAME = "My Birthday";
		final String TASK_DATE = "13102020";
		final String TASK_TIME = "1010";
		final String TASK_LOCATION = "Bali";

		final String NEW_NAME = "Combined Birthday";
		final String NEW_DATE = "21102020";
		final String NEW_TIME = "1710";
		final String NEW_LOCATION = "Bora Bora";

// LOGIN PAGE SUCCESS
		this.driver.get("http://localhost:8181/");
		WebElement username = this.driver.findElementByXPath("//*[@id=\"username\"]");
		username.sendKeys(USER);
		WebElement password = this.driver.findElementByXPath("//*[@id=\"password\"]");
		password.sendKeys(PASS);

		WebElement submit = this.driver.findElementByXPath("//*[@id=\"createUser\"]");
		submit.click();

		Thread.sleep(2000);
		assertEquals("http://localhost:8181/HTML/planner.html", this.driver.getCurrentUrl());

// CREATE A TASK
		this.driver.get("http://localhost:8181/HTML/planner.html");

		WebElement taskName = this.driver.findElementByXPath("//*[@id=\"task_name\"]");
		taskName.sendKeys(TASK_NAME);
		WebElement taskDate = this.driver.findElementByXPath("//*[@id=\"task_date\"]");
		taskDate.sendKeys(TASK_DATE);
		WebElement taskTime = this.driver.findElementByXPath("//*[@id=\"task_time\"]");
		taskTime.sendKeys(TASK_TIME);
		WebElement taskLocation = this.driver.findElementByXPath("//*[@id=\"task_location\"]");
		taskLocation.sendKeys(TASK_LOCATION);

		WebElement addToCal = this.driver.findElementByXPath("//*[@id=\"createTask\"]");
		addToCal.click();

		Thread.sleep(2000);

// ACCEPT ALERT
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();

// VIEW ALL TASKS
		WebElement viewTasks = this.driver.findElementByXPath("//*[@id=\"getTask\"]");
		viewTasks.click();

		Thread.sleep(2000);

		assertTrue(this.driver.findElementByXPath("//*[@id=\"bigOutput\"]/div").isDisplayed());
		Thread.sleep(2000);

// CLICK ON TASK
		WebElement clickTask = this.driver.findElementByXPath("//*[@id=\"bigOutput\"]/div");
		clickTask.click();
		Thread.sleep(2000);

		assertTrue(this.driver.getCurrentUrl().contains("details"));
		Thread.sleep(2000);

// UPDATE TASK
		WebElement newName = this.driver.findElementByXPath("//*[@id=\"edit_name\"]");
		newName.sendKeys(NEW_NAME);
		WebElement newDate = this.driver.findElementByXPath("//*[@id=\"edit_date\"]");
		newDate.sendKeys(NEW_DATE);
		WebElement newTime = this.driver.findElementByXPath("//*[@id=\"edit_time\"]");
		newTime.sendKeys(NEW_TIME);
		WebElement newLocation = this.driver.findElementByXPath("//*[@id=\"edit_location\"]");
		newLocation.sendKeys(NEW_LOCATION);

		WebElement updateTaskBUtton = this.driver.findElementByXPath("//*[@id=\"editTask\"]");
		updateTaskBUtton.click();

		Thread.sleep(2000);

// ACCEPT ALERT
		WebDriverWait waitUpdate = new WebDriverWait(driver, 2);
		waitUpdate.until(ExpectedConditions.alertIsPresent());
		Alert alertUpdate = driver.switchTo().alert();
		alertUpdate.accept();

// REFRESH
		driver.navigate().to(driver.getCurrentUrl());
		driver.navigate().refresh();
		Thread.sleep(2000);

// GET ID
		String id = this.driver.getCurrentUrl().substring(this.driver.getCurrentUrl().length() - 1);

// DELETE
		WebElement deleteInput = this.driver.findElementByXPath("//*[@id=\"delete_input\"]");
		deleteInput.sendKeys(id);

		WebElement deleteButton = this.driver.findElementByXPath("//*[@id=\"delete_button\"]");
		deleteButton.click();

// ACCEPT ALERT
		WebDriverWait waitDelete = new WebDriverWait(driver, 2);
		waitDelete.until(ExpectedConditions.alertIsPresent());
		Alert alertDelete = driver.switchTo().alert();
		alertDelete.accept();

// REFRESH
		driver.navigate().to(driver.getCurrentUrl());
		driver.navigate().refresh();
		Thread.sleep(2000);

		assertTrue(this.driver.findElementByXPath("//*[@id=\"bigOutput\"]").isDisplayed());
		Thread.sleep(2000);

	}

	@After
	public void tearDown() {
		this.driver.quit();
	}

}
