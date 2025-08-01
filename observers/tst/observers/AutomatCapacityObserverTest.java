package observers;

import domainpackage.Automat;
import kuchen.Kuchenprodukt;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


class AutomatCapacityObserverTest {

    @Test void properOutput() {
        PrintStream originalOut=System.out;
        try {
            PrintStream out= Mockito.mock(PrintStream.class);
            System.setOut(out);
            Automat automat= Mockito.mock(Automat.class);
            List<Kuchenprodukt> cakes = new ArrayList<>();
            Kuchenprodukt cake1= Mockito.mock(Kuchenprodukt.class);
            cakes.add(cake1);
            when(automat.getListCake()).thenReturn(cakes);
            Mockito.when(automat.getCapacity()).thenReturn(1);

            AutomatCapacityObserver observer = new AutomatCapacityObserver(automat);

            observer.update();

            Mockito.verify(out).println("Kapazität über 90%");
        }finally {
            System.setOut(originalOut);
        }
    }

    @Test void properStateManagementOnlyChanges() {
        PrintStream originalOut=System.out;
        try {
            PrintStream out= Mockito.mock(PrintStream.class);
            System.setOut(out);

            Automat automat= Mockito.mock(Automat.class);
            List<Kuchenprodukt> cakes = new ArrayList<>();
            Kuchenprodukt cake1= Mockito.mock(Kuchenprodukt.class);
            cakes.add(cake1);
            when(automat.getListCake()).thenReturn(cakes);
            Mockito.when(automat.getCapacity()).thenReturn(2);

            AutomatCapacityObserver observer = new AutomatCapacityObserver(automat);

            observer.update();

            Mockito.verifyNoInteractions(out);
        }finally {
            System.setOut(originalOut);
        }
    }
    @Test void kapazitaetZero() {
        PrintStream originalOut=System.out;
        try {
            PrintStream out= Mockito.mock(PrintStream.class);
            System.setOut(out);

            Automat automat= Mockito.mock(Automat.class);

            Mockito.when(automat.getCapacity()).thenReturn(0);

            AutomatCapacityObserver observer = new AutomatCapacityObserver(automat);

            observer.update();

            Mockito.verifyNoInteractions(out);
        }finally {
            System.setOut(originalOut);
        }
    }



}