/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering.Mirror;

import com.PrimaryTypes.*;
import com.Types.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class ReadingBase implements Serializable
{
    public ReadingBase ()
    {
        Random random = new Random();
        consumptionBlock =  new ConsumptionBlockType();
        qualityFlags = new HexBinary16(random.nextLong());
        timePeriod = new DateTimeInterval();
        touTier = new TOUType();
        value = new Int48(random.nextInt(48));
    }
    public ConsumptionBlockType consumptionBlock;
    public HexBinary16 qualityFlags;
    public DateTimeInterval  timePeriod ;
    public TOUType touTier;
    public Int48  value;
    
}
