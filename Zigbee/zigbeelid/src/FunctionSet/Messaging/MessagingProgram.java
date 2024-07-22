/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Messaging;

import com.PrimaryTypes.String42;
import com.Types.*;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Khanh
 */
public class MessagingProgram implements Serializable
{
    public MessagingProgram()
    {
        Random random = new Random();
        locale = "";
        primacy = 0;
        textMessageList = new TextMessageList();
        activeTextMessageList = new TextMessageList();
    }
    public String locale ;
    public long  primacy;
    public TextMessageList textMessageList;
    public TextMessageList activeTextMessageList;
}
