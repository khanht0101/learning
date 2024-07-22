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
public class TOUType implements Serializable
{
    public TOUType()  
    {
        value =  new UInt8(new Random().nextInt(8));
    }
    public TOUType (UInt8 _value) 
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
    public UInt8 value = new UInt8();
}
