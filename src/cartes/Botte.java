package cartes;

import java.util.Objects;

public class Botte extends Probleme {
	private Type type;

	public Botte(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return getType().getBotte();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

}
