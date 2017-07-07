package com.intertec.usermanager.util;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.intertec.usermanager.domain.ListItem;


public class UserUtil {
	
	public static String generateUniqueUserName(Set<ListItem> whiteList, String name
			) {
		Random rn = new Random();
		String suggestUsername = StringUtils.EMPTY;
		
		if (whiteList != null ) {
			Iterator<ListItem> iterator = whiteList.iterator();
			if(iterator.hasNext()){
				ListItem suggest = iterator.next();
				suggestUsername =  name + suggest.getValue();
				whiteList.remove(suggest);
			}
		} else {
			int randomNumber = rn.nextInt(999 - 1 + 1) + 1;
			suggestUsername = name + randomNumber;
		}
		System.out.println("nombre sugerido: " + suggestUsername);
		return suggestUsername;
	}

}
