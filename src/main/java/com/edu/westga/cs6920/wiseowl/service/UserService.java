package com.edu.westga.cs6920.wiseowl.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.westga.cs6920.wiseowl.model.*;


@Service("userService")
@Transactional
public class UserService 
{
	

	@PersistenceContext
    private EntityManager em;
	
		
	/** Login a User using this method. It gets the UserAuth domain object from the front-end layer,
	 *  looks for the user, and returns it back to the front end */
	@SuppressWarnings("unchecked")
	public User loginUser(UserAuth userAuth)
	{
		User user=null;
		String query ="SELECT user from User user JOIN user.userAuth userauth WHERE userauth.username=?1 AND userauth.password=?2";
		List<User> users=em.createQuery(query).setParameter(1, userAuth.getUsername()).setParameter(2, userAuth.getPassword()).getResultList();
		if(users.size()>0){	return user=users.get(0);}
		return user;
	}
	
}
