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
public class TariffProfileList implements Serializable
{
  public TariffProfileList ()
  {
      tariffProfileList = new ArrayList<TariffProfile>();
      tariffProfileList.add(new TariffProfile());
  }
  public  List<TariffProfile> tariffProfileList;
  public void Add(TariffProfile tariffProfile)
  {
      tariffProfileList.add(tariffProfile);
  }
  public void Remove(TariffProfile tariffProfile)
  {
      tariffProfileList.remove(tariffProfile);
  }
  public void Remove(int index)
  {
      tariffProfileList.remove(index);
  }
  public List<TariffProfile> GetTariffProfileList()
  {
      return tariffProfileList;
  }
  public TariffProfile Get(int i)
  {
      return tariffProfileList.get(i);
  }
  
  public List<TariffProfile> GetValues()
  {
      return tariffProfileList;
  } 
  
  public int Length()
  {
      return tariffProfileList.size();
  }
  
}
