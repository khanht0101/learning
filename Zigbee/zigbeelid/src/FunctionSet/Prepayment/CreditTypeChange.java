/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import com.Types.TimeType;
import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class CreditTypeChange implements Serializable{
    public CreditTypeChange ()
    {
        newType = new CreditTypeType();
        startTime = new TimeType();
    }
    public CreditTypeType newType;
    public TimeType startTime;
}
