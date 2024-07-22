/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;

import com.PrimaryTypes.*;
import com.Types.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class LoadShedAvailability implements  Serializable
{
    public LoadShedAvailability ()
    {
        Random random = new Random();
        availabilityDuration = new UInt32(new Random().nextInt(32));
        sheddablePercent = new PerCent(random.nextLong());
        sheddablePower = new ActivePower(new UInt8(random.nextInt(8)));
    }
    public UInt32 availabilityDuration ;
    public PerCent sheddablePercent ;
    public ActivePower sheddablePower ;
    public String Response(String pace)
    {
        String response ="";
        response = pace + "<availabilityDuration>"+availabilityDuration.getValue()+"</availabilityDuration>\n";      
        response += pace + "<sheddablePercent>"+sheddablePercent.value+"</sheddablePercent>\n";
        response += pace + "<sheddablePower>"+sheddablePower.getValue().getValue()+"</sheddablePower>\n";
        
        return response;
    }
}
