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
public class BillingReadingList implements Serializable
{
    public BillingReadingList()
    {
      billingReadingList = new ArrayList<BillingReading>();
    }
    private  List<BillingReading> billingReadingList;
    public void Add(BillingReading billingReading)
    {
        billingReadingList.add(billingReading);
    }
    public void Remove(BillingReading billingReading)
    {
        billingReadingList.remove(billingReading);
    }
    public void Remove(int index)
    {
        billingReadingList.remove(index);
    }
    
    public int Length()
    {        
        return billingReadingList.size();
    }
    public List<BillingReading> GetValues()
    {
        return billingReadingList;   
    }
    public BillingReading Get(int index)
    {
        return billingReadingList.get(index);   
    }
  
}
