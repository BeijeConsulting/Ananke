package it.beije.ananke.capitolo5.astrazione;

public class PCTest {
    public static void main(String[] args) {
        Gaming rog = new Gaming("Intercore i7", "Nvidia GeForce" , "Microsoft" , "ssd GB", "call of duty");
        rog.start();
        rog.play();
        rog.setGame("Fifa 2021");

        WorkStation asus = new WorkStation("Intel Core i7", "Intel iris plus", "Microsoft windows 10", "SSD 512GB");
        asus.printSoftware();

        asus.work();
        asus.setSoftware("SolidWorks");
        asus.work();
        asus.setSoftware("Microsoft Excel");
        asus.work();

        asus.printSoftware();
    }
}
