/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.UInt8;
import com.PrimaryTypes.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class UnitValueType implements Serializable
{
    public UnitValueType () 
    {
        Random random = new Random();
        multiplier = new PowerOfTenMultiplierType(random.nextLong());
        unit = new UomType(new UInt8(random.nextInt(8)));
        value = new Int32(random.nextInt(32));
    }
    public PowerOfTenMultiplierType multiplier; 
    public UomType unit = new UomType() ;
    public Int32 value = new Int32() ;
}
