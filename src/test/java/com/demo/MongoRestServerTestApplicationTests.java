package com.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class MongoRestServerTestApplicationTests {


    int randomServerPort=8082;
    
    static String id=null;
	@Test
	@Order(1)
	public void testSaveUser() throws URISyntaxException 
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" + randomServerPort + "/api/user";
	    URI uri = new URI(baseUrl);
        Map<String, Object> input=new HashMap<>();
        input.put("firstName", "Tester1");
        input.put("surName", "Prod");
        input.put("dob", "2004-10-04T09:05:30.025+00:00");
        input.put("title", "Mr.");
	    ResponseEntity<HashMap> result  = 
	    	      restTemplate.postForEntity(uri, input, HashMap.class);
        HashMap<String, Object> data=result.getBody();
        id=(String) data.get("_id");
	    System.out.println(data);
	    assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void testGetEmployeeListSuccess() throws URISyntaxException 
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" + randomServerPort + "/api/user/"+id;
	    URI uri = new URI(baseUrl); 
	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	    System.out.println(result.getBody());
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void testUpdateUser() throws URISyntaxException 
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" + randomServerPort + "/api/user/"+id;
	    System.out.println(baseUrl);
	    URI uri = new URI(baseUrl);
	        User user=new User( "Tester1", "Updated Prod", new Date(), "Mr");
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity<User> entity = new HttpEntity<User>(user,headers);
	        ResponseEntity<HashMap> result=restTemplate.exchange(uri, HttpMethod.PUT, entity, HashMap.class);   
	        HashMap<String, Object> data=result.getBody();
	        System.out.println(result.getStatusCode());
	    assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}
	
	@Test
	@Order(4)
	public void deleteUpdateUser() throws URISyntaxException 
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" + randomServerPort + "/api/user/"+id;
	    URI uri = new URI(baseUrl);
	      HttpEntity<String> entity = new HttpEntity<String>("",new HttpHeaders());
	      ResponseEntity result = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
	    System.out.println(result.getBody());
	    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}
	

}
