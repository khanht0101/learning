/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Identification;

import com.PrimaryTypes.HexBinary128;
import com.Types.mRIDType;
import com.Types.SubscribableType;
import com.PrimaryTypes.String32;
import com.PrimaryTypes.UInt8;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class RespondableSubscribableIdentifiedObject implements Serializable
{
    public RespondableSubscribableIdentifiedObject() 
    {
        Random random = new Random();
        description = new String32("Identified "+ random.nextInt(10));
        mRID = new mRIDType(new HexBinary128(random.nextLong()));
        subscribable = new SubscribableType(new UInt8(random.nextInt(8)));
    }
    public String32 description;
    public mRIDType mRID;
    public SubscribableType subscribable;
}
