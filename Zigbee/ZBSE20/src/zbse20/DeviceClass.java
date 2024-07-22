/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class DeviceClass 
{
    
    public ReadExcelFile readExcelFile = new ReadExcelFile();
    public DeviceClass()
    {
    }
   public List<List> getAllDevice()
    {
        return readExcelFile.ReadFile("device", "Device");
    }
    
     public List<List> getAllFunction()
    {
        return readExcelFile.ReadFile("device", "Functions");
    }
    public List<List> getSelectedFunctionSet(String DeviceID)
    {        
        List<List> functionSetList = readExcelFile.ReadFile("device", "DeviceMapping");
        List<List> rowList = new ArrayList<List>();
        for(int i = 1; i< functionSetList.size(); i++)
        {
            String dvId = functionSetList.get(i).get(0).toString();
            String fuId = functionSetList.get(i).get(1).toString();
            if(dvId.indexOf(".")>0)
            {
                dvId = dvId.substring(0, dvId.indexOf("."));
            }
            if(dvId.equals(DeviceID))
            {
                List<String> cellList = new ArrayList<String>();
                
                if(fuId.indexOf(".")>0)
                {
                    fuId = fuId.substring(0, fuId.indexOf("."));
                }
                cellList.add(fuId);
                cellList.add(functionSetList.get(i).get(2).toString());
                cellList.add(functionSetList.get(i).get(3).toString());
                rowList.add(cellList);
            }
        }
        return rowList;
    }
}
