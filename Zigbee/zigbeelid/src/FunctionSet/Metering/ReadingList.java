/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering;

import com.Types.SubscribableType;
import com.PrimaryTypes.HexBinary16;
import com.PrimaryTypes.UInt8;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author CONG HUY
 */
public class ReadingList implements Serializable
{
    public ReadingList()
    {
      readingList = new ArrayList<Reading>();      
    }
    private  List<Reading> readingList;
    public void Add(Reading reading)
    {
        
        readingList.add(reading);
    }
    public void Insert(Reading reading, int index)
    {
        readingList.add(index, reading);
    }
    public void Remove(Reading reading)
    {
        readingList.remove(reading);
    }
    public void Remove(int index)
    {
        readingList.remove(index);
    }
    public List<Reading> GetReadingList()
    {
        return readingList;
    }
    
    public int Length()
    {        
        return readingList.size();
    }
    public List<Reading> Values()
    {
        return readingList;   
    }
    public Reading Get(int index)
    {
        return readingList.get(index);   
    }
  
}
