package lesson4;

import java.io.*;

/**
 * Created by vidok on 21.09.17.
 */
public class MySerializationAction {

    static public ObjectInputStream ois;

    static Journal readJournal(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Journal journal = (Journal)ois.readObject();
        return journal;
    }

    static Journal readJournal(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName)
        );
        return (Journal)ois.readObject();
    }

    static Group readGroup(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName)
        );
        return (Group)ois.readObject();
    }

    static void serializeGroup(Group group) throws IOException{
        File file = new File("group.txt");
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file)
        );
        oos.writeObject(group);
    }

    static void serializeJournal(Journal journal) throws IOException{
        File file = new File("journalgroup.txt");
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file)
        );
        oos.writeObject(journal);
    }

}
