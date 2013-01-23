package com.abstractlayers.demo.helper;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;

import com.abstractlayers.demo.controller.FacebookController;

public class FbConnectionHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(FbConnectionHelper.class);
	
	//@Autowired
	//private ConnectionRepository userConnectionRepository;
	
	@Autowired
	private ConnectionFactoryRegistry connectionFactoryRegistry;
	
	
	/*
	 * if providerUserId not found, throws execution
	*/
	private boolean checkConnectionDataExists(String providerUserId, ConnectionRepository userConnectionRepository)  {
		logger.info("getConnectionData -"+providerUserId);
		try{
		  Connection<Facebook> existingFacebookConnection = userConnectionRepository.getConnection(Facebook.class, providerUserId);
		 // ConnectionData existingConnectionData = existingFacebookConnection.createData();
		  return true;
		} catch (Exception ex){
			logger.error("Could not find connection info for"+providerUserId,ex);
			return false;
		}
         
	}
	
	public boolean checkForUserInRepository(String providerUserId , ConnectionRepository userConnectionRepository){
		return checkConnectionDataExists(providerUserId,userConnectionRepository);
	}
	
    public void updateExistingConnectionInRepository(String providerUserId, String facebookAccessToken,ConnectionRepository userConnectionRepository ){
    	 logger.info("updateExistingConnection -"+facebookAccessToken);
		 ConnectionFactory<Facebook> facebookConnectionFactory = connectionFactoryRegistry.getConnectionFactory(Facebook.class);
		 ConnectionData existingConnectionData = userConnectionRepository.getConnection(Facebook.class, providerUserId).createData();
		 ConnectionData  updatedConnectionData = new ConnectionData(existingConnectionData.getProviderId(),
                                                                   existingConnectionData.getProviderUserId(), 
                                                                   existingConnectionData.getDisplayName(), 
												                   existingConnectionData.getProfileUrl(), 
												                   existingConnectionData.getImageUrl(), 
												                   facebookAccessToken, 
												                   existingConnectionData.getSecret(),
												                   existingConnectionData.getRefreshToken(),
												                   getNewExpireTime());
		
		  Connection<Facebook> newfacebookConnection = facebookConnectionFactory.createConnection(updatedConnectionData);
		  userConnectionRepository.updateConnection(newfacebookConnection);
	}

    public void addNewConnectionToRepository(String providerUserId, String facebookAccessToken , ConnectionRepository userConnectionRepository){
    	logger.info("addNewConnection -"+facebookAccessToken);
		ConnectionFactory<Facebook> facebookConnectionFactory = connectionFactoryRegistry.getConnectionFactory(Facebook.class);
		ConnectionData updatedConnectionData = new ConnectionData("facebook",
                                                                 providerUserId, 
												                  null, 
												                null, 
												                null, 
												                facebookAccessToken, 
												                null,
												                null,
												                getNewExpireTime());

          Connection<Facebook> newfacebookConnection = facebookConnectionFactory.createConnection(updatedConnectionData);
          userConnectionRepository.addConnection(newfacebookConnection);
     }
	
	
	
	/*private void updateExistingConnection(String providerUserId, String facebookAccessToken, ConnectionRepository userConnectionRepository){
		 logger.info("updateExistingConnection -"+facebookAccessToken);
		 ConnectionFactory<Facebook> facebookConnectionFactory = connectionFactoryRegistry.getConnectionFactory(Facebook.class);
		 ConnectionData existingConnectionData = userConnectionRepository.getConnection(Facebook.class, providerUserId).createData();
		 ConnectionData  updatedConnectionData = new ConnectionData(existingConnectionData.getProviderId(),
                                                                   existingConnectionData.getProviderUserId(), 
                                                                   existingConnectionData.getDisplayName(), 
												                   existingConnectionData.getProfileUrl(), 
												                   existingConnectionData.getImageUrl(), 
												                   facebookAccessToken, 
												                   existingConnectionData.getSecret(),
												                   existingConnectionData.getRefreshToken(),
												                   getNewExpireTime());
		
		  Connection<Facebook> newfacebookConnection = facebookConnectionFactory.createConnection(updatedConnectionData);
		  userConnectionRepository.updateConnection(newfacebookConnection);
		
	}
	
	private void addNewConnection(String providerUserId, String facebookAccessToken, ConnectionRepository userConnectionRepository) {
		logger.info("addNewConnection -"+facebookAccessToken);
		ConnectionFactory<Facebook> facebookConnectionFactory = connectionFactoryRegistry.getConnectionFactory(Facebook.class);
		ConnectionData updatedConnectionData = new ConnectionData("facebook",
                                                                 providerUserId, 
												                  null, 
												                null, 
												                null, 
												                facebookAccessToken, 
												                null,
												                null,
												                getNewExpireTime());

          Connection<Facebook> newfacebookConnection = facebookConnectionFactory.createConnection(updatedConnectionData);
          userConnectionRepository.addConnection(newfacebookConnection);
	}*/
	
	
	/*private void doAutoLogin(String username, String password, HttpServletRequest request) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
		SecurityContextHolder.getContext().getAuthentication().
		GrantedAuthority [] newGrantedAuth = new GrantedAuthority[2];
		newGrantedAuth[0] = new GrantedAuthorityImpl("ROLE_ADMIN");
		newGrantedAuth[0] = new GrantedAuthorityImpl("ROLE_SUPERUSER");

		SecurityContextHolder.getContext().setAuthentication( new UsernamePasswordAuthenticationToken(principal, credentials)); 
	}*/
	
	
	private long getNewExpireTime(){
		  Calendar calendar = Calendar.getInstance();
		  Date today = calendar.getTime();
		  calendar.add(Calendar.DAY_OF_YEAR, 30);
		  Date expireTime = calendar.getTime();
		  return expireTime.getTime();
	}

}
