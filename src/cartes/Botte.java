package cartes;

public class Botte extends Probleme {
	private Type type;

	public Botte(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return getType().getBotte();
	}

}
