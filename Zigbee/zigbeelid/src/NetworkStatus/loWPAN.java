/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkStatus;

import com.PrimaryTypes.UInt32;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class loWPAN implements Serializable
{
    public loWPAN () {}
    public UInt32 octetsRx;
    public UInt32 octetsTx;
    public UInt32 packetsRx;
    public UInt32 packetsTx;
    public UInt32 rxFragError;
}
