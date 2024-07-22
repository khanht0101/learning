/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Messaging;

import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class Messaging implements Serializable
{
    public Messaging ()
    {
        messagingProgramList = new MessagingProgramList();
    }
    public MessagingProgramList messagingProgramList;
}
