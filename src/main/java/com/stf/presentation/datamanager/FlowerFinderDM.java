package com.stf.presentation.datamanager;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.stf.persistence.entity.Item;

@Named
@SessionScoped
public class FlowerFinderDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SelectItem> flowerCategorySelectItems;
	private List<SelectItem> flowerTypeSelectItems;
	private List<SelectItem> flowerColorSelectItems;
	private List<SelectItem> flowerVarietySelectItems;
	private List<SelectItem> flowerGradeSelectItems;
	private List<SelectItem> growersPerBoxTypeSelectItems;
	private String orderCode;
	private String boxConfigurationCode;
	private List<Item> findedItems;
	private Integer findedItemsIndex;
	private Item selectedItem;

	public List<SelectItem> getGrowersPerBoxTypeSelectItems() {
		return growersPerBoxTypeSelectItems;
	}

	public void setGrowersPerBoxTypeSelectItems(
			List<SelectItem> growersPerBoxTypeSelectItems) {
		this.growersPerBoxTypeSelectItems = growersPerBoxTypeSelectItems;
	}

	public List<SelectItem> getFlowerCategorySelectItems() {
		return flowerCategorySelectItems;
	}

	public void setFlowerCategorySelectItems(
			List<SelectItem> flowerCategorySelectItems) {
		this.flowerCategorySelectItems = flowerCategorySelectItems;
	}

	public List<SelectItem> getFlowerTypeSelectItems() {
		return flowerTypeSelectItems;
	}

	public void setFlowerTypeSelectItems(List<SelectItem> flowerTypeSelectItems) {
		this.flowerTypeSelectItems = flowerTypeSelectItems;
	}

	public List<SelectItem> getFlowerColorSelectItems() {
		return flowerColorSelectItems;
	}

	public void setFlowerColorSelectItems(
			List<SelectItem> flowerColorSelectItems) {
		this.flowerColorSelectItems = flowerColorSelectItems;
	}

	public List<SelectItem> getFlowerVarietySelectItems() {
		return flowerVarietySelectItems;
	}

	public void setFlowerVarietySelectItems(
			List<SelectItem> flowerVarietySelectItems) {
		this.flowerVarietySelectItems = flowerVarietySelectItems;
	}

	public List<SelectItem> getFlowerGradeSelectItems() {
		return flowerGradeSelectItems;
	}

	public void setFlowerGradeSelectItems(
			List<SelectItem> flowerGradeSelectItems) {
		this.flowerGradeSelectItems = flowerGradeSelectItems;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCodeNewBox() {
		return orderCode;
	}

	public void setOrderCodeNewBox(String orderCode) {
		this.boxConfigurationCode = null;
		this.orderCode = orderCode;
	}

	public String getBoxConfigurationCode() {
		return boxConfigurationCode;
	}

	public void setBoxConfigurationCode(String boxConfigurationCode) {
		this.boxConfigurationCode = boxConfigurationCode;
	}

	public List<Item> getFindedItems() {
		return findedItems;
	}

	public void setFindedItems(List<Item> findedItems) {
		this.findedItems = findedItems;
	}

	public Integer getFindedItemsIndex() {
		return findedItemsIndex;
	}

	public void setFindedItemsIndex(Integer findedItemsIndex) {
		this.findedItemsIndex = findedItemsIndex;
	}

	public Item getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}

}
