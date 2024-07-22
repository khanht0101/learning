/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.FlowReservation;

import com.PrimaryTypes.UInt8;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class ReservationStatusType 
{    
     public ReservationStatusType()
    {
        Value = new UInt8( (new Random().nextInt(8)));
    }
    public ReservationStatusType (UInt8 value)
    {
        Value = value;        
    }
    public UInt8 Value;
    
}
