package io;

import domainpackage.dto.AutomatDTO;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class AutomatXMLSerializer {

    public static void save(String filename, AutomatDTO automat) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)))) {
            save(encoder,automat);
        } catch (Exception e) {
            System.err.println("Fehler: "+e.getMessage());
        }
    }
    public static void save(XMLEncoder encoder ,AutomatDTO automat) throws IOException  {
        encoder.writeObject(automat);
    }

    public static AutomatDTO load(String filename) {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)))) {
            return (AutomatDTO) decoder.readObject();
        } catch (Exception e) {
            System.err.println("Fehler: "+e.getMessage());
        }
        return null;
    }
    public static AutomatDTO load(XMLDecoder decoder) throws IOException,ClassNotFoundException  {
        return (AutomatDTO) decoder.readObject();
    }

}