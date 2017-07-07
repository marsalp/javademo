package com.intertec.usermanager.services.api;

import java.util.Set;

import com.intertec.usermanager.exception.UserValidationException;

/**
 * Interface that define a contract for user login operations
 * @author mcsalas
 *
 */
public interface IUserService {
	
	/**
	 * This method validates if the username was given exists 
	 * and return a list of suggested usernames otherwise return a empty list.
	 * @param userName
	 * @return a list of suggested user names
	 * @throws UserValidationException 
	 */
	Set<String> checkUserName(String userName) throws UserValidationException;
	

}
