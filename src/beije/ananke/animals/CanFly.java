package beije.ananke.animals;

public interface CanFly {

	int speed();
	
	public default boolean hasFeathers()
	{
		return true;
	}
}
