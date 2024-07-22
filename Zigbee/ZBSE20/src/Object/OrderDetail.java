/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Khanh
 */
@XmlRootElement(namespace="", name = "OrderDetail")
public class OrderDetail implements Serializable
{

@XmlAttribute(name ="lineId")
public String lineId;
@XmlElement(name = "itemNumber")
public  String itemNumber;
@XmlElement(name = "quantity")
public int quantity = 0;

public Double price;
//  
//
// public void setItemNumber(String itemNumber) {
//  this.itemNumber = itemNumber;
// }
// public int getQuantity() {
//  return quantity;
// }
// public void setQuantities(int quantity) {
//  this.quantity = quantity;
// }
// public Double getPrice() {
//  return price;
// }
// 
// public void setPricea(Double price) {
//  this.price = price;
// }
// 
}
