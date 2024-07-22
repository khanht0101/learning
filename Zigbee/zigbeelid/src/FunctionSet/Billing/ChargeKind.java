/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import com.PrimaryTypes.UInt8;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class ChargeKind implements Serializable
{
    public ChargeKind()
    {
        value = new UInt8(new Random().nextInt(8));
    }
    public ChargeKind (UInt8 _value) 
    {
        value =_value;
    }
    
    public void setValue(UInt8 _value)
    {
        value = _value;
    }
    public UInt8 getValue()
    {
        return value;
    }
    public UInt8 value;
    
}
