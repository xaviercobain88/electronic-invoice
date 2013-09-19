package com.stf.persistence.util;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class ComboBoxModel {

	@Column(columnDefinition = "BIT")
	protected Boolean active;
	@Transient
	protected Object comboBoxValue;
	@Transient
	protected String comboBoxLabel;

	public abstract Object getComboBoxValue();

	public abstract String getComboBoxLabel();

	/**
	 * @param list
	 * @return
	 */
	public static Integer countActiveRecords(List<? extends ComboBoxModel> list) {
		Integer resultCount = 0;
		if (list != null) {
			for (ComboBoxModel comboBoxModel : list) {
				if (comboBoxModel.getActive()) {
					resultCount++;
				}
			}
		}
		return resultCount;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
