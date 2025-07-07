package persistence;

import domainpackage.*;
import kuchen.Allergen;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import verwaltung.Hersteller;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AutomatSerializerTest {

    @Test
    void serialize() {
        ObjectOutput oos = mock(ObjectOutput.class);
        Automat automat = new Automat(3);
        try {
            AutomatSerializer.serialize(oos, automat);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        try {
            verify(oos).writeObject(automat);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void deserialize() {
        ObjectInput objectInput = mock(ObjectInput.class);
        try {
            Automat automat = AutomatSerializer.deserialize(objectInput);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        try {
            verify(objectInput).readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}