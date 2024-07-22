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
public class NeighborList implements Serializable
{
  public NeighborList (){}
  public  List<Neighbor> neighborList;
  public void Add( Neighbor neighbor)
  {
      neighborList.add(neighbor);
  }
  public void Remove(Neighbor neighbor)
  {
      neighborList.remove(neighbor);
  }
  public void Remove(int index)
  {
      neighborList.remove(index);
  }
  public List<Neighbor> GetNeighborList()
  {
      return neighborList;
  }
  
}
