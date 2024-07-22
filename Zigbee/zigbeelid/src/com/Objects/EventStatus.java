package com.Objects;
import com.Types.TimeType;
import com.PrimaryTypes.UInt8;
import com.PrimaryTypes.String32;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class EventStatus implements Serializable
{
    public EventStatus () 
    {
        currentStatus = new UInt8(1);
        datetime = new TimeType();
        reason = new String32(new Random().toString());
        potentiallySuperseded = false;
        potentiallySupersededTime = new TimeType();
    }
    public UInt8 currentStatus;
    public TimeType datetime;
    public String32 reason;
    public boolean potentiallySuperseded;
    public TimeType potentiallySupersededTime;
}
