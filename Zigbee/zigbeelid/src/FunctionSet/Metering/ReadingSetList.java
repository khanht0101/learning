/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering;

import com.Types.SubscribableType;
import com.PrimaryTypes.HexBinary16;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author CONG HUY
 */
public class ReadingSetList implements Serializable
{
    public ReadingSetList()
    {
      readingSetList = new ArrayList<ReadingSet>();
    }
    private  List<ReadingSet> readingSetList;
    public void Add(ReadingSet readingSet)
    {
        readingSetList.add(readingSet);
    }
    public void Remove(ReadingSet readingSet)
    {
        readingSetList.remove(readingSet);
    }
    public void Remove(int index)
    {
        readingSetList.remove(index);
    }
    public List<ReadingSet> GetReadingSetList()
    {
        return readingSetList;
    }
    
    public int Length()
    {        
        return readingSetList.size();
    }
    public List<ReadingSet> Values()
    {
        return readingSetList;   
    }
    public ReadingSet Get(int index)
    {
        return readingSetList.get(index);   
    }
  
}
