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
public class HistoricalReadingList implements Serializable
{
    public HistoricalReadingList()
    {
      historicalReadingList = new ArrayList<HistoricalReading>();
    }
    private  List<HistoricalReading> historicalReadingList;
    public void Add(HistoricalReading historicalReading)
    {
        historicalReadingList.add(historicalReading);
    }
    public void Remove(CustomerAgreement historicalReading)
    {
        historicalReadingList.remove(historicalReading);
    }
    public void Remove(int index)
    {
        historicalReadingList.remove(index);
    }
    
    public int Length()
    {        
        return historicalReadingList.size();
    }
    public List<HistoricalReading> GetValues()
    {
        return historicalReadingList;   
    }
    public HistoricalReading Get(int index)
    {
        return historicalReadingList.get(index);   
    }
  
}
