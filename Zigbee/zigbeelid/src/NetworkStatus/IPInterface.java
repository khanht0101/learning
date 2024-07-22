/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkStatus;

import com.PrimaryTypes.UInt16;
import com.PrimaryTypes.String192;
import com.PrimaryTypes.UInt32;
import com.PrimaryTypes.Int64;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class IPInterface implements Serializable
{
    public IPInterface(){}
    public String192 ifDescr ;
    public UInt32 ifHighSpeed ;
    public UInt32 ifInBroadcastPkts;
    public UInt32 ifIndex;
    public UInt32 ifInDiscards;
    public UInt32 ifInErrors;
    public UInt32 ifInMulticastPkts;
    public UInt32 ifInOctets;
    public UInt32 ifInUcastPkts;
    public UInt32 ifInUnknownProtos;
    public UInt32 ifMtu;
    public UInt32 ifName;
    public UInt32 ifOutBroadcastPkts;
    public UInt32 ifOutDiscards;
    public UInt32 ifOutErrors;
    public UInt32 ifOutMulticastPkts;
    public UInt32 ifOutOctets;
    public UInt32 ifPromiscuousMode;
    public UInt32 ifSpeed;
    public UInt16 ifType;
    public Int64 lastResetTime;    
    public Int64 lastUpdatedTime;

}
