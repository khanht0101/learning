/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkStatus;

import com.PrimaryTypes.HexBinary128;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class RPLSourceRoutes implements Serializable
{
    public RPLSourceRoutes () {}
    public HexBinary128 DestAddress;
    public HexBinary128 SourceRoute;
}
