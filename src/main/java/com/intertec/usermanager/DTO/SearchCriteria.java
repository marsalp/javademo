package com.intertec.usermanager.DTO;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria implements Serializable{

	private static final long serialVersionUID = 6484337083101969964L;
	
	@NotBlank(message = "{empty.username}")
    @Size(min=6, max=20, message="{username.size.non.allowed}")
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}