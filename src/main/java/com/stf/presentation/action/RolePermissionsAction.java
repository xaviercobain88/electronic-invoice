package com.stf.presentation.action;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import com.stf.business.facade.PermissionFacade;
import com.stf.business.facade.RoleFacade;
import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;
import com.stf.persistence.enums.PermissionNameEnum;
import com.stf.presentation.datamanager.RolePermissionsDM;
import com.stf.security.AllowedPermission;
import com.stf.security.enums.PageInformationEnum;
import com.stf.util.MessagesConstants;

/**
 * @author xavier
 * 
 */
@Named
@RequestScoped
@URLMappings(mappings = {
		@URLMapping(id = "rolePermissions1", pattern = "/rol-permissions/", viewId = "/pages/rol-permissions.jsf"),
		@URLMapping(id = "rolePermissions2", pattern = "/rol-permissions", viewId = "/pages/rol-permissions.jsf") })
public class RolePermissionsAction extends BaseController {
	@EJB
	RoleFacade roleFacade;
	@EJB
	PermissionFacade permissionFacade;

	@Inject
	RolePermissionsDM rolePermissionsDM;

	/**
	 * Init Method
	 */
	@URLAction
	// @Interceptors(Guard.class)
	@AllowedPermission(permissionNameEnum = PermissionNameEnum.ROLE_PERMISSIONS, pageInformationEnum = PageInformationEnum.SYSTEM)
	public String init() {

		if (rolePermissionsDM.getRoleSelectItems() == null
				|| rolePermissionsDM.getRoleSelectItems().isEmpty()) {
			try {
				rolePermissionsDM.setRoleSelectItems(getSelectItems(
						roleFacade.findAll(), true));
			} catch (NotFoundException e) {
				e.printStackTrace();
				addErrorMessageBundle(MessagesConstants.RESULT_EMPTY);
			}
		}
		
		
		

		return null;
	}

	/**
	 * 
	 */
	public void selectRoleListener() {
		if (rolePermissionsDM.getSelectedRoleId() != null) {
			try {
				rolePermissionsDM.setPermissionSelectItems(getSelectItems(
						permissionFacade.findByRoleId(rolePermissionsDM
								.getSelectedRoleId()), false));
				
			} catch (NotFoundException e) {
				e.printStackTrace();
				addInfoMessageBundle(MessagesConstants.RESULT_EMPTY);
			}
		} else {
			addWarnMessageBundle(MessagesConstants.RESULT_SELECT_VALID);
		}
	}

	/**
	 * @return
	 */
	public String cancel() {
		if (rolePermissionsDM.getSelectedRoleId() != null) {
			selectRoleListener();
		}
		return null;
	}

	/**
	 * @return
	 */
	public String savePermissions() {

		try {
			if (rolePermissionsDM.getSelectedRoleId() != null
					&& rolePermissionsDM.getSelectedPermissionsId() != null) {
				roleFacade.saveRoleWithPermissions(
						rolePermissionsDM.getSelectedRoleId(),
						rolePermissionsDM.getSelectedPermissionsId());
				addInfoMessageBundle(MessagesConstants.RESULT_SAVED);

			} else {
				throw new NotSaveException();
			}
		} catch (NotSaveException e) {
			e.printStackTrace();
			addErrorMessageBundle(MessagesConstants.RESULT_NOT_SAVED);
		} catch (NotFoundException e) {
			e.printStackTrace();
			addErrorMessageBundle(MessagesConstants.RESULT_NOT_SAVED);
		}
		return null;

	}
}
