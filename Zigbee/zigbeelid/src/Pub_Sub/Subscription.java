/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pub_Sub;

import com.PrimaryTypes.UInt16;
import com.PrimaryTypes.UInt8;
import com.PrimaryTypes.String16;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class Subscription implements Serializable
{
    public Subscription  () {}
    public UInt8 encoding = new UInt8();
    public String16 level = new  String16();
    public UInt16 limit = new UInt16();
    
}
