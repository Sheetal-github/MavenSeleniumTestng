package com.gohighlevel.web.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gohighlevel.web.selenium.dataProviders.DataLoader;

public class GenericPage implements DataLoader {

	public GenericPage genericPage;

	public BasePage basePage;

	/**************************
	 * Login Page params
	 */

	@FindBy(id = "email")
	public WebElement login_username;

	@FindBy(id = "password")
	public WebElement login_password;

	@FindBy(tagName = "button")
	public WebElement submit_login;
	
	@FindBy(id = "dashboard")
	public WebElement after_login;

	public GenericPage() {
	}

	public GenericPage(BasePage basePage) {
		genericPage = PageFactory.initElements(basePage.getDriver(), GenericPage.class);
		genericPage.setBasePage(basePage);
	}

	public void setBasePage(BasePage basePage) {
		this.basePage = basePage;
	}

	public GenericPage initPage() {
		return (genericPage);
	}

	/**
	 * Log In to Gohighlevel
	 */
	public void login() {
	
		String userName = getDataFromProperties.getProperty("username");
		String password = getDataFromProperties.getProperty("password");
		
		login_username.clear();
		login_username.sendKeys(userName);
		login_password.clear();
		login_password.sendKeys(password);
		submit_login.click();
		//basePage.pause(5000);
		basePage.waitForWebElementToDisplay_BasePage(after_login, "30");
	}

	public void goToHomePage_BasePage() {
      if(!this.isLoggedIn()){
    	  this.login();
      }
	}

	/**
	 * Verify if user is already logged in
	 */
	
	public boolean isLoggedIn() {
		  try {
	            if (login_password.isDisplayed()) {
	                return false;
	            }
	        } catch (Exception e) {
	            return true;
	        }
	        return true;
	}

}
