package com.ibm.websphere.samples.daytrader.microservices.order;

public class HoldingCreatedMessage extends OrderMessage {
	
	private HoldingResponse holding;

	public HoldingResponse getHolding() {
		return holding;
	}

	public void setHolding(HoldingResponse holding) {
		this.holding = holding;
	}

}
