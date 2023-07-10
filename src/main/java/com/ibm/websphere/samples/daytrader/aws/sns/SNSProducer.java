package com.ibm.websphere.samples.daytrader.aws.sns;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.google.gson.Gson;
import com.ibm.websphere.samples.daytrader.microservices.order.OrderMessage;
import com.ibm.websphere.samples.daytrader.util.Log;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Singleton
public class SNSProducer {
	
	@Inject
    @ConfigProperty(name = "AWS_ACCESS_KEY")
    private String awsAccessKey;
    
    @Inject
    @ConfigProperty(name = "AWS_SECRET_KEY")
    private String awsSecretKey;
    
    @Inject
    @ConfigProperty(name = "AWS_REGION_KEY")
    private String awsRegion;
    
    @Inject
    @ConfigProperty(name = "SNS_ARN")
    private String snsARN;

    
    private SnsClient snsClient;
    
    public SNSProducer() {
    	
    }
    
    public <T> void sendMessage(T message) {
    	Log.error("SNSARN "+snsARN);
    	if(this.snsClient==null) {
    		this.snsClient = SnsClient.builder().region(Region.of(awsRegion))
                    .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKey, awsSecretKey)))
                    .build();
    	}
    	Gson gson = new Gson();
    	String jsonMessage = gson.toJson(message);
    	
        PublishRequest request = PublishRequest.builder()
                .topicArn(snsARN)
                .message(jsonMessage)
                .build();

        snsClient.publish(request);
    }
    
    public void close() {
        snsClient.close();
    }
}
