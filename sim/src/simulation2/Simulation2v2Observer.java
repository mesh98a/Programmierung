package simulation2;

import observerpattern.Beobachter;

public class Simulation2v2Observer implements Beobachter {
    private InserterTask inserter;
    private RemoverTask remover;

    public Simulation2v2Observer(InserterTask inserter,RemoverTask remover) {
        this.inserter = inserter;
        this.remover = remover;
    }

    @Override
    public void update() {
    }
}
