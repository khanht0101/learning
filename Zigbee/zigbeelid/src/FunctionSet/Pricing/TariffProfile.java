/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Pricing;

import com.Types.PowerOfTenMultiplierType;
import com.Types.CurrencyCode;
import com.Types.PrimacyType;
import com.Types.ServiceKind;
import com.PrimaryTypes.String20;
import com.Types.*;
import com.PrimaryTypes.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class TariffProfile implements Serializable
{
    public TariffProfile ()
    {
        Random random = new Random();
        currency = new CurrencyCode( new UInt16(random.nextInt(16)));
        pricePowerOfTenMultilier = new PowerOfTenMultiplierType(random.nextInt());
        primacy = new PrimacyType( random.nextInt());
        rateCode = new String20(random.nextLong()+"");
        serviceCategoryKind = new ServiceKind(new UInt8(random.nextInt(8)));
        rateComponentList = new RateComponentList();
    }
    public CurrencyCode currency;
    public PowerOfTenMultiplierType pricePowerOfTenMultilier;
    public PrimacyType primacy;
    public String20 rateCode;
    public ServiceKind serviceCategoryKind;
    public RateComponentList rateComponentList;
    public ServiceKind getserviceKindObject()            
    {
        return serviceCategoryKind;
    }
    
}
