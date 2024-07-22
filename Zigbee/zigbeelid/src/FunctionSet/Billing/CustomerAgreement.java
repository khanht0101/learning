/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Billing;

import FunctionSet.Metering.UsagePointList;
import FunctionSet.Prepayment.PrepaymentList;
import FunctionSet.Pricing.TariffProfileList;
import com.Identification.IdentifiedObject;
import com.PrimaryTypes.String42;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class CustomerAgreement implements Serializable
{
    public CustomerAgreement () 
    {
        Random random = new Random();
        serviceAccount = new String42("Service Account "+ random.nextInt(10));
        serviceLocation = new String42("Service Location "+ random.nextInt(10));
        identifiedObject = new IdentifiedObject();
        historicalReadingList = new HistoricalReadingList();
        usagePointList = new UsagePointList();
        serviceSupplier = new ServiceSupplier();
        billingPeriodList = new BillingPeriodList();
        projectionReadingList = new ProjectionReadingList();
        targetReadingList = new TargetReadingList();
        activeProjectionReadingList = new ProjectionReadingList();
        prepaymentList = new PrepaymentList();
        activeBillingPeriodList = new BillingPeriodList();
        tariffProfileList = new TariffProfileList();
        activeTariffProfileList = new TariffProfileList();
    }
    public String42 serviceAccount;
    public String42 serviceLocation;  
    public IdentifiedObject identifiedObject;
    public HistoricalReadingList historicalReadingList;
    public UsagePointList usagePointList;
    public BillingPeriodList billingPeriodList;
    public ProjectionReadingList projectionReadingList;
    public TargetReadingList targetReadingList;
    public ProjectionReadingList activeProjectionReadingList;
    public PrepaymentList prepaymentList;
    public BillingPeriodList activeBillingPeriodList;
    public TariffProfileList tariffProfileList;
    public TariffProfileList activeTariffProfileList;
    public ServiceSupplier serviceSupplier;
    
   
}
