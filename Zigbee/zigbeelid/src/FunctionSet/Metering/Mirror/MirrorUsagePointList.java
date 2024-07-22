/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering.Mirror;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class MirrorUsagePointList implements Serializable
{
    public MirrorUsagePointList()
    {
      mirrorUsagePointList = new ArrayList<MirrorUsagePoint>();
    }
    private  List<MirrorUsagePoint> mirrorUsagePointList;
    public void Add(MirrorUsagePoint mirrorUsagePoint)
    {
        mirrorUsagePointList.add(mirrorUsagePoint);
    }
    public void Remove(MirrorMeterReading mirrorUsagePoint)
    {
        mirrorUsagePointList.remove(mirrorUsagePoint);
    }
    public void Remove(int index)
    {
        mirrorUsagePointList.remove(index);
    }
    
    public int Length()
    {        
        return mirrorUsagePointList.size();
    }
    public List<MirrorUsagePoint> Values()
    {
        return mirrorUsagePointList;   
    }
    public MirrorUsagePoint Get(int index)
    {
        return mirrorUsagePointList.get(index);   
    }
  
}
