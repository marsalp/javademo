package com.intertec.usermanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.intertec.usermanager.domain.ListItem;
import com.intertec.usermanager.domain.ListItemType;
import com.intertec.usermanager.domain.User;
/**
 * This class is used to load dummy information
 * @author mcsalas
 *
 */
@Component
public class DataBaseLoader implements CommandLineRunner {
	
	private final UserRepository userRepository;
	
	private final ListItemRepository listItemRepository;
	
	@Autowired
	public DataBaseLoader(UserRepository userRepository, 
			ListItemRepository listItemRepository) {
		this.userRepository = userRepository;
		this.listItemRepository = listItemRepository;
	}

	@Override
	public void run(String... params) throws Exception {
		this.userRepository.save(new User("jhonjhon", null));
		ListItem blackItem = new ListItem();
		blackItem.setValue("crack");
		blackItem.setListItemType(ListItemType.BLACK_ITEM);
		
		ListItem blackItem1 = new ListItem();
		blackItem1.setValue("cannabis");
		blackItem1.setListItemType(ListItemType.BLACK_ITEM);
		
		ListItem blackItem2 = new ListItem();
		blackItem2.setValue("abuse");
		blackItem2.setListItemType(ListItemType.BLACK_ITEM);
		
		ListItem blackItem3 = new ListItem();
		blackItem3.setValue("drunk");
		blackItem3.setListItemType(ListItemType.BLACK_ITEM);
		
		this.listItemRepository.save(blackItem);
		this.listItemRepository.save(blackItem1);
		this.listItemRepository.save(blackItem2);
		this.listItemRepository.save(blackItem3);
		
		ListItem whiteItem = new ListItem();
		whiteItem.setValue("cool");
		whiteItem.setListItemType(ListItemType.WHITE_ITEM);
		
		ListItem whiteItem2 = new ListItem();
		whiteItem2.setValue("awesome");
		whiteItem2.setListItemType(ListItemType.WHITE_ITEM);
		
		ListItem whiteItem3 = new ListItem();
		whiteItem3.setValue("2017");
		whiteItem3.setListItemType(ListItemType.WHITE_ITEM);
		
		ListItem whiteItem4 = new ListItem();
		whiteItem4.setValue("thebest");
		whiteItem4.setListItemType(ListItemType.WHITE_ITEM);
		
		this.listItemRepository.save(whiteItem);
		this.listItemRepository.save(whiteItem2);
		this.listItemRepository.save(whiteItem3);
		this.listItemRepository.save(whiteItem4);

	}

}
