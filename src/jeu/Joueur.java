package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur mainJoueur;
	
	public Joueur(String nom) {
		super();
		this.nom = nom;
		zoneDeJeu = new ZoneDeJeu();
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
	
	public boolean estDepotAutourise(Carte carte) {
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
	

}
