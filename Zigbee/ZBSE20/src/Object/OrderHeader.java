/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;
import java.util.ArrayList;
 
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import Object.OrderDetail;
import java.io.Serializable;
 
//the root-element of our order XML file
@XmlRootElement(namespace="", name = "OrderHeader")
public class OrderHeader implements Serializable{
  
 String customerId = null;
 String orderId = null;
 Double orderTotal = null;
 
 @XmlElementWrapper(name = "OrderDetails")
 @XmlElement(name = "OrderDetail")
 public ArrayList<OrderDetail> orderDetailList;
  
 public String getCustomerId() {
  return customerId;
 }
  
 //defines the XML element name and it's namespace
 @XmlElement(name = "CustomerId")
 public void setCustomerId(String customerId) {
  this.customerId = customerId;
 }
  
 public String getOrderId() {
  return orderId;
 }
  
 //this makes it an attribute for the parent node
 @XmlAttribute
 public void setOrderId(String orderId) {
  this.orderId = orderId;
 }
 public Double getOrderTotal() {
  return orderTotal;
 }
  
 //rename the given element
 @XmlElement(name = "OrderTotal")
 public void setOrderTotal(Double orderTotal) {
  this.orderTotal = orderTotal;
 }
  
 
 public ArrayList<OrderDetail> getOrderDetailList() {
  return orderDetailList;
 }
  
// //adds a wrapper element around the XML representation
// @XmlElementWrapper(name = "OrderDetails")
//  
// //override the name for the XML element
// @XmlElement(name = "OrderDetail")
// public void setOrderDetailList(ArrayList<OrderDetail> orderDetailList) {
//  this.orderDetailList = orderDetailList;
// }
// 
}