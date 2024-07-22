/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareDownload;

import com.PrimaryTypes.UInt32;
import com.PrimaryTypes.HexBinary160;
import com.PrimaryTypes.String16;
import com.PrimaryTypes.String32;
import com.PrimaryTypes.HexBinary16;
import com.Types.TimeType;
import com.Types.PENType;
import com.Types.anyURI;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class File implements Serializable
{
    public File () {}
    public TimeType activateTime;
    public anyURI fileURI;
    public HexBinary160 IFDI;
    public String32 mfHwVer;
    public PENType mfID;
    public String32 mfModel;
    public String32 mfSerNum;
    public String16 mfVer;
    public UInt32 size;
    public HexBinary16 type;
}
