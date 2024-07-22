/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering.Mirror;

import com.Types.TimeType;
import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class MirrorMeterReading implements Serializable
{
 
    public MirrorMeterReading () 
    {
        lastUpdateTime = new TimeType();
        nextUpdateTime = new TimeType();
    }
    public TimeType lastUpdateTime;
    public TimeType nextUpdateTime;
}
