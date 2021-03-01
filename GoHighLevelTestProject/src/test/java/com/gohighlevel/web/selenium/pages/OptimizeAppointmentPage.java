package com.gohighlevel.web.selenium.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.gohighlevel.web.selenium.loader.PageLoader;

public class OptimizeAppointmentPage implements PageLoader {

	@FindBy(className = "icon-settings-1")
	public WebElement navbar_settings;

	@FindBy(linkText = "Calendars")
	public WebElement section_calendar;

	@FindBy(xpath = "//span[contains(text(),'testCalen')]/following::span[@class='ellipsis']")
	public WebElement calendar_ellipsis;

	@FindBy(css = ".dropdown-menu > button")
	public WebElement calendar_edit;

	@FindBy(xpath = "(//h5[@class='modal-title'])[2]")
	public WebElement calendar_edit_title;

	@FindBy(css = ".section-team-members>.option--member >label")
	public List<WebElement> team_members;

	@FindBy(xpath = "//button[contains(text(),'/onboarding_call/test_automation')]")
	public WebElement calendar_link;

	@FindBy(className = "widgets--service-name")
	public WebElement calendar_widget_name;
	
	@FindBy(css="td[data-id='2021-3-8'] > div")
	public WebElement calendar_date;
	
	@FindBy(xpath="//span[text()='09:00 AM']")
	public WebElement calendar_time_slot;
	
	@FindBy(css="input[name='first_name']")
	public WebElement calendar_user_first_name;

	@FindBy(css="input[name='last_name']")
	public WebElement calendar_user_last_name;
	
	@FindBy(css="input[name='phone']")
	public WebElement calendar_user_phone_number;
	
	@FindBy(css="input[name='email']")
	public WebElement calendar_user_email;
	
	@FindBy(className="confirmation-message")
	public WebElement calendar_confirmation_message;
	
	OptimizeAppointmentPage optAptPage;

	BasePage basePage;

	GenericPage genericPage;

	public OptimizeAppointmentPage() {

	}

	// initialising page using pagefactory

	public OptimizeAppointmentPage(BasePage basePage, GenericPage genericPage) {
		optAptPage = PageFactory.initElements(basePage.getDriver(), OptimizeAppointmentPage.class);
		optAptPage.setBasePage(basePage, genericPage);
	}

	public void setBasePage(BasePage basePage, GenericPage genericPage) {
		this.basePage = basePage;
		this.genericPage = genericPage;
	}

	public OptimizeAppointmentPage initPage() {
		return (optAptPage);
	}
	
	public void clickOnCalendarsTab() {
		basePage.waitForWebElementToDisplay_BasePage(navbar_settings, "10");
		navbar_settings.click();
		basePage.waitForWebElementToDisplay_BasePage(section_calendar, "15");
		section_calendar.click();

	}

	public void clickOnCalendarsLink() {
		basePage.waitForWebElementToDisplay_BasePage(navbar_settings, "10");
		calendar_link.click();
		String calendarAfterURL = basePage.switchToNextTab_BasePage();
		Assert.assertTrue(calendarAfterURL.contains("/onboarding_call/test_automation"));
		basePage.waitForWebElementToDisplay_BasePage(calendar_widget_name, "15");
	}

	public void selectDateandTime() {
		basePage.waitForWebElementToDisplay_BasePage(calendar_date, "25");
		basePage.pause(7000);
		calendar_date.click();
		calendar_time_slot.click();
		basePage.getWebElement_BasePage("btn-schedule", "className").click();		
		basePage.waitForWebElementToDisplay_BasePage(calendar_user_first_name, "10");
	}

	

	public void enterUserInformation() {
		
		calendar_user_first_name.sendKeys("testUser");
		calendar_user_last_name.sendKeys("testLastName");
		calendar_user_phone_number.sendKeys("12345678901");
		calendar_user_email.sendKeys("test@gmail.com");
		basePage.getWebElement_BasePage("btn-schedule", "className").click();
		basePage.waitForWebElementToDisplay_BasePage(calendar_confirmation_message, "10");
		
	}

	// public void clickAndSelectEditFromCalendarDropdown() {
	// basePage.waitForWebElementToDisplay_BasePage(calendar_ellipsis, "5");
	// calendar_ellipsis.click();
	// basePage.waitForWebElementToDisplay_BasePage(calendar_edit, "5");
	// calendar_edit.click();
	// basePage.waitForWebElementToDisplay_BasePage(calendar_edit_title, "10");
	// basePage.waitForWebElementToDisplay_BasePage(team_members.get(0),"10");
	// // Assert.assertEquals(calendar_edit_title.getText(),"Edit Calendar");
	//
	// }

}
