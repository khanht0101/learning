/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Khanh
 */
@XmlRootElement(namespace = "")
public class LinkObject implements Serializable
{
    @XmlAttribute(name ="href")
    public String Href;
    @XmlAttribute(name ="all")
    public String All;
}
