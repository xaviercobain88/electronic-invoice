package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the box_configuration_item database table.
 * 
 */
@Entity
@Table(name="box_configuration_item")
public class BoxConfigurationItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int amount;

	//bi-directional many-to-one association to BoxConfiguration
    @ManyToOne
	@JoinColumn(name="box_configuration_id")
	private BoxConfiguration boxConfiguration;

	//bi-directional many-to-one association to Item
    @ManyToOne
	private Item item;

    public BoxConfigurationItem() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BoxConfiguration getBoxConfiguration() {
		return this.boxConfiguration;
	}

	public void setBoxConfiguration(BoxConfiguration boxConfiguration) {
		this.boxConfiguration = boxConfiguration;
	}
	
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
}