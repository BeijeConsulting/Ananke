package it.beije.ananke.inherance;

public abstract class Rettili extends Cordati implements DespositoUova {

    abstract void getSpecie();

    public void getClasse(){
        System.out.println("Faccio parte della classe dei rettili");
    }

}
