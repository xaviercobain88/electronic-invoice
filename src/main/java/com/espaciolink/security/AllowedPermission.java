package com.espaciolink.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.espaciolink.persistence.enums.PermissionNameEnum;
import com.espaciolink.security.enums.PageInformationEnum;

/**
 * @author xavier
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedPermission {
	/**
	 * @return
	 */
	PermissionNameEnum permissionNameEnum();

	PageInformationEnum pageInformationEnum();

}
