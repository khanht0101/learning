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
public class PINType implements Serializable
{
    public PINType () {}
    public PINType( long _value) 
    {
        value = _value>maxInclusive?maxInclusive:_value;
    }
    public long value;
    public long maxInclusive = 999999;
        
}
