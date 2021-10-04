package com.demo.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.demo.model.User;


@Repository
public class AppDao {

	@Autowired
    private MongoTemplate mongoTemplate;
	

	public User getUserInfo(String userId) {
		Query query = new Query();
		ObjectId objID = new ObjectId(userId);
		query.addCriteria(Criteria.where("_id").is(objID));
		return mongoTemplate.findOne(query,User.class);
	}


	public User saveUserInfo(User user) {
		// TODO Auto-generated method stub
		return mongoTemplate.save(user);
	}


	public boolean updateUserInfo(User user, String userId) {
		Query query = new Query();
		ObjectId objID = new ObjectId(userId);
		query.addCriteria(Criteria.where("_id").is(objID));
		Update update = new Update();
		update.set("firstName", user.getFirstName());
		update.set("surName", user.getSurName());
		update.set("dob", user.getDob());
		update.set("title", user.getTitle());
		return mongoTemplate.updateFirst(query, update, User.class).wasAcknowledged();
	
	}


	public boolean deleteUserInfo(String userId) {
		Query query = new Query();
		ObjectId objID = new ObjectId(userId);
		query.addCriteria(Criteria.where("_id").is(objID));
		return mongoTemplate.remove(query, User.class).wasAcknowledged();
	}
}
