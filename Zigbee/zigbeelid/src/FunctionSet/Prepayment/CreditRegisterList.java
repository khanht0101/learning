/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import FunctionSet.Pricing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class CreditRegisterList implements Serializable
{
  public CreditRegisterList ()
  {
      creditRegisterList = new ArrayList<CreditRegister>();
  }
  public  List<CreditRegister> creditRegisterList;
  public void Add(CreditRegister creditRegister)
  {
      creditRegisterList.add(creditRegister);
  }
  public void Remove(CreditRegister creditRegister)
  {
      creditRegisterList.remove(creditRegister);
  }
  public void Remove(int index)
  {
      creditRegisterList.remove(index);
  } 
  public int Length()
  {
      return creditRegisterList.size();
  }
  public CreditRegister Get(int i)
  {
      return creditRegisterList.get(i);
  }
}
