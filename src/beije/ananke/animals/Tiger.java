package beije.ananke.animals;


//Concrete class
public class Tiger extends BigCat implements Carnivorous, CanJump {

	@Override
	public void jump() {
		
		System.out.println("This big cat can jump " + jumpHeight() + " meters in the air!");

	}

	@Override
	public int jumpHeight() {
		
		return 4;
	}

	@Override
	public void eatMeat() {
		
		System.out.println("She's eating the hunted meat, stay away!");

	}

	@Override
	public int pregnancy() {
		
		return 106;
	}

	@Override
	public String getName() {
		
		return "Tiger";
	}

	@Override
	public void roar() {

		System.out.println("ROOOOOAAAAAAAAAAAAR!!!");
		
	}

	@Override
	public void scratch() {
		
		System.out.println("She uses her claws to scratch you, be careful!");
		
	}

}
