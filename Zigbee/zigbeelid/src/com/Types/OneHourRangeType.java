/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class OneHourRangeType implements Serializable
{
    public OneHourRangeType()  
    {
        Random random = new Random();
        value = random.nextLong();
        value = Math.max(value, minInclusive);
        value = Math.min(value, maxInclusive);
    }
    public OneHourRangeType( long _value) 
    {
        value = _value;
        value = Math.max(value, minInclusive);
        value = Math.min(value, maxInclusive);
    }
    public long value;
    public long maxInclusive = 3600;
    public long minInclusive = -3600;
    
    public long getValue()
    {
        return value;
    }
    
}
