package com.intertec.usermanager.DTO;

import java.io.Serializable;

public class ListItemDTO implements Serializable{
	
	private static final long serialVersionUID = -1825028404923344903L;

	private String value;
	
	private String type;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
