package com.intertec.usermanager.controller;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intertec.usermanager.DTO.AjaxResponseBody;
import com.intertec.usermanager.DTO.SearchCriteria;
import com.intertec.usermanager.components.Messages;
import com.intertec.usermanager.exception.UserValidationException;
import com.intertec.usermanager.services.api.IUserService;

@RestController
public class UserNameValidatorController {
	 
	@Autowired
	IUserService userService;
	
	@Autowired
    Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(UserNameValidatorController.class);
	
    @PostMapping("/user/checkUsername")
    public ResponseEntity<?> checkUserName(
            @Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            ajaxResponseBody.setMessage(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(ajaxResponseBody);

        }

        Set<String> usernamesSuggested = new TreeSet<>();
        
		try {
			usernamesSuggested = userService.checkUserName(search.getUsername());
			if (usernamesSuggested.isEmpty()) {
	            ajaxResponseBody.setMessage(String.format(messages.get("user.available"), 
	            		search.getUsername()));
	        } else {
	            ajaxResponseBody.setMessage(String.format("user.not.available", 
	            		search.getUsername()));
	        }
			
	        ajaxResponseBody.setUsernamesSuggested(usernamesSuggested);
	        
		} catch (UserValidationException e) {
			ajaxResponseBody.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			ajaxResponseBody.setMessage("We had a internal error, please contact the admin");
			logger.error(e.getMessage(), e);
		}
        
        return ResponseEntity.ok(ajaxResponseBody);

    }
}
