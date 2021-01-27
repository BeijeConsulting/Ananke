package it.beije.ananke.astrazione;

import java.util.ArrayList;

import javafx.scene.AccessibleAction;

public class Workstation extends Pc implements Work{
	private String cpu;
	private String gpu;
	private String ios;
	private String memory;
	private String game;
	private String newSoftware;
	private ArrayList<String> softwares = new ArrayList<String>();

	public Workstation(String cpu, String gpu, String ios, String memory,String newSoftware) {
		this.cpu=cpu;
		this.gpu = gpu;
		this.ios= ios;
		this.memory = memory;
		this.softwares.add("Microsoft office Word");
		this.softwares.add("Eclipse");
		this.softwares.add("Photoshop");
		this.softwares.add("Catia");
		this.newSoftware=newSoftware;
	}

	public void setSoftware(String nomeSoftware) {
		if(this.softwares.contains(nomeSoftware)) {
			this.newSoftware=nomeSoftware;
			work();
		}
		else {
			this.softwares.add(nomeSoftware);
			this.newSoftware=nomeSoftware;
			work();
		}
	}

	public void printSoftware(){
		//		for(int i=0;i<softwares.size();i++)
		//			System.out.println(softwares.get(i));
		for (String software : softwares) {
			System.out.println(software);
		}
	}

	@Override
	public void getCpu() {
		System.out.println("Il tuo computer monta " + cpu);
	}

	@Override
	public void getGpu() {
		System.out.println("Il tuo computer ha come gpu " + gpu);

	}

	@Override
	public void getIos() {
		System.out.println("Il tuo pc ha come ios: " + ios);

	}

	@Override
	public void getMemoryType() {
		System.out.println("Il tipo di memoria del tuo computer è " + memory);

	}

	@Override
	public void work() {
		System.out.println("Da adesso stai utilizzando il seguente programma: " + this.newSoftware);
	}

}

