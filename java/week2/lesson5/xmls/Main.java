package week2.lesson5.xmls;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
//        DocumentBuilderFactory dbf =
//                DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder =
//                dbf.newDocumentBuilder();
//        Document document = builder.parse(new File("order.xml"));
//        parseXML(document);
//        createXML("order2.xml");
    }

    public static void parseXML(Node document){
        NodeList tags = document.getChildNodes();
        for(int i=0; i < tags.getLength(); i++){
            Node node = tags.item(i);
            System.out.println(node.getNodeName());

            if(node instanceof Element){
                Element elem = (Element) node;
                System.out.println(elem.getTextContent());
            } else {
                parseXML(node);
            }
        }
    }

//    public static void createXML(String fileName) throws ParserConfigurationException, TransformerException {
//        DocumentBuilderFactory dbf =
//                DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
//
//        DOMImplementation implementation =
//                documentBuilder.getDOMImplementation();
//
//        Document document =
//                implementation.createDocument(null, null, null);
//
//        Element element = document.createElement("Person");
//        element.setTextContent("something");
//        document.appendChild(element);
////        Element element2 = document.createElement("Order");
////        element2.setAttribute("oredrId", "78");
////        element2.setNodeValue("Zakaz");
////        document.appendChild(element2);
//
//        Transformer transformer =
//                TransformerFactory.newInstance().newTransformer();
//        Result output = new StreamResult(new File(fileName));
//        Source source = new DOMSource(document);
//
//        transformer.transform(source, output);
//    }

}
