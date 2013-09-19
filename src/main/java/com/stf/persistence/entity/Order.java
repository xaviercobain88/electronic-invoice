package com.stf.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.stf.persistence.enums.OrderStatusOrderEnum;
import com.stf.persistence.enums.OrderTypeEnum;
import com.stf.persistence.enums.ReceivingStatusOrderEnum;

/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name = "order_session")
@NamedQueries({ @NamedQuery(name = "Order.findByClientId", query = "SELECT o FROM Order o "
		+ "WHERE o.client.id = :id and o.active = :active") })
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String code;

	@Column(name = "order_type")
	@Enumerated(EnumType.STRING)
	private OrderTypeEnum orderTypeEnum;

	@Column(name = "ordering_status")
	@Enumerated(EnumType.STRING)
	private OrderStatusOrderEnum orderStatusOrderEnum;

	@Column(name = "receiving_status")
	@Enumerated(EnumType.STRING)
	private ReceivingStatusOrderEnum receivingStatusOrderEnum;

	@Column(columnDefinition = "BIT")
	private Boolean active;

	@Column(name = "hidden_by_client", columnDefinition = "BIT")
	private Boolean hiddenByClient;

	@Column(name = "client_freight_forwarder", columnDefinition = "BIT")
	private Boolean clientFreightForwarder;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "delivery_date_pos")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDatePos;

	@Column(name = "order_deadline")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDeadline;

	@Column(name = "credit_deadline")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creditDeadline;

	private String status;

	private String pos;

	@Column(name = "system_id")
	private String systemId;

	// bi-directional many-to-one association to BoxConfiguration
	@OneToMany(mappedBy = "order")
	private List<BoxConfiguration> boxConfigurations;

	// bi-directional many-to-one association to ClientInvoiceDetail
	@OneToMany(mappedBy = "order")
	private List<ClientInvoiceDetail> clientInvoiceDetails;

	// bi-directional many-to-one association to Comission
	@OneToMany(mappedBy = "order")
	private List<Comission> comissions;

	// bi-directional many-to-one association to FreightForwarderInvoiceDetail
	@OneToMany(mappedBy = "order")
	private List<FreightForwarderInvoiceDetail> freightForwarderInvoiceDetails;

	// bi-directional many-to-one association to GrowerInvoiceDetail
	@OneToMany(mappedBy = "order")
	private List<GrowerInvoiceDetail> growerInvoiceDetails;

	// bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name = "client_id", insertable = false, updatable = false)
	private User client;

	@Column(name = "client_id")
	private Integer clientId;

	// bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name = "shipping_address_id", insertable = false, updatable = false)
	private ShippingAddress shippingAddress;

	@Column(name = "shipping_address_id")
	private Integer shippingAddressId;

	@Transient
	private Integer orderedBoxAmount;

	@Transient
	private Integer closedBoxAmount;

	@Transient
	private Integer openBoxAmount;

	@Transient
	private Integer expiredBoxAmount;

	@Transient
	private Integer shippedBoxAmount;

	public Order() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public List<BoxConfiguration> getBoxConfigurations() {
		return this.boxConfigurations;
	}

	public void setBoxConfigurations(List<BoxConfiguration> boxConfigurations) {
		this.boxConfigurations = boxConfigurations;
	}

	public List<ClientInvoiceDetail> getClientInvoiceDetails() {
		return this.clientInvoiceDetails;
	}

	public void setClientInvoiceDetails(
			List<ClientInvoiceDetail> clientInvoiceDetails) {
		this.clientInvoiceDetails = clientInvoiceDetails;
	}

	public List<Comission> getComissions() {
		return this.comissions;
	}

	public void setComissions(List<Comission> comissions) {
		this.comissions = comissions;
	}

	public List<FreightForwarderInvoiceDetail> getFreightForwarderInvoiceDetails() {
		return this.freightForwarderInvoiceDetails;
	}

	public void setFreightForwarderInvoiceDetails(
			List<FreightForwarderInvoiceDetail> freightForwarderInvoiceDetails) {
		this.freightForwarderInvoiceDetails = freightForwarderInvoiceDetails;
	}

	public List<GrowerInvoiceDetail> getGrowerInvoiceDetails() {
		return this.growerInvoiceDetails;
	}

	public void setGrowerInvoiceDetails(
			List<GrowerInvoiceDetail> growerInvoiceDetails) {
		this.growerInvoiceDetails = growerInvoiceDetails;
	}

	public User getClient() {
		return this.client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public OrderTypeEnum getOrderTypeEnum() {
		return orderTypeEnum;
	}

	public void setOrderTypeEnum(OrderTypeEnum orderTypeEnum) {
		this.orderTypeEnum = orderTypeEnum;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Date getDeliveryDatePos() {
		return deliveryDatePos;
	}

	public void setDeliveryDatePos(Date deliveryDatePos) {
		this.deliveryDatePos = deliveryDatePos;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Integer getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Integer shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getClientFreightForwarder() {
		return clientFreightForwarder;
	}

	public void setClientFreightForwarder(Boolean clientFreightForwarder) {
		this.clientFreightForwarder = clientFreightForwarder;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getOrderedBoxAmount() {
		return orderedBoxAmount;
	}

	public void setOrderedBoxAmount(Integer orderedBoxAmount) {
		this.orderedBoxAmount = orderedBoxAmount;
	}

	public Integer getClosedBoxAmount() {
		return closedBoxAmount;
	}

	public void setClosedBoxAmount(Integer closedBoxAmount) {
		this.closedBoxAmount = closedBoxAmount;
	}

	public Integer getOpenBoxAmount() {
		return openBoxAmount;
	}

	public void setOpenBoxAmount(Integer openBoxAmount) {
		this.openBoxAmount = openBoxAmount;
	}

	public Integer getExpiredBoxAmount() {
		return expiredBoxAmount;
	}

	public void setExpiredBoxAmount(Integer expiredBoxAmount) {
		this.expiredBoxAmount = expiredBoxAmount;
	}

	public Integer getShippedBoxAmount() {
		return shippedBoxAmount;
	}

	public void setShippedBoxAmount(Integer shippedBoxAmount) {
		this.shippedBoxAmount = shippedBoxAmount;
	}

	public Boolean getHiddenByClient() {
		return hiddenByClient;
	}

	public void setHiddenByClient(Boolean hiddenByClient) {
		this.hiddenByClient = hiddenByClient;
	}

	public Date getOrderDeadline() {
		return orderDeadline;
	}

	public void setOrderDeadline(Date orderDeadline) {
		this.orderDeadline = orderDeadline;
	}

	public OrderStatusOrderEnum getOrderStatusOrderEnum() {
		return orderStatusOrderEnum;
	}

	public void setOrderStatusOrderEnum(
			OrderStatusOrderEnum orderStatusOrderEnum) {
		this.orderStatusOrderEnum = orderStatusOrderEnum;
	}

	public ReceivingStatusOrderEnum getReceivingStatusOrderEnum() {
		return receivingStatusOrderEnum;
	}

	public void setReceivingStatusOrderEnum(
			ReceivingStatusOrderEnum receivingStatusOrderEnum) {
		this.receivingStatusOrderEnum = receivingStatusOrderEnum;
	}

	public Date getCreditDeadline() {
		return creditDeadline;
	}

	public void setCreditDeadline(Date creditDeadline) {
		this.creditDeadline = creditDeadline;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

}