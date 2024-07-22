/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Pricing;

import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class Pricing implements Serializable
{
    public Pricing() 
    {
       tariffProfileList = new TariffProfileList();
    }
   // public ConsumptionTariffIntervalList consumptionTariffIntervalList = new ConsumptionTariffIntervalList();
    public TariffProfileList tariffProfileList;
//    public EnvironmentalCost environmentalCost= new EnvironmentalCost();
//    public RateComponent rateComponent = new RateComponent();
//    public TariffProfile tariffProfile = new TariffProfile();
//    public TimeTariffInterval timeTariffInterval = new TimeTariffInterval();
//    public TariffProfile gettariffProfileObject()
//    {
//        return tariffProfile;
//    }
//    public RateComponent getrateComponentObject()
//    {
//        return rateComponent;
//    }
//    public EnvironmentalCost getenvironmentalCostObject()
//    {
//        return environmentalCost;
//    }
//    public TimeTariffInterval gettimeTariffIntervalObject()
//    {
//        return timeTariffInterval;
//    }
    
}
