package com.stf.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.stf.persistence.enums.MenuTypeEnum;
import com.stf.persistence.enums.PermissionNameEnum;
import com.stf.persistence.enums.PermissionTypeEnum;
import com.stf.persistence.util.ComboBoxModel;

/**
 * The persistent class for the permission database table.
 * 
 */
@Entity
@Table(name = "permission")
@NamedQueries({
		@NamedQuery(name = "Permission.findByUsername", query = "SELECT DISTINCT p FROM  Permission p JOIN p.roles r JOIN r.users u WHERE p.active IN ( :active ) AND u.username = :username"),
		@NamedQuery(name = "Permission.findByRoleName", query = "SELECT DISTINCT p FROM  Permission p JOIN p.roles r WHERE p.active IN ( :active ) AND r.roleName = :roleName"),
		@NamedQuery(name = "Permission.findByRoleId", query = "SELECT DISTINCT p FROM  Permission p JOIN p.roles r WHERE p.active IN ( :active ) AND r.id = :roleId"),
		@NamedQuery(name = "Permission.findAll", query = "SELECT  p FROM  Permission p WHERE p.active IN ( :active )"),
		@NamedQuery(name = "Permission.findByIdList", query = "SELECT DISTINCT p FROM  Permission p  WHERE p.active IN ( :active ) AND p.id IN  ( :idList )") })
public class Permission extends ComboBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@ManyToMany(mappedBy = "permissions")
	private List<Role> roles;

	@Column(name = "permission_name")
	@Enumerated(EnumType.STRING)
	private PermissionNameEnum permissionNameEnum;

	@Column(name = "permission_type")
	@Enumerated(EnumType.STRING)
	private PermissionTypeEnum permissionTypeEnum;

	@Column(name = "menu_type")
	@Enumerated(EnumType.STRING)
	private MenuTypeEnum menuTypeEnum;

	// bi-directional many-to-one association to Catalog
	@ManyToOne
	@JoinColumn(name = "parent_id", updatable = false, insertable = false)
	private Permission parentPermission;

	@Column(name = "parent_id")
	@NotNull(message = "Field can't be empty")
	private Integer parentPermissionId;

	// bi-directional many-to-one association to Catalog
	@OneToMany(mappedBy = "parentPermission")
	private List<Permission> childPermissions;

	public Permission() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public PermissionNameEnum getPermissionNameEnum() {
		return permissionNameEnum;
	}

	public void setPermissionNameEnum(PermissionNameEnum permissionNameEnum) {
		this.permissionNameEnum = permissionNameEnum;
	}

	public PermissionTypeEnum getPermissionTypeEnum() {
		return permissionTypeEnum;
	}

	public void setPermissionTypeEnum(PermissionTypeEnum permissionTypeEnum) {
		this.permissionTypeEnum = permissionTypeEnum;
	}

	@Override
	public Object getComboBoxValue() {
		return id;
	}

	@Override
	public String getComboBoxLabel() {

		return permissionNameEnum.name();
	}

	public MenuTypeEnum getMenuTypeEnum() {
		return menuTypeEnum;
	}

	public void setMenuTypeEnum(MenuTypeEnum menuTypeEnum) {
		this.menuTypeEnum = menuTypeEnum;
	}

	public Permission getParentPermission() {
		return parentPermission;
	}

	public void setParentPermission(Permission parentPermission) {
		this.parentPermission = parentPermission;
	}

	public Integer getParentPermissionId() {
		return parentPermissionId;
	}

	public void setParentPermissionId(Integer parentPermissionId) {
		this.parentPermissionId = parentPermissionId;
	}

	public List<Permission> getChildPermissions() {
		return childPermissions;
	}

	public void setChildPermissions(List<Permission> childPermissions) {
		this.childPermissions = childPermissions;
	}

}