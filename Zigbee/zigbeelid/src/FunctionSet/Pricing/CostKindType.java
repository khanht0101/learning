/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Pricing;

import com.PrimaryTypes.UInt8;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class CostKindType implements Serializable
{
    public CostKindType() {}
    private UInt8 value = new UInt8();
    public CostKindType(UInt8 _value)
    {
        value = _value;
    }
    public void setValue(UInt8 _value)
    {
        value = _value;
    }
    public UInt8 getValue()
    {
        return value;
    }
    
}
