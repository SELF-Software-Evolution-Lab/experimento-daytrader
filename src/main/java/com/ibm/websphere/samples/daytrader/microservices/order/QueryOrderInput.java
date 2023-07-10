package com.ibm.websphere.samples.daytrader.microservices.order;

import java.util.List;

public class QueryOrderInput {
	
	private String accountId;
	private String userId;
	private List<OrderStatus> states;
	
	public QueryOrderInput(String accountId,String userId, List<OrderStatus> states) {
		super();
		this.accountId = accountId;
		this.userId = userId;
		this.states = states;
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
	public List<OrderStatus> getStates() {
		return states;
	}
	public void setStates(List<OrderStatus> states) {
		this.states = states;
	}
	

}
