package it.beije.ananke.astrazione;

public interface InterfacciaDiProva extends Play, Work{
public default int effettuaRicerca(String string) {
	return 16;
}
}
