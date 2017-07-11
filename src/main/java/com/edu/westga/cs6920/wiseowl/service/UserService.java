package com.edu.westga.cs6920.wiseowl.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		String queryString ="SELECT user from User user JOIN user.userAuth userauth WHERE userauth.username=?1 AND userauth.password=?2";
		Query query = em.createQuery(queryString);
		query.setParameter(1, userAuth.getUsername());
		query.setParameter(2, userAuth.getPassword());
		List<User> users=query.getResultList();
		if(users.size()>0){	return user=users.get(0);}
		return user;
	}
	
	
	/** Register a new User using this method. It gets the model User object from the front-end layer,
	 *  persists it, and returns it to the front end */
	public User registerUser(User user)
	{
		String query ="SELECT user from User user JOIN user.userAuth userauth WHERE userauth.username=?1";
		List<User> users=em.createQuery(query).setParameter(1, user.getUserAuth().getUsername()).getResultList();
		if(users.size()>0){	return null;} //since username is already in use, cannot register

		//If no user with that username found, proceed with persisting
		if(user.getUserRole()==null)
		{
			UserRole userRole = em.find(UserRole.class, new Long(2));
			user.setUserRole(userRole);
		}
		em.persist(user);
		return user;
	}
	
	
	/** Gets the model User object from the front-end layer, updates it, and returns it back with
	 * extra fields populated */
	public User updateProfile(User user)
	{
		User userFromDB = em.find(User.class, new Long(user.getUser_ID()));// The EM find method finds by primary key the row in the database
		userFromDB.setFirstname(user.getFirstname());
		userFromDB.setLastname(user.getLastname());
		userFromDB.setNickname(user.getNickname());
		userFromDB.setDob(user.getDob());
		return userFromDB;
	}
	
}
