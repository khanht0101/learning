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
public class DemandResponseProgramList implements Serializable
{
    public DemandResponseProgramList()
    {
      demandResponseProgramList = new ArrayList<DemandResponseProgram>();
    }
    private  List<DemandResponseProgram> demandResponseProgramList;
    public void Add(DemandResponseProgram demandResponseProgram)
    {
        demandResponseProgramList.add(demandResponseProgram);
    }
    public void Remove(DemandResponseProgram demandResponseProgram)
    {
        demandResponseProgramList.remove(demandResponseProgram);
    }
    public void Remove(int index)
    {
        demandResponseProgramList.remove(index);
    }
    
    public int Length()
    {        
        return demandResponseProgramList.size();
    }
    public List<DemandResponseProgram> GetValues()
    {
        return demandResponseProgramList;   
    }
    public DemandResponseProgram Get(int index)
    {
        return demandResponseProgramList.get(index);   
    }
  
}
