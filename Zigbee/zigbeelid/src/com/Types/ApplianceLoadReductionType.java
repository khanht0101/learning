/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.UInt8;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class ApplianceLoadReductionType implements Serializable
{
    public ApplianceLoadReductionType() {}
    private UInt8 value;
    public ApplianceLoadReductionType(UInt8 _value)
    {
        value = _value;
    }
    public void setValue(UInt8 _value)
    {
        value = _value;
    }
    public UInt8 getValue()
    {
        return value;
    }
    
}
