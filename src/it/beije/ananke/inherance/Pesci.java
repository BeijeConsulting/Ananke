package it.beije.ananke.inherance;

public abstract class Pesci extends Cordati implements DespositoUova{

    abstract void getSpecie();

    public void getClasse(){
        System.out.println("Faccio parte della classe dei pesci");
    }

}
