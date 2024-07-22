/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pub_Sub;

import com.Types.anyURI;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class SubscriptionBase implements Serializable
{
    public SubscriptionBase () {}
    public anyURI subscribedResource;
}
