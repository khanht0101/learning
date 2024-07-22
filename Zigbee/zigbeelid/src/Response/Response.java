/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Response;

import com.PrimaryTypes.HexBinary160;
import com.Types.TimeType;
import com.Types.StatusType;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class Response implements Serializable
{
    public Response() {}
    public TimeType createDateTime;
    public HexBinary160 endDeviceLFDI;
    public StatusType status;    
}
