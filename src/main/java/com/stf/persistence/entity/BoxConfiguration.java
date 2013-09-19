package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the box_configuration database table.
 * 
 */
@Entity
@Table(name = "box_configuration")
public class BoxConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Integer amount;

	private String code;

	@Column(name = "create_date")
	private Timestamp createDate;

	// bi-directional many-to-one association to Order
	@ManyToOne
	private Order order;

	// bi-directional many-to-one association to BoxConfigurationItem
	@OneToMany(mappedBy = "boxConfiguration")
	private List<BoxConfigurationItem> boxConfigurationItems;

	// bi-directional many-to-one association to SuborderBoxConfiguration
	@OneToMany(mappedBy = "boxConfiguration")
	private List<Box> boxes;

	public BoxConfiguration() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<BoxConfigurationItem> getBoxConfigurationItems() {
		return boxConfigurationItems;
	}

	public void setBoxConfigurationItems(
			List<BoxConfigurationItem> boxConfigurationItems) {
		this.boxConfigurationItems = boxConfigurationItems;
	}

	public List<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}

}