package com.stf.presentation.datamanager;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class RequestParametersDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pageInformationOwner;
	private Boolean read, write;

	public String getPageInformationOwner() {
		return pageInformationOwner;
	}

	public void setPageInformationOwner(String pageInformationOwner) {
		this.pageInformationOwner = pageInformationOwner;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Boolean getWrite() {
		return write;
	}

	public void setWrite(Boolean write) {
		this.write = write;
	}

}
