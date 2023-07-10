package com.ibm.websphere.samples.daytrader.microservices.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrderService {
	
	public static final String BASE_URL = "http://localhost:3000/orders";

	
	public OrderResponse createOrder(OrderCreateInput orderCreateInput) {
		

	      // Convert the order object to JSON
	      Gson gson = new GsonBuilder().create();
	      String jsonInput = gson.toJson(orderCreateInput);

	      
		try {
			// Specify the URL of the endpoint you want to call
		    URL url = new URL(BASE_URL);
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
	        String urlString = BASE_URL + "/"+orderId;
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
	            String urlString = BASE_URL + "?" + queryString;
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
