/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

/**
 *
 * @author CONG HUY
 */
public class FunctionSet 
{
    public FunctionSet()
    {}
    public FunctionSet(String name, String ser)
    {
        functionsetName = name;
        server = ser;
    }
    public FunctionSet(String name, String ser, String cli)
    {
        functionsetName = name;
        server = ser;
        client = cli;
    }
    public String functionsetName = "";
    public String server = "T";
    public String client = "T";
}
