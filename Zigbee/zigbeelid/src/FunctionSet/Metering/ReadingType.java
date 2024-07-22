/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering;

import com.Types.AccumulationBehaviourType;
import com.Types.CommodityType;
import com.Types.DataQualifierType;
import com.Types.FlowDirectionType;
import com.Types.KindType;
import com.Types.PhaseCode;
import com.Types.PowerOfTenMultiplierType;
import com.Types.SubscribableType;
import com.Types.UnitValueType;
import com.Types.UomType;
import com.PrimaryTypes.HexBinary16;
import com.PrimaryTypes.UInt32;
import com.PrimaryTypes.UInt48;
import com.PrimaryTypes.UInt8;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author CONG HUY
 */
public class ReadingType implements Serializable
{
    public ReadingType()
    {
        Random random= new Random();
        accumulationBehaviour = new AccumulationBehaviourType(new UInt8(random.nextInt(8)));
        calorificValue = new UnitValueType();
        commodity = new CommodityType(new UInt8(random.nextInt(8)));
        conversionFactor = new UnitValueType();
        dataQualifier = new DataQualifierType(new UInt8(random.nextInt(8)));
        flowDirection = new FlowDirectionType(new UInt8(random.nextInt(8)));
        intervalLength= new UInt32(random.nextInt(32));
        kind = new KindType(new UInt8(random.nextInt(8)));
        numberOfConsumptionBlocks = new UInt8(random.nextInt(8));
        numberOfTouTiers = new  UInt8(random.nextInt(8));
        phase = new PhaseCode(new UInt8(random.nextInt(8)));
        powerOfTenMultiplier = new PowerOfTenMultiplierType(random.nextLong());
        subIntervalLength = new UInt32(random.nextInt(32));
        supplyLimit = new UInt48(random.nextInt(48));
        uom = new UomType(new  UInt8(random.nextInt(8)));
    }
    public AccumulationBehaviourType  accumulationBehaviour = new AccumulationBehaviourType();
    public UnitValueType  calorificValue = new UnitValueType();
    public CommodityType  commodity  = new CommodityType();
    public UnitValueType  conversionFactor = new UnitValueType();
    public DataQualifierType dataQualifier  = new DataQualifierType();
    public FlowDirectionType flowDirection = new FlowDirectionType();
    public UInt32  intervalLength  = new UInt32();
    public KindType  kind =new  KindType();
    public UInt8  numberOfConsumptionBlocks =new UInt8();
    public UInt8  numberOfTouTiers = new UInt8();
    public PhaseCode  phase =new PhaseCode();
    public PowerOfTenMultiplierType  powerOfTenMultiplier = new PowerOfTenMultiplierType();
    public UInt32  subIntervalLength = new UInt32();
    public UInt48  supplyLimit = new UInt48();
    public boolean  tieredConsumptionBlocks  = false;
    public UomType  uom = new UomType();
}
