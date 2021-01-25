package beije.ananke.animals;

public class Shark extends Fishes implements Carnivorous {

	@Override
	public void swim() {
		
		System.out.println("Swimming chill");

	}

	@Override
	public void swimSpeed() {
		
		System.out.println("The average speed of sharks is 50/60 Km/h");

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Shark";
	}

	@Override
	public void eatMeat() {

		System.out.println("Eating some fishes");
		
	}

}
