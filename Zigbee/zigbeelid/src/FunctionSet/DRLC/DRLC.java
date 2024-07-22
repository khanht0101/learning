/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;

import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class DRLC  implements Serializable

{
    public DRLC()
    {
        demandResponseProgramList = new DemandResponseProgramList();
        demandResponseProgramList.Add(new DemandResponseProgram());
        loadShedAvailability = new LoadShedAvailability();
    }
    public DemandResponseProgramList demandResponseProgramList;
    public LoadShedAvailability loadShedAvailability;
    
}
