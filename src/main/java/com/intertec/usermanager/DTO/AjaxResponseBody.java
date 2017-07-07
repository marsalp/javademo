package com.intertec.usermanager.DTO;

import java.io.Serializable;
import java.util.Set;




public class AjaxResponseBody implements Serializable{
	
	private static final long serialVersionUID = -8448612364553249382L;

	private String message;
 
 	private Set<String> usernamesSuggested;

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Set<String> getUsernamesSuggested() {
		return usernamesSuggested;
	}
	
	public void setUsernamesSuggested(Set<String> usernamesSuggested) {
		this.usernamesSuggested = usernamesSuggested;
	}
	 
	
	
	 

}
