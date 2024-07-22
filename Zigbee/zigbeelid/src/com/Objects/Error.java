/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Objects;

import com.PrimaryTypes.UInt16;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class Error implements Serializable
{
    public Error() 
    {
        Random random = new Random();
        maxRetryDuration = new UInt16(random.nextInt(16));
        reasonCode = new UInt16(random.nextInt(16));
    }
    public UInt16 maxRetryDuration;
    public UInt16 reasonCode;
    
}
