package com.server;

import com.user.DeviceInfo;
import java.util.List;

import com.user.User;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RMI User Service Interface
 * 
 * @author  onlinetechvision.com
 * @since   24 Feb 2012
 * @version 1.0.0
 *
 */
public interface IRMIUserService{

	/**
	 * Add User
	 * 
	 * @param  User user
	 * @return boolean response of the method
	 */
             
        public void setKeyNumber(String keyNumber);
	public boolean addUser(User user);
	public User getUser(long userID);
        public ConcurrentHashMap<Long, User> UserList();
        public List<String> SendRequest(String clientName, String cmdmsg);
        public List<String> SendRequest(String clientName, String cmdmsg, String context);
        
       public String GetName();
	/**
	 * Delete User
	 * 
	 * @param  User user
	 * @return boolean response of the method
	 */
	public boolean deleteUser(User user);
	
	
	/**
	 * Get User List
	 * 
	 * @return List user list
	 */
	public List<User> getUserList();
	
        public List<DeviceInfo> GetDeviceList();
        
        public void SetDevice(User user);
        
        public User GetDevice();
}
