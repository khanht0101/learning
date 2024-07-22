/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Response;

import com.Types.CreditTypeType;
import com.Types.ServiceStatusType;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class PrepayResponse implements Serializable
{
    public PrepayResponse() {}
    public CreditTypeType creditTypeApplied;
    public ServiceStatusType serviceStatusApplied ;
    
    
}
