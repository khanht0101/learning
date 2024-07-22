/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import FunctionSet.Metering.MeterReading;
import FunctionSet.Metering.MeterReadingList;
import FunctionSet.Metering.Reading;
import FunctionSet.Metering.ReadingSet;
import com.user.User;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Khanh
 */
public class RequestDeleteClass {
    public RequestDeleteClass() {}   
    public User currentUser = new User();
    public List<String> SendDelete(String cmdmsg) 
    {
        List<String> list = Arrays.asList(new String[]{"F",""});
        if(cmdmsg.indexOf("/upt/")>0)
        {
            return RequestMetering(cmdmsg);
        }
        if(cmdmsg.indexOf("/tp/")>0)
        {
            return RequestPricing(cmdmsg);
        }
        if(cmdmsg.indexOf("/msg/")>0)
        {
            return RequestMessaging(cmdmsg);
        }
        if(cmdmsg.indexOf("/bill/")>0)
        {
            return DeleteBilling(cmdmsg);
        }
        if(cmdmsg.indexOf("/ppy/")>0)
        {
            return Prepayment(cmdmsg);
        }
         
        return list;
    }
    
    // Start Delete Perpayment
    private List<String> Prepayment(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 5 && msgList[3].equals("si"))
        {
            return SupplyInterruptionOverride(cmdmsg);
        }
        if(msgList.length == 5 && msgList[3].equals("cr"))
        {
            return CreditRegister(cmdmsg);
        }
         
