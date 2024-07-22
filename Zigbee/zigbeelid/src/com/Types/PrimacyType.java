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
public class PrimacyType implements Serializable
{
    public PrimacyType() {}
    public PrimacyType( long _value) 
    {
        value = _value>maxInclusive?maxInclusive:_value;
    }
    private long value = 0;
    public void setValue(long _value)
    {
        value = _value>maxInclusive?maxInclusive:_value;
    }
    public long getValue()
    {
        return value;
    }
    public long maxInclusive = 3; 
    
}
