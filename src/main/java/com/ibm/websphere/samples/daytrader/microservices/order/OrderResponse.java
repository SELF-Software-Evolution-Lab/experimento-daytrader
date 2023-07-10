package com.ibm.websphere.samples.daytrader.microservices.order;

import com.google.gson.annotations.SerializedName;

public class OrderResponse {

	 @SerializedName("accountId")
     private String accountId;
	 @SerializedName("userId")
     private String userId;
     @SerializedName("quoteId")
     private String quoteId;
     @SerializedName("holdingId")
     private String holdingId;
     @SerializedName("type")
     private OrderType type;
     @SerializedName("quantity")
     private int quantity;
     @SerializedName("price")
     private double price;
     @SerializedName("orderFee")
     private double orderFee;
     @SerializedName("completionDate")
     private String completionDate;
     @SerializedName("id")
     private int id;
     @SerializedName("status")
     private OrderStatus status;
     @SerializedName("openDate")
     private String openDate;
     @SerializedName("createdAt")
     private String createdAt;
     @SerializedName("updatedAt")
     private String updatedAt;
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
	public OrderType getType() {
		return type;
	}
	public void setType(OrderType type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getOrderFee() {
		return orderFee;
	}
	public void setOrderFee(double orderFee) {
		this.orderFee = orderFee;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
