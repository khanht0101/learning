/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import FunctionSet.Metering.Mirror.MeterReadingBase;
import FunctionSet.Metering.ReadingType;
import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class BillingMeterReadingBase implements Serializable
{
    public BillingMeterReadingBase ()
    {
        readingType = new ReadingType();
        billingReadingSetList = new BillingReadingSetList();
        meterReadingBase = new MeterReadingBase();
    }
    public ReadingType readingType;
    public BillingReadingSetList billingReadingSetList;
    public MeterReadingBase meterReadingBase;
    
}
