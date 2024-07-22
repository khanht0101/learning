/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.DRLC;

import com.PrimaryTypes.UInt8;
import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import java.io.Serializable;

/**
 *
 * @author khanhnguyenc
 */
public class Offset implements Serializable
{
    public Offset ()
    {
    }
    public UInt8 coolingOffset;
    public UInt8  heatingOffset;
    public String Response(String pace)
    {
        String response ="";
        response = pace + "<coolingOffset>"+coolingOffset.getValue()+"</coolingOffset>\n";
        response += pace + "<heatingOffset>"+coolingOffset.getValue()+"</heatingOffset>\n";        
        return response;
    }
}
