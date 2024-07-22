/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class AccountBalance implements Serializable
{
    public AccountBalance ()
    {
        availableCredit = new AccountingUnit();
        creditStatus = new CreditStatusType();
        emergencyCredit = new AccountingUnit();
        emergencyCreditStatus = new CreditStatusType();
    }
    public AccountingUnit  availableCredit ;
    public CreditStatusType  creditStatus ;
    public AccountingUnit  emergencyCredit ;
    public CreditStatusType  emergencyCreditStatus ;
    
}
