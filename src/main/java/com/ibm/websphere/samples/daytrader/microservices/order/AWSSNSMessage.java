package com.ibm.websphere.samples.daytrader.microservices.order;

public class AWSSNSMessage {
    private String Type;
    private String MessageId;
    private String TopicArn;
    private String Message;
    
    
	public AWSSNSMessage() {
		super();
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getMessageId() {
		return MessageId;
	}
	public void setMessageId(String messageId) {
		MessageId = messageId;
	}
	public String getTopicArn() {
		return TopicArn;
	}
	public void setTopicArn(String topicArn) {
		TopicArn = topicArn;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
    
    

}
