/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class TestCaseClass {
  
    public ReadExcelFile readExcelFile = new ReadExcelFile();
    public TestCaseClass()
    {
        
    }
    public List<List> getTestCase()
    {
       return readExcelFile.ReadFile("testcases", "testcase");        
    }
    public void UpdateTestCase(List<List> list)
    {
        readExcelFile.UpdateStatusFile(list,"testcases", "testcase");
    }
}
