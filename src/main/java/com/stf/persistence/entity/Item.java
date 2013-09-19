package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(name = "item")
@NamedQueries({
		@NamedQuery(name = "Item.maxPrice", query = "SELECT i.uom.name, max(i.growerPrice) FROM Item i "
				+ "WHERE i.active = :activeItem AND i.approved = :approvedItem AND i.category.id = :categoryId AND i.type.id = :typeId "
				+ "AND i.color.id = :colorId AND i.variety.id = :varietyId AND i.grade.id = :gradeId "),
		@NamedQuery(name = "Item.uomPerBox", query = "SELECT i.uom.name, max(i.growerPrice) FROM Item i "
				+ "WHERE i.active = :activeItem AND i.approved = :approvedItem AND i.category.id = :categoryId AND i.type.id = :typeId "
				+ "AND i.color.id = :colorId AND i.variety.id = :varietyId AND i.grade.id = :gradeId "),
		@NamedQuery(name = "Item.countDistinctGrower", query = "SELECT count(distinct i.grower.id) FROM Item i "
				+ "WHERE i.active = :activeItem AND i.approved = :approvedItem AND i.boxType.active = :activeBoxType AND i.category.id = :categoryId AND i.type.id = :typeId "
				+ "AND i.color.id = :colorId AND i.variety.id = :varietyId AND i.grade.id = :gradeId ") })
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(columnDefinition = "BIT")
	private Boolean active;

	@Column(columnDefinition = "BIT")
	private Boolean approved;

	@Column(name = "grower_price")
	private BigDecimal growerPrice;

	@Column(name = "item_description")
	private String itemDescription;

	@Column(name = "max_quantity")
	private int maxQuantity;

	@Column(name = "order_multiples")
	private int orderMultiples;

	private String status;

	@Column(name = "tariff_code")
	private String tariffCode;

	// bi-directional many-to-one association to BoxConfigurationItem
	@OneToMany(mappedBy = "item")
	private List<BoxConfigurationItem> boxConfigurationItems;

	// bi-directional many-to-one association to Catalog
	@ManyToOne
	@JoinColumn(name = "type_catalog", insertable = false, updatable = false)
	private Catalog type;

	// bi-directional many-to-one association to Catalog
	@ManyToOne
	@JoinColumn(name = "category_catalog", insertable = false, updatable = false)
	private Catalog category;

	// bi-directional many-to-one association to Catalog
	@ManyToOne
	@JoinColumn(name = "color_catalog", insertable = false, updatable = false)
	private Catalog color;

	// bi-directional many-to-one association to Catalog
	@ManyToOne
	@JoinColumn(name = "variety_catalog", insertable = false, updatable = false)
	private Catalog variety;

	@ManyToOne
	@JoinColumn(name = "box_type_catalog", insertable = false, updatable = false)
	private Catalog boxType;

	// bi-directional many-to-one association to Catalog
	@ManyToOne
	@JoinColumn(name = "grade_catalog", insertable = false, updatable = false)
	private Catalog grade;

	@ManyToOne
	@JoinColumn(name = "uom_catalog")
	private Catalog uom;

	// bi-directional many-to-one association to Grower
	@ManyToOne
	private Grower grower;

	@Column(name = "type_catalog")
	private Integer typeCatalogId;

	@Column(name = "category_catalog")
	private Integer categoryCatalogId;

	@Column(name = "color_catalog")
	private Integer colorCatalogId;

	@Column(name = "variety_catalog")
	private Integer varietyCatalogId;

	@Column(name = "grade_catalog")
	private Integer gradeCatalogId;

	private String typeName;

	@Transient
	private String categoryName;

	@Transient
	private String colorName;

	@Transient
	private String varietyName;

	@Transient
	private String gradeName;

	@Transient
	private String maximunPricePerUom;

	@Transient
	private String uomPerBox;

	@Transient
	private String maximunLinePrice;

	@Transient
	private String wrapping;

	@Transient
	private Integer QtyPerBox;

	public Item() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public BigDecimal getGrowerPrice() {
		return this.growerPrice;
	}

	public void setGrowerPrice(BigDecimal growerPrice) {
		this.growerPrice = growerPrice;
	}

	public String getItemDescription() {
		return this.itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getMaxQuantity() {
		return this.maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public int getOrderMultiples() {
		return this.orderMultiples;
	}

	public void setOrderMultiples(int orderMultiples) {
		this.orderMultiples = orderMultiples;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTariffCode() {
		return this.tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public List<BoxConfigurationItem> getBoxConfigurationItems() {
		return this.boxConfigurationItems;
	}

	public void setBoxConfigurationItems(
			List<BoxConfigurationItem> boxConfigurationItems) {
		this.boxConfigurationItems = boxConfigurationItems;
	}

	public Catalog getType() {
		return type;
	}

	public void setType(Catalog type) {
		this.type = type;
	}

	public Catalog getCategory() {
		return category;
	}

	public void setCategory(Catalog category) {
		this.category = category;
	}

	public Catalog getColor() {
		return color;
	}

	public void setColor(Catalog color) {
		this.color = color;
	}

	public Catalog getVariety() {
		return variety;
	}

	public void setVariety(Catalog variety) {
		this.variety = variety;
	}

	public Catalog getGrade() {
		return grade;
	}

	public void setGrade(Catalog grade) {
		this.grade = grade;
	}

	public Grower getGrower() {
		return this.grower;
	}

	public void setGrower(Grower grower) {
		this.grower = grower;
	}

	public Catalog getBoxType() {
		return boxType;
	}

	public void setBoxType(Catalog boxType) {
		this.boxType = boxType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getMaximunPricePerUom() {
		return maximunPricePerUom;
	}

	public void setMaximunPricePerUom(String maximunPricePerUom) {
		this.maximunPricePerUom = maximunPricePerUom;
	}

	public String getUomPerBox() {
		return uomPerBox;
	}

	public void setUomPerBox(String uomPerBox) {
		this.uomPerBox = uomPerBox;
	}

	public String getMaximunLinePrice() {
		return maximunLinePrice;
	}

	public void setMaximunLinePrice(String maximunLinePrice) {
		this.maximunLinePrice = maximunLinePrice;
	}

	public String getWrapping() {
		return wrapping;
	}

	public void setWrapping(String wrapping) {
		this.wrapping = wrapping;
	}

	public Integer getQtyPerBox() {
		return QtyPerBox;
	}

	public void setQtyPerBox(Integer qtyPerBox) {
		QtyPerBox = qtyPerBox;
	}

	public Integer getTypeCatalogId() {
		return typeCatalogId;
	}

	public void setTypeCatalogId(Integer typeCatalogId) {
		this.typeCatalogId = typeCatalogId;
	}

	public Integer getCategoryCatalogId() {
		return categoryCatalogId;
	}

	public void setCategoryCatalogId(Integer categoryCatalogId) {
		this.categoryCatalogId = categoryCatalogId;
	}

	public Integer getColorCatalogId() {
		return colorCatalogId;
	}

	public void setColorCatalogId(Integer colorCatalogId) {
		this.colorCatalogId = colorCatalogId;
	}

	public Integer getVarietyCatalogId() {
		return varietyCatalogId;
	}

	public void setVarietyCatalogId(Integer varietyCatalogId) {
		this.varietyCatalogId = varietyCatalogId;
	}

	public Integer getGradeCatalogId() {
		return gradeCatalogId;
	}

	public void setGradeCatalogId(Integer gradeCatalogId) {
		this.gradeCatalogId = gradeCatalogId;
	}

	public Catalog getUom() {
		return uom;
	}

	public void setUom(Catalog uom) {
		this.uom = uom;
	}

}