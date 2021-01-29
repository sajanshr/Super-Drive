package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	//test for trying to access pages other than login and signup for non user
	public void restrictUnauthorized(){
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	//test for sigining up and loggin in
	public void signUpLogin(){
		driver.get("http://localhost:" + this.port + "/signup");
		driver.findElement(By.id("inputFirstName")).sendKeys("Sam");
		driver.findElement(By.id("inputLastName")).sendKeys("Vu");
		driver.findElement(By.id("inputUsername")).sendKeys("sam");
		driver.findElement(By.id("inputPassword")).sendKeys("sam123");
		driver.findElement(By.id("submit-button")).click();
		driver.get("http://localhost:" + this.port + "/login");
		driver.findElement(By.id("inputUsername")).sendKeys("sam");
		driver.findElement(By.id("inputPassword")).sendKeys("sam123");
		driver.findElement(By.id("submit-button")).click();
		Assertions.assertEquals("Home", driver.getTitle());






	}

	@Test
	//test for logout
	public void Logout(){
		signUpLogin();
		driver.findElement(By.id("logout-button")).click();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

	}


	//Test for creating note and verifying it is displayed
	@Test public void createNoteTest(){
		signUpLogin();
		driver.findElement(By.id("nav-notes-tab")).click();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addNoteButton")));
		driver.findElement(By.id("addNoteButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		driver.findElement(By.id("note-title")).sendKeys("noteTest");
		driver.findElement(By.id("note-description")).sendKeys("note Description Test");
		driver.findElement(By.id("noteModalSubmitButton")).click();
		driver.findElement(By.linkText("here")).click();
		WebElement notesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
		notesTab.click();
		WebElement body = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
		String bodyText = body.getText();

		Assertions.assertEquals(true, bodyText.contains("noteTest"));





	}



}
