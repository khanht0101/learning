/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.UInt8;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class ServiceKind implements Serializable
{
    public ServiceKind () {}
    public ServiceKind (UInt8 _value) 
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
    public UInt8 value = new UInt8(0);
    
}
