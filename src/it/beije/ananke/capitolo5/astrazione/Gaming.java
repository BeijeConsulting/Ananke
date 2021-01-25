package it.beije.ananke.capitolo5.astrazione;

public class Gaming extends PersonalComputer implements Play {
    private String CPU;
    private String GPU;
    private String IOS;
    private String memory;
    private String game;

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
        this.play();
    }

    public Gaming(String CPU, String GPU, String IOS, String memory, String game) {
        this.CPU = CPU;
        this.GPU = GPU;
        this.IOS = IOS;
        this.memory = memory;
        this.game = game;
    }

    @Override
    public void getCPU() {
        System.out.println("Il tuo computer da gaming monta un " + this.CPU);
    }

    @Override
    public void getGPU() {
        System.out.println("Il tuo computer da gaming monta un " + this.GPU);
    }

    @Override
    public void getIOS() {
        System.out.println("Il tuo computer da gaming monta un " + this.IOS);
    }

    @Override
    public void getMemoryType() {
        System.out.println("Il tuo computer da gaming monta un " + this.memory);
    }

    @Override
    public void play() {
        System.out.println("Stai giocando a: " + this.getGame());
    }
}
