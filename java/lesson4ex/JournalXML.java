package lesson4ex;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JournalXML extends Journal {

    public JournalXML(ArrayList<Lesson> lessons){
        super(lessons);
    }

    protected static void setFinal(Object parent, Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(parent, newValue);
    }

    static <T> T valueOf(Class<T> klazz, String arg) {
        Exception cause = null;
        T ret = null;
        try {
            ret = klazz.cast(
                    klazz.getDeclaredMethod("valueOf", String.class)
                            .invoke(null, arg)
            );
        } catch (NoSuchMethodException e) {
            cause = e;
        } catch (IllegalAccessException e) {
            cause = e;
        } catch (InvocationTargetException e) {
            cause = e;
        }
        if (cause == null) {
            return ret;
        } else {
            throw new IllegalArgumentException(cause);
        }
    }

    protected static void parseNode(Node root, Object current) throws Exception {
        for(Node node : new WrapNodeList<Node>(root.getChildNodes())){
            if(!node.getNodeName().equals("#text")) {
                Object next = null;
                if(node.getNodeName().equals("field") || node.getNodeName().equals("object")) {
                    if(node.getNodeName().equals("object")) {
                        Field id =
                            current.getClass().getDeclaredField(node.getAttributes().getNamedItem("id").getNodeValue());
                        String className = node.getAttributes().getNamedItem("type").getNodeValue();
                        Class<?> clazz = Class.forName(className);
                        Constructor<?> ctor = clazz.getConstructor();
                        next = ctor.newInstance();
                        setFinal(current, id, next);
                    }
                    else if(node.getNodeName().equals("field")){
                        String type = node.getAttributes().getNamedItem("type").getNodeValue();
                        if(type.equals("byte")||type.equals("short")||type.equals("int")||
                                type.equals("long")|| type.equals("float")||type.equals("double")||
                                type.equals("boolean")||type.equals("char")||type.equals("java.lang.String")||
                                type.equals("java.lang.Integer")|| type.equals("java.lang.Double")||
                                type.equals("java.lang.Float")||type.equals("java.lang.Boolean")||
                                type.equals("java.lang.Long")||type.equals("java.lang.Short")){
                            Field id =
                                    current.getClass().getDeclaredField(node.getAttributes().getNamedItem("id").getNodeValue());
                            String value =
                                    node.getAttributes().getNamedItem("value").getNodeValue();
                            Map<String, String> map = new HashMap<>();
                            map.put("boolean", "java.lang.Boolean");
                            map.put("byte", "java.lang.Byte");
                            map.put("short", "java.lang.Short");
                            map.put("char", "java.lang.Character");
                            map.put("int", "java.lang.Integer");
                            map.put("long", "java.lang.Long");
                            map.put("float", "java.lang.Float");
                            map.put("double", "java.lang.Double");
                            type = map.containsKey(type) ? map.get(type) : type;
                            Object newValue = type.equals("java.lang.String") ? value : valueOf(Class.forName(type), value);
                            setFinal(current, id, newValue);
                        }
                    }
                    String type = node.getAttributes().getNamedItem("type").getNodeValue();
                    System.out.println("<" + node.getNodeName() + " type=" + type + ">");
                    for (int i = 0; i < node.getAttributes().getLength(); i++) {
                        Node attr = node.getAttributes().item(i);
                        System.out.println("    " + attr.getNodeName() + " : " + attr.getNodeValue());
                        /**
                         * @ простой или сложный тип данных
                         */
                        if (node.getAttributes().getNamedItem("hash") == null) {
                            /**
                             * @ простой
                             */
                        } else {
                            /**
                             * @ сложный
                             */
                        }
                    }
                    System.out.println("</" + node.getNodeName() + ">");
                }
                if (node.getChildNodes().getLength() > 0) {
                    parseNode(node, next != null ? next : current);
                }
            }
        }
    }

    protected static ArrayList<PresentStudent> deSerializeCollection(String fileName) throws Exception {
        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder =
                dbf.newDocumentBuilder();
        Document document = builder.parse(new File(fileName));
        ArrayList<PresentStudent> presentStudents = new ArrayList<>();
        for(Node node : new WrapNodeList<Node>(document.getChildNodes())){
            /**
             * all nodes here are PresentStudent objects
             */
            PresentStudent ps = new PresentStudent(null, null, null);
            presentStudents.add(ps);
            parseNode(node, ps);
        }
        return presentStudents;
    }

    protected static void serializePresentStudents(String fileName, ArrayList<PresentStudent> presentStudents) throws Exception {
        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        DOMImplementation implementation =
                documentBuilder.getDOMImplementation();
        Document document =
                implementation.createDocument(null, null, null);
        Element root = document.createElement("root");
        document.appendChild(root);
        for (PresentStudent ps : presentStudents){
            xmlizePresentStudent(ps, document, root);
        }
        Transformer transformer =
                TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        Result output = new StreamResult(new File(fileName));
        Source source = new DOMSource(document);
        transformer.transform(source, output);
    }

    protected static void printObject(Object obj, Document document, Node parent, String tab) throws Exception {

        for(Field f: obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            Object next = f.get(obj);
            if(next instanceof Student || next instanceof Group || next instanceof Lesson || next instanceof Journal) {
                Element element = document.createElement("object");
                element.setAttribute("id", f.getName());
                element.setAttribute("type", f.getType().getName());
                element.setAttribute("hash", Integer.toString(next.hashCode()));
                parent.appendChild(element);
                printObject(next, document, element, tab + "\t");
            }
            else if(next instanceof Collection){
                Element element = document.createElement("field");
                element.setAttribute("id", f.getName());
                element.setAttribute("type", f.getType().getName());
                element.setAttribute("value", next.toString());
                parent.appendChild(element);
                for(Object o : ((Collection)next).toArray()){
                    Element e = document.createElement("field");
                    e.setAttribute("id", o.getClass().getName());
                    e.setAttribute("type", o.getClass().getTypeName());
                    e.setAttribute("hash", Integer.toString(o.hashCode()));
                    element.appendChild(e);
                }
            }
            else {
                Element element = document.createElement("field");
                element.setAttribute("id", f.getName());
                element.setAttribute("type", f.getType().getName());
                element.setAttribute("value", next.toString());
                parent.appendChild(element);
            }
        }
        for(Method m: obj.getClass().getDeclaredMethods()){
            Element element = document.createElement("method");
            element.setAttribute("id", m.getName());
            element.setAttribute("type", m.getReturnType().getName());
            parent.appendChild(element);
            for(Parameter p: m.getParameters()){
                Element arg = document.createElement("arg");
                arg.setAttribute("id", p.getName());
                arg.setAttribute("type", p.getType().getName());
                element.appendChild(arg);
            }
        }
    }

    protected static void xmlizePresentStudent(PresentStudent presentStudent, Document document, Element root) throws Exception {
        /**
         * надо пробежать по всем методам и переменным объекта
         */
        Element newPresentStudent = document.createElement("PresentStudent");
        newPresentStudent.setAttribute("type", presentStudent.getClass().getTypeName());
        root.appendChild(newPresentStudent);
        printObject(presentStudent, document, newPresentStudent, "");
    }

    public static ArrayList<String> getGenericName(Field field){
        ArrayList<String> result = new ArrayList<String>();
        Type genericFieldType = field.getGenericType();
        if(genericFieldType instanceof ParameterizedType){
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for(Type fieldArgType : fieldArgTypes){
                Class fieldArgClass = (Class) fieldArgType;
                result.add(new String(fieldArgClass.toString()));
            }
        }
        return result;
    }


}
