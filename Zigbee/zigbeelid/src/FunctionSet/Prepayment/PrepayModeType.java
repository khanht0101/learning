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
public class PrepayModeType implements Serializable
{
    public PrepayModeType ()
    {
        Value = new UInt8( (new Random().nextInt(8)));
    }
    public PrepayModeType (UInt8 value)
    {
        Value = value;        
    }
    public UInt8 Value;
    
}
