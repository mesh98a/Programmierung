import domainpackage.AbstractCake;
import domainpackage.Automat;
import domainpackage.HerstellerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import simulation2.InserterTask;
import verwaltung.Hersteller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Random;

public class InserterTaskTest {

    @Test
    public void test() {
        Automat automat = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Bäcker");
        automat.insertHersteller("Bäcker");
        Random random = Mockito.mock(Random.class);

        AbstractCake cake1 = Mockito.mock(AbstractCake.class);
        Mockito.when(cake1.getHersteller()).thenReturn(hersteller);

        AbstractCake cake2 = Mockito.mock(AbstractCake.class);
        Mockito.when(cake2.getHersteller()).thenReturn(hersteller);

        InserterTask inserterTask = Mockito.mock(InserterTask.class);
        Mockito.when(inserterTask.extracted(hersteller, random)).thenReturn(true).thenReturn(false);

        List<AbstractCake> kuchenListe = automat.displayListCake();

        assertEquals(1,kuchenListe.size());


    }

}
