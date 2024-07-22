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
public class ProjectionReadingList implements Serializable
{
    public ProjectionReadingList()
    {
      projectionReadingList = new ArrayList<ProjectionReading>();
    }
    private  List<ProjectionReading> projectionReadingList;
    public void Add(ProjectionReading projectionReading)
    {
        projectionReadingList.add(projectionReading);
    }
    public void Remove(ProjectionReading projectionReading)
    {
        projectionReadingList.remove(projectionReading);
    }
    public void Remove(int index)
    {
        projectionReadingList.remove(index);
    }
    
    public int Length()
    {        
        return projectionReadingList.size();
    }
    public List<ProjectionReading> GetValues()
    {
        return projectionReadingList;   
    }
    public ProjectionReading Get(int index)
    {
        return projectionReadingList.get(index);   
    }
  
}