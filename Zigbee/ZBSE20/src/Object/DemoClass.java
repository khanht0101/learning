/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Common.XmlSerialize;
import FunctionSet.Metering.UsagePoint;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.List;
import zbse20.NTSMain;
 
public class DemoClass
{

    private NTSMain  frm;
    public DemoClass( NTSMain _frm) 
    {
        frm = _frm;
    }
    
    public OrderHeader convertXMLToObject() throws FileNotFoundException
    {   
        
        OrderHeader myclas = new OrderHeader();
        
        myclas = (OrderHeader) XmlSerialize.convertXMLToObject(myclas.getClass(), ss);
        
        try
        {
        
        List<Object> usagt =  XmlSerialize.convertXMLToObjectList((new UsagePoint()).getClass(), ttt);
        System.out.println(((UsagePoint) usagt.get(0)).MeterReadingListLink.Href);
        
        String filename = System.getProperty("user.dir")+"/usagePoint.xml" ;
        UsagePoint usagt1 =  (UsagePoint)frm.convertXMLFileToObject(new UsagePoint(), filename);
        
        System.out.println(usagt1.serviceCategoryKind);       

        }
        catch (Exception exs)
        {
            System.err.println(exs.getMessage());
        }
        
        try
        {
            InputStream is = new FileInputStream( System.getProperty("user.dir")+"/myclass.xml" );
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderHeader.class);        
            Unmarshaller jaxbUnmarshaller  = jaxbContext.createUnmarshaller();          
            myclas = (OrderHeader) jaxbUnmarshaller.unmarshal(is);
        } catch (FileNotFoundException | JAXBException ex) {
           System.out.println(ex.getMessage());
        }
        return myclas;
    }
   public  String ss = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<OrderHeader orderId=\"101010\" >\n" +
"    <CustomerId>CUST01</CustomerId>\n" +
"    <OrderDetails>\n" +
"        <OrderDetail lineId=\"1\">\n" +
"            <itemNumber>ABC</itemNumber>\n" +
"            <price>10.0</price>\n" +
"            <quantity>9</quantity>\n" +
"        </OrderDetail>\n" +
"        <OrderDetail lineId=\"2\">\n" +
"            <itemNumber>XYZ</itemNumber>\n" +
"            <price>9.99</price>\n" +
"            <quantity>1</quantity>\n" +
"        </OrderDetail>\n" +
"    </OrderDetails>\n" +
"    <OrderTotal>99.99</OrderTotal>\n" +
"</OrderHeader>";
    
   public String ttt ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ 
"<UsagePointList>\n" +
"<UsagePoint href=\"/upt/0\">\n" +
"	<roleFlags>3</roleFlags>\n" +
"	<serviceCategoryKind>2</serviceCategoryKind>\n" +
"	<status>1</status>\n" +
"	<MeterReadingListLink all=\"6\" href=\"/upt/0/mr\"/>\n" +
"</UsagePoint>\n" +
"</UsagePointList>";
   
}



