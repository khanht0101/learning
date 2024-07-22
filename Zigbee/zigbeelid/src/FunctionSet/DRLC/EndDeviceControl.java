/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;

import com.PrimaryTypes.HexBinary32;
import com.PrimaryTypes.*;
import com.Types.DeviceCategoryType;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class EndDeviceControl implements  Serializable
{
    public EndDeviceControl ()
    {
        Random random =new Random();
        deviceCategory = new DeviceCategoryType(new HexBinary32(random.nextLong()));
        drProgramMandatory = false;
        loadShiftForward= false;
        overrideDuration = new UInt16(random.nextInt(16));
    }
    public DeviceCategoryType  deviceCategory ;
    public boolean  drProgramMandatory ;
    public boolean  loadShiftForward ;
    public UInt16  overrideDuration ;
}
