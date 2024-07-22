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
public class RPLInstanceList implements Serializable
{
  public RPLInstanceList (){}
  public  List<RPLInstance> rPLInstanceList;
  public void Add( RPLInstance rPLInstance)
  {
      rPLInstanceList.add(rPLInstance);
  }
  public void Remove(RPLInstance rPLInstance)
  {
      rPLInstanceList.remove(rPLInstance);
  }
  public void Remove(int index)
  {
      rPLInstanceList.remove(index);
  }
  public List<RPLInstance> GetRPLInstanceList()
  {
      return rPLInstanceList;
  }
  
}
