package com.intertec.usermanager.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class define encapsu
 * @author mcsalas
 *
 */
@Entity
public class ListItem implements Serializable, Comparable<ListItem> {

	private static final long serialVersionUID = -490014487592278205L;
	
	@Id
	@GeneratedValue
	private long Id;
	
	private String value;
	
	private ListItemType listItemType;
	
	public ListItem(){
		
	}
	
	public ListItem(String valor, ListItemType listItemType) {
		this.value = valor;
		this.listItemType = listItemType;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ListItemType getListItemType() {
		return listItemType;
	}

	public void setListItemType(ListItemType listItemType) {
		this.listItemType = listItemType;
	}

	@Override
	public int compareTo(ListItem otherObject) {
		return  this.value.compareTo(otherObject.value);
	}

	@Override
	public String toString() {
		return "ListItem [valor=" + value + ", listItemType=" + listItemType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listItemType == null) ? 0 : listItemType.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListItem other = (ListItem) obj;
		if (listItemType != other.listItemType)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	

}
