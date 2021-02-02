package it.beije.ananke.altro.capitolo5.astrazione;

import java.util.*;

public class WorkStation extends PersonalComputer implements Work {
    private String CPU;
    private String GPU;
    private String IOS;
    private String memory;
    private ArrayList<String> softwares = new ArrayList<>();
    private String actualSoftware;

    public WorkStation(String CPU, String GPU, String IOS, String memory) {
        this.CPU = CPU;
        this.GPU = GPU;
        this.IOS = IOS;
        this.memory = memory;
        this.softwares.add("Microsoft office Word");
        this.softwares.add("Eclipse");
        this.softwares.add("Photoshop");
        this.softwares.add("Catia");
        this.actualSoftware = softwares.get(0);
    }

    public void printSoftware() {
        softwares.stream()
                .sorted()
                .forEach(s -> System.out.println(s));
    }

    public void setSoftware(String newSoftware) {
        if(!softwares.contains(newSoftware)) {
            this.softwares.add(newSoftware);
        }
        actualSoftware = newSoftware;
    }

    @Override
    public void getCPU() {
        System.out.println("La tua workstation monta un: " + this.CPU);
    }

    @Override
    public void getGPU() {
        System.out.println("La tua workstation monta un: " + this.CPU);
    }

    @Override
    public void getIOS() {
        System.out.println("La tua workstation monta un: " + this.CPU);
    }

    @Override
    public void getMemoryType() {
        System.out.println("La tua workstation monta un: " + this.CPU);
    }

    @Override
    public void work() {
        System.out.println("Adesso stai lavorando con: " + actualSoftware);
    }
}
