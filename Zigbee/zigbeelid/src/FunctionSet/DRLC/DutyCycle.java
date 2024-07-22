
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;

import com.PrimaryTypes.UInt8;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class DutyCycle implements  Serializable
{
    public DutyCycle ()
    {
        normalValue = new UInt8(new Random().nextInt(8));
    }
    public UInt8 normalValue;
    public String Response(String pace)
    {
        String response ="";
        response = pace + "<normalValue>"+normalValue.getValue()+"</normalValue>\n";        
        return response;
    }
}
