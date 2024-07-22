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
public class DstRuleType implements Serializable
{
    public DstRuleType() {}
    private int value;
    public DstRuleType(int _value)
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
