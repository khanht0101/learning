/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;

import com.Identification.IdentifiedObject;
import com.PrimaryTypes.UInt8;
import com.Types.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class DemandResponseProgram implements  Serializable
{
    public DemandResponseProgram ()
    {
        Random random = new Random();
        availabilityUpdatePercentChangeThreshold = new PerCent(random.nextLong());
        availabilityUpdatePowerChangeThreshold = new ActivePower(new UInt8(random.nextInt(8)));
        primacy = new PrimacyType(random.nextLong());
        identifiedObject = new IdentifiedObject();
    }
    public PerCent  availabilityUpdatePercentChangeThreshold ;
    public ActivePower  availabilityUpdatePowerChangeThreshold ;
    public PrimacyType  primacy ;
    public IdentifiedObject identifiedObject;
}
