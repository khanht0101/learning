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
public class CustomerAgreementList implements Serializable
{
    public CustomerAgreementList()
    {
      customerAgreementList = new ArrayList<CustomerAgreement>();
    }
    private  List<CustomerAgreement> customerAgreementList;
    public void Add(CustomerAgreement customerAgreement)
    {
        customerAgreementList.add(customerAgreement);
    }
    public void Remove(CustomerAgreement customerAgreement)
    {
        customerAgreementList.remove(customerAgreement);
    }
    public void Remove(int index)
    {
        customerAgreementList.remove(index);
    }
    
    public int Length()
    {        
        return customerAgreementList.size();
    }
    public List<CustomerAgreement> GetValues()
    {
        return customerAgreementList;   
    }
    public CustomerAgreement Get(int index)
    {
        return customerAgreementList.get(index);   
    }
  
}
