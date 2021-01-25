package it.beije.ananke.capitolo5.astrazione;

public interface Prova extends Play, Work{
    public abstract void start();

    public abstract void effettuaRicerca(String string);
}
