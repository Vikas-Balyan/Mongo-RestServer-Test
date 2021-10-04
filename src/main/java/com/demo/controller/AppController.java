package com.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.RecordNotFoundException;
import com.demo.model.User;
import com.demo.service.AppService;

import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/api")
public class AppController {
	@Autowired
	AppService appService;
	
	@Operation(summary = "Get User Data Using userId")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getUserInfo(@PathVariable String userId) {
        
        User user=appService.getUserInfo(userId);
        if(user==null) {
        	throw new RecordNotFoundException("Invalid user id : " + userId);
         }
     
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }
	
	@Operation(summary = "Save User Info")
    @PostMapping("/user")
    public ResponseEntity<Object> saveUserInfo(@RequestBody User user) {
        
        User usr=appService.saveUserInfo(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usr);

    }
	@Operation(summary = "Update User Info")
    @PutMapping("/user/{userId}")
    public ResponseEntity<Object> updateUserInfo(@RequestBody User user,@PathVariable String userId) {
        
		 boolean status=appService.updateUserInfo(user,userId);
        if(!status) {
        	throw new RecordNotFoundException("Invalid user id : " + userId);
         }
        user.set_id(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }
	
	@Operation(summary = "Delete User Info")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Object> deleteUserInfo(@PathVariable String userId) {
        
        boolean status=appService.deleteUserInfo(userId);
        if(!status) {
        	throw new RecordNotFoundException("Invalid user id : " + userId);
         }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");

    }
	

}
