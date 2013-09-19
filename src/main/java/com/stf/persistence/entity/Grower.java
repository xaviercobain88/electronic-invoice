package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the grower database table.
 * 
 */
@Entity
@Table(name = "grower")
@NamedQueries({ @NamedQuery(name = "Grower.findByUsername", query = "SELECT g FROM Grower g WHERE g.user.username = :username") })
public class Grower implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	@Column(columnDefinition = "BIT")
	private Boolean active;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	// bi-directional many-to-one association to GrowerInvoiceDetail
	@OneToMany(mappedBy = "grower")
	private List<GrowerInvoiceDetail> growerInvoiceDetails;

	// bi-directional many-to-one association to Item
	@OneToMany(mappedBy = "grower")
	private List<Item> items;

	@OneToMany(mappedBy = "grower")
	private List<BoxGrowerNegotiation> boxGrowerNegotiations;

	public Grower() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<GrowerInvoiceDetail> getGrowerInvoiceDetails() {
		return growerInvoiceDetails;
	}

	public void setGrowerInvoiceDetails(
			List<GrowerInvoiceDetail> growerInvoiceDetails) {
		this.growerInvoiceDetails = growerInvoiceDetails;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<BoxGrowerNegotiation> getBoxGrowerNegotiations() {
		return boxGrowerNegotiations;
	}

	public void setBoxGrowerNegotiations(
			List<BoxGrowerNegotiation> boxGrowerNegotiations) {
		this.boxGrowerNegotiations = boxGrowerNegotiations;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}