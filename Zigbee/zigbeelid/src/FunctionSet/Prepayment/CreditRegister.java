/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import com.Identification.IdentifiedObject;
import com.PrimaryTypes.String32;
import com.PrimaryTypes.UInt8;
import com.Types.TimeType;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class CreditRegister  implements Serializable
{
    public CreditRegister ()
    {
        Random random = new Random();
        creditAmount = new AccountingUnit();
        creditType = new CreditTypeType( new UInt8(random.nextInt(8)));
        effectiveTime = new TimeType(random.nextLong());
        token = new String32(random.nextLong()+"");
        identifiedObject = new IdentifiedObject();
    }
    public AccountingUnit  creditAmount ;
    public CreditTypeType  creditType;
    public TimeType  effectiveTime ;
    public String32  token ;
    public IdentifiedObject identifiedObject;
}
