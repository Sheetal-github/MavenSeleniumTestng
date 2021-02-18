package com.gohighlevel.web.selenium.tests.Calendar;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.gohighlevel.web.selenium.loader.PageLoader;

public class OptimizeAppointment implements PageLoader {
	@BeforeClass
	public void beforeClass() {
		genericPage.goToHomePage_BasePage();
		optAptPage.clickOnCalendarsTab();
	}

	@Test
	public void verifyEditCalendarPageOpensUp() {

		optAptPage.clickAndSelectEditFromCalendarDropdown();
		Assert.assertTrue(optAptPage.calendar_edit_title.getText().contains("Edit Calendar"),
				"Edit calendar title is not correct");
	}

	@Test
	public void verifyTeamMembersInCalendar() {
		List<WebElement> teamMemberList = optAptPage.team_members;

		List<String> expectedTeamMemberList = new ArrayList<String>();
		expectedTeamMemberList.add("Sheetal Kapoor");
		expectedTeamMemberList.add("Tester 1");
		expectedTeamMemberList.add("Tester 2");

		List<String> actualteamMemberNames = new ArrayList<String>();
		for (WebElement teamMember : teamMemberList) {
			actualteamMemberNames.add(teamMember.getText().trim());
			System.out.println(teamMember.getText());
		}
		Assert.assertEquals(actualteamMemberNames, expectedTeamMemberList);
	}

	@AfterClass
	public void tearDown() {
		basePage.getDriver().quit();
	}

}
