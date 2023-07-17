package com.ibm.websphere.samples.daytrader.aws.sqs;
import java.util.List;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.websphere.samples.daytrader.interfaces.TradeServices;
import com.ibm.websphere.samples.daytrader.microservices.order.OrderMessage;
import com.ibm.websphere.samples.daytrader.microservices.order.OrderResponse;
import com.ibm.websphere.samples.daytrader.microservices.order.AWSSNSMessage;
import com.ibm.websphere.samples.daytrader.microservices.order.MessageType;
import com.ibm.websphere.samples.daytrader.microservices.order.OrderCreatedMessage;
import com.ibm.websphere.samples.daytrader.util.Log;
import com.ibm.websphere.samples.daytrader.util.TradeConfig;
import com.ibm.websphere.samples.daytrader.util.TradeRunTimeModeLiteral;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.*;

@WebListener
public class SQSListener implements ServletContextListener {

    private static final int POLLING_INTERVAL_MS = 5000;
    
    
    private SqsClient sqsClient;
    private TradeServices tradeAction;
    
    
    @Inject
    @ConfigProperty(name = "AWS_ACCESS_KEY")
    private String awsAccessKey;
    
    @Inject
    @ConfigProperty(name = "AWS_SECRET_KEY")
    private String awsSecretKey;
    
    @Inject
    @ConfigProperty(name = "AWS_SESSION_TOKEN")
    private String awsSessionToken;
    
    @Inject
    @ConfigProperty(name = "AWS_REGION_KEY")
    private String awsRegion;
    
    @Inject
    @ConfigProperty(name = "SQS_URL")
    private String sqsUrl;
    
    
    @Inject 
    public SQSListener(@Any Instance<TradeServices> services) {
      tradeAction = services.select(new TradeRunTimeModeLiteral(TradeConfig.getRunTimeModeNames()[TradeConfig.getRunTimeMode()])).get();
    }

    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	
		AwsSessionCredentials sessionCredentials = AwsSessionCredentials.create(awsAccessKey, awsSecretKey, awsSessionToken);

        this.sqsClient = SqsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(StaticCredentialsProvider.create(sessionCredentials))
                .build();

        startListening();
    }
    
    private void startListening() {
        Thread listenerThread = new Thread(() -> {
            while (true) {
                ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                        .queueUrl(sqsUrl)
                        .maxNumberOfMessages(1)
                        .build();

                ReceiveMessageResponse receiveResponse = sqsClient.receiveMessage(receiveRequest);
                List<Message> messages = receiveResponse.messages();
                for (Message message : messages) {
                    String body = message.body();

                    Log.log("Received message: " + body);
                    Gson gson = new GsonBuilder().create();
                    AWSSNSMessage snsMessage = gson.fromJson(body, AWSSNSMessage.class);
                    
                    OrderMessage orderMessage= gson.fromJson(snsMessage.getMessage(),OrderMessage.class);

                    Log.log("Message type: " + orderMessage.getMessageType());
                    
                    if(orderMessage.getMessageType() == MessageType.ORDER_CREATED){
                    	Log.error("Handling: " + orderMessage.getMessageType());
                    	
                    	OrderCreatedMessage orderCreatedMessage= gson.fromJson(snsMessage.getMessage(),OrderCreatedMessage.class);

                    	tradeAction.orderCreated(orderCreatedMessage.getOrder());       
                        
                    }
                    else {
                    	Log.log("Message handler not defined: " + orderMessage.getMessageType());
                    }
                    
                    String receiptHandle = message.receiptHandle();
                    DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                            .queueUrl(sqsUrl)
                            .receiptHandle(receiptHandle)
                            .build();
                    sqsClient.deleteMessage(deleteRequest);
                    
                }

                try {
                    Thread.sleep(POLLING_INTERVAL_MS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        listenerThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Clean up resources if needed
    }
}
