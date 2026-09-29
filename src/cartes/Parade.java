package cartes;

public class Parade extends Bataille {

	private Type type;

	public Parade(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return getType().getParade();
	}

}
