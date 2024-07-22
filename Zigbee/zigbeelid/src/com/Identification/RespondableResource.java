/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Identification;

import com.PrimaryTypes.HexBinary8;
import com.Types.anyURI;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class RespondableResource implements Serializable
{
    public RespondableResource ()
    {
        replyTo = new anyURI();
        responseRequired = new HexBinary8(new Random().nextLong());
    }
    public anyURI replyTo;
    public HexBinary8 responseRequired;
    
}
