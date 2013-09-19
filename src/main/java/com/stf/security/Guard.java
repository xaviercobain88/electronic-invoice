package com.stf.security;

import java.lang.reflect.Method;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.stf.persistence.entity.Permission;
import com.stf.persistence.entity.User;
import com.stf.persistence.enums.PermissionTypeEnum;
import com.stf.presentation.datamanager.RequestParametersDM;
import com.stf.util.ParametersConstants;

/**
 * @author xavier
 * 
 */
public class Guard {

	@Inject
	@LoggedUser
	Instance<User> users;
	@Inject
	RequestParametersDM requestParametersDM;

	@AroundInvoke
	public Object validatePermissions(InvocationContext ic)
			throws Exception {
		Method method = ic.getMethod();
		User user = users.get();
		if (user == null || !isAllowed(method, user)) {
			throw new SecurityException(
					"User  is not allowed to execute the method " + method);
		}
		return ic.proceed();
	}

	/**
	 * @param method
	 * @param u
	 * @return
	 */
	boolean isAllowed(Method method, User u) {
		AllowedPermission annotation = method
				.getAnnotation(AllowedPermission.class);
		if (annotation == null) {
			return true;
		}
		List<Permission> permissions = u.getPermissions();
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				if (permission.getPermissionNameEnum() != null
						&& permission.getPermissionNameEnum().equals(
								annotation.permissionNameEnum())) {

					if (u.getUsername().equals(
							requestParametersDM.getPageInformationOwner())
							&& permission.getPermissionTypeEnum() != null) {

						if (permission.getPermissionTypeEnum().equals(
								PermissionTypeEnum.WRITE_OWN)) {
							requestParametersDM.setWrite(true);
							requestParametersDM.setRead(true);
							return true;
						} else if (permission.getPermissionTypeEnum().equals(
								PermissionTypeEnum.READ_OWN)) {
							requestParametersDM.setWrite(false);
							requestParametersDM.setRead(true);
							return true;
						}

					} else if (requestParametersDM.getPageInformationOwner() != null) {
						if (permission.getPermissionTypeEnum().equals(
								PermissionTypeEnum.WRITE_OTHER)) {
							requestParametersDM.setWrite(true);
							requestParametersDM.setRead(true);
							return true;
						} else if (permission.getPermissionTypeEnum().equals(
								PermissionTypeEnum.READ_OTHER)) {
							requestParametersDM.setWrite(false);
							requestParametersDM.setRead(true);
							return true;
						}

					}

				}
			}
		}
		return false;
	}
}
