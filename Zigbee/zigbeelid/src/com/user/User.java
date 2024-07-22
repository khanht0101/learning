package com.user;

import DeviceStatus.EndDevice;
import FunctionSet.Billing.Billing;
import FunctionSet.Billing.CustomerAccountList;
import FunctionSet.DRLC.DRLC;
import FunctionSet.Messaging.Messaging;
import FunctionSet.Prepayment.Payment;
import FunctionSet.Pricing.Pricing;
import com.Device.Metering.Metering;
import com.DeviceType.DeviceType;
import java.io.Serializable;

/**
 * User Bean
 * 
 * @author  onlinetechvision.com
 * @since   24 Feb 2012
 * @version 1.0.0
 *
 */
public class User implements  Serializable{

	public long id;
	public String Name;
        public String keyNumber;
        public int Port;
	public String Ip;
        public DeviceType type;        
        public Metering Meter;
        public Pricing pricing = new Pricing();
        public EndDevice endDevice = new EndDevice();
        public Messaging messaging = new Messaging();
        public boolean IsUpdated = false;
        public Billing billing  = new Billing();
        public Payment payment = new Payment();
	public DRLC drlc = new DRLC();
        public User()
        {
            
        }
	/**
	 * Get User Id
	 * 
	 * @return long id
	 */
	public long getId() {
		return id;
	}
        public void setMetering(Metering meter)
        {
            this.Meter = meter;
        }
        public Metering getMetering()
        {
            return this.Meter;
        }
        
        public void setDeviceType(DeviceType type)
        {
            this.type = type;
        }
        
        public DeviceType getDeviceType()
        {
            return this.type;
        }
	/**
	 * Set User Id
	 * 
	 * @param long id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Get User Name
	 * 
	 * @return long id
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Set User Name
	 * 
	 * @param String name
	 */
	public void setName(String name) {
		this.Name = name;
	}

	/**
	 * Get User Surname
	 * 
	 * @return long id
	 */
	public String getIp() {
		return Ip;
	}

	/**
	 * Set User Surname
	 * 
	 * @param String surname
	 */
	public void setIp(String ip) {
		this.Ip = ip;
	}
		
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Id : ").append(getId());
		strBuilder.append(", Name : ").append(getName());
		strBuilder.append(", IP : ").append(getIp());
		return strBuilder.toString();
	}      
        
        public Pricing getpricingObject()
        {
            return pricing;
        }
         public EndDevice getendDeviceObject()
        {
            return endDevice;
        }
}
