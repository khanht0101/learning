/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author CONG HUY
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class UsagePointList implements Serializable
{
    
    public UsagePointList()
    {      
      usagePointList = new ArrayList<UsagePoint>();
    }
    
    @XmlElementWrapper(name = "UsagePointList")
    @XmlElement(name = "UsagePoint")
    public  ArrayList<UsagePoint> usagePointList;
    public void Add(UsagePoint usagePoint)
    {
        usagePointList.add(usagePoint);
    }
    public void Remove(UsagePoint usagePoint)
    {
        usagePointList.remove(usagePoint);
    }
    public void Remove(int index)
    {
        usagePointList.remove(index);
    }
    public List<UsagePoint> GetUsagePointList()
    {
        return usagePointList;
    }
    public int Length()
    {        
        return usagePointList.size();
    }
    public List<UsagePoint> Values()
    {
        return usagePointList;   
    }
    public UsagePoint Get(int index)
    {
        return usagePointList.get(index);   
    }
  
}
