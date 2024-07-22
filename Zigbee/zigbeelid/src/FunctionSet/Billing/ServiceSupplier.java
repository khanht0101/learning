/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import com.Identification.IdentifiedObject;
import com.PrimaryTypes.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class ServiceSupplier implements Serializable
{
    public ServiceSupplier ()
    {
        Random random = new Random();
        email = new String32("ntses"+random.nextInt(10)+"@nts.com");
        phone = new String20("84909046558");
        providerID = new UInt32(random.nextInt(32));
        identifiedObject = new IdentifiedObject();
        web = new String42("ntses.com");
    }
    
    public String32 email;
    public String20 phone;
    public UInt32 providerID;
    public IdentifiedObject identifiedObject ;
    public String42 web;
    
}
