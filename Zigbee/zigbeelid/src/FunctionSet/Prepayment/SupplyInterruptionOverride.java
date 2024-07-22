/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import com.PrimaryTypes.String32;
import com.Types.DateTimeInterval;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class SupplyInterruptionOverride implements Serializable
{
    public SupplyInterruptionOverride ()
    {
        description = new String32(new Random().toString());
        interval = new DateTimeInterval();
    }
    public String32 description;
   
    public DateTimeInterval interval;
    
}
