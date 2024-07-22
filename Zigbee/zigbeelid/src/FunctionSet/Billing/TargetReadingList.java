/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class TargetReadingList implements Serializable
{
    public TargetReadingList()
    {
      targetReadingList = new ArrayList<TargetReading>();
    }
    private  List<TargetReading> targetReadingList;
    public void Add(TargetReading targetReading)
    {
        targetReadingList.add(targetReading);
    }
    public void Remove(TargetReading targetReading)
    {
        targetReadingList.remove(targetReading);
    }
    public void Remove(int index)
    {
        targetReadingList.remove(index);
    }
    
    public int Length()
    {        
        return targetReadingList.size();
    }
    public List<TargetReading> GetValues()
    {
        return targetReadingList;   
    }
    public TargetReading Get(int index)
    {
        return targetReadingList.get(index);   
    }
  
}