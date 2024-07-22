/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Identification;

import com.Types.VersionType;
import com.Types.mRIDType;
import com.PrimaryTypes.String32;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class RespondableIdentifiedObject implements Serializable
{
    public RespondableIdentifiedObject() {}
    public String32 description;
    public mRIDType mRID;
    public VersionType version;    
}
