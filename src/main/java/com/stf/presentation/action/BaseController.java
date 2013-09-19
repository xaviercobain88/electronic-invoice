package com.stf.presentation.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stf.persistence.util.ComboBoxModel;
import com.stf.util.BaseUtils;
import com.stf.util.LabelsConstants;
import com.stf.util.MessagesConstants;
import com.stf.util.ParametersConstants;

//import ec.com.superleague.persistence.entity.ComboBoxModel;

public abstract class BaseController extends BaseUtils {

	protected String getUsername() {
		if (getServletRequest().getUserPrincipal() != null) {
			return getServletRequest().getUserPrincipal().getName();
		}
		return null;
	}

	protected void addErrorMessage(final String resumen) {
		FacesMessage message = new FacesMessage(resumen);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addSpecificErrorMessage(final String resumen, String clientId) {
		FacesMessage message = new FacesMessage(resumen);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}

	protected void addErrorMessageBundle(final String resumenKey) {
		addErrorMessage(getMessageFromResourceBundle(resumenKey));
	}

	protected void addErrorMessage(final String resumen, final String detalle) {
		FacesMessage message = new FacesMessage(resumen, detalle);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addErrorMessageBundle(final String resumenKey,
			final String detalleKey) {
		addErrorMessage(getMessageFromResourceBundle(resumenKey),
				getMessageFromResourceBundle(detalleKey));
	}

	protected void addInfoMessage(final String resumen) {
		FacesMessage message = new FacesMessage(resumen);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addInfoMessageBundle(final String resumenKey) {
		addInfoMessage(getMessageFromResourceBundle(resumenKey));
	}

	protected void addInfoMessage(final String resumen, final String detalle) {
		FacesMessage message = new FacesMessage(resumen, detalle);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addInfoMessageBundle(final String resumenKey,
			final String detalleKey) {
		addInfoMessage(getMessageFromResourceBundle(resumenKey),
				getMessageFromResourceBundle(detalleKey));
	}

	protected void addWarnMessage(final String resumen) {
		FacesMessage message = new FacesMessage(resumen);
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addWarnMessageBundle(final String resumenKey) {
		addWarnMessage(getMessageFromResourceBundle(resumenKey));
	}

	protected void addWarnMessage(final String resumen, final String detalle) {
		FacesMessage message = new FacesMessage(resumen, detalle);
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addWarnMessageBundle(final String resumenKey,
			final String detalleKey) {
		addWarnMessage(getMessageFromResourceBundle(resumenKey),
				getMessageFromResourceBundle(detalleKey));
	}

	public boolean isExistErrors() {
		Severity maximunSeverity = getFacesContext().getMaximumSeverity();
		if (FacesMessage.SEVERITY_ERROR.equals(maximunSeverity)) {
			return true;
		}
		return false;
	}

	protected static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected Object getRequestParameter(final String parameterName) {
		return getFacesContext().getExternalContext().getRequestParameterMap()
				.get(parameterName);
	}

	protected String getRemoteUser() {
		String username = FacesContext.getCurrentInstance()
				.getExternalContext().getRemoteUser();
		return username;
	}

	protected HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	protected ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

	}

	protected HttpServletResponse getServletResponse() {
		return (HttpServletResponse) getFacesContext().getExternalContext()
				.getResponse();
	}

	protected HttpServletRequest getServletRequest() {
		return (HttpServletRequest) getFacesContext().getExternalContext()
				.getRequest();
	}

	protected Map<String, Object> getSessionMap() {
		return getFacesContext().getExternalContext().getSessionMap();
	}

	protected boolean isNumeric(final String value) {
		try {
			Long.valueOf(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String getContextPath() {
		return getServletContext().getContextPath();
	}

	public String getMessageFromResourceBundle(String key) {
		return getFacesContext().getApplication()
				.getResourceBundle(getFacesContext(), MessagesConstants.NAME)
				.getString(key);
	}

	public String getParameterFromResourceBundle(String key) {
		return getFacesContext().getApplication()
				.getResourceBundle(getFacesContext(), ParametersConstants.NAME)
				.getString(key);
	}

	public String getLabelFromResourceBundle(String key) {
		return getFacesContext().getApplication()
				.getResourceBundle(getFacesContext(), LabelsConstants.NAME)
				.getString(key);
	}

	public Locale getDefaultLocale() {
		return new Locale(
				getParameterFromResourceBundle(ParametersConstants.LOCALE_LANGUAGE),
				getParameterFromResourceBundle(ParametersConstants.LOCALE_COUNTRY));
	}

	protected List<SelectItem> getSelectItems(
			List<? extends ComboBoxModel> lista, boolean itemSeleccione) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (itemSeleccione) {
			items.add(new SelectItem(
					null,
					getLabelFromResourceBundle(LabelsConstants.COMBOBOX_SELECCIONE)));
		}
		if (lista != null) {
			for (ComboBoxModel item : lista) {
				items.add(new SelectItem(item.getComboBoxValue(), item
						.getComboBoxLabel()));
			}
		}
		return items;
	}

	

	
	

}