package com.stf.presentation.action;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stf.presentation.datamanager.ErrorPageDM;

@Named
@RequestScoped
public class ErrorPageAction extends BaseController {

	@Inject
	ErrorPageDM errorPageDM;

	/**
	 * Dumb method for trigger {@link PostConstruct} on page
	 * 
	 * @return
	 */
	public String getInit() {
		return "";
	}

	/**
	 * Init Method
	 */
	@PostConstruct
	public void init() {

	}

}
