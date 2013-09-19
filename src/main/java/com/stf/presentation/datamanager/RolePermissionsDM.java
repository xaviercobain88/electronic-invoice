package com.stf.presentation.datamanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 * @author xavier
 * 
 */
@Named
@SessionScoped
public class RolePermissionsDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SelectItem> roleSelectItems;
	private List<SelectItem> permissionSelectItems;
	private List<Integer> selectedPermissionsId;
	private Integer selectedRoleId;

	public List<SelectItem> getRoleSelectItems() {
		return roleSelectItems;
	}

	public void setRoleSelectItems(List<SelectItem> roleSelectItems) {
		this.roleSelectItems = roleSelectItems;
	}

	public List<SelectItem> getPermissionSelectItems() {
		return permissionSelectItems;
	}

	public void setPermissionSelectItems(List<SelectItem> permissionSelectItems) {
		this.permissionSelectItems = permissionSelectItems;
	}

	public List<Integer> getSelectedPermissionsId() {
		return selectedPermissionsId;
	}

	public void setSelectedPermissionsId(List<Integer> selectedPermissionsId) {
		this.selectedPermissionsId = selectedPermissionsId;
	}

	public Integer getSelectedRoleId() {
		return selectedRoleId;
	}

	public void setSelectedRoleId(Integer selectedRoleId) {
		this.selectedRoleId = selectedRoleId;
	}

}
