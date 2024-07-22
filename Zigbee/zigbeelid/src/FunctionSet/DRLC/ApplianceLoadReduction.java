/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;

import com.PrimaryTypes.UInt8;
import com.Types.ApplianceLoadReductionType;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class ApplianceLoadReduction implements  Serializable
{
    public ApplianceLoadReduction ()
    {
        type = new ApplianceLoadReductionType(new UInt8(new Random().nextInt(8)));
    }
    public ApplianceLoadReductionType  type ;
}
