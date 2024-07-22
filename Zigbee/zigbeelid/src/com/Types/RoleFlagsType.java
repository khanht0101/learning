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
public class RoleFlagsType implements Serializable
{
    public RoleFlagsType() {}
    private int value = 1;
    public RoleFlagsType(int _value)
    {       
        value = _value;
    }
    public void setValue(int _value)
    {
        value = _value;
    }
    public int getValue()
    {
        return value;
    } 
    
}
