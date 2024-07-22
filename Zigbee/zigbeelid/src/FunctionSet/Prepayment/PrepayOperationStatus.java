/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import java.io.Serializable;

/**
 *
 * @author khanhnguyenc
 */
public class PrepayOperationStatus implements Serializable
{
    public PrepayOperationStatus()
    {
        creditTypeChange = new CreditTypeChange();
        creditTypeInUse = new CreditTypeType();
        serviceChange = new ServiceChange();
        serviceStatus = new ServiceStatusType();
    }
    public CreditTypeChange creditTypeChange;
    public CreditTypeType creditTypeInUse;
    public ServiceChange serviceChange;
    public ServiceStatusType serviceStatus;
}
