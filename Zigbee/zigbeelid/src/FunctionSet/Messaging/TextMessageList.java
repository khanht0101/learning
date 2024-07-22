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
public class TextMessageList implements Serializable
{
    public TextMessageList()
    {
      textMessageList = new ArrayList<TextMessage>();
    }
    private  List<TextMessage> textMessageList;
    public void Add(TextMessage textMessage)
    {
        textMessageList.add(textMessage);
    }
    public void Remove(TextMessage textMessage)
    {
        textMessageList.remove(textMessage);
    }
    public void Remove(int index)
    {
        textMessageList.remove(index);
    }   
    
    public int Length()
    {        
            return textMessageList.size();
    }
    public List<TextMessage> getValues()
    {
        return textMessageList;   
    }
    public TextMessage Get(int index)
    {
        return textMessageList.get(index);   
    }
  
}
