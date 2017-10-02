package jaxb.tests.modifymarshal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import primer.po.*;

public class Main {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        String baseDir = System.getProperty("user.dir");

        JAXBContext jc = JAXBContext.newInstance( "primer.po" );
        Unmarshaller u = jc.createUnmarshaller();
        JAXBElement<?> poElement =
                (JAXBElement<?>)u.unmarshal( new FileInputStream( baseDir + "/src/main/resources/jaxb/tests/po.xml" ) );
        PurchaseOrderType po = (PurchaseOrderType)poElement.getValue();
        // examine some of the content in the PurchaseOrder
        System.out.println( "Ship the following items to: " );

        // display the shipping address
        USAddress address = po.getShipTo();
        displayAddress( address );

        // display the items
        Items items = po.getItems();
        displayItems( items );

    }

    public static void displayAddress( USAddress address ) {
        // display the address
        System.out.println( "\t" + address.getName() );
        System.out.println( "\t" + address.getStreet() );
        System.out.println( "\t" + address.getCity() +
                ", " + address.getState() +
                " "  + address.getZip() );
        System.out.println( "\t" + address.getCountry() + "\n");
    }

    public static void displayItems( Items items ) {
        // the items object contains a List of primer.po.ItemType objects
        List itemTypeList = items.getItem();


        // iterate over List
        for(Iterator iter = itemTypeList.iterator(); iter.hasNext(); ) {
            Items.Item item = (Items.Item)iter.next();
            System.out.println( "\t" + item.getQuantity() +
                    " copies of \"" + item.getProductName() +
                    "\"" );
        }
    }

}
