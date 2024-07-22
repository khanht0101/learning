/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import com.PrimaryTypes.*;
import com.Types.DateTimeInterval;
import com.Types.TimeType;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class BillingPeriod  implements Serializable
{
    public BillingPeriod () 
    {
        Random random = new Random();
        billLastPeriod = new Int48(random.nextInt(48));
        billToDate = new Int48(random.nextInt(48));
        interval = new DateTimeInterval();
        statusTimeStamp = new TimeType();
    }
    public Int48 billLastPeriod;
    public Int48 billToDate;
    public TimeType statusTimeStamp ;
    public DateTimeInterval interval;
}
