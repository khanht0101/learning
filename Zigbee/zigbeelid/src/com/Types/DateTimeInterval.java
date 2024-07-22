/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.UInt32;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class DateTimeInterval implements Serializable
{
    public DateTimeInterval() 
    {
        duration = new UInt32(new Random().nextInt(32));
        start = new TimeType();
    }
    public UInt32 duration;
    public TimeType start;    
}
