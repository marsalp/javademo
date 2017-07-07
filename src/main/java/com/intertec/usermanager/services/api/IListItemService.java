package com.intertec.usermanager.services.api;

import java.util.Set;

import com.intertec.usermanager.domain.ListItem;

public interface IListItemService {
	
	public Set<ListItem> getAllBlacklistItems();
	
	public Set<ListItem> getAllWhitelistItems();
	
	public Set<ListItem> getAllListItems();
	
	public void saveListItem(ListItem listItem);


}
