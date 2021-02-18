package com.gohighlevel.web.selenium.pages;

import java.util.List;

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

	public void clickAndSelectEditFromCalendarDropdown() {
		basePage.waitForWebElementToDisplay_BasePage(calendar_ellipsis, "5");
		calendar_ellipsis.click();
		basePage.waitForWebElementToDisplay_BasePage(calendar_edit, "5");
		calendar_edit.click();
		basePage.waitForWebElementToDisplay_BasePage(calendar_edit_title, "10");
		basePage.waitForWebElementToDisplay_BasePage(team_members.get(0),"10");
		// Assert.assertEquals(calendar_edit_title.getText(),"Edit Calendar");

	}

}
