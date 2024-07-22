/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Messaging;

import com.Identification.RespondableSubscribableIdentifiedObject;
import com.Objects.Event;
import com.PrimaryTypes.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class TextMessage implements Serializable
{
    public TextMessage()
    {
        Random random = new Random();
        originator = new String20(random.nextLong()+"");
        priority = new PriorityType();
        textMessage = "Text Message "+ random.nextLong();   
        event = new Event();
        respondableSubscribableIdentifiedObject= new RespondableSubscribableIdentifiedObject();
    }
    public String20 originator;
    public PriorityType priority;
    public String textMessage;
    public Event event;
    public RespondableSubscribableIdentifiedObject respondableSubscribableIdentifiedObject;
}
