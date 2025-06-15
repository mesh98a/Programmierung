package listeners.automat;

import domainpackage.AbstractCake;
import domainpackage.Automat;
import domainpackage.HerstellerImpl;
import domainpackage.KuchenTyp;
import domainpackage.CakeFactory;
import eventsimpl.automatevent.InsertCakeEvent;
import eventsystem.automatsystem.AutomatEventListener;
import verwaltung.Hersteller;

public class InsertCakeListener implements AutomatEventListener<InsertCakeEvent> {
    private final Automat automat;
    private AbstractCake cake;
    private CakeFactory cakeFactory;

    public InsertCakeListener(Automat automat) {
        this.automat = automat;
    }


    @Override
    public void onAutomatEvent(InsertCakeEvent event) {
        Hersteller hersteller = new HerstellerImpl(event.getHerstellerName());
        AbstractCake cake = null;
        try {
            KuchenTyp typ  = KuchenTyp.valueOf(event.getKuchentyp().toUpperCase());
            cake = CakeFactory.createCake(typ,
                    hersteller,
                    event.getPreis(),
                    event.getNaehrwert(),
                    event.getHaltbarkeit(),
                    event.getAllergene(),
                    event.getKuchensorten()
            );
        } catch (Exception e) {
            System.out.println("Ungültiger Kuchentyp: " + event.getKuchentyp());
        }

        boolean result = this.automat.insertCake(cake);
        if (result) {
            System.out.println("Kuchen eingefügt");
        } else {
            System.out.println("Kuchen nicht eingefügt");
        }
    }
}
