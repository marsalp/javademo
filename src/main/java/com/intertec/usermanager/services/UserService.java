package com.intertec.usermanager.services;

import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intertec.usermanager.components.Messages;
import com.intertec.usermanager.domain.ListItem;
import com.intertec.usermanager.domain.ListItemType;
import com.intertec.usermanager.domain.User;
import com.intertec.usermanager.exception.UserValidationException;
import com.intertec.usermanager.repository.ListItemRepository;
import com.intertec.usermanager.repository.UserRepository;
import com.intertec.usermanager.services.api.IUserService;
import com.intertec.usermanager.services.api.IUserServiceDelegate;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IUserServiceDelegate userServiceDelegate;
	
	@Autowired
	private ListItemRepository listItemRepository;
	
	@Autowired
    Messages messages;
	
	private static final int NUMBER_OF_SUGGESTED_USERNAMES = 14;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	@Override
	public Set<String> checkUserName(String username) throws UserValidationException {
		
		logger.info("Running method checkUserName, username : ", username );
		
		Set<String> userNamesSugguested= new TreeSet<>();
			
		if(username == null) {
			throw new IllegalArgumentException(messages.get("user.null"));
		}
		
		Set<ListItem> blackList = listItemRepository.
				findAllByListItemType(ListItemType.BLACK_ITEM);
		for (ListItem listItem : blackList) {
			if(username.contains(listItem.getValue())){
				throw new UserValidationException(String.
						format(messages.get("forbidden.word"), listItem.getValue()));
			}
		}
			
		User user = userRepository.findByUserName(username);
		
		if(user != null && username.equals(user.getUserName())) {
			userNamesSugguested = userServiceDelegate.
					getSuggestUserNames(NUMBER_OF_SUGGESTED_USERNAMES, username);
			logger.info("The usernames suggested are--> " + userNamesSugguested);
		}
		
		return userNamesSugguested;
	}


}
