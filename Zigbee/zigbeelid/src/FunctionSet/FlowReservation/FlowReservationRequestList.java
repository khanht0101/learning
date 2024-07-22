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
public class FlowReservationRequestList implements Serializable
{
  public FlowReservationRequestList ()
  {
      flowReservationRequestList = new ArrayList<FlowReservationRequest>();
  }
  public  List<FlowReservationRequest> flowReservationRequestList;
  public void Add(FlowReservationRequest flowReservationRequest)
  {
      flowReservationRequestList.add(flowReservationRequest);
  }
  public void Remove(FlowReservationRequest flowReservationRequest)
  {
      flowReservationRequestList.remove(flowReservationRequest);
  }
  public void Remove(int index)
  {
      flowReservationRequestList.remove(index);
  } 
  public int Length()
  {
      return flowReservationRequestList.size();
  }
  public FlowReservationRequest Get(int i)
  {
      return flowReservationRequestList.get(i);
  }
}
