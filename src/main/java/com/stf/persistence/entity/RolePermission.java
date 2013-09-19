package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the role_permission database table.
 * 
 */
@Entity
@Table(name="role_permission")
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Permission
    @ManyToOne
	@JoinColumn(name="option_id")
	private Permission permission;

	//bi-directional many-to-one association to Role
    @ManyToOne
	private Role role;

    public RolePermission() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}