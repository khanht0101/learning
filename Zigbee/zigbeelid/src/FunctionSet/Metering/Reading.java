/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering;

import com.Types.SubscribableType;
import com.PrimaryTypes.HexBinary16;
import com.PrimaryTypes.UInt8;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class Reading implements Serializable
{
    public Reading()
    {
        Random random = new Random();
        localID = new HexBinary16(random.nextLong());
        subscribable = new SubscribableType(new UInt8(random.nextInt(8)));
    }
    public HexBinary16 localID = new HexBinary16();
    public SubscribableType subscribable  = new  SubscribableType();
}
