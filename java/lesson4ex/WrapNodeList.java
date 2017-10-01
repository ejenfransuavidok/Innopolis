package lesson4ex;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class WrapNodeList<Type> implements Iterable<Type> {

    private NodeList nodeList;
    private int currentSize;
    private Object [] arrayList;

    WrapNodeList(NodeList nodeList){
        this.nodeList = nodeList;
        this.currentSize = nodeList.getLength();
        this.arrayList = new Object [nodeList.getLength()];
        for(int i=0; i<nodeList.getLength(); i++){
            this.arrayList[i] = (Type)nodeList.item(i);
        }
    }

    @Override
    public Iterator iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public Type next() {
                return (Type)arrayList[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
