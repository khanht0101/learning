/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Pricing;

import LogEvents.*;
import NetworkStatus.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class ConsumptionTariffIntervalList implements Serializable
{
  public ConsumptionTariffIntervalList ()
  {
      consumptionTariffIntervalList = new ArrayList<ConsumptionTariffInterval>();
  }
  public  List<ConsumptionTariffInterval> consumptionTariffIntervalList;
  public void Add(ConsumptionTariffInterval consumptionTariffInterval)
  {
      consumptionTariffIntervalList.add(consumptionTariffInterval);
  }
  public void Remove(ConsumptionTariffInterval consumptionTariffInterval)
  {
      consumptionTariffIntervalList.remove(consumptionTariffInterval);
  }
  public void Remove(int index)
  {
      consumptionTariffIntervalList.remove(index);
  }
  public List<ConsumptionTariffInterval> GetConsumptionTariffIntervalList()
  {
      return consumptionTariffIntervalList;
  }
  public List<ConsumptionTariffInterval> GetValues()
  {
      return consumptionTariffIntervalList;
  }
  
  public ConsumptionTariffInterval Get(int index)
  {
      return consumptionTariffIntervalList.get(index);
  }
  
  public int Length()
  {
      return consumptionTariffIntervalList.size();
  }
}
