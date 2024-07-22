/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Messaging;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class MessagingProgramList implements Serializable
{
    public MessagingProgramList()
    {
      messagingProgramList = new ArrayList<MessagingProgram>();
      messagingProgramList.add(new MessagingProgram());
    }
    private  List<MessagingProgram> messagingProgramList;
    public void Add(MessagingProgram messagingProgram)
    {
        messagingProgramList.add(messagingProgram);
    }
    public void Remove(MessagingProgram messagingProgram)
    {
        messagingProgramList.remove(messagingProgram);
    }
    public void Remove(int index)
    {
        messagingProgramList.remove(index);
    }
    
    public int Length()
    {        
        return messagingProgramList.size();
    }
    public List<MessagingProgram> GetValues()
    {
        return messagingProgramList;   
    }
    public MessagingProgram Get(int index)
    {
        return messagingProgramList.get(index);   
    }
  
}
