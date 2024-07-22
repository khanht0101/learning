/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LogEvents;

import com.PrimaryTypes.UInt16;
import com.PrimaryTypes.UInt32;
import com.PrimaryTypes.UInt8;
import com.Types.TimeType;
import com.Types.PENType;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class LogEvent implements Serializable
{
    public LogEvent  () {}
    public TimeType createdDateTime;
    public UInt32 extendedData;
    public UInt8 functionSet;
    public UInt8 logEventCode;
    public UInt16 logEventID;
    public PENType logEventPEN;
    public UInt8 profileID;
}
