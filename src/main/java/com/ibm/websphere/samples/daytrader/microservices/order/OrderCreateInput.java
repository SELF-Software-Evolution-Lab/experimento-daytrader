package com.ibm.websphere.samples.daytrader.microservices.order;

import java.math.BigDecimal;

public class OrderCreateInput {
	private String accountId;
	private String userId;
	private String quoteId;
	private String holdingId;
	private double quantity;
	private BigDecimal price;
	private BigDecimal orderFee;
	private String type;
	public OrderCreateInput(String accountId, String userId, String quoteId, String holdingId, double quantity, BigDecimal price,
			BigDecimal orderFee, String type) {
		super();
		this.accountId = accountId;
		this.userId = userId;
		this.quoteId = quoteId;
		this.holdingId = holdingId;
		this.quantity = quantity;
		this.price = price;
		this.orderFee = orderFee;
		this.type = type;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getHoldingId() {
		return holdingId;
	}
	public void setHoldingId(String holdingId) {
		this.holdingId = holdingId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getOrderFee() {
		return orderFee;
	}
	public void setOrderFee(BigDecimal orderFee) {
		this.orderFee = orderFee;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
