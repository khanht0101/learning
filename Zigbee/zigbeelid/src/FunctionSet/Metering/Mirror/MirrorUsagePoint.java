/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering.Mirror;

import com.PrimaryTypes.HexBinary160;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class MirrorUsagePoint implements Serializable{
    
    public MirrorUsagePoint ()
    {
       deviceLFDI = new HexBinary160((new Random()).nextLong());
    }
    public HexBinary160 deviceLFDI;
}
