package beije.ananke.animals;

public class TestAnimals {

	public static void main(String[] args) {
	
		Tiger beast = new Tiger();
		Shark fish = new Shark();
		
		System.out.println("About tiger:");
		beast.eatMeat();
		beast.jump();
		
		System.out.println("\nAbout Shark:");
		fish.swimSpeed();
		fish.eatMeat();

	}

}
