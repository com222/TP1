package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;

	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		Carte[] cartes = jeu.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		
		listeCartes = GestionCartes.melanger(listeCartes);
		
		sabot = new Sabot((Carte[]) listeCartes.toArray());
		
	}

	public Sabot getSabot() {
		return sabot;
	}
	
	
}
