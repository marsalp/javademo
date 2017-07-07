package com.intertec.usermanager.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intertec.usermanager.components.Messages;
import com.intertec.usermanager.domain.ListItem;
import com.intertec.usermanager.domain.ListItemType;
import com.intertec.usermanager.domain.User;
import com.intertec.usermanager.exception.UserValidationException;
import com.intertec.usermanager.repository.ListItemRepository;
import com.intertec.usermanager.repository.UserRepository;
import com.intertec.usermanager.services.api.IUserService;
import com.intertec.usermanager.services.api.IUserServiceDelegate;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private IUserServiceDelegate userServiceDelegate = new UserServiceDelegate();
	
	@Mock
	private ListItemRepository listItemRepository;
	
	@Mock
	private Messages messages;
	
	@InjectMocks
	private IUserService userManagerService = new UserService();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkUserNameUserNameNullTest() throws UserValidationException{
		when(messages.get(anyString())).thenReturn("the username can not be null");
		userManagerService.checkUserName(null);
	}
	
	@Test(expected = UserValidationException.class)
	public void checkUserNameUserIsInBlackListTest() throws UserValidationException{
		Set<ListItem> blacklistItems = createMockBlackListItems();

		when(listItemRepository.findAllByListItemType(ListItemType.BLACK_ITEM)).
				thenReturn(blacklistItems);
		when(messages.get(anyString())).thenReturn("The word crack is not allowed");
		userManagerService.checkUserName("jhoncrack");
	}
	
	@Test
	public void checkUserNameUserExistTest() throws UserValidationException{
		User user = new User("mcsalas", "1234");
		
		when(userRepository.findByUserName("mcsalas")).thenReturn(user);
		
		String[] userNames = {"mcsalas2017", "mcsalasbold", "mcsalascold"};
		Set<String> userNamesSet = Arrays.stream(userNames).
				collect(Collectors.toCollection(TreeSet::new));
		when(userServiceDelegate.getSuggestUserNames(anyInt(), anyString())).
			thenReturn(userNamesSet);
		
		Set<String> userNamesSuggested = userManagerService.checkUserName("mcsalas");
		
		verify(userServiceDelegate, times(1)).getSuggestUserNames(anyInt(), anyString());
		verify(userRepository, times(1)).findByUserName("mcsalas");
		assertNotNull(userNamesSuggested);
		assertTrue(!userNamesSuggested.isEmpty());
	}
	
	
	
	@Test
	public void checkUserNameNonExistTest() throws UserValidationException{
		
		when(userRepository.findByUserName("mcsalas")).thenReturn(null);
		
		when(userServiceDelegate.getSuggestUserNames(anyInt(), anyString())).
			thenReturn(new TreeSet<>());
		
		Set<String> userNameList = userManagerService.checkUserName("mcsalas");
		verify(userRepository, times(1)).findByUserName("mcsalas");
		assertNotNull(userNameList);
		assertTrue(userNameList.isEmpty());
		
	}
	
	private static Set<ListItem> createMockBlackListItems() {
		Set<ListItem> listItems = new TreeSet<>();
		ListItem lisitem1 = new ListItem();
		lisitem1.setValue("cannabis");
		ListItem lisitem2 = new ListItem();
		lisitem2.setValue("abuse");
		ListItem lisitem3 = new ListItem();
		lisitem3.setValue("crack");
		listItems.add(lisitem1);
		listItems.add(lisitem2);
		listItems.add(lisitem3);
		return listItems;
	}

}
