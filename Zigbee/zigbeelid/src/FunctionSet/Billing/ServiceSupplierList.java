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
public class ServiceSupplierList implements Serializable
{
    public ServiceSupplierList()
    {
      serviceSupplierList = new ArrayList<ServiceSupplier>();
      serviceSupplierList.add(new ServiceSupplier());
    }
    private  List<ServiceSupplier> serviceSupplierList;
    public void Add(ServiceSupplier serviceSupplier)
    {
        serviceSupplierList.add(serviceSupplier);
    }
    public void Remove(ServiceSupplier serviceSupplier)
    {
        serviceSupplierList.remove(serviceSupplier);
    }
    public void Remove(int index)
    {
        serviceSupplierList.remove(index);
    }
    
    public int Length()
    {        
        return serviceSupplierList.size();
    }
    public List<ServiceSupplier> GetValues()
    {
        return serviceSupplierList;   
    }
    public ServiceSupplier Get(int index)
    {
        return serviceSupplierList.get(index);   
    }
  
}