/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pub_Sub;

import com.PrimaryTypes.UInt8;
import com.Types.anyURI;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class Notification implements Serializable
{
    public Notification  () {}
    public anyURI newResourceURI;
    public UInt8 status;
}
