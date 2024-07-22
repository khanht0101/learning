/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Response;

import com.PrimaryTypes.UInt16;
import com.Types.UnitType;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class AppliedTargetReduction implements Serializable
{
    public AppliedTargetReduction  () {}
    public UnitType type;
    public UInt16 value;
    
}
