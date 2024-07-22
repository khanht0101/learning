package com.server;

import FunctionSet.Metering.MeterReading;
import FunctionSet.Metering.ReadingSet;
import FunctionSet.Metering.ReadingSetList;
import com.cache.service.ICacheService;
import com.user.DeviceInfo;
import com.user.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.log4j.Logger;

/**
 * RMI User Service Implementation
 * 
 * @author  onlinetechvision.com
 * @since   24 Feb 2012
 * @version 1.0.0
 *
 */
public class RMIUserService implements IRMIUserService {

	private static Logger logger = Logger.getLogger(RMIUserService.class);
	private String keyNumber ="";
	//Remote Cache Service is injected...
	ICacheService cacheService;
        
        ConcurrentHashMap<Long, User> userMap = new ConcurrentHashMap<>();
        RequestGetClass requestGet = new RequestGetClass();
        RequestDeleteClass requestDelete = new RequestDeleteClass();
        RequestPostClass requestPost = new RequestPostClass();
        RequestPutClass requestPut = new RequestPutClass();
        User DeviceUser = new User();
	
        public String GetName()
        {
            return "Nguyen Cong Khanh";
        }
        public void ConnectedHost() 
        {      
              DisplayConsole.treeRoot.removeAllChildren();
              for(User user:userMap.values())
              { 
                    javax.swing.tree.DefaultMutableTreeNode newHost = new javax.swing.tree.DefaultMutableTreeNode(user.getName());

                    javax.swing.tree.DefaultMutableTreeNode ipNode = new javax.swing.tree.DefaultMutableTreeNode("IP: "+user.getIp());
                    newHost.add(ipNode);

                    javax.swing.tree.DefaultMutableTreeNode nameNode = new javax.swing.tree.DefaultMutableTreeNode("Name:" +user.getName());
                    newHost.add(nameNode);
                    DisplayConsole.treeRoot.add(newHost);
              }
              DisplayConsole.treeRoot.setAllowsChildren(true);
          }
	/**
	 * Add User
	 * 
	 * @param  User user
	 * @return boolean response of the method
	 */
        @Override
	public boolean addUser(User user) {
           
            if(!user.keyNumber.equals(keyNumber))
                return false;
            User oldUser = userMap.get(user.getId());            
            userMap.put(user.getId(), user);            
            if(oldUser != null)
            { 
                if(!oldUser.getName().equals(user.getName()))
                {
                    DisplayConsole.uiConsole.append("Client " + oldUser.getName()+" changed name to "+user.getName()+".\n");
                    ConnectedHost();
                }
            }
            else
            {
                DisplayConsole.uiConsole.append("Client " + user.getName()+" joined to network.\n");
                ConnectedHost();
            }
            return true;
            
	}
         @Override
    public User getUser(long userID) 
        {
            return userMap.get(userID); 
        }
public ConcurrentHashMap<Long, User> UserList()
{
    return userMap;
}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 * @return boolean response of the method
	 */
        public boolean deleteUser(User user) {
		userMap.remove(user.getId());
		DisplayConsole.uiConsole.append("Client "+user.getName()+" disconnected to network.\n");
                ConnectedHost();
		return true;
	}

	/**
	 * Get User List
	 * 
	 * @return List user list
	 */
	public List<User> getUserList() {
		List<User> list = new ArrayList<User>();
                list.addAll(userMap.values());
		//list.addAll(getCacheService().getUserMap().values());                
		logger.debug("User List : "+list);
		return list;
	}
      

	/**
	 * Get RMI User Service
	 * 
	 * @return IRMIUserService RMI User Service
	 */
	public ICacheService getCacheService() {
		return cacheService;
	}

	/**
	 * Set RMI User Service
	 * 
	 * @param IRMIUserService RMI User Service
	 */
	public void setCacheService(ICacheService cacheService) {
		this.cacheService = cacheService;
	}
    private void ShowCommandMessage(String clientName, String cmsg)
    {
       if(DisplayConsole.uiConsole!=null)
       {
           Date date = new Date();
           DisplayConsole.uiConsole.append("["+date.toString()+"] "+clientName + " sent command :"+ cmsg+"\n");
       }
    }
    @Override  
    
    public List<String> SendRequest(String clientName, String cmdmsg) 
    {
        ShowCommandMessage(clientName, cmdmsg);
        List<String> list = Arrays.asList(new String[]{"F",""});
        cmdmsg = cmdmsg.replaceAll(" ", "");
        if(cmdmsg.indexOf("get/") == 0)
        {
            requestGet.currentUser = DeviceUser;
            return requestGet.SendGet(cmdmsg);
        }
        if(cmdmsg.indexOf("post/") == 0)
        {
            requestPost.currentUser = DeviceUser;
            list = requestPost.SendPost(cmdmsg);
            if(requestPost.currentUser.IsUpdated)
            {
                DeviceUser = requestPost.currentUser;
            }
            return list;
        }
        if(cmdmsg.indexOf("put/") == 0)
        {
            requestPut.currentUser = DeviceUser;
            list = requestPut.SendPut(cmdmsg);
            if(requestPut.currentUser.IsUpdated)
            {
               DeviceUser = requestPut.currentUser;
            }
            return list;
        }
        if(cmdmsg.indexOf("delete/") == 0)
        {
            requestDelete.currentUser = DeviceUser;
            list = requestDelete.SendDelete(cmdmsg);
            DeviceUser = requestDelete.currentUser;
            return list;
        }
        return list;
    }

    @Override
    public List<DeviceInfo> GetDeviceList() 
    {
       List<DeviceInfo> deviceList = new ArrayList<DeviceInfo>();
       for(User user:userMap.values())
       {
          DeviceInfo newDevice = new DeviceInfo();
          newDevice.Name = user.Name;
          newDevice.IP = user.Ip;
          newDevice.Port = user.Port;
          deviceList.add(newDevice);
       }
       return deviceList;
    }    

    @Override
    public void SetDevice(User user) 
    {
        DeviceUser = user;
    }

    @Override
    public User GetDevice() 
    {
        return DeviceUser;
    }

    @Override
    public void setKeyNumber(String keynumber) 
    {
        keyNumber = keynumber;
    }

    @Override
    public List<String> SendRequest(String clientName, String cmdmsg, String context) 
    {
       ShowCommandMessage(clientName, cmdmsg);
        List<String> list = Arrays.asList(new String[]{"F",""});
        cmdmsg = cmdmsg.replaceAll(" ", "");
        if(cmdmsg.indexOf("get/") == 0)
        {
            requestGet.currentUser = DeviceUser;
            return requestGet.SendGet(cmdmsg);
        }
        if(cmdmsg.indexOf("post/") == 0)
        {
            requestPost.currentUser = DeviceUser;
            list = requestPost.SendPostCommand(cmdmsg, context);
            if(requestPost.currentUser.IsUpdated)
            {
                DeviceUser = requestPost.currentUser;
            }
            return list;
        }
        if(cmdmsg.indexOf("put/") == 0)
        {
            requestPut.currentUser = DeviceUser;
            list = requestPut.SendPut(cmdmsg);
            if(requestPut.currentUser.IsUpdated)
            {
               DeviceUser = requestPut.currentUser;
            }
            return list;
        }
        if(cmdmsg.indexOf("delete/") == 0)
        {
            requestDelete.currentUser = DeviceUser;
            list = requestDelete.SendDelete(cmdmsg);
            DeviceUser = requestDelete.currentUser;
            return list;
        }
        return list;
    }
}
