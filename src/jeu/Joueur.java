package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur mainJoueur;
	
	public Joueur(String nom) {
		this.nom = nom;
		zoneDeJeu = new ZoneDeJeu();
		mainJoueur = new MainJoueur();
	}
	
	public void donner(Carte carte) {
		mainJoueur.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if(sabot.estVide()) 
			return null; 
		Carte carte = sabot.piocher();			
		mainJoueur.prendre(carte);
		return carte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public void deposer(Carte c) {
		zoneDeJeu.deposer(c);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur joueur) {
			return toString().equals(joueur.toString());
		}
		return false;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.append(nom).toString();
	}
	
	// TP4

	@Override
	public int hashCode() { 
		return nom.hashCode(); 
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coupsValides = new HashSet<>();
		List<Carte> cartes = mainJoueur.donnerListeCartes();
		 
			for (Joueur joueur : participants) {
				for (Carte carte : cartes) {
					Coup coup = new Coup(this, carte, joueur);
					if(coup.estValide()) {
						coupsValides.add(coup);
					}
				}
			}
		return coupsValides; 
	}
	
	public  Set<Coup> coupsDefausse(){
		Set<Coup> coupsDefausse = new HashSet<>();
		
		for (Carte carte : mainJoueur.donnerListeCartes()) {
		    coupsDefausse.add(new Coup(this, carte, null));
		}
		return coupsDefausse; 
	}
	
	public void retirerDeLaMain(Carte carte) {
		mainJoueur.jouer(carte);
	}
	
	private Coup choisirAleatoire(Set<Coup> coups) {
	    int index = new Random().nextInt(coups.size());
	    Iterator<Coup> it = coups.iterator();
	    for (int i = 0; i < index; i++) it.next();
	    return it.next();
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupsParticipants = coupsPossibles(participants);
		if(! coupsParticipants.isEmpty()) {
			return choisirAleatoire(coupsParticipants);
		} else {
			return choisirAleatoire(coupsDefausse());
		}
	}
	
	public String afficherEtatJoueur() {
		return "bottes : " + zoneDeJeu.getPileBotte().toString()
		+ ", limitation : " + (zoneDeJeu.donnerLimitationVitesse()<200)
		+ ", sommet : " + zoneDeJeu.getSommetBataille()
		+ ", main : " + mainJoueur;
	}
	
	public String afficherMain() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Il a dans sa main : [");
		
		List<Carte> cartes = mainJoueur.donnerListeCartes();
		for(Carte carte : cartes) {
			stringBuilder.append(carte).append(", ");
		}
		
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append("] \n");
		return stringBuilder.toString();
	}
}
