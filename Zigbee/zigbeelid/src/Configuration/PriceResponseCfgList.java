/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;

import NetworkStatus.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class PriceResponseCfgList implements Serializable
{
  public PriceResponseCfgList (){}
  public  List<PriceResponseCfg> priceResponseCfgList;
  public void Add( PriceResponseCfg priceResponseCfg)
  {
      priceResponseCfgList.add(priceResponseCfg);
  }
  public void Remove(PriceResponseCfg priceResponseCfg)
  {
      priceResponseCfgList.remove(priceResponseCfg);
  }
  public void Remove(int index)
  {
      priceResponseCfgList.remove(index);
  }
  public List<PriceResponseCfg> GetPriceResponseCfgList()
  {
      return priceResponseCfgList;
  }
  
}
