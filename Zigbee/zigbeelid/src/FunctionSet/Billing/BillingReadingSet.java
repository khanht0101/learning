/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import FunctionSet.Metering.Mirror.ReadingSetBase;
import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class BillingReadingSet implements Serializable
{
    public BillingReadingSet () 
    {
        billingReadingList = new BillingReadingList();
        readingSetBase =  new ReadingSetBase();
    }
    public BillingReadingList billingReadingList;
    public ReadingSetBase readingSetBase;
}
