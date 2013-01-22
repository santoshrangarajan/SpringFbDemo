package com.abstractlayers.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abstractlayers.demo.dto.FriendsList;
import com.abstractlayers.demo.helper.FbConnectionHelper;
import com.abstractlayers.demo.helper.FbOperationsHelper;


@Controller
@RequestMapping("/fb")
public class FacebookController {
	
	private static final Logger logger = LoggerFactory.getLogger(FacebookController.class);
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Autowired
	private FbConnectionHelper fbConnectionHelper;
	
	@Autowired
	private FbOperationsHelper fbOperationsHelper;
	
	/*@ModelAttribute("source")
	public String source() {
		return "fb";
	}*/
	

	@RequestMapping(value="/profile")
	public String getProfile(ModelMap model) {
		try {
			Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();		
			model.addAttribute("profileLink", facebook.userOperations().getUserProfile().getLink());
			model.addAttribute("profileInfo", facebook.userOperations().getUserProfile());
			return "facebook/profile";
		}  catch (NotConnectedException e) {
			return "connect/facebookConnect";
		}
	}
	
	
	
	@RequestMapping(value="/friends/{providerUserId}/{facebookAccessToken}", method = RequestMethod.GET)
	public String getFriends(@PathVariable("facebookAccessToken") String facebookAccessToken, 
			                          @PathVariable("providerUserId") String providerUserId,
			                          Model model){
		
		  logger.info("providerUserId = "+providerUserId);
		  logger.info("facebookAccessToken = "+facebookAccessToken); 
		  ConnectionData connectionData = fbConnectionHelper.getConnectionData(providerUserId);
		  if(connectionData !=null) {
			  fbConnectionHelper.updateExistingConnection(connectionData, facebookAccessToken);
		  } else {
			  fbConnectionHelper.addNewConnection(providerUserId, facebookAccessToken);
		  }
		  logger.info("Update repository with new Token");
		  Facebook facebook =connectionRepository.getConnection(Facebook.class, providerUserId).getApi();
		  logger.info("Got new connection");
		  
		  FriendsList friendsListDto = fbOperationsHelper.getFriendNames(facebook);
		
		  model.addAttribute("friendsListDto",friendsListDto);//// for xml
		  model.addAttribute("friendsList",friendsListDto.getFriendsList()); //// for jsp
		  
		  return "facebook/friends";
	}
	
}
