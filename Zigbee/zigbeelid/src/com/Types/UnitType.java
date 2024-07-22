/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.UInt8;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class UnitType implements Serializable
{
    public UnitType () {}
    public UnitType (UInt8 _value) 
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
