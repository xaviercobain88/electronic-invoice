package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the box_tracking database table.
 * 
 */
@Entity
@Table(name="box_tracking")
public class BoxTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Box
    @ManyToOne
	private Box box;

    public BoxTracking() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}
	
}