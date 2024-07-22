/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LogEvents;

import NetworkStatus.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class LogEventList implements Serializable
{
  public LogEventList (){}
  public  List<LogEvent> logEventList;
  public void Add( LogEvent logEvent)
  {
      logEventList.add(logEvent);
  }
  public void Remove(LogEvent logEvent)
  {
      logEventList.remove(logEvent);
  }
  public void Remove(int index)
  {
      logEventList.remove(index);
  }
  public List<LogEvent> GetLogEventList()
  {
      return logEventList;
  }
  
}
