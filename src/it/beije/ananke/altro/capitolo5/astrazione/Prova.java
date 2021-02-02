package it.beije.ananke.altro.capitolo5.astrazione;

public interface Prova extends Play, Work{
    public abstract void start();

    public abstract int effettuaRicerca(String string);
}
