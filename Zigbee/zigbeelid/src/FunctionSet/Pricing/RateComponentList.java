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
public class RateComponentList implements Serializable
{
  public RateComponentList ()
  {
      rateComponentList = new ArrayList<RateComponent>();
  }
  public  List<RateComponent> rateComponentList;
  public void Add(RateComponent rateComponent)
  {
      rateComponentList.add(rateComponent);
  }
  public void Remove(RateComponent rateComponent)
  {
      rateComponentList.remove(rateComponent);
  }
  public void Remove(int index)
  {
      rateComponentList.remove(index);
  }
  public List<RateComponent> GetRateComponentList()
  {
      return rateComponentList;
  }
  public List<RateComponent> GetValues()
  {
      return rateComponentList;
  }
  
  public int Length()
  {
      return rateComponentList.size();
  }
  public RateComponent Get(int i)
  {
      return rateComponentList.get(i);
  }
}
