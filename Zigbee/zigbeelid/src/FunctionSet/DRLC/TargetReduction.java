/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;

import com.PrimaryTypes.*;
import com.Types.UnitType;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class TargetReduction implements Serializable
{
    public TargetReduction ()
    {
        Random random = new Random();
        type = new UnitType(new UInt8(random.nextInt(8)));
        value =  new UInt16(random.nextInt(16)); 
    }
    public UnitType  type ;
    public UInt16 value ;
    public String Response(String pace)
    {
        String response ="";
        response = pace + "<type>"+type.getValue()+"</type>\n";      
        response += pace + "<value>"+value.getValue()+"</value>\n";   
        return response;
    }
}
