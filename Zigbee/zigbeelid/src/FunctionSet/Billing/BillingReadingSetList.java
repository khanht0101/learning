/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class BillingReadingSetList implements Serializable
{
    public BillingReadingSetList()
    {
      billingReadingSetList = new ArrayList<BillingReadingSet>();
    }
    private  List<BillingReadingSet> billingReadingSetList;
    public void Add(BillingReadingSet billingReadingSet)
    {
        billingReadingSetList.add(billingReadingSet);
    }
    public void Remove(BillingReadingSet billingReadingSet)
    {
        billingReadingSetList.remove(billingReadingSet);
    }
    public void Remove(int index)
    {
        billingReadingSetList.remove(index);
    }
    
    public int Length()
    {        
        return billingReadingSetList.size();
    }
    public List<BillingReadingSet> GetValues()
    {
        return billingReadingSetList;   
    }
    public BillingReadingSet Get(int index)
    {
        return billingReadingSetList.get(index);   
    }
  
}
