package com.stf.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.stf.persistence.util.ComboBoxModel;

/**
 * The persistent class for the catalog database table.
 * 
 */
@Entity
@Table(name = "catalog")
@NamedQueries({
		@NamedQuery(name = "Catalog.findByParentId", query = "SELECT DISTINCT c FROM Catalog c "
				+ "WHERE c.parentCatalogId = :parentCatalogId and c.active IN ( :active ) ORDER BY LOWER(c.name)"),
		@NamedQuery(name = "Catalog.findByParentIdNull", query = "SELECT DISTINCT c FROM Catalog c "
				+ "WHERE c.parentCatalogId is null and c.active IN ( :active ) ORDER BY LOWER(c.name)"),
		@NamedQuery(name = "Catalog.findCategoryFromItem", query = "SELECT DISTINCT i.category FROM Item i "
				+ "WHERE i.active = :activeItem AND i.approved = :approvedItem AND i.category.active = :activeCategory "),
		@NamedQuery(name = "Catalog.findTypeFromItemByCategory", query = "SELECT DISTINCT i.type FROM Item i "
				+ "WHERE i.active = :activeItem AND i.approved = :approvedItem AND i.type.active = :activeType AND i.category.id = :categoryId "),
		@NamedQuery(name = "Catalog.findColorFromItemByType", query = "SELECT DISTINCT i.color FROM Item i "
				+ "WHERE i.active = :activeItem AND i.approved = :approvedItem AND i.color.active = :activeColor AND i.category.id = :categoryId AND i.type.id = :typeId"),
		@NamedQuery(name = "Catalog.findVarietyFromItemByColor", query = "SELECT DISTINCT i.variety FROM Item i "
				+ "WHERE i.active = :activeItem AND i.approved = :approvedItem AND i.variety.active = :activeVariety AND i.category.id = :categoryId AND i.type.id = :typeId AND i.color.id = :colorId"),
		@NamedQuery(name = "Catalog.findGradeFromItemByVariety", query = "SELECT DISTINCT i.grade FROM Item i "
				+ "WHERE i.active = :activeItem AND i.approved = :approvedItem AND i.grade.active = :activeGrade AND i.category.id = :categoryId AND i.type.id = :typeId AND i.color.id = :colorId AND i.variety.id = :varietyId "),
		@NamedQuery(name = "Catalog.findBoxTypeWithCountGrower", query = "SELECT i.boxType.id, i.boxType.name, count(distinct i.grower.id) FROM Item i "
				+ "WHERE i.active = :activeItem AND i.approved = :approvedItem AND i.boxType.active = :activeBoxType AND i.category.id = :categoryId AND i.type.id = :typeId "
				+ "AND i.color.id = :colorId AND i.variety.id = :varietyId AND i.grade.id = :gradeId GROUP BY i.boxType.id") })
public class Catalog extends ComboBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String description;

	private String mnemonic;

	@NotEmpty(message = "Field can't be empty")
	@Column(nullable = false)
	private String name;

	// bi-directional many-to-one association to Catalog
	@ManyToOne
	@JoinColumn(name = "parent_id", updatable = false, insertable = false)
	private Catalog parentCatalog;

	@Column(name = "parent_id")
	@NotNull(message = "Field can't be empty")
	private Integer parentCatalogId;

	// bi-directional many-to-one association to Catalog
	@OneToMany(mappedBy = "parentCatalog")
	private List<Catalog> childCatalogs;

	// bi-directional many-to-one association to Item
	@OneToMany(mappedBy = "category")
	private List<Item> itemsByCategory;

	// bi-directional many-to-one association to Item
	@OneToMany(mappedBy = "type")
	private List<Item> itemsByType;

	// bi-directional many-to-one association to Item
	@OneToMany(mappedBy = "color")
	private List<Item> itemsByColor;

	// bi-directional many-to-one association to Item
	@OneToMany(mappedBy = "variety")
	private List<Item> itemsByVariety;

	// bi-directional many-to-one association to Item
	@OneToMany(mappedBy = "grade")
	private List<Item> itemsByGrade;

	@OneToMany(mappedBy = "boxType")
	private List<Item> itemsByBoxType;

	@OneToMany(mappedBy = "boxType")
	private List<Item> itemsByUom;

	public Catalog() {
		active = true;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMnemonic() {
		return this.mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public String getName() {
		return this.name;
	}

	public String getNameLowerCase() {
		if (name != null) {
			return this.name.toLowerCase();
		}
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Catalog> getChildCatalogs() {
		return childCatalogs;
	}

	public void setChildCatalogs(List<Catalog> childCatalogs) {
		this.childCatalogs = childCatalogs;
	}

	public Catalog getParentCatalog() {
		return parentCatalog;
	}

	public void setParentCatalog(Catalog parentCatalog) {
		this.parentCatalog = parentCatalog;
	}

	public List<Item> getItemsByCategory() {
		return itemsByCategory;
	}

	public void setItemsByCategory(List<Item> itemsByCategory) {
		this.itemsByCategory = itemsByCategory;
	}

	public List<Item> getItemsByType() {
		return itemsByType;
	}

	public void setItemsByType(List<Item> itemsByType) {
		this.itemsByType = itemsByType;
	}

	public List<Item> getItemsByColor() {
		return itemsByColor;
	}

	public void setItemsByColor(List<Item> itemsByColor) {
		this.itemsByColor = itemsByColor;
	}

	public List<Item> getItemsByVariety() {
		return itemsByVariety;
	}

	public void setItemsByVariety(List<Item> itemsByVariety) {
		this.itemsByVariety = itemsByVariety;
	}

	public List<Item> getItemsByGrade() {
		return itemsByGrade;
	}

	public void setItemsByGrade(List<Item> itemsByGrade) {
		this.itemsByGrade = itemsByGrade;
	}

	public Integer getParentCatalogId() {
		return parentCatalogId;
	}

	public void setParentCatalogId(Integer parentCatalogId) {
		this.parentCatalogId = parentCatalogId;
	}

	@Override
	public Object getComboBoxValue() {
		return id;
	}

	@Override
	public String getComboBoxLabel() {
		return name;
	}

	public List<Item> getItemsByBoxType() {
		return itemsByBoxType;
	}

	public void setItemsByBoxType(List<Item> itemsByBoxType) {
		this.itemsByBoxType = itemsByBoxType;
	}

	public List<Item> getItemsByUom() {
		return itemsByUom;
	}

	public void setItemsByUom(List<Item> itemsByUom) {
		this.itemsByUom = itemsByUom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}