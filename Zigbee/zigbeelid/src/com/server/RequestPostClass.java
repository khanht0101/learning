/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import Common.XmlSerialize;
import FunctionSet.Billing.BillingPeriod;
import FunctionSet.Billing.BillingReading;
import FunctionSet.Billing.BillingReadingSet;
import FunctionSet.Billing.CustomerAgreement;
import FunctionSet.Billing.HistoricalReading;
import FunctionSet.Billing.ProjectionReading;
import FunctionSet.Billing.TargetReading;
import FunctionSet.Messaging.TextMessage;
import FunctionSet.Metering.MeterReading;
import FunctionSet.Metering.MeterReadingList;
import FunctionSet.Metering.Reading;
import FunctionSet.Metering.ReadingSet;
import FunctionSet.Metering.UsagePoint;
import FunctionSet.Prepayment.CreditRegister;
import FunctionSet.Prepayment.SupplyInterruptionOverride;
import FunctionSet.Pricing.ConsumptionTariffInterval;
import FunctionSet.Pricing.RateComponent;
import FunctionSet.Pricing.TimeTariffInterval;
import com.user.User;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Khanh
 */
public class RequestPostClass {
     public User currentUser = new User();
     public List<String> SendPost(String cmdmsg) 
    {
        List<String> list = Arrays.asList(new String[]{"F",""});
        if(cmdmsg.indexOf("/upt/")>0)
        {
            return RequestMetering(cmdmsg, "");
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
            return PostBilling(cmdmsg);
        }
        if(cmdmsg.indexOf("/ppy/")>0)
        {
            return Prepayment(cmdmsg);
        }
        return list;
    }
     
      public List<String> SendPostCommand(String cmdmsg,String context) 
    {
        List<String> list = Arrays.asList(new String[]{"F",""});
        if(cmdmsg.indexOf("/upt")> 0)
        {
            return RequestMetering(cmdmsg, context);
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
            return PostBilling(cmdmsg);
        }
        if(cmdmsg.indexOf("/ppy/")>0)
        {
            return Prepayment(cmdmsg);
        }
        return list;
    }
     
