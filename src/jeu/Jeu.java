package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> joueurs = new LinkedHashSet<>();
	private Iterator<Joueur> iterateur;
	private static int NBCARTES = 6;

	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		Carte[] cartes = jeu.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		
		listeCartes = GestionCartes.melanger(listeCartes);
		
		sabot = new Sabot(listeCartes.toArray(new Carte[0]));
		
	}

	public Sabot getSabot() {
		return sabot;
	}
	
	public void inscrire(Joueur... inscrits) {
		Collections.addAll(joueurs, inscrits);
	}
	
	public void distribuerCartes() {
		for(int i = 0; i < NBCARTES; i++) {
			for (Joueur joueur : joueurs) {
				joueur.donner(sabot.piocher());
			}
		}
	}
	
	public String jouerTour(Joueur joueur) {
		assert(joueurs.contains(joueur));
		
		StringBuilder stringBuilder = new StringBuilder();
		
		Carte cartePioche = joueur.prendreCarte(sabot);
		stringBuilder.append("Le joueur ").append(joueur).append(" a pioche ").append(cartePioche).append("\n");
		
		stringBuilder.append(joueur.afficherMain());
		
		Coup coup = joueur.choisirCoup(joueurs);
		joueur.retirerDeLaMain(coup.getCarteJouee());
		stringBuilder.append(joueur).append(coup);
		
		if(coup.getJoureurCible()==null) {
			sabot.ajouterCarte(coup.getCarteJouee());
		} else {
			coup.getJoureurCible().deposer(coup.getCarteJouee());
		}
		
		return stringBuilder.toString();
	}

	public Joueur donnerJoueurSuivant() {
	    if (iterateur == null || !iterateur.hasNext()) {
	        iterateur = joueurs.iterator();
	    }
	    return iterateur.next();
	}
	
	public String lancer() {
	    StringBuilder stringBuilder = new StringBuilder();
	    boolean fini = false;
	    while (!fini && !sabot.estVide()) {
	        Joueur joueur = donnerJoueurSuivant();
	        stringBuilder.append(jouerTour(joueur));
	        if (joueur.donnerKmParcourus() >= 1000) {
	        	stringBuilder.append(joueur).append(" a gagne !\n");
	            fini = true;
	        }
	    }
	    return stringBuilder.toString();
	}
}
