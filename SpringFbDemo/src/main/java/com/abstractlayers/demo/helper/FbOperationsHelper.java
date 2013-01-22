package com.abstractlayers.demo.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;

import com.abstractlayers.demo.dto.FriendsList;

public class FbOperationsHelper {
	
	public FriendsList getFriendNames(Facebook facebook){
		
		List<String> friendsList = new ArrayList<String>();
		  List<FacebookProfile> friendsProfileLIst = facebook.friendOperations().getFriendProfiles();
		  
		  for(FacebookProfile fbProfile :friendsProfileLIst){
			  friendsList.add(fbProfile.getFirstName());
		  }
		  
		  FriendsList friendsListDto = new FriendsList();
		  friendsListDto.setFriendsList(friendsList);
		  
		  return friendsListDto;  
	}

}
