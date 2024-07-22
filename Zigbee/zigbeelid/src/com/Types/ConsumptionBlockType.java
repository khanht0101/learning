/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.UInt8;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class ConsumptionBlockType implements Serializable
{
    public ConsumptionBlockType() 
            
    {
        value = new UInt8(new Random().nextInt(8));
    }
    private UInt8 value;
    public ConsumptionBlockType(UInt8 _value)
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
