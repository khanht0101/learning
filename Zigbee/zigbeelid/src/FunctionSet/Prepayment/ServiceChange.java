/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import com.Types.TimeType;
import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class ServiceChange implements Serializable
{
    public ServiceChange ()
    {
       newStatus = new ServiceStatusType();
       startTime = new TimeType();
    }
    public ServiceStatusType newStatus;
    public TimeType startTime;
}
