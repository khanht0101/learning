/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import FunctionSet.Metering.*;
import FunctionSet.Prepayment.*;
import com.user.User;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Khanh
 */
public class RequestPutClass {
    public RequestPutClass() {} 
    public User currentUser = new  User();
    public List<String> SendPut(String cmdmsg) 
    {
        List<String> list = Arrays.asList(new String[]{"F",""});
        if(cmdmsg.indexOf("/upt/")>0)
        {
            return RequestMetering(cmdmsg);
        }
        if(cmdmsg.indexOf("/ppy/")>0)
        {
            return Prepayment(cmdmsg);
        }
         
        return list;
    }
    
    // start Put Prepayment
    private List<String> Prepayment(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 4 && msgList[3].equals("ab"))
        {
            return AccountBalance(cmdmsg);
        }
        if(msgList.length == 4 && msgList[3].equals("os"))
        {
            return PrepayOperationStatus(cmdmsg);
        }
        if(msgList.length == 5 && msgList[3].equals("si"))
        {
            return SupplyInterruptionOverride(cmdmsg);
        }
         
        return NoneResponse();
    }
    
    private List<String> AccountBalance(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            currentUser.payment.prepaymentList.Get(index).accountBalance = new AccountBalance();
            currentUser.IsUpdated = true;
            return CreatedResponse();
        }
        catch(Exception ex){}
         
        return NoneResponse();
    } 
    
    private List<String> PrepayOperationStatus(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            currentUser.payment.prepaymentList.Get(index).prepayOperationStatus = new PrepayOperationStatus();
            currentUser.IsUpdated = true;
            return CreatedResponse();
        }
        catch(Exception ex){}
         
        return NoneResponse();
    } 
    
    private List<String> SupplyInterruptionOverride(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int si = Integer.parseInt(msgList[4]);
            SupplyInterruptionOverrideList supplyInterruptionOverrideList = null;            
            supplyInterruptionOverrideList = currentUser.payment.prepaymentList.Get(index).supplyInterruptionOverrideList;
            
            if(supplyInterruptionOverrideList != null && supplyInterruptionOverrideList.Length()>-0)
            {               
                supplyInterruptionOverrideList.Remove(si);
                supplyInterruptionOverrideList.Insert(new SupplyInterruptionOverride(), index);
                currentUser.payment.prepaymentList.Get(index).supplyInterruptionOverrideList = supplyInterruptionOverrideList;
                currentUser.IsUpdated = true;
                return CreatedResponse();               
            }
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    
    // end of Put Prepayment
    private List<String> RequestMetering(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 6 && msgList[3].equals("mr") && msgList[5].equals("rt"))
        {
            return ResquestPutReadingType(cmdmsg);
        }
        if(msgList.length == 9 && msgList[3].equals("mr") && msgList[5].equals("rs") && msgList[7].equals("r"))
        {
            return ResquestPutReading(cmdmsg);
        }
         
        return NoneResponse();
    }
    private List<String> ResquestPutReadingType(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            MeterReadingList meterReadingList = null;            
            meterReadingList = currentUser.endDevice.usagePointList.Get(index).meterReadingList;
            
            if(meterReadingList != null)
            {
                int inx = Integer.parseInt(msgList[4]);
                if(meterReadingList.Length() > inx)
                {
                    meterReadingList.Get(inx).readingType = new ReadingType();
                    currentUser.endDevice.usagePointList.Get(index).meterReadingList = meterReadingList;
                    currentUser.IsUpdated = true;
                    return CreatedResponse();
                }
            }
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }    
    private List<String> ResquestPutReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int i =0;
            MeterReadingList meterReadingList = null;            
            meterReadingList = currentUser.endDevice.usagePointList.Get(index).meterReadingList;
            
            if(meterReadingList != null)
            {
                int inmr = Integer.parseInt(msgList[4]);
                int inrs = Integer.parseInt(msgList[6]);  
                int inr = Integer.parseInt(msgList[8]); 
                if(meterReadingList.Length() > inmr && meterReadingList.Get(inmr).readingSetList.Length()>inrs &&meterReadingList.Get(inmr).readingSetList.Get(inrs).readingList.Length()>inr)
                {
                    meterReadingList.Get(inmr).readingSetList.Get(inrs).readingList.Remove(inr);
                    meterReadingList.Get(inmr).readingSetList.Get(inrs).readingList.Insert(new Reading(),inr);
                    currentUser.endDevice.usagePointList.Get(index).meterReadingList = meterReadingList;
                    currentUser.IsUpdated = true;
                    return CreatedResponse();
                }
            }
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    
    private List<String> NoneResponse()
    {
        return Arrays.asList(new String[]{"F","HTTP 204 - No content"});
    }
    private List<String> CreatedResponse()
    {
        return Arrays.asList(new String[]{"T","HTTP 201 - Created"});
    }
     
}
