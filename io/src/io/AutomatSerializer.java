package io;

import domainpackage.Automat;

import java.io.*;

public class AutomatSerializer implements Serializable {
    static final long serialVersionUID = 1L;

    public static void serialize(String filename, Automat automat) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            serialize(oos, automat);
        } catch (Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }

    public static void serialize(ObjectOutput objectOutput, Automat automat) throws IOException {
        objectOutput.writeObject(automat);
    }

    public static Automat deserialize(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return deserialize(ois);
        } catch (Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
        return null;
    }

    public static Automat deserialize(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        return (Automat) objectInput.readObject();
    }

}
