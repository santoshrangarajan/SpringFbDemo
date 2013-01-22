package com.abstractlayers.demo.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="friends")
public class FriendsList {
	
	
	private List<String> friendsList;

	@XmlElement(name="friend")
	public List<String> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<String> friendsList) {
		this.friendsList = friendsList;
	}
	
}
