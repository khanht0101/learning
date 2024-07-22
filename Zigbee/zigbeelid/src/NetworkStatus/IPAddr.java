/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkStatus;

import com.PrimaryTypes.HexBinary128;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class IPAddr implements Serializable{
    public IPAddr() {}   
    public HexBinary128 address;
    public IPAddr(HexBinary128 _value)
    {
        address = _value;
    }   
}
