package com.gohighlevel.web.selenium.loader;

import com.gohighlevel.web.selenium.pages.BasePage;
import com.gohighlevel.web.selenium.pages.GenericPage;
import com.gohighlevel.web.selenium.pages.OptimizeAppointmentPage;

public interface PageLoader {

	// BasePage Object
	BasePage basePage = new BasePage();
	
	// GenericPage Object
    GenericPage genericPage = basePage.initGenericPage();
	
	//OptimizeAppointment object
	OptimizeAppointmentPage optAptPage =new OptimizeAppointmentPage(basePage,genericPage).initPage();
}