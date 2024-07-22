/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class TimeOffsetType implements Serializable
{
    public TimeOffsetType () {}
    public TimeOffsetType (long _value) 
    {
        value =_value;
    }
    
    public void setValue(long _value)
    {
        value = _value;
    }
    public long getValue()
    {
        return value;
    }
    public long value;
    
}
