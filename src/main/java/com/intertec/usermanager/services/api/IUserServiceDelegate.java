package com.intertec.usermanager.services.api;

import java.util.Set;

public interface IUserServiceDelegate {
	
	Set<String> getSuggestUserNames(int numbers, String username);

}
