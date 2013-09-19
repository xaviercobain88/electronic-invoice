package com.stf.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.stf.persistence.util.ComboBoxModel;

/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name = "role")
@NamedQueries({ @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r WHERE r.active IN ( :active) ") })
public class Role extends ComboBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "role_name", nullable = false)
	private String roleName;

	private String description;

	@ManyToMany
	@JoinTable(name = "role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "permission_id") })
	private List<Permission> permissions;

	// bi-directional many-to-one association to User
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public Object getComboBoxValue() {
		return id;
	}

	@Override
	public String getComboBoxLabel() {
		return roleName;
	}

}