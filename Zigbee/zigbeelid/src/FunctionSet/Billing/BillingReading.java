/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import FunctionSet.Metering.Mirror.ReadingBase;
import FunctionSet.Metering.Mirror.ReadingSetBase;
import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class BillingReading implements Serializable
{
    public BillingReading ()
    {
        charge = new Charge();
        readingBase = new ReadingBase();
    }
    public Charge charge;
    public ReadingBase readingBase;
}
