/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering;

import com.Types.SubscribableType;
import com.PrimaryTypes.HexBinary16;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class ReadingSet implements Serializable
{
    public ReadingSet(){};
    public ReadingList readingList = new ReadingList();
}
