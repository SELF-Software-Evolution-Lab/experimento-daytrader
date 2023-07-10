package com.ibm.websphere.samples.daytrader.microservices.order;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.websphere.samples.daytrader.entities.OrderDataBean;

public class OrderResponseToOrderDataBeanConvertor {
	
	public OrderDataBean convertToOrderDataBean(OrderResponse orderResponse) {
		OrderDataBean order = new OrderDataBean();
		order.setOrderID(orderResponse.getId());
		order.setOrderStatus(this.getOrderStatus(orderResponse.getStatus()));
		order.setOrderType(orderResponse.getType()==OrderType.BUY?"buy":"sell");
		order.setSymbol(orderResponse.getQuoteId());
		
		order.setPrice(BigDecimal.valueOf(orderResponse.getPrice()));
		order.setOrderFee(BigDecimal.valueOf(orderResponse.getOrderFee()));
		order.setQuantity(orderResponse.getQuantity());
		order.setOpenDate(this.getDateFromString(orderResponse.getOpenDate()));
		if(orderResponse.getCompletionDate()!=null)
			order.setCompletionDate(this.getDateFromString(orderResponse.getCompletionDate()));
		
		return order;
	}

	
	private String getOrderStatus(OrderStatus orderStatus){
		
		if(orderStatus == OrderStatus.OPEN) {
			return "open";
		}
		else if(orderStatus == OrderStatus.PROCESSING) {
			return "processing";
		}
		else if(orderStatus == OrderStatus.COMPLETED) {
			return "completed";		
		}
		else if(orderStatus == OrderStatus.CLOSED) {
			return "closed";
		}
		else if(orderStatus == OrderStatus.CANCELED) {
			return "canceled";
		}
		return null;
	}
	
	private Date getDateFromString(String date) {
		try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return null;
	}
}
