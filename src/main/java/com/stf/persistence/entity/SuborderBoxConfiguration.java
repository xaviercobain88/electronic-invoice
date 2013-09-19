package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the suborder_box_configuration database table.
 * 
 */
@Entity
@Table(name="suborder_box_configuration")
public class SuborderBoxConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="grouped_boxes")
	private int groupedBoxes;

	@Column(name="ungrouped_boxes")
	private int ungroupedBoxes;

	//bi-directional many-to-one association to Box
	@OneToMany(mappedBy="suborderBoxConfiguration")
	private List<Box> boxs;

	//bi-directional many-to-one association to BoxConfiguration
    @ManyToOne
	@JoinColumn(name="box_configuration_id")
	private BoxConfiguration boxConfiguration;

	//bi-directional many-to-one association to Suborder
    @ManyToOne
	@JoinColumn(name="selected_grower_id")
	private Suborder suborder;

    public SuborderBoxConfiguration() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupedBoxes() {
		return this.groupedBoxes;
	}

	public void setGroupedBoxes(int groupedBoxes) {
		this.groupedBoxes = groupedBoxes;
	}

	public int getUngroupedBoxes() {
		return this.ungroupedBoxes;
	}

	public void setUngroupedBoxes(int ungroupedBoxes) {
		this.ungroupedBoxes = ungroupedBoxes;
	}

	public List<Box> getBoxs() {
		return this.boxs;
	}

	public void setBoxs(List<Box> boxs) {
		this.boxs = boxs;
	}
	
	public BoxConfiguration getBoxConfiguration() {
		return this.boxConfiguration;
	}

	public void setBoxConfiguration(BoxConfiguration boxConfiguration) {
		this.boxConfiguration = boxConfiguration;
	}
	
	public Suborder getSuborder() {
		return this.suborder;
	}

	public void setSuborder(Suborder suborder) {
		this.suborder = suborder;
	}
	
}