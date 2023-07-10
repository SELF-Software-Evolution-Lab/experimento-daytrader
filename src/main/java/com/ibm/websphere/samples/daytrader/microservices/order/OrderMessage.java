package com.ibm.websphere.samples.daytrader.microservices.order;

public class OrderMessage {
	
	private MessageType messageType;
	public OrderMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
}