        return NoneResponse();
    }
    
    private List<String> SupplyInterruptionOverride(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int si = Integer.parseInt(msgList[4]);
            currentUser.payment.prepaymentList.Get(index).supplyInterruptionOverrideList.Remove(si);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
              
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    
    private List<String> CreditRegister(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int cr = Integer.parseInt(msgList[4]);
            currentUser.payment.prepaymentList.Get(index).creditRegisterList.Remove(cr);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
             
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    // End of Delete Prepayment
    //-- Delete Billing
    private List<String> DeleteBilling(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 5 && msgList[3].equals("ca"))
        {
            return DeleteCustomerAgreement(cmdmsg);
        } 
        if(msgList.length == 7 && msgList[3].equals("ca") && msgList[5].equals("bp"))
        {
            return DeleteBillingPeriod(cmdmsg);
        }
        if(msgList.length == 7 && msgList[3].equals("ca") && msgList[5].equals("pro"))
        {
            return DeleteProjectionReading(cmdmsg);
        }
        if(msgList.length == 7 && msgList[3].equals("ca") && msgList[5].equals("tar"))
        {
            return DeleteTargetReading(cmdmsg);
        }
        if(msgList.length == 7 && msgList[3].equals("ca") && msgList[5].equals("ver"))
        {
            return DeleteHistoricalReading(cmdmsg);
        }
        if(msgList.length == 9 && msgList[3].equals("ca") && msgList[5].equals("ver") && msgList[7].equals("brs"))
        {
            return DeleteBillingReadingSet(cmdmsg);
        }
        if(msgList.length == 11 && msgList[3].equals("ca") && msgList[5].equals("ver") && msgList[7].equals("brs") && msgList[9].equals("br"))
        {
            return DeleteBillingReading(cmdmsg);
        }
         
        return NoneResponse();
    }
    private List<String> DeleteCustomerAgreement(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Remove(ca);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
             
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    private List<String> DeleteBillingPeriod(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int bp = Integer.parseInt(msgList[6]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).billingPeriodList.Remove(bp);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
              
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    private List<String> DeleteProjectionReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int pro = Integer.parseInt(msgList[6]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).projectionReadingList.Remove(pro);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
               
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    private List<String> DeleteHistoricalReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int ver = Integer.parseInt(msgList[6]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Remove(ver);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
                
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    private List<String> DeleteBillingReadingSet(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int ver = Integer.parseInt(msgList[6]);
            int brs = Integer.parseInt(msgList[8]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Get(ver).billingMeterReadingBase.billingReadingSetList.Remove(brs);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
                
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    private List<String> DeleteBillingReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int ver = Integer.parseInt(msgList[6]);
            int brs = Integer.parseInt(msgList[8]);
            int br = Integer.parseInt(msgList[10]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Get(ver).billingMeterReadingBase.billingReadingSetList.Get(brs).billingReadingList.Remove(br);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
                
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    private List<String> DeleteTargetReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int tar = Integer.parseInt(msgList[6]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).targetReadingList.Remove(tar);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
                
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    //-- End of Delete Billing
    
    private List<String> RequestMetering(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 5 && msgList[3].equals("mr"))
        {
            return ResquestDeleteMerteringReading(cmdmsg);
        }
        if(msgList.length == 7 && msgList[3].equals("mr") && msgList[5].equals("rs"))
        {
            return ResquestDeleteReadingSet(cmdmsg);
        }
        if(msgList.length == 9 && msgList[3].equals("mr") && msgList[5].equals("rs") && msgList[5].equals("r"))
        {
            return ResquestDeleteReading(cmdmsg);
        }
         
        return NoneResponse();
    }
    private List<String> ResquestDeleteMerteringReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            long index = Long.parseLong(msgList[2]);
            currentUser.endDevice.usagePointList.Get((int)index).meterReadingList.Remove(Integer.parseInt(msgList[4]));
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
                
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
     
     private List<String> ResquestDeleteReadingSet(String cmdmsg)
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
                    meterReadingList.Get(inx).readingSetList.Remove(Integer.parseInt(msgList[6]));
                    currentUser.endDevice.usagePointList.Get(index).meterReadingList = meterReadingList;
                    currentUser.IsUpdated = true;
                    return CreatedResponse();
                }
            }
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
     
    private List<String> ResquestDeleteReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            MeterReadingList meterReadingList = null;            
            meterReadingList = currentUser.endDevice.usagePointList.Get(index).meterReadingList;
            
            if(meterReadingList != null)
            {
                int inmr = Integer.parseInt(msgList[4]);
                int inrs = Integer.parseInt(msgList[6]);                
                if(meterReadingList.Length() > inmr && meterReadingList.Get(inmr).readingSetList.Length()>inrs)
                {
                    meterReadingList.Get(inmr).readingSetList.Get(inrs).readingList.Remove(Integer.parseInt(msgList[8]));
                    currentUser.endDevice.usagePointList.Get(index).meterReadingList = meterReadingList;
                    currentUser.IsUpdated = true;
                    return CreatedResponse();
                }
            }
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
       
       
    private List<String> RequestPricing(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 5 && msgList[3].equals("rc"))
        {
            return ResquestDeleteRateComponent(cmdmsg);
        }
        if(msgList.length == 7 && msgList[3].equals("rc") && msgList[5].equals("tti"))
        {
            return ResquestDeleteTimeTariffInterval(cmdmsg);
        }
        if(msgList.length == 9 && msgList[3].equals("rc") && msgList[5].equals("tti") && msgList[5].equals("cti"))
        {
            return ResquestDeleteConsumptionTariffInterval(cmdmsg);
        }
         
        return NoneResponse();
    }
    
    private List<String> ResquestDeleteRateComponent(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Remove(inrc);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
               
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    
    private List<String> ResquestDeleteTimeTariffInterval(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            int intti = Integer.parseInt(msgList[6]);
            currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Get(inrc).timeTariffIntervalList.Remove(intti);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
                
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    
    private List<String> ResquestDeleteConsumptionTariffInterval(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            int intti = Integer.parseInt(msgList[6]);
            int incti = Integer.parseInt(msgList[8]);
            currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Get(inrc).timeTariffIntervalList.Get(intti).conTariffIntervalList.Remove(incti);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
                
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    
    private List<String> RequestMessaging(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 5 && msgList[3].equals("txt"))
        {
            return ResquestDeleteTextMessagin(cmdmsg);
        }        
         
        return NoneResponse();
    }
    
    private List<String> ResquestDeleteTextMessagin(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int intxt = Integer.parseInt(msgList[4]);
            currentUser.messaging.messagingProgramList.Get(index).activeTextMessageList.Remove(intxt);
                    currentUser.IsUpdated= true;
                    return CreatedResponse();
                
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
        return Arrays.asList(new String[]{"T","HTTP 201 - Deleted"});
    }
     
}
