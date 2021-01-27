package it.beije.ananke.astrazione;

public class Pctest {

	public static void main(String[] args) {
Gaming lt = new Gaming("", "", "", "", "cod bo2");
lt.play();
lt.setGame("fifa21");
//Note
Gaming rog = new Gaming("Intercore i7", "Nvidia GeForce" , "Microsoft" , "ssd GB", "call of duty");
rog.start();
rog.play();
rog.setGame("Fifa 2021");

Workstation asus = new Workstation("Intel Core i7", "Intel iris plus", "Microsoft windows 10", "SSD 512GB", "Word");
asus.printSoftware();
asus.work();
asus.setSoftware("SolidWorks");
asus.work();
asus.setSoftware("Microsoft Excel");
asus.work();
asus.printSoftware();
Chromebook ch = new Chromebook("cc", "jj", "fhcn", "rcfg");
System.out.println(ch.effettuaRicerca("s"));

	}

}
