/*
 */
package com.espaciolink.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.espaciolink.persistence.entity.Permission;
import com.espaciolink.persistence.exception.NotFoundException;

import static com.espaciolink.persistence.util.QueryParameter.with;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class PermissionDao extends GenericDao<Permission, Integer> {

	public PermissionDao() {
		super(Permission.class);

	}

	/**
	 * @param username
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<Permission> findByUsername(String username,
			ArrayList<Boolean> active) throws NotFoundException {

		return findByNamedQuery("Permission.findByUsername",
				with("active", active).and("username", username).parameters());

	}

	/**
	 * @param username
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<Permission> findByRole(String roleName,
			ArrayList<Boolean> active) throws NotFoundException {

		return findByNamedQuery("Permission.findByRoleName",
				with("active", active).and("roleName", roleName).parameters());

	}

	/**
	 * @param roleId
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<Permission> findByRoleId(Integer roleId,
			ArrayList<Boolean> active) throws NotFoundException {

		return findByNamedQuery("Permission.findByRoleId",
				with("active", active).and("roleId", roleId).parameters());

	}

	/**
	 * @param idList
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<Permission> findByIdList(List<Integer> idList,
			ArrayList<Boolean> active) throws NotFoundException {

		return findByNamedQuery("Permission.findByRoleId",
				with("active", active).and("idList", idList).parameters());

	}

	/**
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<Permission> findAll(ArrayList<Boolean> active)
			throws NotFoundException {

		return findByNamedQuery("Permission.findAll", with("active", active)
				.parameters());

	}

}
