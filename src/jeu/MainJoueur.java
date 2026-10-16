package jeu;


import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
	 private final List<Carte> cartes = new ArrayList<>();
	
	public void prendre(Carte carte) {
		cartes.add(carte);
	}
	
	public void jouer (Carte carte) {
		assert cartes.contains(carte);
		cartes.remove(carte);
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(Carte carte : cartes) {
			stringBuilder.append("-").append(carte).append("\n");
		}
		return stringBuilder.toString();
	}
}
