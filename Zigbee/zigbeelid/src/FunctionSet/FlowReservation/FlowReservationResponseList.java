/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.FlowReservation;

import FunctionSet.Prepayment.*;
import FunctionSet.Pricing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class FlowReservationResponseList implements Serializable
{
  public FlowReservationResponseList ()
  {
      flowReservationResponseList = new ArrayList<FlowReservationResponse>();
  }
  public  List<FlowReservationResponse> flowReservationResponseList;
  public void Add(FlowReservationResponse flowReservationResponse)
  {
      flowReservationResponseList.add(flowReservationResponse);
  }
  public void Remove(FlowReservationResponse flowReservationResponse)
  {
      flowReservationResponseList.remove(flowReservationResponse);
  }
  public void Remove(int index)
  {
      flowReservationResponseList.remove(index);
  } 
  public int Length()
  {
      return flowReservationResponseList.size();
  }
  public FlowReservationResponse Get(int i)
  {
      return flowReservationResponseList.get(i);
  }
}
