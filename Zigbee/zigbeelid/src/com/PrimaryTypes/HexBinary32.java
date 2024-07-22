/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PrimaryTypes;

import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class HexBinary32 implements Serializable
{
   private long value = 0;
   public void setValue(long _value)
   {
       value = _value;
   }
   public long getValue ()
   {
       return value;
   }
   public HexBinary32(long _value)
   {
       value = _value;
   }
    public HexBinary32() {}
    
}
