/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import java.io.Serializable;
import java.util.Random;
import java.util.Date;

/**
 *
 * @author CONG HUY
 */
public class TimeType implements Serializable
{
    public TimeType () 
    {
        value = new Date().getTime();
    }
    public TimeType (long _value) 
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
