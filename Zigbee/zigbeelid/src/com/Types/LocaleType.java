 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.String42;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class LocaleType implements Serializable
{
    public LocaleType() {}
    private String42 value;
    public LocaleType(String42 _value)
    {
        value = _value;
    }
    public void setValue(String42 _value)
    {
        value = _value;
    }
    public String42 getValue()
    {
        return value;
    }
}
