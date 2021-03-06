/*
 */
package com.espaciolink.business.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.espaciolink.persistence.dao.PermissionDao;
import com.espaciolink.persistence.dao.RoleDao;
import com.espaciolink.persistence.entity.Permission;
import com.espaciolink.persistence.entity.Role;
import com.espaciolink.persistence.exception.NotFoundException;
import com.espaciolink.persistence.exception.NotSaveException;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class RoleFacade {

	@EJB
	RoleDao roleDao;
	@EJB
	PermissionDao permissionDao;

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<Role> findAll() throws NotFoundException {

		ArrayList<Boolean> activeList = new ArrayList<Boolean>();
		activeList.add(true);

		return roleDao.findAll(activeList);
	}

	/**
	 * @param roleId
	 * @param permissionIdList
	 * @return
	 * @throws NotSaveException
	 * @throws NotFoundException
	 */
	public Role saveRoleWithPermissions(Integer roleId,
			List<Integer> permissionIdList) throws NotSaveException,
			NotFoundException {

		ArrayList<Boolean> activeList = new ArrayList<Boolean>();
		activeList.add(true);

		List<Permission> permissionList = permissionDao.findByIdList(
				permissionIdList, activeList);

		Role role = roleDao.findById(roleId);
		role.setPermissions(permissionList);
		Role savedRole = roleDao.update(role);
		if (savedRole != null && savedRole.getPermissions() != null) {
			// For avoiding LazyInitializationException
			savedRole.getPermissions().size();
		}
		return savedRole;

	}
}
