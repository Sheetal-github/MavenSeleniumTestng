package com.gohighlevel.web.selenium.pages;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gohighlevel.web.selenium.dataProviders.DataLoader;

public class BasePage implements DataLoader {
	private WebDriver driver;

	protected String browser;

	protected int webDriverTimeout;

	protected String baseUrl;

	GenericPage genericPage;

	public BasePage() {
		init();
		load();

	}

	public void init() {
		browser = getDataFromProperties.getProperty("browser");
		webDriverTimeout = Integer.parseInt(getDataFromProperties.getProperty("webDriverTimeout"));
		baseUrl = getDataFromProperties.getProperty("url");

		/* No need to re-initiate */
		if (getDriver() == null) {
			setDriver_BasePage();
		}
	}

	/**
	 * Set driver
	 */
	public void setDriver_BasePage() {

		driver = initiateWebDriver_BasePage();

		if (driver == null)
			System.exit(-1);

		initDriverProperties_BasePage();
	}

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Initiate WebDriver
	 */
	private WebDriver initiateWebDriver_BasePage() {

		WebDriver driver = null;
		if (browser.equalsIgnoreCase("FIREFOX")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("CHROME")) {
			String os = System.getProperty("os.name");
			System.out.println("Operating system:\t" + os);
			String chromeDriverExe = "";
			if (os.toLowerCase().contains("windows")) {
				chromeDriverExe = "chromedriver.exe";
			}
			System.setProperty("webdriver.chrome.driver", chromeDriverExe);
			driver = new ChromeDriver();

		} else {
			driver = new FirefoxDriver();
		}
		return driver;
	}

	/**
	 * Initiate all driver properties
	 */
	private void initDriverProperties_BasePage() {
		getDriver().manage().timeouts().implicitlyWait(webDriverTimeout, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();

	}

	/**
	 * load url
	 */
	public void load() {
		getDriver().get(baseUrl);
	}

	public GenericPage initGenericPage() {
		this.genericPage = new GenericPage(this).initPage();
		return genericPage;
	}

	/**
	 * returns a WebElement if found else returns null. identifierType ->
	 * className/cssSelector/id/linkText/name/partialLinkText/tagName/xpath.
	 * 
	 * @param elementIdentifier
	 * @param identifierType
	 * @return WebElement
	 */
	public WebElement getWebElement_BasePage(String elementIdentifier, String identifierType)
			throws NoSuchElementException, StaleElementReferenceException {
		if (identifierType.equalsIgnoreCase("className")) {
			return getDriver().findElement(By.className(elementIdentifier));
		} else if (identifierType.equalsIgnoreCase("cssSelector")) {
			return getDriver().findElement(By.cssSelector(elementIdentifier));
		} else if (identifierType.equalsIgnoreCase("id")) {
			System.out.println("****Testing" + elementIdentifier + ":" + identifierType);
			return getDriver().findElement(By.id(elementIdentifier));
		} else if (identifierType.equalsIgnoreCase("linkText")) {
			return getDriver().findElement(By.linkText(elementIdentifier));
		} else if (identifierType.equalsIgnoreCase("name")) {
			return getDriver().findElement(By.name(elementIdentifier));
		} else if (identifierType.equalsIgnoreCase("partialLinkText")) {
			return getDriver().findElement(By.partialLinkText(elementIdentifier));
		} else if (identifierType.equalsIgnoreCase("tagName")) {
			return getDriver().findElement(By.tagName(elementIdentifier));
		} else if (identifierType.equalsIgnoreCase("xpath")) {
			return getDriver().findElement(By.xpath(elementIdentifier));

		} else {
			return null;
		}
	}

	/**
	 * Wait for a WebElement to load
	 * 
	 */
	public WebElement wait(String elementIdentifier, String identifierType) {
		this.waitForElementToLoad_BasePage(elementIdentifier, identifierType, "40");
		return this.getWebElement_BasePage(elementIdentifier, identifierType);
	}

	/**
	 * wait for elements to load.
	 * 
	 */
	public void waitForElementToLoad_BasePage(final String elementIdentifier, final String identifierType,
			String timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(timeOutInSeconds));
		WebElement element = wait
				.until(ExpectedConditions.visibilityOf(getWebElement_BasePage(elementIdentifier, identifierType)));

	}

	/**
	 * wait for web element to load.
	 * 
	 */
	public void waitForWebElementToDisplay_BasePage(final WebElement element, String timeOutInSeconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Integer.parseInt(timeOutInSeconds))).pollingEvery(Duration.ofSeconds(1))
				.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				return (element.isDisplayed());
			}
		});
	}

	public void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	 /**
     * Switch to Next Tab
     * 
     */
    public String switchToNextTab_BasePage() {
    	ArrayList<String> tabs2 = new ArrayList<String> (this.driver.getWindowHandles());
	    this.driver.switchTo().window(tabs2.get(1));
	    return this.driver.getCurrentUrl();
    }

}
