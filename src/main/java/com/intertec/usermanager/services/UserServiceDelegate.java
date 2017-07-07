package com.intertec.usermanager.services;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intertec.usermanager.domain.ListItem;
import com.intertec.usermanager.domain.ListItemType;
import com.intertec.usermanager.domain.User;
import com.intertec.usermanager.repository.ListItemRepository;
import com.intertec.usermanager.repository.UserRepository;
import com.intertec.usermanager.services.api.IUserServiceDelegate;
import com.intertec.usermanager.util.UserUtil;

/**
 * 
 * @author mcsalas
 *
 */
@Service
public class UserServiceDelegate implements IUserServiceDelegate {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ListItemRepository listItemRepository;
	
	public Set<String> getSuggestUserNames(int numbersOfUserNames, String username) {
		Set<String> resultlist = new TreeSet<String> ();
		Set<ListItem> whiteListItems = listItemRepository.
				findAllByListItemType(ListItemType.WHITE_ITEM);
		
		for (int i = 0; i < numbersOfUserNames; i++) {
			User user = null;
			String generatedUserName = null;
			if(!whiteListItems.isEmpty()) {
				generatedUserName = UserUtil.
						generateUniqueUserName(whiteListItems, username);
				user = userRepository.findByUserName(generatedUserName);
			}
			
			if (user == null && generatedUserName != null 
					&& !resultlist.contains(generatedUserName)) {
				resultlist.add(generatedUserName);
			}
			else {
				resultlist.add(getRecursiveUserName(username, resultlist));
			}
			
		}
		
		return resultlist;
	}
	
	
	private String getRecursiveUserName(String username, Set<String> resultlist) {
		String generatedUserName = UserUtil.generateUniqueUserName(null, username);
		User user = userRepository.findByUserName(generatedUserName);
		if (user == null && !resultlist.contains(generatedUserName)) {
			return generatedUserName;
		}
		else {
			return getRecursiveUserName(username, resultlist);
		}
	}

}
