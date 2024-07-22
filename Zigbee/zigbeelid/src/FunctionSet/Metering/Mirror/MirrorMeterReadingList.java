/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering.Mirror;

import FunctionSet.Metering.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class MirrorMeterReadingList implements Serializable
{
    public MirrorMeterReadingList()
    {
      mirrorMeterReadingList = new ArrayList<MirrorMeterReading>();
    }
    private  List<MirrorMeterReading> mirrorMeterReadingList;
    public void Add(MirrorMeterReading mirrorMeterReading)
    {
        mirrorMeterReadingList.add(mirrorMeterReading);
    }
    public void Remove(MirrorMeterReading mirrorMeterReading)
    {
        mirrorMeterReadingList.remove(mirrorMeterReading);
    }
    public void Remove(int index)
    {
        mirrorMeterReadingList.remove(index);
    }
    
    public int Length()
    {        
        return mirrorMeterReadingList.size();
    }
    public List<MirrorMeterReading> Values()
    {
        return mirrorMeterReadingList;   
    }
    public MirrorMeterReading Get(int index)
    {
        return mirrorMeterReadingList.get(index);   
    }
  
}
