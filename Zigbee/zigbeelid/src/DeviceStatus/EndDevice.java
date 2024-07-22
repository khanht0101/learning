/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeviceStatus;

import DeviceInformation.DeviceInformation;
import FunctionSet.Metering.UsagePointList;
import FunctionSetAssignments.*;
import Pub_Sub.Subscription;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class EndDevice implements Serializable
{
    public EndDevice() {}
    //public String  FlowReservationResponseListLink;
    public Subscription subscription = new Subscription();
    public Registration registration = new Registration();
    public FunctionSetAssignments functionSetAssignments = new FunctionSetAssignments();
    public DeviceInformation deviceInformation = new DeviceInformation();
    //public String FlowReservationRequestListLink;
    public DeviceStatus deviceStatus = new DeviceStatus();
    
    public UsagePointList usagePointList = new UsagePointList();
    
    public DeviceInformation getdeviceInformationObject()
    {
        return deviceInformation;
    }
    
    public DeviceStatus getdeviceStatusObject()
    {
        return deviceStatus;
    }
    
    public Subscription getsubscriptionObject()
    {
        return subscription;
    }
    public Registration getregistrationObject()
    {
        return registration;
    }
    public FunctionSetAssignments getfunctionSetAssignmentsObject()
    {
        return functionSetAssignments;
    }

}
