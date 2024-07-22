/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering.Mirror;

import com.PrimaryTypes.UInt8;
import com.Types.RoleFlagsType;
import com.Types.ServiceKind;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class UsagePointBase implements Serializable
{
    public UsagePointBase ()
    {
        Random random = new Random();
        roleFlags = new RoleFlagsType(random.nextInt());
        serviceCategoryKind = new ServiceKind(new UInt8(random.nextInt(8)));
        status = new UInt8(random.nextInt(8));
    }
    public RoleFlagsType roleFlags;
    public ServiceKind serviceCategoryKind;
    public UInt8 status;
    
}
