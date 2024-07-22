/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class PrepaymentList implements Serializable
{
    public PrepaymentList()
    {
      prepaymentList = new ArrayList<Prepayment>();      
    }
    private  List<Prepayment> prepaymentList;
    public void Add(Prepayment prepayment)
    {
        
        prepaymentList.add(prepayment);
    }
    public void Insert(Prepayment prepayment, int index)
    {
        prepaymentList.add(index, prepayment);
    }
    public void Remove(Prepayment prepayment)
    {
        prepaymentList.remove(prepayment);
    }
    public void Remove(int index)
    {
        prepaymentList.remove(index);
    }
    
    public int Length()
    {        
        return prepaymentList.size();
    }
    public List<Prepayment> Values()
    {
        return prepaymentList;   
    }
    public Prepayment Get(int index)
    {
        return prepaymentList.get(index);   
    }
  
}
