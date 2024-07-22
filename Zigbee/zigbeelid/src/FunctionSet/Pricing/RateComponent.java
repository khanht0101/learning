/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Pricing;

import FunctionSet.Metering.ReadingType;
import com.Identification.IdentifiedObject;
import com.Types.UnitValueType;
import com.Types.RoleFlagsType;
import com.Types.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class RateComponent  implements Serializable
{
    public RateComponent () 
    {
        flowRateEndLimit = new UnitValueType();
        flowRateSatrtLimit = new UnitValueType();
        roleFlags = new RoleFlagsType();
        timeTariffIntervalList = new  TimeTariffIntervalList();
        identifiedObject = new IdentifiedObject();
        readingType = new ReadingType();
    }
    public UnitValueType flowRateEndLimit;
    public UnitValueType flowRateSatrtLimit;
    public RoleFlagsType roleFlags;
    public IdentifiedObject identifiedObject;
    public TimeTariffIntervalList timeTariffIntervalList; 
    public ReadingType readingType;

    public UnitValueType getflowRateEndLimitObject()            
    {
        return flowRateEndLimit;
    }
    public UnitValueType getflowRateSatrtLimitObject()            
    {
        return flowRateSatrtLimit;
    }
    public RoleFlagsType getroleFlagsObject()            
    {
        return roleFlags;
    }
}
