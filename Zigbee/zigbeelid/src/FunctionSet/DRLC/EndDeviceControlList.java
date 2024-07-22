/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;


import FunctionSet.Messaging.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class EndDeviceControlList implements Serializable
{
    public EndDeviceControlList()
    {
      endDeviceControlList = new ArrayList<EndDeviceControl>();
    }
    private  List<EndDeviceControl> endDeviceControlList;
    public void Add(EndDeviceControl endDeviceControl)
    {
        endDeviceControlList.add(endDeviceControl);
    }
    public void Remove(EndDeviceControl endDeviceControl)
    {
        endDeviceControlList.remove(endDeviceControl);
    }
    public void Remove(int index)
    {
        endDeviceControlList.remove(index);
    }
    
    public int Length()
    {        
        return endDeviceControlList.size();
    }
    public List<EndDeviceControl> GetValues()
    {
        return endDeviceControlList;   
    }
    public EndDeviceControl Get(int index)
    {
        return endDeviceControlList.get(index);   
    }
  
}
