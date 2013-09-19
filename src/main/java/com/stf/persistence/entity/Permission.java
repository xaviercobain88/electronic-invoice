package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

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
	private int id;

	@Column(columnDefinition = "BIT")
	private Boolean active;

	@Column(name = "menu_type")
	private String menuType;

	@Column(name = "permission_name")
	private String permissionName;

	@Column(name = "permission_type")
	private String permissionType;

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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionType() {
		return this.permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
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