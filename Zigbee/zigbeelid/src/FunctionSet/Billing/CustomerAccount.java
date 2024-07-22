/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import com.Identification.IdentifiedObject;
import com.PrimaryTypes.*;
import com.Types.PowerOfTenMultiplierType;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class CustomerAccount implements Serializable
{
    public CustomerAccount ()
    {
        Random random = new Random();
        currency = new Int32(random.nextInt(32));
        customerAccount = new String42("Customer Account " +random.nextInt(10));
        customerName = new String42("Customer " + random.nextInt(10));
        identifiedObject = new IdentifiedObject();
        pricePowerOfTenMultiplier = new PowerOfTenMultiplierType(random.nextLong());
        customerAgreementList = new CustomerAgreementList();
        serviceSupplierList = new ServiceSupplierList();
    }
    public IdentifiedObject identifiedObject;
    public Int32 currency;
    public String42 customerAccount;
    public String42 customerName;
    public CustomerAgreementList customerAgreementList;
    public PowerOfTenMultiplierType  pricePowerOfTenMultiplier ;
    public ServiceSupplierList  serviceSupplierList;
}
