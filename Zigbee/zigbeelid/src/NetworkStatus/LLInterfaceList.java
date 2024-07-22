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
public class LLInterfaceList implements Serializable
{
  public LLInterfaceList (){}
  public  List<LLInterface> llInterfaceList;
  public void Add( LLInterface llInterface)
  {
      llInterfaceList.add(llInterface);
  }
  public void Remove(LLInterface llInterface)
  {
      llInterfaceList.remove(llInterface);
  }
  public void Remove(int index)
  {
      llInterfaceList.remove(index);
  }
  public List<LLInterface> GetLLInterfaceList()
  {
      return llInterfaceList;
  }
  
}
