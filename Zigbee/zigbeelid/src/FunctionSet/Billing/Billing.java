/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class Billing implements Serializable
{
    public Billing ()
    {
        customerAccountList = new CustomerAccountList();
        billingReadingSetList = new BillingReadingSetList();
    }
    public CustomerAccountList customerAccountList;
    public BillingReadingSetList billingReadingSetList;
}
