package it.beije.ananke.astrazione;

public abstract class Pc implements Spegnimento, Avvio {
	public abstract void getCpu();
	public abstract void getGpu();
	public abstract void getIos();
	public abstract void getMemoryType();
}