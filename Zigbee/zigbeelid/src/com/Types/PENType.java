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
public class PENType implements Serializable
{
    public PENType () {}
    public PENType (long _value)
    {
        value = _value;
    }
    public long value;
    
}
