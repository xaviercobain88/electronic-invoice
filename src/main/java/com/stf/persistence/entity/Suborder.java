package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the suborder database table.
 * 
 */
@Entity
@Table(name="suborder")
public class Suborder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Order
    @ManyToOne
	private Order order;

	//bi-directional many-to-one association to Grower
    @ManyToOne
	private Grower grower;

	//bi-directional many-to-one association to SuborderBoxConfiguration
	@OneToMany(mappedBy="suborder")
	private List<SuborderBoxConfiguration> suborderBoxConfigurations;

    public Suborder() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Grower getGrower() {
		return this.grower;
	}

	public void setGrower(Grower grower) {
		this.grower = grower;
	}
	
	public List<SuborderBoxConfiguration> getSuborderBoxConfigurations() {
		return this.suborderBoxConfigurations;
	}

	public void setSuborderBoxConfigurations(List<SuborderBoxConfiguration> suborderBoxConfigurations) {
		this.suborderBoxConfigurations = suborderBoxConfigurations;
	}
	
}