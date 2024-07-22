/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.HexBinary128;
import com.PrimaryTypes.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class mRIDType implements Serializable
{
    public mRIDType() {}
     public mRIDType(HexBinary128 _value)
    {
        value = _value;
    }
    private HexBinary128 value = new HexBinary128(1234);
    public void setValue(HexBinary128 _value)
    {
        value = _value;
    }
    public HexBinary128 getValue()
    {
        return value;
    }
}
