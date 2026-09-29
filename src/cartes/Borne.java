package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		super();
		this.km = km;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.append(km).append("KM").toString();
	}

}
