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
public class UInt48 implements Serializable
{
   private int value = 0;
   public void setValue(int _value)
   {
       value = _value;
   }
   public int getValue ()
   {
       return value;
   }
   public UInt48(int _value)
   {
       value = _value;
   }
    public UInt48(){}
}
