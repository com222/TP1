package testsFonctionnels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.JeuDeCartes;
import cartes.Type;
import jeu.Jeu;
import jeu.Sabot;
import utils.GestionCartes;

public class testPerso {
	public static void main(String[] args) {
		Botte botte = new Botte(Type.ACCIDENT);
		Borne borne = new Borne(25);
		DebutLimite debutlimite = new DebutLimite();
		//System.out.println(botte);
		//System.out.println(borne);
		//System.out.println(debutlimite);
		
		JeuDeCartes cartes = new JeuDeCartes();
//		System.out.println(cartes.affichageJeuCartes());
//		System.out.println(cartes.donnerCartes().length);
		
		Sabot sabot = new Sabot(cartes.donnerCartes());
//		System.out.println(sabot.estVide());
//		sabot.ajouterCarte(borne);
//		for (int i = 0; i < 106; i++) {
//			sabot.piocher();
//			System.out.println(sabot.piocher());
//		}
//		System.out.println(sabot.piocher());
		
		Botte botte2 = botte; 
		Botte botte3 = new Botte(Type.ESSENCE);
//		System.out.println(botte.equals(borne));
//		System.out.println(botte.equals(botte2));
//		System.out.println(botte.equals(botte3));
		
		System.out.println(cartes.checkCount());
		
		Jeu jeu = new Jeu(); 
		
		jeu.getSabot(); 
		System.out.println();
	}

}
