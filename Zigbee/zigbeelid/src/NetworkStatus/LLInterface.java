/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkStatus;

import com.PrimaryTypes.UInt32;
import com.PrimaryTypes.UInt8;
import com.PrimaryTypes.HexBinary64;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class LLInterface implements Serializable
{
    public LLInterface(){}
    public HexBinary64 EUI64 ;
    public UInt32 CRCerrors ;
    public UInt8 linkLayerType;
    public UInt32 LLAckNotRx;
    public UInt32 LLCSMAFail;
    public UInt32 LLFramesDropRx;
    public UInt32 LLFramesDropTx;
    public UInt32 LLFramesRx;
    public UInt32 LLFramesTx;
    public UInt32 LLMediaAccessFail;
    public UInt32 LLOctetsRx;
    public UInt32 LLOctetsTx;
    public UInt32 LLRetryCount;
    public UInt32 LLSecurityErrorRx;
    
}
