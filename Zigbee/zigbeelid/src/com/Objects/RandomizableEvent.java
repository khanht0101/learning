/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Objects;

import com.Types.OneHourRangeType;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class RandomizableEvent implements Serializable
{
    public RandomizableEvent()
    {
        randomizeDuration = new OneHourRangeType();
        randomizeStart = new OneHourRangeType();
    }
    public OneHourRangeType randomizeDuration;
    public OneHourRangeType randomizeStart;
    
}
