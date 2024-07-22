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
public class TimeTariffIntervalList implements Serializable
{
  public TimeTariffIntervalList ()
  {
      timeTariffIntervalList = new ArrayList<TimeTariffInterval>();
  }
  public  List<TimeTariffInterval> timeTariffIntervalList;
  public void Add(TimeTariffInterval timeTariffInterval)
  {
      timeTariffIntervalList.add(timeTariffInterval);
  }
  public void Remove(TimeTariffInterval timeTariffInterval)
  {
      timeTariffIntervalList.remove(timeTariffInterval);
  }
  public void Remove(int index)
  {
      timeTariffIntervalList.remove(index);
  }
  public List<TimeTariffInterval> GetTimeTariffIntervalList()
  {
      return timeTariffIntervalList;
  }
  
  public List<TimeTariffInterval> GetValues()
  {
      return timeTariffIntervalList;
  } 
  public int Length()
  {
      return timeTariffIntervalList.size();
  }
  public TimeTariffInterval Get(int index)
  {
      return timeTariffIntervalList.get(index);
  }
}
