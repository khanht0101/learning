/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Pricing;

import com.PrimaryTypes.UInt48;
import com.Types.ConsumptionBlockType;
import com.PrimaryTypes.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class ConsumptionTariffInterval implements Serializable
{
    public ConsumptionTariffInterval() 
    {
        Random random = new Random();
        consumptionBlock = new ConsumptionBlockType(new UInt8(random.nextInt(8)));
        price = new Int32(random.nextInt(32));
        startValue = new  UInt48(random.nextInt(48));
        environmentalCost = new EnvironmentalCost();
    }
    public EnvironmentalCost environmentalCost;
    public ConsumptionBlockType  consumptionBlock;
    public Int32 price;
    public UInt48 startValue;
}
