package it.beije.ananke.astrazione;

public class Gaming extends Pc implements Play{
	private String cpu;
	private String gpu;
	private String ios;
	private String memory;
	private String game;

	public Gaming(String cpu, String gpu, String ios, String memory, String game) {
		this.cpu=cpu;
		this.gpu = gpu;
		this.ios= ios;
		this.memory = memory;
		this.game= game;
	}

	public void setGame(String game) {	
		this.game=game;
		play();
	}

	public String getGame() {	
		return this.game;
	}

	@Override
	public void play() {	
		System.out.println("Mando in esecuzione il seguente gioco: " + this.getGame());
	}

	@Override
	public void getCpu() {
		System.out.println("Il tuo computer da gaming monta " + cpu);
	}

	@Override
	public void getGpu() {
		System.out.println("Il tuo computer da gaming ha come gpu " + gpu);

	}

	@Override
	public void getIos() {
		System.out.println("Il tuo gaming pc ha come ios: " + ios);

	}

	@Override
	public void getMemoryType() {
		System.out.println("Il tipo di memoria del tuo computer da gaming è " + memory);

	}

}
