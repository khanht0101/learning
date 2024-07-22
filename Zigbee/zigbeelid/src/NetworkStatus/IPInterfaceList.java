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
public class IPInterfaceList implements Serializable
{
  public IPInterfaceList (){}
  public  List<IPInterface> ipInterfaceList;
  public void Add( IPInterface ipInterface)
  {
      ipInterfaceList.add(ipInterface);
  }
  public void Remove(IPInterface ipInterface)
  {
      ipInterfaceList.remove(ipInterface);
  }
  public void Remove(int index)
  {
      ipInterfaceList.remove(index);
  }
  public List<IPInterface> GetIPInterfaceList()
  {
      return ipInterfaceList;
  }
  
}
