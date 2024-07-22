/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import com.PrimaryTypes.UInt8;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class ServiceStatusType implements Serializable
{
    public ServiceStatusType ()
    {
        Value = new UInt8( (new Random().nextInt(8)));
    }
    public ServiceStatusType (UInt8 value)
    {
        Value = value;        
    }
    public UInt8 Value;
    
}
