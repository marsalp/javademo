package com.intertec.usermanager.controller;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intertec.usermanager.DTO.AjaxResponseBody;
import com.intertec.usermanager.DTO.ListItemDTO;
import com.intertec.usermanager.domain.ListItem;
import com.intertec.usermanager.domain.ListItemType;
import com.intertec.usermanager.services.api.IListItemService;

@RestController
public class ListItemController {
	
	@Autowired
	private IListItemService listItemService;
	
	private static final Logger logger = LoggerFactory.getLogger(ListItemController.class);
	
	@RequestMapping(value="/api/listItem/all", method=RequestMethod.GET)
	public ResponseEntity<Set<ListItem>> getAllListItem(){
    	logger.info("Returning all the List Items");
		return new ResponseEntity<Set<ListItem>>(listItemService.getAllListItems(), HttpStatus.OK);
	}
	
	@PostMapping("/api/listItem/register")
    public ResponseEntity<?> createListItem(
            @Valid @RequestBody ListItemDTO listItemDTO, Errors errors) {

        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            ajaxResponseBody.setMessage(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(ajaxResponseBody);

        }

		try {
			ListItem listItem = new ListItem();
			listItem.setValue(listItemDTO.getValue());
			listItem.setListItemType(ListItemType.valueOf(listItemDTO.getType()));
			listItemService.saveListItem(listItem);
			ajaxResponseBody.setMessage("success");
		} catch (Exception e) {
			ajaxResponseBody.setMessage("We had a internal error, please contact the admin");
			logger.error(e.getMessage(), e);
		}
        
        return ResponseEntity.ok(ajaxResponseBody);

    }

}
