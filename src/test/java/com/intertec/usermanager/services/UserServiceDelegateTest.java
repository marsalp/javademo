package com.intertec.usermanager.services;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intertec.usermanager.domain.ListItem;
import com.intertec.usermanager.domain.ListItemType;
import com.intertec.usermanager.domain.User;
import com.intertec.usermanager.repository.ListItemRepository;
import com.intertec.usermanager.repository.UserRepository;
import com.intertec.usermanager.services.api.IUserServiceDelegate;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceDelegateTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ListItemRepository listItemRepository;
	
	@InjectMocks
	private IUserServiceDelegate userServiceDelegate = new UserServiceDelegate();
	
	@Test
	public void getSuggestUserNamesAllFromWhiteListTest() {
		
		Set<ListItem> listItems1 = createMockListItems();
		
		when(listItemRepository.findAllByListItemType(ListItemType.WHITE_ITEM)).
				thenReturn(listItems1);
		User user1 = null;
		User user2 = null;
		when(userRepository.findByUserName(anyString())).thenReturn(user1, user2);
		Set<String> userNamesSuggested = userServiceDelegate.getSuggestUserNames(2, "Jhon");
		verify(listItemRepository, times(1)).findAllByListItemType(ListItemType.WHITE_ITEM);
		verify(userRepository, times(2)).findByUserName(anyString());
		assertNotNull(userNamesSuggested);
		assertTrue(!userNamesSuggested.isEmpty());
		System.out.println(userNamesSuggested);
	}
	
	@Test
	public void getSuggestUserNamesAfewFromWhiteListTest() {
		
		Set<ListItem> listItems1 = createMockListItems();
		
		when(listItemRepository.findAllByListItemType(ListItemType.WHITE_ITEM)).
				thenReturn(listItems1);
		User user1 = null;
		User user2 = new User("Jhon", "1234");
		User user3 = null;
		when(userRepository.findByUserName(anyString())).thenReturn(user1, user2, user3);
		Set<String> userNamesSuggested = userServiceDelegate.getSuggestUserNames(2, "Jhon");
		verify(listItemRepository, times(1)).findAllByListItemType(ListItemType.WHITE_ITEM);
		verify(userRepository, times(3)).findByUserName(anyString());
		assertNotNull(userNamesSuggested);
		assertTrue(!userNamesSuggested.isEmpty());
		System.out.println(userNamesSuggested);
	}

	
	
	private static Set<ListItem> createMockListItems(ListItem... alistItem) {
		Set<ListItem> listItems = new TreeSet<>();
		ListItem lisitem1 = new ListItem();
		lisitem1.setValue("blond");
		ListItem lisitem2 = new ListItem();
		lisitem2.setValue("black");
		ListItem lisitem3 = new ListItem();
		lisitem3.setValue("2017");
		listItems.add(lisitem1);
		listItems.add(lisitem2);
		listItems.add(lisitem3);
		if(alistItem != null && alistItem.length != 0) {
			Set<ListItem> listItemscopy = new TreeSet<>();
			for (ListItem item : alistItem) {
				listItemscopy.addAll(listItems);
				listItemscopy.remove(item);
			}
			return listItemscopy;
			
		}
		return listItems;
		
	}
	
	

}