     // Start Post Prepayment
     private List<String> Prepayment(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 4 && msgList[3].equals("si"))
        {
            return SupplyInterruptionOverrideList(cmdmsg);
        }
        if(msgList.length == 4 && msgList[3].equals("cr"))
        {
            return CreditRegisterList(cmdmsg);
        }
        
         
        return NoneResponse();
    } 
     
    private List<String> SupplyInterruptionOverrideList(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            currentUser.payment.prepaymentList.Get(index).supplyInterruptionOverrideList.Add(new SupplyInterruptionOverride());
            currentUser.IsUpdated = true;
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }  
    
    private List<String> CreditRegisterList(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            long index = Long.parseLong(msgList[2]);
            currentUser.payment.prepaymentList.Get((int)index).creditRegisterList.Add(new CreditRegister());
            
            currentUser.IsUpdated= true;
            return CreatedResponse();
             
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }  
     
     // End of Post Prepayment
     // --- Post billing
     private List<String> PostBilling(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 4 && msgList[3].equals("ca"))
        {
            return PostCustomerAgreement(cmdmsg);
        }        
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("bp"))
        {
            return PostBillingPeriod(cmdmsg);
        }        
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("pro"))
        {
            return PostProjectionReading(cmdmsg);
        } 
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("ver"))
        {
            return PostHistoricalReading(cmdmsg);
        }
        if(msgList.length == 8 && msgList[3].equals("ca") && msgList[5].equals("ver") && msgList[7].equals("brs"))
        {
            return PostBillingReadingSet(cmdmsg);
        }
        if(msgList.length == 10 && msgList[3].equals("ca") && msgList[5].equals("ver") && msgList[7].equals("brs")&& msgList[7].equals("br"))
        {
            return PostBillingReading(cmdmsg);
        }
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("tar"))
        {
            return PostTargetReading(cmdmsg);
        }
         
        return NoneResponse();
    } 
     
    private List<String> PostCustomerAgreement(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            long index = Long.parseLong(msgList[2]);
            currentUser.billing.customerAccountList.Get((int)index).customerAgreementList.Add(new CustomerAgreement());
            currentUser.IsUpdated= true;
            return CreatedResponse();
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }     
    private List<String> PostBillingPeriod(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).billingPeriodList.Add(new BillingPeriod());
            currentUser.IsUpdated= true;
            return CreatedResponse();
           
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }    
    private List<String> PostProjectionReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).projectionReadingList.Add(new ProjectionReading());
            currentUser.IsUpdated= true;
                    return CreatedResponse();
            
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }    
    private List<String> PostHistoricalReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Add(new HistoricalReading());
             currentUser.IsUpdated= true;
                    return CreatedResponse();
           
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }    
    private List<String> PostBillingReadingSet(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int ver = Integer.parseInt(msgList[6]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Get(ver).billingMeterReadingBase.billingReadingSetList.Add(new BillingReadingSet());
            currentUser.IsUpdated= true;
                    return CreatedResponse();
            
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    private List<String> PostBillingReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int ver = Integer.parseInt(msgList[6]);
            int brs = Integer.parseInt(msgList[8]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Get(ver).billingMeterReadingBase.billingReadingSetList.Get(brs).billingReadingList.Add(new BillingReading());
            currentUser.IsUpdated= true;
                    return CreatedResponse();
            
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    private List<String> PostTargetReading(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).targetReadingList.Add(new TargetReading());
            currentUser.IsUpdated= true;
                    return CreatedResponse();
            
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }     
     // -- end post billing
    
    private List<String> RequestMetering(String cmdmsg, String context)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 2)
        {
            return ResquestPostMertering(cmdmsg, context);
        }
        if(msgList.length == 4 && msgList[3].equals("mr"))
        {
            return ResquestPostMerteringReading(cmdmsg, context);
        }
        if(msgList.length == 6 && msgList[3].equals("mr") && msgList[5].equals("rs"))
        {
            return ResquestPostReadingSet(cmdmsg);
        }
        if(msgList.length == 8 && msgList[3].equals("mr") && msgList[5].equals("rs") && msgList[7].equals("r"))
        {
            return ResquestPostReading(cmdmsg);
        }
         
        return NoneResponse();
    }
    private List<String> ResquestPostMertering(String cmdmsg, String context)
    {
        try
        {           
            UsagePoint usagePoint = (UsagePoint) XmlSerialize.convertXMLToObject(UsagePoint.class, context);
            UsagePoint newUsagePoint  = new UsagePoint();
            newUsagePoint.usagePoinitBase.roleFlags = usagePoint.roleFlags;
            newUsagePoint.usagePoinitBase.serviceCategoryKind = usagePoint.serviceCategoryKind;
            newUsagePoint.usagePoinitBase.status = usagePoint.status;
            currentUser.endDevice.usagePointList.Add(newUsagePoint);
            currentUser.IsUpdated= true;
                    return CreatedResponse();
                
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    
    private List<String> ResquestPostMerteringReading(String cmdmsg, String context)
    {
        try
        {
            MeterReading meterReading = (MeterReading) XmlSerialize.convertXMLToObject(MeterReading.class, context);
            MeterReading newmeterReading  = new MeterReading();
            newmeterReading.meterReadingBase.identifiedObject.mRID = meterReading.mRID;
            newmeterReading.meterReadingBase.identifiedObject.description = meterReading.description;
            newmeterReading.meterReadingBase.identifiedObject.version = meterReading.version;
            String[] msgList = cmdmsg.split("/");
            long index = Long.parseLong(msgList[2]);
            currentUser.endDevice.usagePointList.Get((int)index).meterReadingList.Add(newmeterReading);
            currentUser.IsUpdated= true;
                    return CreatedResponse();
                
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
     private List<String> ResquestPostReadingSet(String cmdmsg)
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
                    meterReadingList.Get(inx).readingSetList.Add(new ReadingSet());
                    currentUser.endDevice.usagePointList.Get(index).meterReadingList = meterReadingList;
                    currentUser.IsUpdated = true;
                    return CreatedResponse();
                }
            }
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
     private List<String> ResquestPostReading(String cmdmsg)
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
                    meterReadingList.Get(inmr).readingSetList.Get(inrs).readingList.Add(new Reading());
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
        if(msgList.length == 4 && msgList[3].equals("rc"))
        {
            return ResquestPostRateComponent(cmdmsg);
        }
        if(msgList.length == 6 && msgList[3].equals("rc") && msgList[5].equals("tti"))
        {
            return ResquestPostTimeTariffInterval(cmdmsg);
        }
        if(msgList.length == 8 && msgList[3].equals("rc") && msgList[5].equals("tti") && msgList[7].equals("cti"))
        {
            return ResquestPostConsumptionTariffInterval(cmdmsg);
        }
         
        return NoneResponse();
    }
     
    private List<String> ResquestPostRateComponent(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            long index = Long.parseLong(msgList[2]);
            int i =0;
            currentUser.pricing.tariffProfileList.Get((int)index).rateComponentList.Add(new RateComponent());
            currentUser.IsUpdated= true;
            return CreatedResponse();
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
     
    private List<String> ResquestPostTimeTariffInterval(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Get(inrc).timeTariffIntervalList.Add(new TimeTariffInterval());
            currentUser.IsUpdated= true;
                    return CreatedResponse();
           
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    
    private List<String> ResquestPostConsumptionTariffInterval(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            int intti = Integer.parseInt(msgList[6]);
            currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Get(inrc).timeTariffIntervalList.Get(intti).conTariffIntervalList.Add(new ConsumptionTariffInterval());
            currentUser.IsUpdated= true;
                    return CreatedResponse();
               
        }
        catch(Exception ex){}
         
        return NoneResponse();
    }
    
    private List<String> RequestMessaging(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 4 && msgList[3].equals("txt"))
        {
            return ResquestPostTextMessage(cmdmsg);
        }        
         
        return NoneResponse();
    }
    
    private List<String> ResquestPostTextMessage(String cmdmsg)
    {
        try
        {
            String[] msgList = cmdmsg.split("/");
            long index = Long.parseLong(msgList[2]);
            currentUser.messaging.messagingProgramList.Get((int)index).textMessageList.Add(new TextMessage());
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
        return Arrays.asList(new String[]{"T","HTTP 201 - Created"});
    }
}
