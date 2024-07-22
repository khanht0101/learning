/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;


import FunctionSet.Messaging.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class BillingPeriodList implements Serializable
{
    public BillingPeriodList()
    {
      billingPeriodList = new ArrayList<BillingPeriod>();
    }
    private  List<BillingPeriod> billingPeriodList;
    public void Add(BillingPeriod billingPeriod)
    {
        billingPeriodList.add(billingPeriod);
    }
    public void Remove(BillingPeriod billingPeriod)
    {
        billingPeriodList.remove(billingPeriod);
    }
    public void Remove(int index)
    {
        billingPeriodList.remove(index);
    }
    
    public int Length()
    {        
        return billingPeriodList.size();
    }
    public List<BillingPeriod> GetValues()
    {
        return billingPeriodList;   
    }
    public BillingPeriod Get(int index)
    {
        return billingPeriodList.get(index);   
    }
  
}
