package com.stf.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.stf.persistence.enums.PointTypeEnum;
import com.stf.persistence.util.ComboBoxModel;

/**
 * The persistent class for the box database table.
 * 
 */
@Entity
@Table(name = "point")
@NamedQueries({ @NamedQuery(name = "Point.findByPointType", query = "SELECT p FROM Point p WHERE p.active = :active AND p.pointTypeEnum  IN (:pointTypeList)") })
public class Point extends ComboBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;


	private String name;

	private String description;

	@Column(name = "product_list")
	private String productList;

	@Column(name = "lead_time_days")
	private Integer leadTimeDays;

	@Column(name = "point_type")
	@Enumerated(EnumType.STRING)
	private PointTypeEnum pointTypeEnum;

	@Transient
	private String leadTimeDescription;

	@Transient
	private String arrivalToPOS;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductList() {
		return productList;
	}

	public void setProductList(String productList) {
		this.productList = productList;
	}

	public Integer getLeadTimeDays() {
		return leadTimeDays;
	}

	public void setLeadTimeDays(Integer leadTimeDays) {
		this.leadTimeDays = leadTimeDays;
	}

	public PointTypeEnum getPointTypeEnum() {
		return pointTypeEnum;
	}

	public void setPointTypeEnum(PointTypeEnum pointTypeEnum) {
		this.pointTypeEnum = pointTypeEnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Object getComboBoxValue() {
		return id;
	}

	@Override
	public String getComboBoxLabel() {
		return name;
	}

	public String getLeadTimeDescription() {
		return leadTimeDescription;
	}

	public void setLeadTimeDescription(String leadTimeDescription) {
		this.leadTimeDescription = leadTimeDescription;
	}

	public String getArrivalToPOS() {
		return arrivalToPOS;
	}

	public void setArrivalToPOS(String arrivalToPOS) {
		this.arrivalToPOS = arrivalToPOS;
	}

}