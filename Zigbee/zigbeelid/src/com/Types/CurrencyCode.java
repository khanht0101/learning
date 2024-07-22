/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.UInt16;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class CurrencyCode implements Serializable
{
    public CurrencyCode() {}
    private UInt16 value = new UInt16();
    public CurrencyCode(UInt16 _value)
    {
        value = _value;
    }
    public void setValue(UInt16 _value)
    {
        value = _value;
    }
    public UInt16 getValue()
    {
        return value;
    }
}
