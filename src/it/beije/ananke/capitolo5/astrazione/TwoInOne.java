package it.beije.ananke.capitolo5.astrazione;

public class TwoInOne extends Notebook implements Touchscreen{
    private String CPU;
    private String GPU;
    private String IOS;
    private String memory;
    private String browser;

    public TwoInOne(String CPU, String GPU, String IOS, String memory) {
        this.CPU = CPU;
        this.GPU = GPU;
        this.IOS = IOS;
        this.memory = memory;
        this.browser = "Microsoft Edge";
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowser(){
        return browser;
    }

    @Override
    public void getCPU() {
        System.out.println("Il tuo convertibile monta un: " + this.CPU);
    }

    @Override
    public void getGPU() {
        System.out.println("Il tuo convertibile monta un: " + this.GPU);
    }

    @Override
    public void getIOS() {
        System.out.println("Il tuo convertibile monta un: " + this.IOS);
    }

    @Override
    public void getMemoryType() {
        System.out.println("Il tuo convertibile monta un: " + this.memory);
    }

    @Override
    public void connect() {
        System.out.println("Il tuo convertibile è connesso a: " + getBrowser());
    }
}
