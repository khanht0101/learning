/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareDownload;

import NetworkStatus.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CONG HUY
 */
public class FileList implements Serializable
{
  public FileList (){}
  public  List<File> fileList;
  public void Add( File file)
  {
      fileList.add(file);
  }
  public void Remove(File file)
  {
      fileList.remove(file);
  }
  public void Remove(int index)
  {
      fileList.remove(index);
  }
  public List<File> GetFileList()
  {
      return fileList;
  }
  
}
