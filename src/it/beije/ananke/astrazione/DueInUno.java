package it.beije.ananke.astrazione;

public class DueInUno extends Notebook implements Touchscreen{

	private String cpu;
	private String gpu;
	private String ios;
	private String memory;
	private String browser;
	
	public DueInUno(String cpu, String gpu, String ios, String memory) {
		this.cpu=cpu;
		this.gpu = gpu;
		this.ios= ios;
		this.memory = memory;
		this.browser= "Microsoft Edge";
	}

	public void setBrowser(String browser) {	
		this.browser=browser;
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

}

