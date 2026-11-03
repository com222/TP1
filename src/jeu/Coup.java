package jeu;

import java.util.Objects;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup {
	private final Joueur joueurCourant;
	private final Carte carteJouee;
	private final Joueur joueurCible;
	
	public Coup(Joueur joueurCourant, Carte carteJouée, Joueur jourCible) {
		super();
		this.joueurCourant = joueurCourant;
		this.carteJouee = carteJouée;
		this.joueurCible = jourCible;
	}
	// TP4

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Carte getCarteJouee() {
		return carteJouee;
	}

	public Joueur getJoureurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
	    if (joueurCible == null) return false;
	    if (carteJouee instanceof Attaque || carteJouee instanceof Limite) {
	        return !joueurCible.equals(joueurCourant)
	            && joueurCible.estDepotAutorise(carteJouee);
	    }
	    return joueurCible.equals(joueurCourant)
	        && joueurCible.estDepotAutorise(carteJouee);
	}
	
	@Override
	public String toString() {
	    if (joueurCible == null)
	        return " defausse la carte " + carteJouee + "\n";
	    if (joueurCible.equals(joueurCourant))
	        return " depose la carte " + carteJouee + " dans sa zone de jeu" + "\n";
	    return " depose la carte " + carteJouee + " dans la zone de jeu de " + joueurCible + "\n";
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof Coup coup)) return false;
	    return Objects.equals(joueurCourant, coup.joueurCourant)
	        && Objects.equals(carteJouee, coup.carteJouee)
	        && Objects.equals(joueurCible, coup.joueurCible);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(joueurCourant, carteJouee, joueurCible);
	}
}
