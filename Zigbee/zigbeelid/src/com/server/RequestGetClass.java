/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import FunctionSet.Billing.*;
import FunctionSet.Messaging.MessagingProgram;

import FunctionSet.Messaging.TextMessage;
import FunctionSet.Messaging.TextMessageList;
import FunctionSet.Metering.MeterReading;
import FunctionSet.Metering.ReadingList;
import FunctionSet.Metering.ReadingSetList;
import FunctionSet.Metering.ReadingType;
import FunctionSet.Prepayment.*;
import FunctionSet.Pricing.*;
import com.user.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class RequestGetClass 
{
     
     public User currentUser = new User();
     public RequestGetClass() {}    
     
     public List<String> SendGet(String cmdmsg) 
    {
        if(cmdmsg.indexOf("get/tp")>-1)
            return ResponsePricing(cmdmsg);
        if(cmdmsg.indexOf("get/upt")>-1)
            return ResponseMetering(cmdmsg);        
        if(cmdmsg.indexOf("get/edev")>-1)
            return ResponseDeviceList(cmdmsg);
        if(cmdmsg.indexOf("get/msg")>-1)
            return ResponseMessaging(cmdmsg);
        if(cmdmsg.indexOf("get/bill")>-1)
            return ResponseBilling(cmdmsg);
        if(cmdmsg.indexOf("get/ppy")>-1)
            return Payment(cmdmsg);
        if(cmdmsg.indexOf("get/devi")>-1)
        return ResponseDeviceInformation(cmdmsg);
        return NoneResponse();
    }  
     
     // Start Prepayment
      private List<String> Payment(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 2)
            return PrepaymentList(cmdmsg);
        if(msgList.length == 3)
            return Prepayment(cmdmsg);
        if(msgList.length == 4 && msgList[3].equals("ab"))
            return AccountBalance(cmdmsg);
        if(msgList.length == 4 && msgList[3].equals("os"))
            return PrepayOperationStatus(cmdmsg);
        if(msgList.length == 4 && msgList[3].equals("actsi"))
            return ActiveSupplyInterruptionOverrideList(cmdmsg);
        if(msgList.length == 4 && msgList[3].equals("si"))
            return SupplyInterruptionOverrideList(cmdmsg);
        if(msgList.length == 5 && msgList[3].equals("si"))
            return SupplyInterruptionOverride(cmdmsg);
        if(msgList.length == 4 && msgList[3].equals("cr"))
            return CreditRegisterList(cmdmsg);
        if(msgList.length == 5 && msgList[3].equals("cr"))
            return CreditRegister(cmdmsg);
         
        return NoneResponse();
    }
    
    private List<String> PrepaymentList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response ;                 
            response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            response += "<PrepaymentList all=\"1\" href=\"get/ppy\" results=\""+currentUser.payment.prepaymentList.Length()+"\" subscribable=\"0\" xmlns=\"http://zigbee.org/sep\">\n";
           
            for(int i = 0; i<currentUser.payment.prepaymentList.Length(); i++)
            {                
                response += "<Prepayment href=\"get/ppy/"+i+"\">\n";
                response += "	<creditExpiryLevel>\n";
                response += "       <energyUnit>"+currentUser.payment.prepaymentList.Get(i).creditExpiryLevel.energyUnit+"</energyUnit>\n";
                response += "       <monetaryUnit>"+currentUser.payment.prepaymentList.Get(i).creditExpiryLevel.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                response += "       <multiplier>"+currentUser.payment.prepaymentList.Get(i).creditExpiryLevel.multiplier.getValue()+"</multiplier>\n";
                response += "       <value>"+currentUser.payment.prepaymentList.Get(i).creditExpiryLevel.value.getValue()+"</value>\n";
                response += "   </creditExpiryLevel>\n";                               
                response += "	<lowCreditWarningLevel>\n";
                response += "       <energyUnit>"+currentUser.payment.prepaymentList.Get(i).lowCreditWarningLevel.energyUnit.value+"</energyUnit>\n";
                response += "       <monetaryUnit>"+currentUser.payment.prepaymentList.Get(i).lowCreditWarningLevel.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                response += "       <multiplier>"+currentUser.payment.prepaymentList.Get(i).lowCreditWarningLevel.multiplier.getValue()+"</multiplier>\n";
                response += "       <value>"+currentUser.payment.prepaymentList.Get(i).lowCreditWarningLevel.value.getValue()+"</value>\n";
                response += "   </lowCreditWarningLevel>\n";
                response += "	<lowEmergencyCreditWarningLevel>\n";
                response += "       <energyUnit>"+currentUser.payment.prepaymentList.Get(i).lowEmergencyCreditWarningLevel.energyUnit.value+"</energyUnit>\n";
                response += "       <monetaryUnit>"+currentUser.payment.prepaymentList.Get(i).lowEmergencyCreditWarningLevel.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                response += "       <multiplier>"+currentUser.payment.prepaymentList.Get(i).lowEmergencyCreditWarningLevel.multiplier.getValue()+"</multiplier>\n";
                response += "       <value>"+currentUser.payment.prepaymentList.Get(i).lowEmergencyCreditWarningLevel.value.getValue()+"</value>\n";
                response += "   </lowEmergencyCreditWarningLevel>\n";
                response += "	<prepayMode>"+currentUser.payment.prepaymentList.Get(i).prepayMode.Value.getValue()+"</prepayMode>\n";
                response += "</Prepayment>\n";
               
            }
            response += "</PrepaymentList>\n";
            list.add("T");
            list.add(response);
            return list;
        }
        catch (Exception ex){}
        return NoneResponse();
    }
    
    private List<String> Prepayment(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        Prepayment  prepayment = null;
        try
        {
             int index = Integer.parseInt(msgList[2]);
             prepayment = currentUser.payment.prepaymentList.Get(index);             
             if(prepayment!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<Prepayment href=\"get/ppy/"+index+"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
                response += "	<creditExpiryLevel>\n";
                response += "       <energyUnit>"+prepayment.creditExpiryLevel.energyUnit.value+"</energyUnit>\n";
                response += "       <monetaryUnit>"+prepayment.creditExpiryLevel.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                response += "       <multiplier>"+prepayment.creditExpiryLevel.multiplier.getValue()+"</multiplier>\n";
                response += "       <value>"+prepayment.creditExpiryLevel.value.getValue()+"</value>\n";
                response += "   </creditExpiryLevel>\n";                               
                response += "	<lowCreditWarningLevel>\n";
                response += "       <energyUnit>"+prepayment.lowCreditWarningLevel.energyUnit.value+"</energyUnit>\n";
                response += "       <monetaryUnit>"+prepayment.lowCreditWarningLevel.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                response += "       <multiplier>"+prepayment.lowCreditWarningLevel.multiplier.getValue()+"</multiplier>\n";
                response += "       <value>"+prepayment.lowCreditWarningLevel.value.getValue()+"</value>\n";
                response += "   </lowCreditWarningLevel>\n";
                response += "	<lowEmergencyCreditWarningLevel>\n";
                response += "       <energyUnit>"+prepayment.lowEmergencyCreditWarningLevel.energyUnit.value+"</energyUnit>\n";
                response += "       <monetaryUnit>"+prepayment.lowEmergencyCreditWarningLevel.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                response += "       <multiplier>"+prepayment.lowEmergencyCreditWarningLevel.multiplier.getValue()+"</multiplier>\n";
                response += "       <value>"+prepayment.lowEmergencyCreditWarningLevel.value.getValue()+"</value>\n";
                response += "   </lowEmergencyCreditWarningLevel>\n";
                response += "	<prepayMode>"+prepayment.prepayMode.Value.getValue()+"</prepayMode>\n";
                response += "</Prepayment>\n";    
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> AccountBalance(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        AccountBalance   accountBalance  = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            accountBalance = currentUser.payment.prepaymentList.Get(index).accountBalance;            
             if(accountBalance!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<AccountBalance href=\"get/ppy/"+index+"/ab\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
                response += "	<availableCredit>\n";
                response += "       <energyUnit>"+accountBalance.availableCredit.energyUnit.value+"</energyUnit>\n";
                response += "       <monetaryUnit>"+accountBalance.availableCredit.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                response += "       <multiplier>"+accountBalance.availableCredit.multiplier.getValue()+"</multiplier>\n";
                response += "       <value>"+accountBalance.availableCredit.value.getValue()+"</value>\n";
                response += "   </availableCredit>\n";                               
                response += "   <creditStatus>"+accountBalance.creditStatus.Value.getValue()+"</creditStatus>\n";
                 response += "	<emergencyCredit>\n";
                response += "       <energyUnit>"+accountBalance.emergencyCredit.energyUnit.value+"</energyUnit>\n";
                response += "       <monetaryUnit>"+accountBalance.emergencyCredit.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                response += "       <multiplier>"+accountBalance.emergencyCredit.multiplier.getValue()+"</multiplier>\n";
                response += "       <value>"+accountBalance.emergencyCredit.value.getValue()+"</value>\n";
                response += "   </emergencyCredit>\n";
                response += "	<emergencyCreditStatus>"+accountBalance.emergencyCreditStatus.Value.getValue()+"</emergencyCreditStatus>\n";
                response += "</AccountBalance>\n";    
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> PrepayOperationStatus(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        PrepayOperationStatus   prepayOperationStatus  = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            prepayOperationStatus = currentUser.payment.prepaymentList.Get(index).prepayOperationStatus;
            
             if(prepayOperationStatus!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<PrepayOperationStatus href=\"get/ppy/"+index+"/os\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
                response += "	<creditTypeChange>\n";
                response += "       <newType>"+prepayOperationStatus.creditTypeChange.newType.Value.getValue()+"</newType>\n";
                response += "       <startTime>"+prepayOperationStatus.creditTypeChange.startTime.value+ "</startTime>\n";
                response += "   </creditTypeChange>\n";                               
                response += "   <creditTypeInUse>"+prepayOperationStatus.creditTypeInUse.Value.getValue()+"</creditTypeInUse>\n";
                 response += "	<serviceChange>\n";
                response += "       <newStatus>"+prepayOperationStatus.serviceChange.newStatus.Value.getValue()+"</newStatus>\n";
                response += "       <monetaryUnit>"+prepayOperationStatus.serviceChange.startTime.getValue()+"</monetaryUnit>\n";
                response += "   </serviceChange>\n";
                response += "	<serviceStatus>"+prepayOperationStatus.serviceStatus.Value.getValue()+"</serviceStatus>\n";
                response += "</PrepayOperationStatus>\n";    
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> ActiveSupplyInterruptionOverrideList(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        SupplyInterruptionOverrideList   activeSupplyInterruptionOverrideList  = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            activeSupplyInterruptionOverrideList = currentUser.payment.prepaymentList.Get(index).activeSupplyInterruptionOverrideList;
             
             if(activeSupplyInterruptionOverrideList!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<ActiveSupplyInterruptionOverrideList href=\"get/ppy/"+index+"/actsi\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
                for(int k = 0; k< activeSupplyInterruptionOverrideList.Length(); k++)
                {
                    response += "<SupplyInterruptionOverride href=\"get/ppy/"+index+"/actsi/"+k+"\">\n"; 
                    response += "<interval>\n";
                    response += "   <duration>"+ activeSupplyInterruptionOverrideList.Get(k).interval.duration.value+"</duration>\n";
                    response += "   <start>"+ activeSupplyInterruptionOverrideList.Get(k).interval.start.value+"</start>\n";
                    response += "</interval>\n";
                    response += "<description>"+ activeSupplyInterruptionOverrideList.Get(k).description.getValue()+"</description>\n";
                    response += "</SupplyInterruptionOverride>\n"; 
                }
                response += "</ActiveSupplyInterruptionOverrideList>\n";    
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    private List<String> SupplyInterruptionOverrideList(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        SupplyInterruptionOverrideList supplyInterruptionOverrideList  = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            supplyInterruptionOverrideList = currentUser.payment.prepaymentList.Get(index).supplyInterruptionOverrideList;
            
             if(supplyInterruptionOverrideList!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<SupplyInterruptionOverrideList href=\"get/ppy/"+index+"/si\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
                for(int k = 0; k< supplyInterruptionOverrideList.Length(); k++)
                {
                    response += "<SupplyInterruptionOverride href=\"get/ppy/"+index+"/si/"+k+"\">\n"; 
                    response += "<interval>\n";
                    response += "   <duration>"+ supplyInterruptionOverrideList.Get(k).interval.duration.value+"</duration>\n";
                    response += "   <start>"+ supplyInterruptionOverrideList.Get(k).interval.start.value+"</start>\n";
                    response += "</interval>\n";
                    response += "<description>"+ supplyInterruptionOverrideList.Get(k).description.getValue()+"</description>\n";
                    response += "</SupplyInterruptionOverride>\n"; 
                }
                response += "</SupplyInterruptionOverrideList>\n";    
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> SupplyInterruptionOverride(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        SupplyInterruptionOverride supplyInterruptionOverride  = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            int sio = Integer.parseInt(msgList[4]);
            supplyInterruptionOverride = currentUser.payment.prepaymentList.Get(index).supplyInterruptionOverrideList.Get(sio);
            
             if(supplyInterruptionOverride!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<SupplyInterruptionOverride href=\"get/ppy/"+index+"/si/"+sio+"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
                response += "<interval>\n";
                response += "   <duration>"+ supplyInterruptionOverride.interval.duration.value+"</duration>\n";
                response += "   <start>"+ supplyInterruptionOverride.interval.start.value+"</start>\n";
                response += "</interval>\n";
                response += "<description>"+ supplyInterruptionOverride.description.getValue()+"</description>\n";
                response += "</SupplyInterruptionOverride>\n";                
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> CreditRegisterList(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        CreditRegisterList creditRegisterList  = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            creditRegisterList = currentUser.payment.prepaymentList.Get(index).creditRegisterList;
            
             if(creditRegisterList!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<CreditRegisterList href=\"get/ppy/"+index+"/cr\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
                for(int k = 0; k< creditRegisterList.Length(); k++)
                {
                    response += "<CreditRegister href=\"get/ppy/"+index+"/si/"+k+"\">\n"; 
                    response += "<mRID>"+ creditRegisterList.Get(k).identifiedObject.mRID+"</mRID>\n";
                    response += "<description>"+ creditRegisterList.Get(k).identifiedObject.description+"</description>\n";
                    response += "<creditAmount>\n";
                    response += "       <monetaryUnit>"+creditRegisterList.Get(k).creditAmount.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                    response += "       <multiplier>"+creditRegisterList.Get(k).creditAmount.multiplier.getValue()+"</multiplier>\n";
                    response += "       <value>"+creditRegisterList.Get(k).creditAmount.value.getValue()+"</value>\n";
                    response += "</creditAmount>\n";                               
                    response += "<creditType>"+ creditRegisterList.Get(k).creditType.Value.getValue()+"</creditType>\n";
                    response += "<effectiveTime>"+ creditRegisterList.Get(k).effectiveTime.value+"</effectiveTime>\n";
                    response += "<token>"+ creditRegisterList.Get(k).token.getValue()+"</token>\n";
                    response += "</CreditRegister>\n"; 
                }
                response += "</CreditRegisterList>\n";    
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> CreditRegister(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        CreditRegister creditRegister  = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            int cr = Integer.parseInt(msgList[4]);
            creditRegister = currentUser.payment.prepaymentList.Get(index).creditRegisterList.Get(cr);
             
             if(creditRegister!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<CreditRegister href=\"get/ppy/"+index+"/cr/"+cr+"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
                response += "<mRID>"+ creditRegister.identifiedObject.mRID+"</mRID>\n";
                response += "<description>"+ creditRegister.identifiedObject.description+"</description>\n";
                response += "<creditAmount>\n";
                response += "       <monetaryUnit>"+creditRegister.creditAmount.monetaryUnit.getValue().getValue()+"</monetaryUnit>\n";
                response += "       <multiplier>"+creditRegister.creditAmount.multiplier.getValue()+"</multiplier>\n";
                response += "       <value>"+creditRegister.creditAmount.value.getValue()+"</value>\n";
                response += "</creditAmount>\n";                               
                response += "<creditType>"+ creditRegister.creditType.Value.getValue()+"</creditType>\n";
                response += "<effectiveTime>"+ creditRegister.effectiveTime.value+"</effectiveTime>\n";
                response += "<token>"+ creditRegister.token.getValue()+"</token>\n";
                response += "</CreditRegister>\n";                  
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
     // End of Prepayment
    
     //Start Billing
     private List<String> ResponseBilling(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 2)
            return ResponseCustomerAccountList(cmdmsg);
        if(msgList.length == 3)
            return ResponseCustomerAccount(cmdmsg);
        if(msgList.length == 4 && msgList[3].equals("ca"))
            return ResponseCustomerAgreementList(cmdmsg);
        if(msgList.length == 5 && msgList[3].equals("ca"))
            return ResponseCustomerAgreement(cmdmsg);
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("actbp"))
            return ResponseActiveBillingPeriodList(cmdmsg);
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("bp"))
            return ResponseBillingPeriodList(cmdmsg);
        if(msgList.length == 7 && msgList[3].equals("ca") && msgList[5].equals("bp"))
            return ResponseBillingPeriod(cmdmsg);        
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("pro"))
            return ProjectionReadingList(cmdmsg);
        if(msgList.length == 7 && msgList[3].equals("ca") && msgList[5].equals("pro"))
            return ProjectionReading(cmdmsg);
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("tar"))
            return TargetReadingList(cmdmsg);
        if(msgList.length == 7 && msgList[3].equals("ca") && msgList[5].equals("tar"))
            return TargetReading(cmdmsg);
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("ver"))
            return HistoricalReadingList(cmdmsg);
        if(msgList.length == 7 && msgList[3].equals("ca") && msgList[5].equals("ver"))
            return HistoricalReading(cmdmsg);
        if(msgList.length == 6 && msgList[3].equals("ca") && msgList[5].equals("ss"))
            return ServiceSupplier(cmdmsg);
        if(msgList.length == 8 && msgList[3].equals("ca") && msgList[5].equals("ver")&& msgList[7].equals("brs"))
            return BillingReadingSetList(cmdmsg);
        if(msgList.length == 9 && msgList[3].equals("ca") && msgList[5].equals("ver")&& msgList[7].equals("brs"))
            return BillingReadingSet(cmdmsg);
        if(msgList.length == 10 && msgList[3].equals("ca") && msgList[5].equals("ver")&& msgList[7].equals("brs") && msgList[9].equals("br"))
            return BillingReadingList(cmdmsg);        
        if(msgList.length == 11 && msgList[3].equals("ca") && msgList[5].equals("ver")&& msgList[7].equals("brs") && msgList[9].equals("br"))
            return BillingReading(cmdmsg);
                
        return NoneResponse();
    }
     
    private List<String> ResponseCustomerAccountList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response ;                 
            response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            response += "<CustomerAccountList all=\"1\" href=\"get/bill\" results=\""+currentUser.billing.customerAccountList.Length()+"\" subscribable=\"0\" xmlns=\"http://zigbee.org/sep\">\n";
            
            for(int i = 0; i< currentUser.billing.customerAccountList.Length(); i++)
            {                
                response += "<CustomerAccount href=\"get/bill/"+i+"\" >\n";
                response += "<customerName>"+currentUser.billing.customerAccountList.Get(i).customerName.getValue()+"</customerName>\n";
                response += "<currency>"+currentUser.billing.customerAccountList.Get(i).currency.getValue()+"</currency>\n";                      
                response += "</CustomerAccount>\n";
              
            }
            response += "</CustomerAccountList>\n";
            list.add("T");
            list.add(response);
            return list;
        }
        catch (Exception ex){}
        return NoneResponse();
    }
     
    private List<String> ResponseCustomerAccount(String cmdmsg)
    {        
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        try
        {
            int index = Integer.parseInt(msgList[2]);
            
            response = "<CustomerAccount href=\"/bill/"+index+"\" xmlns=\"http://zigbee.org/sep\"\n";
            response += "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
            response += "         xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
            response += "         <mRID>"+currentUser.billing.customerAccountList.Get(index).identifiedObject.mRID+"</mRID>\n";
            response += "         <description>"+currentUser.billing.customerAccountList.Get(index).identifiedObject.description+"</description>\n";
            response += "         <currency>"+currentUser.billing.customerAccountList.Get(index).currency.getValue()+"</currency>\n";
            response += "         <customerAccount>"+currentUser.billing.customerAccountList.Get(index).currency.getValue()+"</customerAccount>\n";
            response += "         <CustomerAgreementListLink href=\"get/bill/"+index+"/ca\" all=\"1\" />\n";
            response += "         <customerName>"+currentUser.billing.customerAccountList.Get(index).customerName.getValue()+"</customerName>\n";
            response += "         <pricePowerOfTenMultiplier>"+currentUser.billing.customerAccountList.Get(index).pricePowerOfTenMultiplier.getValue()+"</pricePowerOfTenMultiplier>\n";
            response += "         <ServiceSupplierLink href=\"get/bill/"+index+"/ss\" />\n";
            response += " </CustomerAccount>\n";
                  
            list.add("T");
            list.add(response);
            return list;
        }
        catch(Exception ex) {}
        return NoneResponse();
    }
     
    private List<String> ResponseCustomerAgreementList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            CustomerAgreementList customerAgreementList = new CustomerAgreementList();
            int index = Integer.parseInt(msgList[2]);
            customerAgreementList = currentUser.billing.customerAccountList.Get(index).customerAgreementList;
                               
            response = "<CustomerAgreementList xmlns=\"http://zigbee.org/sep\" all=\""+customerAgreementList.Length()+"\"\n";
            response += "href=\"get/bill/"+index+"/ca\" results=\""+customerAgreementList.Length()+"\"\n";
            response += "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
            response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";

            for(int k =0 ; k < customerAgreementList.Length(); k++)
            {
                 response += "<CustomerAgreement href=\"get/bill/"+index+"/ca/"+k+"\">\n";
                 response += "  <mRID>"+customerAgreementList.Get(k).identifiedObject.mRID+"</mRID>\n";
		 response += "  <description>"+customerAgreementList.Get(k).identifiedObject.description+"</description>\n";
		 response += "  <ActiveBillingPeriodListLink href=\"get/bill/"+index+"/ca/1/actbp\" all=\""+customerAgreementList.Get(k).activeBillingPeriodList.Length()+"\" />\n";
		 response += "  <BillingPeriodListLink href=\"get/bill/"+index+"/ca/"+k+"/bp\" all=\""+customerAgreementList.Get(k).billingPeriodList.Length()+"\" />\n";
		 response += "  <HistoricalReadingListLink href=\"get/bill/"+index+"/ca/"+k+"/ver\" all=\""+customerAgreementList.Get(k).historicalReadingList.Length()+"\" />\n";
		 response += "  <ProjectionReadingListLink href=\"get/bill/"+index+"/ca/"+k+"/pro\" all=\""+customerAgreementList.Get(k).projectionReadingList.Length()+"\" />\n";
		 response += "  <serviceLocation>"+customerAgreementList.Get(k).serviceLocation.getValue()+"</serviceLocation>\n";
		 response += "  <TariffProfileLink href=\"get/tp/"+index+"\" />\n";
		 response += "  <UsagePointLink href=\"get/upt/"+index+"\" />\n";
                 response += "</CustomerAgreement>\n";
            }
            response += "</CustomerAgreementList>\n";   
            list.add("T");
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
     
    private List<String> ResponseCustomerAgreement(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            CustomerAgreement customerAgreement = new CustomerAgreement();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            customerAgreement = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca);
                           
            response = "<CustomerAgreement href=\"get/bill/"+index+"/ca/"+ca+"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
            response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
            response += "  <mRID>"+customerAgreement.identifiedObject.mRID+"</mRID>\n";
            response += "  <description>"+customerAgreement.identifiedObject.description+"</description>\n";
            response += "  <ActiveBillingPeriodListLink href=\"get/bill/"+index+"/ca/1/actbp\" all=\""+customerAgreement.activeBillingPeriodList.Length()+"\" />\n";
            response += "  <BillingPeriodListLink href=\"get/bill/"+index+"/ca/"+ca+"/bp\" all=\""+customerAgreement.billingPeriodList.Length()+"\" />\n";
            response += "  <HistoricalReadingListLink href=\"get/bill/"+index+"/ca/"+ca+"/ver\" all=\""+customerAgreement.historicalReadingList.Length()+"\" />\n";
            response += "  <ProjectionReadingListLink href=\"get/bill/"+index+"/ca/"+ca+"/pro\" all=\""+customerAgreement.projectionReadingList.Length()+"\" />\n";
            response += "  <serviceLocation>"+customerAgreement.serviceLocation.getValue()+"</serviceLocation>\n";
            response += "  <TariffProfileLink href=\"get/tp/"+index+"\" />\n";
            response += "  <UsagePointLink href=\"get/upt/"+index+"\" />\n";
            response += "</CustomerAgreement>\n"; 
            list.add("T");
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> ResponseActiveBillingPeriodList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            BillingPeriodList activeBillingPeriodList = new BillingPeriodList();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            activeBillingPeriodList = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).activeBillingPeriodList;
                             
            response = "<ActiveBillingPeriodList href=\"get/bill/"+index+"/ca/"+ca+"/actbp\" all=\""+activeBillingPeriodList.Length()+"\"\n" +
                       "subscribable=\"1\" results=\"1\" xmlns=\"http://zigbee.org/sep\"\n" +
                       "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                       "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
                for(int k = 0; k < activeBillingPeriodList.Length(); k++ )
                {
                    response += "<BillingPeriod href=\"get/bill/"+index+"/ca/"+ca+"/actbp/"+k+"\">\n" +
                    "   <billLastPeriod>"+activeBillingPeriodList.Get(k).billLastPeriod.getValue()+"</billLastPeriod>\n" +
                    "   <billToDate>"+activeBillingPeriodList.Get(k).billToDate.getValue()+"</billToDate>\n" +
                    "   <interval>\n" +
                    "       <duration>"+activeBillingPeriodList.Get(k).interval.duration.getValue()+"</duration>\n" +
                    "       <start>"+activeBillingPeriodList.Get(k).interval.start.getValue()+"</start>\n" +
                    "   </interval>\n" +
                    "   <statusTimeStamp>"+activeBillingPeriodList.Get(k).statusTimeStamp.getValue()+"</statusTimeStamp>\n" +
                    "</BillingPeriod>\n";
               }
            response += "</ActiveBillingPeriodList>\n";   
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> ResponseBillingPeriodList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            BillingPeriodList billingPeriodList = new BillingPeriodList();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            billingPeriodList = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).billingPeriodList;
                           
            response = "<BillingPeriodList href=\"get/bill/"+index+"/ca/"+ca+"/bp\" all=\""+billingPeriodList.Length()+"\"\n" +
                       "subscribable=\"1\" results=\"1\" xmlns=\"http://zigbee.org/sep\"\n" +
                       "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                       "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
                for(int k = 0; k < billingPeriodList.Length(); k++ )
                {
                    response += "<BillingPeriod href=\"get/bill/"+index+"/ca/"+ca+"/bp/"+k+"\">\n" +
                    "   <billLastPeriod>"+billingPeriodList.Get(k).billLastPeriod.getValue()+"</billLastPeriod>\n" +
                    "   <billToDate>"+billingPeriodList.Get(k).billToDate.getValue()+"</billToDate>\n" +
                    "   <interval>\n" +
                    "       <duration>"+billingPeriodList.Get(k).interval.duration.getValue()+"</duration>\n" +
                    "       <start>"+billingPeriodList.Get(k).interval.start.getValue()+"</start>\n" +
                    "   </interval>\n" +
                    "   <statusTimeStamp>"+billingPeriodList.Get(k).statusTimeStamp.getValue()+"</statusTimeStamp>\n" +
                    "</BillingPeriod>\n";
               }
            response += "</BillingPeriodList>\n";   
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> ResponseBillingPeriod(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            BillingPeriod  billingPeriod = new BillingPeriod();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int bp = Integer.parseInt(msgList[6]);
            int i =0;
            billingPeriod = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).billingPeriodList.Get(bp);
                             
             response = "<BillingPeriod href=\"get/bill/"+index+"/ca/"+ca+"/bp/"+bp+"\" xmlns=\"http://zigbee.org/sep\"\n" +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n"+
                        "   <billLastPeriod>"+billingPeriod.billLastPeriod.getValue()+"</billLastPeriod>\n" +
                        "   <billToDate>"+billingPeriod.billToDate.getValue()+"</billToDate>\n" +
                        "   <interval>\n" +
                        "       <duration>"+billingPeriod.interval.duration.getValue()+"</duration>\n" +
                        "       <start>"+billingPeriod.interval.start.getValue()+"</start>\n" +
                        "   </interval>\n" +
                        "   <statusTimeStamp>"+billingPeriod.statusTimeStamp.getValue()+"</statusTimeStamp>\n" +
                        "</BillingPeriod>\n";               
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> ProjectionReadingList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            ProjectionReadingList projectionReadingList = new ProjectionReadingList();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
           projectionReadingList = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).projectionReadingList;
                           
            response = "<ProjectionReadingList href=\"get/bill/"+index+"/ca/"+ca+"/pro\"\n" +
                        "	all=\""+projectionReadingList.Length()+"\" results=\"1\" xmlns=\"http://zigbee.org/sep\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "	xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
            for(int k = 0; k < projectionReadingList.Length(); k++)
            {
                  response +=   "	<ProjectionReading href=\"get/bill/"+index+"/ca/"+ca+"/pro/"+k+"\">\n" +
                                "		<mRID>"+projectionReadingList.Get(k).billingMeterReadingBase.meterReadingBase.identifiedObject.mRID+"</mRID>\n" +
                                "		<description>"+projectionReadingList.Get(k).billingMeterReadingBase.meterReadingBase.identifiedObject.description+"</description>\n" +
                                "		<BillingReadingSetListLink href=\"get/bill/"+index+"/ca/"+ca+"/pro/"+k+"/brs\"\n" +
                                "			all=\"1\" />\n" +
                                "	</ProjectionReading>\n";
             }
            response += "</ProjectionReadingList>\n";   
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> ProjectionReading(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            ProjectionReading projectionReading = new ProjectionReading();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int pro = Integer.parseInt(msgList[6]);
            projectionReading = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).projectionReadingList.Get(pro);
                           
            response = "<ProjectionReading href=\"get/bill/"+index+"/ca/"+ca+"/pro/"+pro+"\" xmlns=\"http://zigbee.org/sep\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n"+
                        "<ProjectionReading href=\"get/bill/"+index+"/ca/"+ca+"/pro/"+pro+"\">\n" +
                        "		<mRID>"+projectionReading.billingMeterReadingBase.meterReadingBase.identifiedObject.mRID+"</mRID>\n" +
                        "		<description>"+projectionReading.billingMeterReadingBase.meterReadingBase.identifiedObject.description+"</description>\n" +
                        "		<BillingReadingSetListLink href=\"get/bill/"+index+"/ca/"+ca+"/pro/"+pro+"/brs\" all=\"1\" />\n" +
                        "</ProjectionReading>\n";
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> TargetReadingList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            TargetReadingList targetReadingList = new TargetReadingList();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
           
            targetReadingList = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).targetReadingList;
                          
            response = "<TargetReadingList href=\"get/bill/"+index+"/ca/"+ca+"/tar\"\n" +
                        "	subscribable=\"1\" results=\"1\" xmlns=\"http://zigbee.org/sep\"\n" +
                        "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "	xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
            for(int k =0; k < targetReadingList.Length(); k++)
            {
                response += "	<TargetReading href=\"get/bill/"+index+"/ca/"+ca+"/tar/"+k+"\">\n" +
                            "		<mRID>"+targetReadingList.Get(k).billingMeterReadingBase.meterReadingBase.identifiedObject.mRID+"</mRID>\n" +
                            "		<description>"+targetReadingList.Get(k).billingMeterReadingBase.meterReadingBase.identifiedObject.description+"</description>\n" +
                            "		<accumulationBehaviour>"+targetReadingList.Get(k).billingMeterReadingBase.readingType.accumulationBehaviour.getValue().getValue()+"</accumulationBehaviour>\n" +
                            "	</TargetReading>\n";
            }
            response += "</TargetReadingList>\n";   
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> TargetReading(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            TargetReading targetReading = new TargetReading();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int tar = Integer.parseInt(msgList[6]);
            targetReading = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).targetReadingList.Get(tar);
                          
            response = "<TargetReading href=\"get/bill/"+index+"/ca/"+ca+"/tar/"+tar+"\"\n" +
                        "subscribable=\"1\" results=\"1\" xmlns=\"http://zigbee.org/sep\"\n" +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n"+
                        "   <mRID>"+targetReading.billingMeterReadingBase.meterReadingBase.identifiedObject.mRID+"</mRID>\n" +
                        "   <description>"+targetReading.billingMeterReadingBase.meterReadingBase.identifiedObject.description+"</description>\n" +
                        "   <accumulationBehaviour>"+targetReading.billingMeterReadingBase.readingType.accumulationBehaviour.getValue().getValue()+"</accumulationBehaviour>\n" +
                        "   <kind>"+targetReading.billingMeterReadingBase.readingType.kind.getValue().getValue()+"</kind>\n" +
                        "   <numberOfConsumptionBlocks>"+targetReading.billingMeterReadingBase.readingType.numberOfConsumptionBlocks.getValue()+"</numberOfConsumptionBlocks>\n" +
                        "   <numberOfTouTiers>"+targetReading.billingMeterReadingBase.readingType.numberOfTouTiers.getValue()+"</numberOfTouTiers>\n" +
                        "</TargetReading>\n";
             
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> HistoricalReadingList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            HistoricalReadingList historicalReadingList = new HistoricalReadingList();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            historicalReadingList = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList;
                           
            response = "<HistoricalReadingList href=\"get/bill/"+index+"/ca/"+ca+"/ver\"\n" +
                        "	subscribable=\"1\" results=\"1\" xmlns=\"http://zigbee.org/sep\"\n" +
                        "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "	xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
            for(int k =0; k < historicalReadingList.Length(); k++)
            {
                response += "	<HistoricalReading href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+k+"\">\n" +
                            "		<mRID>"+historicalReadingList.Get(k).billingMeterReadingBase.meterReadingBase.identifiedObject.mRID+"</mRID>\n" +
                            "		<description>"+historicalReadingList.Get(k).billingMeterReadingBase.meterReadingBase.identifiedObject.description+"</description>\n" +
                            "		<BillingReadingSetListLink href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+k+"/brs\" all=\"180\" />\n" +
                            "		<ReadingTypeLink href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+k+"/rt\" />\n" +
                            "	</HistoricalReading>\n";
            }
            response += "</HistoricalReadingList>\n";   
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> HistoricalReading(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            HistoricalReading historicalReading = new HistoricalReading();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int ver = Integer.parseInt(msgList[6]);
            historicalReading = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Get(ver);
                            
            response = "<HistoricalReading href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+ver+"\"\n" +
                        "subscribable=\"1\" results=\"1\" xmlns=\"http://zigbee.org/sep\"\n" +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n"+
                        "   <mRID>"+historicalReading.billingMeterReadingBase.meterReadingBase.identifiedObject.mRID+"</mRID>\n" +
                        "   <description>"+historicalReading.billingMeterReadingBase.meterReadingBase.identifiedObject.description+"</description>\n" +
                        "   <BillingReadingSetListLink href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+ver+"/brs\" all=\"180\" />\n" +
                        "   <ReadingTypeLink href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+ver+"/rt\" />\n" +
                        "</HistoricalReading>\n";            
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
     private List<String> ServiceSupplier(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            ServiceSupplier serviceSupplier = new ServiceSupplier();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            
            serviceSupplier = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).serviceSupplier;
                         
            response = "<ServiceSupplier href=\"get/bill/"+index+"/ca/"+ca+"/ss\"\n" +
                        "subscribable=\"1\" results=\"1\" xmlns=\"http://zigbee.org/sep\"\n" +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n"+
                        "   <mRID>"+serviceSupplier.identifiedObject.mRID+"</mRID>\n" +
                        "   <description>"+serviceSupplier.identifiedObject.description+"</description>\n" +
                        "   <email>"+serviceSupplier.email.getValue()+"</email>\n" +
                        "   <phone>"+serviceSupplier.phone.getValue()+"</phone>\n" +
                        "   <web>"+serviceSupplier.web.getValue()+"</web>\n" +
                        "</ServiceSupplier>\n";            
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> BillingReadingSetList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            BillingReadingSetList billingReadingSetList = new BillingReadingSetList();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int pro = Integer.parseInt(msgList[6]);
            billingReadingSetList = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Get(pro).billingMeterReadingBase.billingReadingSetList;
                          
            response = "<BillingReadingSetList href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+pro+"/brs\"\n" +
                        "	subscribable=\"1\" results=\"1\" xmlns=\"http://zigbee.org/sep\"\n" +
                        "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "	xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
            for(int k =0; k < billingReadingSetList.Length(); k++)
            {
                response += "	<BillingReadingSet href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+pro+"/brs/"+k+"\">\n" +
                            "		<mRID>"+billingReadingSetList.Get(k).readingSetBase.identifiedObject.mRID+"</mRID>\n" +
                            "		<timePeriod>\n" +
                            "			<duration>"+billingReadingSetList.Get(k).readingSetBase.timePeriod.duration.getValue()+"</duration>\n" +
                            "			<start>"+billingReadingSetList.Get(k).readingSetBase.timePeriod.start.getValue()+"</start>\n" +
                            "		</timePeriod>\n" +
                            "		<BillingReadingListLink href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+pro+"/brs/"+k+"/br\"/>\n" +
                            "	</BillingReadingSet>\n";
            }
            response += "</BillingReadingSetList>\n";   
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> BillingReadingSet(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            BillingReadingSet billingReadingSet = new BillingReadingSet();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int pro = Integer.parseInt(msgList[6]);
            int brs = Integer.parseInt(msgList[8]);
            billingReadingSet = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Get(pro).billingMeterReadingBase.billingReadingSetList.Get(brs);
                             
            response += "<BillingReadingSet href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+pro+"/brs/"+brs+"\">xmlns=\"http://zigbee.org/sep\"\n" +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "	<mRID>"+billingReadingSet.readingSetBase.identifiedObject.mRID+"</mRID>\n" +
                        "	<timePeriod>\n" +
                        "		<duration>"+billingReadingSet.readingSetBase.timePeriod.duration.getValue()+"</duration>\n" +
                        "		<start>"+billingReadingSet.readingSetBase.timePeriod.start.getValue()+"</start>\n" +
                        "	</timePeriod>\n" +
                        "	<BillingReadingListLink href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+pro+"/brs/"+brs+"/br\"/>\n" +
                        "</BillingReadingSet>\n";
             
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> BillingReadingList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            BillingReadingList billingReadingList = new BillingReadingList();
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int ver = Integer.parseInt(msgList[6]);
            int brs = Integer.parseInt(msgList[8]);
            billingReadingList = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Get(ver).billingMeterReadingBase.billingReadingSetList.Get(brs).billingReadingList;
                          
            response = "<BillingReadingList href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+ver+"/brs/"+brs+"/br\"\n" +
                        "	subscribable=\"1\" results=\"1\" xmlns=\"http://zigbee.org/sep\"\n" +
                        "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "	xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
            for(int k =0; k < billingReadingList.Length(); k++)
            {
                response += "	<BillingReading href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+ver+"/brs/"+brs+"/br/"+k+"\">\n" +
                            "		<consumptionBlock>"+billingReadingList.Get(k).readingBase.consumptionBlock.getValue().getValue()+"</consumptionBlock>\n" +
                            "		<qualityFlags>"+billingReadingList.Get(k).readingBase.qualityFlags.getValue()+"</qualityFlags>\n" +
                            "		<timePeriod>\n" +
                            "			<duration>"+billingReadingList.Get(k).readingBase.timePeriod.duration.getValue()+"</duration>\n" +
                            "			<start>"+billingReadingList.Get(k).readingBase.timePeriod.start.getValue()+"</start>\n" +
                            "		</timePeriod>\n" +
                            "		<touTier>"+billingReadingList.Get(k).readingBase.touTier.getValue().getValue()+"</touTier>\n" +
                            "		<value>"+billingReadingList.Get(k).readingBase.value.getValue()+"</value>\n" +
                            "		<Charge>\n" +
                            "			<kind>"+billingReadingList.Get(k).charge.kind.getValue().getValue()+"</kind>\n" +
                            "			<value>"+billingReadingList.Get(k).charge.value.getValue()+"</value>\n" +
                            "		</Charge>\n" +                            
                            "	</BillingReading>\n";
            }
            response += "</BillingReadingList>\n";   
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> BillingReading(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                              
            int index = Integer.parseInt(msgList[2]);
            int ca = Integer.parseInt(msgList[4]);
            int ver = Integer.parseInt(msgList[6]);
            int brs = Integer.parseInt(msgList[8]);
            int br = Integer.parseInt(msgList[10]);
            BillingReading billingReading = new BillingReading();
            int i =0;
            billingReading = currentUser.billing.customerAccountList.Get(index).customerAgreementList.Get(ca).historicalReadingList.Get(ver).billingMeterReadingBase.billingReadingSetList.Get(brs).billingReadingList.Get(br);
                           
            response =  "<BillingReading href=\"get/bill/"+index+"/ca/"+ca+"/ver/"+ver+"/brs/"+brs+"/br/"+br+"\" Cxmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n" +
                        "	<consumptionBlock>"+billingReading.readingBase.consumptionBlock.getValue().getValue()+"</consumptionBlock>\n" +
                        "	<qualityFlags>"+billingReading.readingBase.qualityFlags.getValue()+"</qualityFlags>\n" +
                        "	<timePeriod>\n" +
                        "		<duration>"+billingReading.readingBase.timePeriod.duration.getValue()+"</duration>\n" +
                        "		<start>"+billingReading.readingBase.timePeriod.start.getValue()+"</start>\n" +
                        "	</timePeriod>\n" +
                        "	<touTier>"+billingReading.readingBase.touTier.getValue().getValue()+"</touTier>\n" +
                        "	<value>"+billingReading.readingBase.value.getValue()+"</value>\n" +
                        "	<Charge>\n" +
                        "		<kind>"+billingReading.charge.kind.getValue().getValue()+"</kind>\n" +
                        "	<value>"+billingReading.charge.value.getValue()+"</value>\n" +
                        "	</Charge>\n" +                            
                        "</BillingReading>\n";
            list.add("T");            
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
     // end billing
     
    // Start Metering
    private List<String> ResponseMetering(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 2)
            return ResponseUsagePointList(cmdmsg);
        if(msgList.length == 4 && msgList[3].equals("mr"))
            return ResponseMeterReadingList(cmdmsg);
        if(msgList.length == 5 && msgList[3].equals("mr"))
            return ResponseMeterReading(cmdmsg);
        if(msgList.length == 6 && msgList[3].equals("mr") && msgList[5].equals("rs"))
            return ResponseReadingSetList(cmdmsg);
        if(msgList.length == 6 && msgList[3].equals("mr") && msgList[5].equals("rt"))
            return ResponseReadingType(cmdmsg);
        if(msgList.length == 8 && msgList[3].equals("mr") && msgList[5].equals("rs") && msgList[7].equals("r"))
            return ResponseReadingList(cmdmsg);
        if(msgList.length == 9 && msgList[3].equals("mr") && msgList[5].equals("rs") && msgList[7].equals("r"))
            return ResponseReading(cmdmsg);
        return NoneResponse();
    }
    
    private List<String> ResponseUsagePointList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response ;                 
            response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            response += "<UsagePointList all=\"1\" href=\"/upt\" results=\""+currentUser.endDevice.usagePointList.Length()+"\" subscribable=\"0\" xmlns=\"http://zigbee.org/sep\">\n";
            
            for(int i = 0 ; i< currentUser.endDevice.usagePointList.Length(); i++)
            {                
                response += currentUser.endDevice.usagePointList.GetUsagePointList().get(i).xmlResponse("/upt/"+i);
            }
            response += "</UsagePointList>\n";
            list.add("T");
            list.add(response);
            return list;
        }
        catch (Exception ex){}
        return NoneResponse();
    }
    
    private List<String> ResponseUsagePoint(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";
                              
            List<MeterReading> meterReading = new ArrayList<MeterReading>();
            long index = Long.parseLong(msgList[2]);
            meterReading = currentUser.endDevice.usagePointList.Get((int)index).meterReadingList.Values();
                          
            response = "<MeterReadingList xmlns=\"http://zigbee.org/sep\" all=\"1\"\n";
            response += "href=\"/upt/"+index+"\" results=\"1\" subscribable=\"0\"\n";
            response += "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
            response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";

            for(int k =0 ; k < meterReading.size(); k++)
            {
                 response += "<MeterReading href=\"get/upt/"+index+"/mr/"+k+"\">\n";
                 response += "        <mRID>"+currentUser.endDevice.functionSetAssignments.mRID+"</mRID>\n";
                 response += "         <description>"+currentUser.endDevice.functionSetAssignments.description+"</description>\n";
                 response += "         <ReadingSetListLink all=\""+meterReading.get(k).readingSetList.Length() +"\" href=\"get/upt/"+index+"/mr/"+k+"/rs\"/>\n";
                 response += "         <ReadingTypeLink href=\"get/upt/"+index+"/mr/"+k+"/rt\">\n";
                 response += " </MeterReading>\n";
            }
            response += "</MeterReadingList>\n";   
            list.add("T");
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> ResponseMeterReadingList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";            
            long index = Long.parseLong(msgList[2]);            
                response = currentUser.endDevice.usagePointList.Get((int)index).meterReadingList.Response("", cmdmsg);
            list.add("T");
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> ResponseMeterReading(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";                   
            List<MeterReading> meterReading = new ArrayList<MeterReading>();
            int index = Integer.parseInt(msgList[2]);
            int inmr = Integer.parseInt(msgList[4]);
             meterReading = currentUser.endDevice.usagePointList.Get((int)index).meterReadingList.Values();
                           
            response = "<MeterReading xmlns=\"http://zigbee.org/sep\" all=\"1\"\n";
            response += "href=\"/upt/"+index+"/mr\" results=\"1\" subscribable=\"0\"\n";
            response += "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
            response += "xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
            response += "        <ReadingSetListLink all=\""+meterReading.get(inmr).readingSetList.Length() +"\" href=\"get/upt/"+index+"/mr/"+inmr+"/rs\"/>\n";
            response += "        <ReadingTypeLink href=\"get/upt/"+index+"/mr/"+inmr+"/rt\">\n";      
            response += "</MeterReading>\n";   
            list.add("T");
            list.add(response);
            return list;            
        }
        catch (Exception ex)                
        {            
        }
        return NoneResponse();
    }
    
    private List<String> ResponseReadingSetList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";
            
            
            List<MeterReading> meterReading = new ArrayList<MeterReading>();
            long index = Long.parseLong(msgList[2]);
          
            Random random = new Random();
            meterReading = currentUser.endDevice.usagePointList.Get((int)index).meterReadingList.Values();
           
             ReadingSetList rslist = meterReading.get(Integer.parseInt(msgList[4])).readingSetList;
             
             response = "<ReadingSetList xmlns=\"http://zigbee.org/sep\" all=\""+rslist.Length()+"\"\n";
             response += "   href=\"/getupt/"+msgList[2]+"/mr/"+msgList[4]+"/rs\" results=\""+rslist.Length()+"\" subscribable=\"0\"\n";
             response += "   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
             response += "   xsi:schemaLocation=\"http://zigbee.org/sep ../sep.xsd\">\n";
             for(int k =0 ; k< rslist.Length(); k++)
             {
                response += "   <ReadingSet href=\"/upt/"+msgList[2]+"/mr/"+msgList[4]+"/rs/"+k+"\">\n";
                response += "        <mRID>"+currentUser.endDevice.functionSetAssignments.mRID+"</mRID>\n";
                response += "         <description>"+currentUser.endDevice.functionSetAssignments.description+"</description>\n";
                response += "           <timePeriod>\n";
                response += "                   <duration>"+random.nextInt(9999)+"</duration>\n";
                response += "                   <start>"+random.nextInt(99999999)+"</start>\n";
                response += "           </timePeriod>\n";
                response += "           <ReadingListLink all=\""+rslist.Get(k).readingList.Length() +"\" href=\"/upt/0/mr/0/rs/"+k+"/r\" />\n";
                response += "   </ReadingSet>\n";
             }
             response += "</ReadingSetList>\n";
             list.add("T");
             list.add(response);
             return list;
        }
        catch (Exception ex)  { }
        return NoneResponse();
        
    }
    
    private List<String> ResponseReadingType(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";
            
            ReadingType readingType = new ReadingType();
            int index = Integer.parseInt(msgList[2]);
            int inmr = Integer.parseInt(msgList[4]);
            readingType = currentUser.endDevice.usagePointList.Get(index).meterReadingList.Get(inmr).readingType;
             
             response = "<ReadingType xmlns=\"http://zigbee.org/sep\" href=\"/upt/"+index+"/mr/"+inmr+"/rt\"\n";
             response += "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
             response += "       xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
             response += "       <accumulationBehaviour>"+readingType.accumulationBehaviour.getValue().getValue()+"</accumulationBehaviour>\n";
             response += "       <commodity>"+readingType.commodity.getValue().getValue()+"</commodity>\n";
             response += "       <dataQualifier>"+readingType.dataQualifier.getValue().getValue()+"</dataQualifier>\n";
             response += "       <flowDirection>"+readingType.flowDirection.getValue().getValue()+"</flowDirection>\n";
             response += "       <kind>"+readingType.kind.getValue().getValue()+"</kind>\n";
             response += "       <numberOfConsumptionBlocks>"+readingType.numberOfConsumptionBlocks.getValue()+"</numberOfConsumptionBlocks>\n";
             response += "       <numberOfTouTiers>"+readingType.numberOfTouTiers.getValue()+"</numberOfTouTiers>\n";
             response += "       <phase>"+readingType.phase.getValue().getValue()+"</phase>\n";
             response += "       <powerOfTenMultiplier>"+readingType.powerOfTenMultiplier.getValue()+"</powerOfTenMultiplier>\n";
             response += "       <uom>"+readingType.uom.getValue().getValue()+"</uom>\n";
             response += "</ReadingType>\n";
             list.add("T");
             list.add(response);
             return list;
        }
        catch (Exception ex)  { }
        return NoneResponse();
        
    }
    
     private List<String> ResponseReadingList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";
            
            ReadingList readingList = new ReadingList();
            int index = Integer.parseInt(msgList[2]);
            int inmr = Integer.parseInt(msgList[4]);
            int inrs = Integer.parseInt(msgList[6]);
            
            readingList = currentUser.endDevice.usagePointList.Get(index).meterReadingList.Get(inmr).readingSetList.Get(inrs).readingList;
            
             response = "<ReadingList xmlns=\"http://zigbee.org/sep\" all=\""+readingList.Length()+"\"\n";
             response += "       href=\"get/upt/"+index+"/mr/"+inmr+"/rs/"+inrs+"/r\" results=\""+readingList.Length()+"\"\n";
             response += "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
             response += "       xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
             for(int k =0 ; k< readingList.Length(); k++)
             {
                response += "       <Reading href=\"get/upt/"+index+"/mr/"+inmr+"/rs/"+inrs+"/r/"+k+"\">\n";
                response += "               <value>"+readingList.Get(k).subscribable.getValue().getValue()+"</value>\n";
                response += "               <localID>"+readingList.Get(k).localID.getValue()+"</localID>\n";
                response += "       </Reading>\n";
             }
             response += "</ReadingList>\n";
             list.add("T");
             list.add(response);
             return list;
        }
        catch (Exception ex)  { }
        return NoneResponse();
        
    }
     
     private List<String> ResponseReading(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response = "";            
            ReadingList readingList = new ReadingList();
            int index = Integer.parseInt(msgList[2]);
            int inmr = Integer.parseInt(msgList[4]);
            int inrs = Integer.parseInt(msgList[6]);
            int inr = Integer.parseInt(msgList[8]);
            readingList = currentUser.endDevice.usagePointList.Get(index).meterReadingList.Get(inmr).readingSetList.Get(inrs).readingList;
            
             response = "<Reading xmlns=\"http://zigbee.org/sep\" href=\"/upt/"+index+"/mr/"+inmr+"/rs/"+inrs+"/r/"+inr+"\"\n" +
                        "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "	xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n" +
                        "	<value>"+readingList.Get(inr).subscribable.getValue().getValue()+"</value>\n" +
                        "</Reading> ";
             list.add("T");
             list.add(response);
             return list;
        }
        catch (Exception ex)  { }
        return NoneResponse();
        
    }
    
     // End of Metering
     
   private List<String> NoneResponse()
    {
        return Arrays.asList(new String[]{"F","HTTP 204 - No content"}); 
    }    
    
    private List<String> ResponseDeviceList(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
       String[] msgList = cmdmsg.split("/");
        String response = "";
        if(msgList.length == 2)
        {           
            response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            response += "<EndDeviceList all=\"1\" href=\"/edev\" results=\"1\" subscribable=\"0\" xmlns=\"http://zigbee.org/se/\">\n";
            
            int i = 1;
            {                
                response += "<EndDevice href=\"/edev/3\" subscribable=\"0\">\n";
                response += "    <ConfigurationLink href=\"/edev/"+i+"/cfg\"/>\n";
                response += "    <DeviceInformationLink href=\"/edev/"+i+"/di\"/>\n";
                response += "    <DeviceStatusLink href=\"/edev/"+i+"/ds\"/>\n";
                response += "    <FileStatusLink href=\"/edev/"+i+"/fs\"/>\n";
                response += "    <PowerStatusLink href=\"/edev/"+i+"/ps\"/>\n";
                response += "    <sFDI>"+currentUser.endDevice.deviceInformation.iFDI+"</sFDI>\n";
                response += "    <FunctionSetAssignmentsListLink all=\""+i+"\" href=\"/edev/"+i+"/fsal\"/>\n";
                response += "    <RegistrationLink href=\"/edev/"+i+"/reg\"/>\n";
                response += "    <SubscriptionListLink all=\"0\" href=\"/edev/"+i+"/subl\"/>\n";
                response += "</EndDevice>\n";
                
            }
            response += "</EndDeviceList>\n";
            list.add("T");
        }
        else 
        {
            list.add("F");
        }
        list.add(response);
                
        return list;
    }    
    
    private List<String> ResponseDeviceInformation(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
       String[] msgList = cmdmsg.split("/");
        String response = "";
        if(msgList.length == 2)
        {           
            response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            response += "<EndDeviceInformation all=\"1\" href=\"/edev\" results=\"1\" subscribable=\"0\" xmlns=\"http://zigbee.org/se/\">\n";
            response += "   <HostName>"+currentUser.Name+"</HostName>\n";
            response += "   <HostIP>"+currentUser.Ip+"</HostIP>\n";
            response += "</EndDeviceInformation>\n";
            list.add("T");
        }
        else 
        {
            list.add("F");
        }
        list.add(response);
                
        return list;
    }   
    
    // Get Pricing information
     private List<String> ResponsePricing(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 2)
            return ResponseTariffProfileList(cmdmsg);
        if(msgList.length == 3)
            return ResponseTariffProfile(cmdmsg);
        if(msgList.length == 4 && msgList[3].equals("rc"))
            return ResponseRateComponentList(cmdmsg);
        if(msgList.length == 5 && msgList[3].equals("rc"))
            return ResponseRateComponent(cmdmsg);
        if(msgList.length == 6 && msgList[3].equals("rc") && msgList[5].equals("tti"))
            return ResponseTimeTariffIntervalList(cmdmsg);
        if(msgList.length == 7 && msgList[3].equals("rc") && msgList[5].equals("tti"))
            return ResponseTimeTariffInterval(cmdmsg);
        if(msgList.length == 8 && msgList[3].equals("rc") && msgList[5].equals("tti") && msgList[5].equals("cti") )
            return ResponseConsumptionTariffIntervalList(cmdmsg);
        if(msgList.length == 9 && msgList[3].equals("rc") && msgList[5].equals("tti") && msgList[5].equals("cti") )
            return ResponseConsumptionTariffInterval(cmdmsg);
        
        return NoneResponse();
    }
    // End
    private List<String> ResponseTariffProfileList(String cmdmsg)
    {        
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        try
        {
            
             response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
             response += "<TariffProfileList href=\"get/tp\" xsi:schemaLocation=\"http://zigbee.org/sep sep.xsd\" xmlns=\"http://zigbee.org/sep\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n";
                        
              for(int i = 0; i< currentUser.pricing.tariffProfileList.Length(); i++)
                {
                     response += "<TariffProfile>\n";                    
                     response += "   <Name>"+currentUser.pricing.tariffProfileList.Get(i).rateCode.getValue()+"</Name>\n";
                     response += "   <TariffProfileLink href=\"get/tp/"+i+"\" all=\"1\"/>\n"; 
                     response += "</TariffProfile>\n";
                   
                } 
              response += "</TariffProfileList>\n";
              
              list.add("T");
              list.add(response);
              return list;
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> ResponseTariffProfile(String cmdmsg)
    {        
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        try
        {
            int index = Integer.parseInt(msgList[2]);
            response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            response += "<TariffProfile href=\"get/tp/"+index+"\" xsi:schemaLocation=\"http://zigbee.org/sep sep.xsd\" xmlns=\"http://zigbee.org/sep\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n";
            response += "<rateCode>"+currentUser.pricing.tariffProfileList.GetValues().get(index).rateCode.getValue()+"</rateCode>\n";
            response += "<RateComponentListLink href=\"get/tp/"+index+"/rc\" all=\"1\"/>\n";
            response += "</TariffProfile>\n";
                   
            list.add("T");
            list.add(response);
            return list;
                        
        }
        catch(Exception ex) {}
        return NoneResponse();
    }   
    
    private List<String> ResponseRateComponentList(String cmdmsg)
    {        
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        RateComponentList rateComponentList = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            rateComponentList = currentUser.pricing.tariffProfileList.Get(index).rateComponentList;
            
             if(rateComponentList!=null)
             {
                response = "<RateComponentList href=\"get/tp/"+index+"/rc\" xmlns=\"http://zigbee.org/sep\"\n";
                response += "        all=\""+rateComponentList.Length()+"\" results=\""+rateComponentList.Length()+"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "        xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
                for(int k = 0; k< rateComponentList.Length(); k++)
                {
                    response += "        <RateComponent href=\"get/tp/"+index+"/rc/"+k+"\">\n";
                    response += "                <mRID>"+rateComponentList.Get(k).identifiedObject.mRID+"</mRID>\n";
                    response += "                <description>"+rateComponentList.Get(k).identifiedObject.description+"</description>\n";
                    response += "                <ActiveTimeTariffIntervalListLink\n";
                    response += "                        all=\"0\" href=\"get/tp/"+index+"/rc/"+k+"/acttti\" />\n";
                    response += "                <flowRateEndLimit>\n";
                    response += "                        <multiplier>"+rateComponentList.Get(k).flowRateEndLimit.multiplier.getValue()+"</multiplier>\n";
                    response += "                        <unit>"+rateComponentList.Get(k).flowRateEndLimit.unit.getValue().getValue()+"</unit>\n";
                    response += "                        <value>"+rateComponentList.Get(k).flowRateEndLimit.value.getValue()+"</value>\n";
                    response += "                </flowRateEndLimit>\n";
                    response += "                <flowRateStartLimit>\n";
                    response += "                        <multiplier>"+rateComponentList.Get(k).flowRateSatrtLimit.multiplier.getValue()+"</multiplier>\n";
                    response += "                        <unit>"+rateComponentList.Get(k).flowRateSatrtLimit.unit.getValue().getValue()+"</unit>\n";
                    response += "                        <value>"+rateComponentList.Get(k).flowRateSatrtLimit.value.getValue()+"</value>\n";
                    response += "                </flowRateStartLimit>\n";
                    response += "                <ReadingTypeLink href=\"/rt/1\" />\n";
                    response += "                <roleFlags>"+rateComponentList.Get(k).roleFlags.getValue()+"</roleFlags>\n";
                    response += "                <TimeTariffIntervalListLink all=\""+rateComponentList.Get(k).timeTariffIntervalList.Length()+"\"\n";
                    response += "                        href=\"get/tp/"+index+"/rc/"+k+"/tti\" />\n";
                    response += "        </RateComponent>\n";
                }    
                response += "</RateComponentList>\n";
              list.add("T");
              list.add(response);
              return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> ResponseRateComponent(String cmdmsg)
    {        
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        RateComponent rateComponent = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            rateComponent = currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Get(inrc);
            
             if(rateComponent!=null)
             {
                response = "<RateComponent href=\"get/tp/"+index+"/rc/"+inrc+"\" xmlns=\"http://zigbee.org/sep\"\n";
                response += "        xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
                    response += "                <mRID>"+rateComponent.identifiedObject.mRID+"</mRID>\n";
                    response += "                <description>"+rateComponent.identifiedObject.description+"</description>\n";
                    response += "                <ActiveTimeTariffIntervalListLink\n";
                    response += "                        all=\"0\" href=\"get/tp/"+index+"/rc/"+inrc+"/acttti\" />\n";
                    response += "                <flowRateEndLimit>\n";
                    response += "                        <multiplier>"+rateComponent.flowRateEndLimit.multiplier.getValue()+"</multiplier>\n";
                    response += "                        <unit>"+rateComponent.flowRateEndLimit.unit.getValue().getValue()+"</unit>\n";
                    response += "                        <value>"+rateComponent.flowRateEndLimit.value.getValue()+"</value>\n";
                    response += "                </flowRateEndLimit>\n";
                    response += "                <flowRateStartLimit>\n";
                    response += "                        <multiplier>"+rateComponent.flowRateSatrtLimit.multiplier.getValue()+"</multiplier>\n";
                    response += "                        <unit>"+rateComponent.flowRateSatrtLimit.unit.getValue().getValue()+"</unit>\n";
                    response += "                        <value>"+rateComponent.flowRateSatrtLimit.value.getValue()+"</value>\n";
                    response += "                </flowRateStartLimit>\n";
                    response += "                <ReadingTypeLink href=\"/rt/1\" />\n";
                    response += "                <roleFlags>"+rateComponent.roleFlags.getValue()+"</roleFlags>\n";
                    response += "                <TimeTariffIntervalListLink all=\""+rateComponent.timeTariffIntervalList.Length()+"\"\n";
                    response += "                        href=\"get/tp/"+index+"/rc/"+inrc+"/tti\" />\n";
                    response += "        </RateComponent>\n";
                
              list.add("T");
              list.add(response);
              return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> ResponseTimeTariffIntervalList(String cmdmsg)
    {        
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        TimeTariffIntervalList timeTariffIntervalList = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            timeTariffIntervalList = currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Get(inrc).timeTariffIntervalList;
            
             if(timeTariffIntervalList!=null)
             {
                response = "<TimeTariffIntervalList href=\"get/tp/"+index+"/rc/"+inrc+"/tti\"\n";
                response += "xmlns=\"http://zigbee.org/sep\" subscribable=\"1\" all=\""+timeTariffIntervalList.Length()+"\" results=\"5\"\n";
                response += "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
                for(int k = 0 ; k<timeTariffIntervalList.Length(); k++)
                {
                    response += "<TimeTariffInterval subscribable=\"1\" href=\"get/tp/"+index+"/rc/"+inrc+"/tti/"+k+"\">\n";
                    response += "        <mRID>"+timeTariffIntervalList.Get(k).respondableSubscribableIdentifiedObject.mRID+"</mRID>\n";
                    response += "        <description>"+timeTariffIntervalList.Get(k).respondableSubscribableIdentifiedObject.description.getValue()+"</description>\n";
                    response += "        <creationTime>"+timeTariffIntervalList.Get(k).event.creationTime.getValue()+"</creationTime>\n";
                    response += "        <EventStatus>\n";
                    response += "                <currentStatus>"+timeTariffIntervalList.Get(k).event.eventStatus.currentStatus.getValue()+"</currentStatus>\n";
                    response += "                <dateTime>"+timeTariffIntervalList.Get(k).event.eventStatus.datetime.getValue()+"</dateTime>\n";
                    response += "                <potentiallySuperseded>"+timeTariffIntervalList.Get(k).event.eventStatus.potentiallySuperseded+"</potentiallySuperseded>\n";
                    response += "        </EventStatus>\n";
                    response += "        <interval>\n";
                    response += "                <duration>"+timeTariffIntervalList.Get(k).event.interval.duration.getValue()+"</duration>\n";
                    response += "                <start>"+timeTariffIntervalList.Get(k).event.interval.start.getValue()+"</start>\n";
                    response += "        </interval>\n";
                    response += "        <randomizeDuration>"+timeTariffIntervalList.Get(k).event.randomizableEvent.randomizeDuration.getValue()+"</randomizeDuration>\n";
                    response += "        <randomizeStart>"+timeTariffIntervalList.Get(k).event.randomizableEvent.randomizeStart.getValue()+"</randomizeStart>\n";
                    response += "        <ConsumptionTariffIntervalListLink\n";
                    response += "                href=\"get/tp/"+index+"/rc/"+inrc+"/tti/"+k+"/cti\" all=\""+timeTariffIntervalList.Get(k).conTariffIntervalList.Length()+"\" />\n";
                    response += "        <touTier>"+timeTariffIntervalList.Get(k).touTier.getValue().getValue()+"</touTier>\n";
                    response += "     </TimeTariffInterval>\n";
                }    
                response += "</TimeTariffIntervalList>\n";
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> ResponseTimeTariffInterval(String cmdmsg)
    {        
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        TimeTariffInterval timeTariffInterval = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            int intti = Integer.parseInt(msgList[6]);
            timeTariffInterval = currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Get(inrc).timeTariffIntervalList.Get(intti);
             if(timeTariffInterval!=null)
             {
                response = "<TimeTariffInterval href=\"get/tp/"+index+"/rc/"+inrc+"/tti/"+intti+"\"\n";
                response += "xmlns=\"http://zigbee.org/sep\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
                response += "        <mRID>"+timeTariffInterval.respondableSubscribableIdentifiedObject.mRID+"</mRID>\n";
                response += "        <description>"+timeTariffInterval.respondableSubscribableIdentifiedObject.description.getValue()+"</description>\n";
                response += "        <creationTime>"+timeTariffInterval.event.creationTime.getValue()+"</creationTime>\n";
                response += "        <EventStatus>\n";
                response += "                <currentStatus>"+timeTariffInterval.event.eventStatus.currentStatus.getValue()+"</currentStatus>\n";
                response += "                <dateTime>"+timeTariffInterval.event.eventStatus.datetime.getValue()+"</dateTime>\n";
                response += "                <potentiallySuperseded>"+timeTariffInterval.event.eventStatus.potentiallySuperseded+"</potentiallySuperseded>\n";
                response += "        </EventStatus>\n";
                response += "        <interval>\n";
                response += "                <duration>"+timeTariffInterval.event.interval.duration.getValue()+"</duration>\n";
                response += "                <start>"+timeTariffInterval.event.interval.start.getValue()+"</start>\n";
                response += "        </interval>\n";
                response += "        <randomizeDuration>"+timeTariffInterval.event.randomizableEvent.randomizeDuration.getValue()+"</randomizeDuration>\n";
                response += "        <randomizeStart>"+timeTariffInterval.event.randomizableEvent.randomizeStart.getValue()+"</randomizeStart>\n";
                response += "        <ConsumptionTariffIntervalListLink\n";
                response += "                href=\"get/tp/"+index+"/rc/"+inrc+"/tti/"+intti+"/cti\" all=\""+timeTariffInterval.conTariffIntervalList.Length()+"\" />\n";
                response += "        <touTier>"+timeTariffInterval.touTier.getValue().getValue()+"</touTier>\n";
                response += "     </TimeTariffInterval>\n";
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> ResponseConsumptionTariffIntervalList(String cmdmsg)
    {        
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        ConsumptionTariffIntervalList consumptionTariffIntervalList = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            int intti = Integer.parseInt(msgList[6]);
            consumptionTariffIntervalList = currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Get(inrc).timeTariffIntervalList.Get(intti).conTariffIntervalList;
            
             if(consumptionTariffIntervalList!=null)
             {
                response = "<ConsumptionTariffIntervalList href=\"get/tp/"+index+"/rc/"+inrc+"/tti/"+intti+"/cti\"\n";
                response += "        all=\""+consumptionTariffIntervalList.Length()+"\" results=\""+consumptionTariffIntervalList.Length()+"\" xmlns=\"http://zigbee.org/sep\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "        xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
                                
                for(int k = 0 ; k<consumptionTariffIntervalList.Length(); k++)
                {
                    response += "        <ConsumptionTariffInterval href=\"get/tp/"+index+"/rc/"+inrc+"/tti/"+intti+"/cti/"+k+"\">\n";
                    response += "                <consumptionBlock>"+consumptionTariffIntervalList.Get(k).consumptionBlock.getValue().getValue()+"</consumptionBlock>\n";
                    response += "                <price>"+consumptionTariffIntervalList.Get(k).price.getValue()+"</price>\n";
                    response += "                <startValue>"+consumptionTariffIntervalList.Get(k).startValue.getValue()+"</startValue>\n";
                    response += "        </ConsumptionTariffInterval>\n";  
                }    
                response += "</ConsumptionTariffIntervalList>\n";
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    private List<String> ResponseConsumptionTariffInterval(String cmdmsg)
    {        
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        ConsumptionTariffInterval consumptionTariffInterval = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            int inrc = Integer.parseInt(msgList[4]);
            int intti = Integer.parseInt(msgList[6]);
            int incti = Integer.parseInt(msgList[8]);
            int i =0;
            consumptionTariffInterval = currentUser.pricing.tariffProfileList.Get(index).rateComponentList.Get(inrc).timeTariffIntervalList.Get(intti).conTariffIntervalList.Get(incti);
            
             if(consumptionTariffInterval!=null)
             {
                response = "<ConsumptionTariffInterval href=\"get/tp/"+index+"/rc/"+inrc+"/tti/"+intti+"/cti/"+incti+"\"\n";
                response += "        xmlns=\"http://zigbee.org/sep\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n";
                response += "        xsi:schemaLocation=\"http://zigbee.org/sep ../../sep.xsd\">\n";
                response += "        <consumptionBlock>"+consumptionTariffInterval.consumptionBlock.getValue().getValue()+"</consumptionBlock>\n";
                response += "        <amount>"+consumptionTariffInterval.environmentalCost.amount.getValue()+"</amount>\n";
                response += "        <costKind>"+consumptionTariffInterval.environmentalCost.costKind.getValue().getValue()+"</costKind>\n";
                response += "        <costLevel>"+consumptionTariffInterval.environmentalCost.costLevel.getValue()+"</costLevel>\n";
                response += "        <numCostLevels>"+consumptionTariffInterval.environmentalCost.numCostLevels.getValue()+"</numCostLevels>\n";
                response += "        <price>"+consumptionTariffInterval.price.getValue()+"</price>\n";
                response += "        <startValue>"+consumptionTariffInterval.startValue.getValue()+"</startValue>\n";
                response += "</ConsumptionTariffInterval>\n";                  
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    
    // End of Pricing
    
    // Messaging
    private List<String> ResponseMessaging(String cmdmsg)
    {
        String[] msgList = cmdmsg.split("/");
        if(msgList.length == 2)
            return ResponseMessagingProgramList(cmdmsg);
        if(msgList.length == 3)
            return ResponseMessagingProgram(cmdmsg);
        if(msgList.length == 4 && msgList[3].equals("txt"))
            return ResponseTextMessageList(cmdmsg);
        if(msgList.length == 5 && msgList[3].equals("txt"))
            return ResponseTextMessage(cmdmsg);
         
        return NoneResponse();
    }
    
    private List<String> ResponseMessagingProgramList(String cmdmsg)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            String[] msgList = cmdmsg.split("/");
            String response ;                 
            response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            response += "<MessagingProgramList all=\"1\" href=\"get/msg\" results=\""+currentUser.messaging.messagingProgramList.Length()+"\" subscribable=\"0\" xmlns=\"http://zigbee.org/sep\">\n";
            
            for(int i=0; i< currentUser.messaging.messagingProgramList.Length(); i++)
            {                
                response += "<MessagingProgram href=\"get/msg/"+i+"\">\n";
                response += "	<locale>"+currentUser.messaging.messagingProgramList.Get(i).locale+"</locale>\n";
                response += "	<primacy>"+currentUser.messaging.messagingProgramList.Get(i).primacy+"</primacy>\n";
                response += "</MessagingProgram>\n";             
            }
            response += "</MessagingProgramList>\n";
            list.add("T");
            list.add(response);
            return list;
        }
        catch (Exception ex){}
        return NoneResponse();
    }
    
     private List<String> ResponseMessagingProgram(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        MessagingProgram  messagingProgram = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            messagingProgram = currentUser.messaging.messagingProgramList.Get(index);
            
             if(messagingProgram!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<MessagingProgram href=\"get/msg/"+index+"\" results =\"1\">\n";
                response += "	<locale>"+messagingProgram.locale+"</locale>\n";
                response += "	<primacy>"+messagingProgram.primacy+"</primacy>\n";
                response += "	<TextMessageList  href=\"get/msg/"+index+"/txt\">\n";
                response += "	<ActiveTextMessageList  href=\"get/msg/"+index+"/acttxt\">\n";
                response += "</MessagingProgram>\n";    
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
     
     private List<String> ResponseTextMessageList(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        TextMessageList  textMessageList = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            textMessageList = currentUser.messaging.messagingProgramList.Get(index).textMessageList;
            
             if(textMessageList!=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                response += "<textMessageList all=\"1\" href=\"get/msg/"+index+"/txt\" results=\""+textMessageList.Length()+"\" subscribable=\"0\" xmlns=\"http://zigbee.org/sep\">\n";
                for(int k = 0; k < textMessageList.Length(); k++)
                {
                    response += "<textMessage href=\"get/msg/"+index+"/txt/"+k+"\">\n";
                    response += "	<originator>"+textMessageList.Get(k).originator.getValue()+"</originator>\n";
                    response += "	<priority>"+textMessageList.Get(k).priority.getValue().getValue()+"</priority>\n";                    
                    response += "	<priority>"+textMessageList.Get(k).textMessage+"</priority>\n";                    
                    response += "</textMessage>\n";    
                }
                response += "</textMessageList>\n";
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
     
     private List<String> ResponseTextMessage(String cmdmsg)
    {
        List<String> list = new ArrayList<String>();
        String[] msgList = cmdmsg.split("/");
        String response = "";
        TextMessage  textMessage = null;
        try
        {
            int index = Integer.parseInt(msgList[2]);
            int intxt = Integer.parseInt(msgList[4]);
            textMessage = currentUser.messaging.messagingProgramList.Get(index).textMessageList.Get(intxt);
            
             if(textMessage !=null)
             {
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";                
                response += "<textMessage href=\"get/msg/"+index+"/txt/"+intxt+"\">\n";
                response += "	<originator>"+textMessage.originator.getValue()+"</originator>\n";
                response += "	<priority>"+textMessage.priority.getValue().getValue()+"</priority>\n";                    
                response += "	<priority>"+textMessage.textMessage+"</priority>\n";      
                response += "   <mRID>"+textMessage.respondableSubscribableIdentifiedObject.mRID+"</mRID>\n";
                response += "   <description>"+textMessage.respondableSubscribableIdentifiedObject.description.getValue()+"</description>\n";
                response += "   <creationTime>"+textMessage.event.creationTime.getValue()+"</creationTime>\n";
                response += "   <EventStatus>\n";
                response += "         <currentStatus>"+textMessage.event.eventStatus.currentStatus.getValue()+"</currentStatus>\n";
                response += "         <dateTime>"+textMessage.event.eventStatus.datetime.getValue()+"</dateTime>\n";
                response += "         <potentiallySuperseded>"+textMessage.event.eventStatus.potentiallySuperseded+"</potentiallySuperseded>\n";
                response += "   </EventStatus>\n";
                response += "   <interval>\n";
                response += "         <duration>"+textMessage.event.interval.duration.getValue()+"</duration>\n";
                response += "         <start>"+textMessage.event.interval.start.getValue()+"</start>\n";
                response += "   </interval>\n";
                response += "   <randomizeDuration>"+textMessage.event.randomizableEvent.randomizeDuration.getValue()+"</randomizeDuration>\n";
                response += "   <randomizeStart>"+textMessage.event.randomizableEvent.randomizeStart.getValue()+"</randomizeStart>\n";
                response += "</textMessage>\n";   
                list.add("T");
                list.add(response);
                return list;
             }
        }
        catch (Exception ex) {}
        return NoneResponse();
    }
    // End of Messaging
}
