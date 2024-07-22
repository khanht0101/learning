/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import com.Identification.IdentifiedObject;
import com.PrimaryTypes.UInt8;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class Prepayment implements Serializable
{
    public Prepayment()
    {
        creditExpiryLevel = new AccountingUnit();
        lowCreditWarningLevel = new AccountingUnit();
        lowEmergencyCreditWarningLevel = new AccountingUnit();
        prepayMode = new PrepayModeType(new UInt8(new Random().nextInt(8)));
        identifiedObject = new IdentifiedObject();
        accountBalance = new AccountBalance();
        prepayOperationStatus = new PrepayOperationStatus();
        activeSupplyInterruptionOverrideList = new SupplyInterruptionOverrideList();
        activeSupplyInterruptionOverrideList.Add(new SupplyInterruptionOverride());
        supplyInterruptionOverrideList = new SupplyInterruptionOverrideList();
        creditRegisterList = new CreditRegisterList();
    }    
    public AccountingUnit  creditExpiryLevel;
    public AccountingUnit  lowCreditWarningLevel ;
    public AccountingUnit  lowEmergencyCreditWarningLevel ;
    public PrepayModeType  prepayMode ;
    public IdentifiedObject identifiedObject;
    public AccountBalance accountBalance;
    public PrepayOperationStatus prepayOperationStatus;
    public SupplyInterruptionOverrideList activeSupplyInterruptionOverrideList;
    public SupplyInterruptionOverrideList supplyInterruptionOverrideList;
    public CreditRegisterList creditRegisterList;
}
