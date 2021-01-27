package beije.ananke.animals;

public class TestAnimals {

	public static void main(String[] args) {
	
		Tiger beast = new Tiger();
		Shark fish = new Shark();
		Eagle bird = new Eagle();
		
		
		System.out.println("About tiger:");
		beast.eatMeat();
		beast.jump();
		
		System.out.println("\nAbout Shark:");
		fish.swimSpeed();
		fish.eatMeat();

		
		System.out.println("\nAbout Eagle:");
		System.out.println("Has feathers: " + bird.hasFeathers());
		System.out.println("Average fly speed: " + bird.speed() + " Km/h");
	}

}
