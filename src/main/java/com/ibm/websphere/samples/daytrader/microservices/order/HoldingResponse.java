package com.ibm.websphere.samples.daytrader.microservices.order;

import com.google.gson.annotations.SerializedName;

public class HoldingResponse {
	
	@SerializedName("orderId")
    private String orderId;
	
	@SerializedName("holdingId")
    private String holdingId;
	
	public HoldingResponse() {
		super();
	}

	public HoldingResponse(String orderId, String holdingId) {
		super();
		this.orderId = orderId;
		this.holdingId = holdingId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getHoldingId() {
		return holdingId;
	}

	public void setHoldingId(String holdingId) {
		this.holdingId = holdingId;
	}

}
