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
public class IPAddrList implements Serializable
{
  public IPAddrList (){}
  public  List<IPAddr> ipAddList;
  public void Add( IPAddr ipadd)
  {
      ipAddList.add(ipadd);
  }
  public void Remove(IPAddr ipadd)
  {
      ipAddList.remove(ipadd);
  }
  public void Remove(int index)
  {
      ipAddList.remove(index);
  }
  public List<IPAddr> GetIPAddrList()
  {
      return ipAddList;
  }
  
}
