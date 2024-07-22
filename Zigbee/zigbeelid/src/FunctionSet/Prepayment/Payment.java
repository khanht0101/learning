/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Prepayment;

import FunctionSet.Messaging.*;
import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class Payment implements Serializable
{
    public Payment ()
    {
        prepaymentList = new PrepaymentList();
    }
    public PrepaymentList prepaymentList;
}
