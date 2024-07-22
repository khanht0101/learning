/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.HexBinary32;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class DeviceCategoryType implements Serializable
{
    public DeviceCategoryType() {}
    private HexBinary32 value;
    public DeviceCategoryType(HexBinary32 _value)
    {
        value = _value;
    }
    public void setValue(HexBinary32 _value)
    {
        value = _value;
    }
    public HexBinary32 getValue()
    {
        return value;
    }
}
