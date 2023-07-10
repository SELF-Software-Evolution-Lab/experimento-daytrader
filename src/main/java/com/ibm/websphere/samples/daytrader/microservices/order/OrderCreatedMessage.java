package com.ibm.websphere.samples.daytrader.microservices.order;

public class OrderCreatedMessage extends OrderMessage {
	
	private OrderResponse order;

	public OrderResponse getOrder() {
		return order;
	}

	public void setOrder(OrderResponse order) {
		this.order = order;
	}

}
