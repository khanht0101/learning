/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author Khanh
 */
import java.io.Serializable;
import java.util.*;
import javax.xml.bind.annotation.XmlAnyElement;
 
public class Wrapper<T> implements Serializable{
 
    private List<T> items;
 
    public Wrapper() {
        items = new ArrayList<T>();
    }
 
    public Wrapper(List<T> items) {
        this.items = items;
    }
 
    @XmlAnyElement(lax=true)
    public List<T> getItems() {
        return items;
    }
 
}