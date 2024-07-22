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
public class CustomerAccountList implements Serializable
{
    public CustomerAccountList()
    {
      customerAccountList = new ArrayList<CustomerAccount>();
      customerAccountList.add(new CustomerAccount());
    }
    private  List<CustomerAccount> customerAccountList;
    public void Add(CustomerAccount customerAccount)
    {
        customerAccountList.add(customerAccount);
    }
    public void Remove(CustomerAccount customerAccount)
    {
        customerAccountList.remove(customerAccount);
    }
    public void Remove(int index)
    {
        customerAccountList.remove(index);
    }
    
    public int Length()
    {        
        return customerAccountList.size();
    }
    public List<CustomerAccount> GetValues()
    {
        return customerAccountList;   
    }
    public CustomerAccount Get(int index)
    {
        return customerAccountList.get(index);   
    }
  
}
