/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Pricing;

import com.Identification.RespondableResource;
import com.Identification.RespondableSubscribableIdentifiedObject;
import com.Objects.*;
import com.Types.TOUType;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class TimeTariffInterval implements Serializable
{
    public TimeTariffInterval() 
    {
        touTier = new TOUType();
        event = new Event();
        respondableSubscribableIdentifiedObject = new RespondableSubscribableIdentifiedObject();
        conTariffIntervalList = new ConsumptionTariffIntervalList();
        respondableResource = new RespondableResource();
    }
    public TOUType touTier = new TOUType();
    public Event event;    
    public RespondableSubscribableIdentifiedObject respondableSubscribableIdentifiedObject;
    public ConsumptionTariffIntervalList conTariffIntervalList;
    public RespondableResource respondableResource;
}
