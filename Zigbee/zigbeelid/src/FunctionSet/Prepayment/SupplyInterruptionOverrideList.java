/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import FunctionSet.Pricing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class SupplyInterruptionOverrideList implements Serializable
{
  public SupplyInterruptionOverrideList ()
  {
      supplyInterruptionOverrideList = new ArrayList<SupplyInterruptionOverride>();
  }
  public  List<SupplyInterruptionOverride> supplyInterruptionOverrideList;
  public void Add(SupplyInterruptionOverride supplyInterruptionOverride)
  {
      supplyInterruptionOverrideList.add(supplyInterruptionOverride);
  }
  public void Insert(SupplyInterruptionOverride supplyInterruptionOverride, int index)
  {
      supplyInterruptionOverrideList.add(index,supplyInterruptionOverride);
  }
  public void Remove(SupplyInterruptionOverride supplyInterruptionOverride)
  {
      supplyInterruptionOverrideList.remove(supplyInterruptionOverride);
  }
  public void Remove(int index)
  {
      supplyInterruptionOverrideList.remove(index);
  }
 
  public int Length()
  {
      return supplyInterruptionOverrideList.size();
  }
  public SupplyInterruptionOverride Get(int i)
  {
      return supplyInterruptionOverrideList.get(i);
  }
}
