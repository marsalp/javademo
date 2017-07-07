package com.intertec.usermanager.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.intertec.usermanager.domain.ListItem;
import com.intertec.usermanager.domain.ListItemType;
import com.intertec.usermanager.repository.ListItemRepository;
import com.intertec.usermanager.services.api.IListItemService;

@Service
public class ListItemService implements IListItemService {
	
	@Autowired
	private ListItemRepository listItemRepository;

	@Override
	@Cacheable(value="blackList")
	public Set<ListItem> getAllBlacklistItems() {
		return listItemRepository.findAllByListItemType(ListItemType.BLACK_ITEM);
	}
	
	@Override
	@Cacheable(value="whiteList")
	public Set<ListItem> getAllWhitelistItems() {
		return listItemRepository.findAllByListItemType(ListItemType.WHITE_ITEM);
	}
	
	@Override
	@Cacheable(value="listItems")
	public Set<ListItem> getAllListItems() {
		return listItemRepository.findAll();
	}
	

	@Override
	@CacheEvict(value = { "listItems", "whiteList", "blackList" }, allEntries = true)
	public void saveListItem(ListItem listItem) {
		listItemRepository.save(listItem);
	}


}
