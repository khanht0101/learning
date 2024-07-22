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
public class TargetReading implements Serializable
{
    public TargetReading ()
    {
        billingMeterReadingBase = new BillingMeterReadingBase();
    }
    public BillingMeterReadingBase billingMeterReadingBase;
}
