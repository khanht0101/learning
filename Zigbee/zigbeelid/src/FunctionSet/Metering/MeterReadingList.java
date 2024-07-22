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
public class MeterReadingList implements Serializable
{
    public MeterReadingList()
    {
      meterReadingList = new ArrayList<MeterReading>();    
    }
    private  List<MeterReading> meterReadingList;
    public void Add(MeterReading meterReading)
    {
        meterReadingList.add(meterReading);
    }
    public void Remove(MeterReading meterReading)
    {
        meterReadingList.remove(meterReading);
    }
    public void Remove(int index)
    {
        meterReadingList.remove(index);
    }
    public List<MeterReading> GetMeterReadingList()
    {
        return meterReadingList;
    }
     public int Length()
    {        
        return meterReadingList.size();
    }
    public List<MeterReading> Values()
    {
        return meterReadingList;   
    }
    public MeterReading Get(int index)
    {
        return meterReadingList.get(index);   
    }  
    public String Response(String pace, String href)
    {
        String response ="<MeterReadingList all=\""+Length()+"\"\n" +
                        "	href=\""+href+"\" results=\""+Length()+"\" subscribable=\"0\">";
                        for(MeterReading meter: Values())
                        {
                            response += meter.Response("    ", href);
                        }        
            response += "</MeterReadingList>\n";
        return response;
    }
}
