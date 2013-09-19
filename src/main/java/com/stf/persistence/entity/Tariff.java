package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tariff database table.
 * 
 */
@Entity
@Table(name="tariff")
public class Tariff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

    public Tariff() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}