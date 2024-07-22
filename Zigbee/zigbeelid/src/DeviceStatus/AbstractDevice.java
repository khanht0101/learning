/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeviceStatus;

import com.Types.SFDIType;
import com.Types.DeviceCategoryType;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class AbstractDevice implements Serializable
{
    public AbstractDevice() {}
    public DeviceCategoryType  loadShedDeviceCategory;
    public SFDIType sFDI ;
}
