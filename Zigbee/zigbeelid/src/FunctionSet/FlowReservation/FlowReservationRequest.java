/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.FlowReservation;

import com.PrimaryTypes.UInt16;
import com.Types.ActivePower;
import com.Types.RealEnergy;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author khanhnguyenc
 */
public class FlowReservationRequest implements Serializable
{
    public FlowReservationRequest ()
    {
        Random random = new Random();
        durationRequested = new UInt16(random.nextInt(16));
        energyRequested = new RealEnergy();
        powerRequested = new ActivePower();
    }
    public UInt16  durationRequested;
    public RealEnergy  energyRequested ;
    public ActivePower  powerRequested ;
}
