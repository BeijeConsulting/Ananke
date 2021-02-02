package it.beije.ananke.capitolo5.astrazione;

public class Chromebook extends Notebook implements Prova{
    private String CPU;
    private String GPU;
    private String IOS;
    private String memory;
    private String browser = "Google Chrome";

    public Chromebook (String CPU, String GPU, String IOS, String memory) {
        this.CPU = CPU;
        this.GPU = GPU;
        this.IOS = IOS;
        this.memory = memory;
    }

    public String getBrowser(){
        return browser;
    }

    @Override
    public void getCPU() {
        System.out.println("Il tuo chromebook monta un: " + this.CPU);
    }

    @Override
    public void getGPU() {
        System.out.println("Il tuo chromebook monta un: " + this.GPU);
    }

    @Override
    public void getIOS() {
        System.out.println("Il tuo chromebook monta un: " + this.IOS);
    }

    @Override
    public void getMemoryType() {
        System.out.println("Il tuo chromebook monta un: " + this.memory);
    }

    @Override
    public void connect() {
        System.out.println("Il tuo chromebook è connesso a: " + getBrowser());
    }

    @Override
    public void play() {
        System.out.println("Non sono adatto per giocare");
    }

    @Override
    public void work() {
        System.out.println("Non sono adatto per lavorare");
    }

    @Override
    public void start() {
        System.out.println("Avvio non riuscito");
    }


}
