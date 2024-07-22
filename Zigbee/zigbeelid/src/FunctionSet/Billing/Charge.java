/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import com.PrimaryTypes.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class Charge  implements Serializable
{
    public Charge()
    {
        Random random = new Random();
        kind = new ChargeKind();
        value = new Int32(random.nextInt(32));
        description = new String20("Charging "+ random.nextInt(10));
    }
    public ChargeKind kind;
    public String20 description;
    public Int32 value;
}
