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
public class RPLInstance implements Serializable
{
    public RPLInstance () {}
    public UInt8 DODAGid;
    public boolean DODAGroot;
    public UInt8 flags;
    public boolean groundedFlag;
    public UInt8 MOP;
    public UInt8 PRF;
    public UInt16 rank;
    public UInt8 RPLInstanceID;
    public UInt8 versionNumber;
    
}
