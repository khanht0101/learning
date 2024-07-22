/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareDownload;

import com.PrimaryTypes.UInt16;
import com.PrimaryTypes.UInt8;
import com.Types.TimeType;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class FileStatus implements Serializable
{
    public FileStatus() {}
    public UInt8 loadPercent;
    public TimeType nextRequestAttempt;;
    public UInt16 request503Count;
    public UInt16 requestFailCount;
    public UInt8 status;
    public TimeType statusTime;
}
