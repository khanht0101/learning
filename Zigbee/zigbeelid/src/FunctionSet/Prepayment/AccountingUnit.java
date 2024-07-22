/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import com.PrimaryTypes.Int32;
import com.PrimaryTypes.UInt16;
import com.Types.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class AccountingUnit implements Serializable
{
    public AccountingUnit()
    {
        Random random = new Random();
        energyUnit = new RealEnergy();
        monetaryUnit = new CurrencyCode(new UInt16(random.nextInt(16)));
        multiplier = new PowerOfTenMultiplierType(random.nextLong());
        value = new Int32(random.nextInt(32));
    }
    public RealEnergy energyUnit;
    public CurrencyCode monetaryUnit;
    public PowerOfTenMultiplierType multiplier ;
    public Int32 value ;
}
