package com.intertec.usermanager.repository;

import org.springframework.data.repository.Repository;

import com.intertec.usermanager.domain.User;

/**
 * Interface that define CRUD user operations 
 * @author mcsalas
 *
 */
public interface UserRepository extends Repository<User, Long> {
	
	User findByUserName(String userName);

	void save(User user);

}
