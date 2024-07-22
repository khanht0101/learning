/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;

import com.PrimaryTypes.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class SetPoint implements Serializable
{
    public SetPoint ()
    {
        Random random = new Random();
        coolingSetpoint = new Int16(random.nextInt(16));
        heatingSetpoint =  new Int16(random.nextInt(16)); 
    }
    public Int16 coolingSetpoint;
    public Int16  heatingSetpoint;
    public String Response(String pace)
    {
        String response ="";
        response = pace + "<coolingSetpoint>"+coolingSetpoint.getValue()+"</coolingSetpoint>\n";
        response += pace + "<heatingSetpoint>"+heatingSetpoint.getValue()+"</heatingSetpoint>\n";        
        return response;
    }
}
