package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String level;

	//bi-directional many-to-one association to ClientAsigned
	@OneToMany(mappedBy="employee")
	private List<ClientAsigned> clientAsigneds;

	//bi-directional many-to-one association to Comission
	@OneToMany(mappedBy="employee")
	private List<Comission> comissions;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="superior_id")
	private Employee employee;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="employee")
	private List<Employee> employees;

	//bi-directional many-to-one association to User
    @ManyToOne
	private User user;

	//bi-directional many-to-one association to TerritoryAsigned
	@OneToMany(mappedBy="employee")
	private List<TerritoryAsigned> territoryAsigneds;

    public Employee() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<ClientAsigned> getClientAsigneds() {
		return this.clientAsigneds;
	}

	public void setClientAsigneds(List<ClientAsigned> clientAsigneds) {
		this.clientAsigneds = clientAsigneds;
	}
	
	public List<Comission> getComissions() {
		return this.comissions;
	}

	public void setComissions(List<Comission> comissions) {
		this.comissions = comissions;
	}
	
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<TerritoryAsigned> getTerritoryAsigneds() {
		return this.territoryAsigneds;
	}

	public void setTerritoryAsigneds(List<TerritoryAsigned> territoryAsigneds) {
		this.territoryAsigneds = territoryAsigneds;
	}
	
}