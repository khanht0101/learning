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
public class PowerOfTenMultiplierType implements Serializable
{
    public PowerOfTenMultiplierType() {}
    public PowerOfTenMultiplierType( long _value) 
    {
        value = _value>maxInclusive?maxInclusive:_value<minInclusive? minInclusive: _value;
    }
    private long value;
    public void setValue(long _value)
    {
        value = _value>maxInclusive?maxInclusive:_value<minInclusive? minInclusive: _value;
    }
    public long getValue()
    {
        return value;
    }
    public long maxInclusive = 9;
    public long minInclusive = -9;   
}
