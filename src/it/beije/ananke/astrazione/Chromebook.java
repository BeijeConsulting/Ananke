package it.beije.ananke.astrazione;

public class Chromebook extends Notebook implements InterfacciaDiProva{

	private String cpu;
	private String gpu;
	private String ios;
	private String memory;
	private String browser;

	public Chromebook(String cpu, String gpu, String ios, String memory) {
		this.cpu=cpu;
		this.gpu = gpu;
		this.ios= ios;
		this.memory = memory;
		this.browser= "Google chrome";
	}
	
	public String getBrowser() {	
		return this.browser;
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

	@Override
	public void connettiti() {
		System.out.println("Ti stai connettendo al seguente browser " + this.getBrowser());
	}

//	@Override
//	public int effettuaRicerca(String string) {
//		return 1;
//	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void work() {
		// TODO Auto-generated method stub
		
	}



}