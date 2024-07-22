/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Pricing;

import com.PrimaryTypes.UInt32;
import com.PrimaryTypes.UInt8;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class EnvironmentalCost implements Serializable
{
    public EnvironmentalCost() 
    {
        amount = new UInt32();
        costKind = new CostKindType();
        costLevel =  new UInt8();
        numCostLevels = new UInt8();
    }
    public UInt32 amount = new UInt32();
    public CostKindType costKind = new CostKindType();
    public UInt8 costLevel = new UInt8();
    public UInt8 numCostLevels = new UInt8();
}
