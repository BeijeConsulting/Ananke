package beije.ananke.animals;

public class Eagle extends Birds implements CanFly, Carnivorous {

	@Override
	public void eatMeat() {
		
		System.out.println("I love rats!");
	}

	@Override
	public int speed() {
		// TODO Auto-generated method stub
		return 105;
	}

	@Override
	public void pecks() {
		
		System.out.println("A beautiful beak that catches preys");

	}

	@Override
	public void whistles() {
		
		System.out.println("One of scariest whistles you can hear");

	}

	@Override
	public String getName() {
		
		return "Eagle";
	}

}
