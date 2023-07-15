package com.ibm.websphere.samples.daytrader.microservices.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.websphere.samples.daytrader.util.Log;

@ApplicationScoped
public class OrderService {

	@Inject
    @ConfigProperty(name = "ORDER_SERVICE_URL")
    private String orderServiceUrl;
	
	public OrderResponse createOrder(OrderCreateInput orderCreateInput) {
	      Log.error(orderServiceUrl);


	      // Convert the order object to JSON
	      Gson gson = new GsonBuilder().create();
	      String jsonInput = gson.toJson(orderCreateInput);

	      Log.error(orderServiceUrl);
		try {
			// Specify the URL of the endpoint you want to call
		    URL url = new URL(orderServiceUrl);
		    // Open a connection to the URL
		      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		      // Set the request method to POST
		      connection.setRequestMethod("POST");
		      connection.setRequestProperty("Content-Type", "application/json");
		      connection.setDoOutput(true);

		      // Send the JSON data as the request body
		      OutputStream outputStream = connection.getOutputStream();
		      outputStream.write(jsonInput.getBytes());
		      outputStream.flush();
		      outputStream.close();

		      // Get the response code
		      int responseCode = connection.getResponseCode();
		      System.out.println("Response Code: " + responseCode);

		      // Read the response from the API
		      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		      StringBuilder response = new StringBuilder();
		      String line;
		      while ((line = reader.readLine()) != null) {
		          response.append(line);
		      }
		      reader.close();

		      // Convert the JSON response to a Java object
		      OrderResponse orderResponse = gson.fromJson(response.toString(), OrderResponse.class);

		      return orderResponse;
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	      
	}
	
	public OrderResponse findOrderById(int orderId) {

        Gson gson = new Gson();

		try {
			
			// Create the full URL with the query string
	        String urlString = orderServiceUrl + "/"+orderId;
			// Specify the URL of the endpoint you want to call
		    URL url = new URL(urlString);
		    // Open a connection to the URL
		      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		      // Set the request method to GET
		      connection.setRequestMethod("GET");
		      

		      // Get the response code
		      int responseCode = connection.getResponseCode();
		      System.out.println("Response Code: " + responseCode);

		      // Read the response from the API
		      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		      StringBuilder response = new StringBuilder();
		      String line;
		      while ((line = reader.readLine()) != null) {
		          response.append(line);
		      }
		      reader.close();

		      // Convert the JSON response to a Java object
		      OrderResponse orderResponse = gson.fromJson(response.toString(), OrderResponse.class);

		      return orderResponse;
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	      
	}
	
	public List<OrderResponse> findOrders(QueryOrderInput queryOrderInput) {

        Gson gson = new Gson();

		try {
			
		 StringBuilder queryBuilder = new StringBuilder();
		 	if(queryOrderInput.getAccountId()!=null)
		 		queryBuilder.append("accountId=").append(queryOrderInput.getAccountId());
		
		if(queryOrderInput.getUserId()!=null)
			queryBuilder.append("userId=").append(queryOrderInput.getUserId());
		
		if(queryOrderInput.getStates()!=null) {
			List<OrderStatus> statesList = queryOrderInput.getStates();
		    for (OrderStatus state : statesList) {
		        queryBuilder.append("&status[]=").append(state);
		    }
		}
		
		String queryString = queryBuilder.toString();
		
		// Create the full URL with the query string
		String urlString = orderServiceUrl + "?" + queryString;
		// Specify the URL of the endpoint you want to call
		URL url = new URL(urlString);
		  // Open a connection to the URL
		  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		  // Set the request method to GET
		  connection.setRequestMethod("GET");
		  
		
		  // Get the response code
		  int responseCode = connection.getResponseCode();
		  System.out.println("Response Code: " + responseCode);
		
		  // Read the response from the API
		  BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		  StringBuilder response = new StringBuilder();
		  String line;
		  while ((line = reader.readLine()) != null) {
		      response.append(line);
		  }
		  reader.close();
		  // Convert the JSON response to a Java object
		  List<OrderResponse> orderResponse = gson.fromJson(response.toString(), new com.google.gson.reflect.TypeToken<List<OrderResponse>>() {}.getType());
		
		  return orderResponse;
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	      
	}
}
