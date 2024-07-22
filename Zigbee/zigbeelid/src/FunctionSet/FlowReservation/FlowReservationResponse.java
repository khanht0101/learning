/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.FlowReservation;

import com.PrimaryTypes.HexBinary128;
import com.PrimaryTypes.UInt8;
import com.Types.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class FlowReservationResponse implements Serializable
{
    public FlowReservationResponse ()
    {
        Random random = new Random();
        energyAvailable = new RealEnergy();
        powerAvailable = new ActivePower(new UInt8(random.nextInt(8)));
        reservationStatus = new ReservationStatusType(new UInt8(random.nextInt(8)));
        subject = new mRIDType(new HexBinary128(random.nextLong()));
    }
    public RealEnergy  energyAvailable ;
    public ActivePower  powerAvailable ;
    public ReservationStatusType  reservationStatus ;
    public mRIDType  subject ;    
}
