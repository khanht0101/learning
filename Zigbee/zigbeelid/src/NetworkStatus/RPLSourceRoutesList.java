/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkStatus;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class RPLSourceRoutesList implements Serializable
{
  public RPLSourceRoutesList (){}
  public  List<RPLSourceRoutes> rPLSourceRoutesList;
  public void Add( RPLSourceRoutes rPLSourceRoutes)
  {
      rPLSourceRoutesList.add(rPLSourceRoutes);
  }
  public void Remove(RPLSourceRoutes rPLSourceRoutes)
  {
      rPLSourceRoutesList.remove(rPLSourceRoutes);
  }
  public void Remove(int index)
  {
      rPLSourceRoutesList.remove(index);
  }
  public List<RPLSourceRoutes> GetRPLSourceRoutesList()
  {
      return rPLSourceRoutesList;
  }
  
}
