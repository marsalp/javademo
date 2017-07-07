package com.intertec.usermanager.repository;

import java.util.Set;

import org.springframework.data.repository.Repository;

import com.intertec.usermanager.domain.ListItem;
import com.intertec.usermanager.domain.ListItemType;

public interface ListItemRepository extends Repository<ListItem, Long> {
	
	Set<ListItem> findAllByListItemType(ListItemType listItemType);
	
	Set<ListItem> findAll();

	void save(ListItem listItem);
	

}
