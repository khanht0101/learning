/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Objects;

import com.Types.TimeType;
import com.Types.DateTimeInterval;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class Event implements Serializable
{
    public Event() 
    {
        creationTime = new TimeType();
        interval = new DateTimeInterval();
        eventStatus = new EventStatus();
        randomizableEvent = new RandomizableEvent();
    }
    public TimeType creationTime;
    public DateTimeInterval interval;
    public EventStatus eventStatus;
    public RandomizableEvent randomizableEvent;
}
