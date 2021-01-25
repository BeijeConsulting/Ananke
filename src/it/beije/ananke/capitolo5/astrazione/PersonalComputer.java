package it.beije.ananke.capitolo5.astrazione;

public abstract class PersonalComputer implements Start, ShutDown {

    public abstract void getCPU();

    public abstract void getGPU();

    public abstract void getIOS();

    public abstract void getMemoryType();
}
