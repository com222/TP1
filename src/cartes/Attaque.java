package cartes;

public class Attaque extends Bataille {
	private Type type;

	public Attaque(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return getType().getAttaque();
	}

}
