/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkStatus;

import com.PrimaryTypes.UInt16;
import com.PrimaryTypes.UInt8;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class Neighbor implements Serializable
{
    public Neighbor() {}
    public boolean isChild;
    public UInt8 linkQuality;
    public UInt16 shortAddress;
    
}
