package testsFonctionnels;

import cartes.Borne;
import cartes.Botte;
import cartes.DebutLimite;
import cartes.JeuDeCartes;
import cartes.Type;
import jeu.Sabot;

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
		for (int i = 0; i < 106; i++) {
			sabot.piocher();
//			System.out.println(sabot.piocher());
		}
		System.out.println(sabot.piocher());
		
	}

}
