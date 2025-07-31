package io;

import domainpackage.dto.AutomatDTO;
import org.junit.jupiter.api.Test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutomatXMLSerializerTest {

    @Test
    void save() {
        XMLEncoder encoder = mock(XMLEncoder.class);
        AutomatDTO dto = new AutomatDTO();
        try {
            AutomatXMLSerializer.save(encoder, dto);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        verify(encoder).writeObject(dto);

    }
    @Test
    void load() {
        XMLDecoder decoder = mock(XMLDecoder.class);
        try {
            AutomatDTO automat = AutomatXMLSerializer.load(decoder);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        verify(decoder).readObject();
    }

}