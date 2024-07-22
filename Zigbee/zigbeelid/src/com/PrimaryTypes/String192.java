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
public class String192 implements Serializable
{
   private String value = "";
   public void setValue(String _value)
   {
       value = _value;
   }
   public String getValue ()
   {
       return value;
   }
   public String192(String _value)
   {
       value = _value;
   }
    public String192(){}
}
