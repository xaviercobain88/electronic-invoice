package com.espaciolink.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.espaciolink.persistence.enums.MenuTypeEnum;
import com.espaciolink.persistence.enums.PermissionNameEnum;
import com.espaciolink.persistence.enums.PermissionTypeEnum;

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
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(columnDefinition = "BIT")
	private Boolean active;

	@Column(name = "permission_name")
	@Enumerated(EnumType.STRING)
	private PermissionNameEnum permissionNameEnum;

	@Column(name = "permission_type")
	@Enumerated(EnumType.STRING)
	private PermissionTypeEnum permissionTypeEnum;

	@Column(name = "menu_type")
	@Enumerated(EnumType.STRING)
	private MenuTypeEnum menuTypeEnum;

	// bi-directional many-to-one association to Permission
	@ManyToOne
	@JoinColumn(name = "parent_permission_id")
	private Permission permission;

	// bi-directional many-to-one association to Permission
	@OneToMany(mappedBy = "permission")
	private List<Permission> permissions;

	// bi-directional many-to-many association to Role
	@ManyToMany(mappedBy = "permissions")
	private List<Role> roles;

	public Permission() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	public MenuTypeEnum getMenuTypeEnum() {
		return menuTypeEnum;
	}

	public void setMenuTypeEnum(MenuTypeEnum menuTypeEnum) {
		this.menuTypeEnum = menuTypeEnum;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Permission addPermission(Permission permission) {
		getPermissions().add(permission);
		permission.setPermission(this);

		return permission;
	}

	public Permission removePermission(Permission permission) {
		getPermissions().remove(permission);
		permission.setPermission(null);

		return permission;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}