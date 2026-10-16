package jeu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimite = new LinkedList<>();
	private List<Bataille> pileBataille = new LinkedList<>();
	private List<Borne> pileBorne = new ArrayList<Borne>();
	
	public int donnerLimitationVitesse() {
		if (pileLimite.isEmpty())
			return 200;
		Limite limite = pileLimite.getLast();
		if (limite instanceof FinLimite) {
			return 200;
		} else {
			return 50;
		}
	}
	
	public int donnerKmParcourus() {
		int kmParcourus = 0;
		for(Borne borne : pileBorne) {
			kmParcourus += borne.getKm();
		}
		return kmParcourus;
	}
	
	public void deposer(Carte c) {
		if (c instanceof Borne borne) {
			pileBorne.add(borne);
		} else if (c instanceof Limite limite) {
			pileLimite.add(limite);
		} else if (c instanceof Bataille bataille) {
			pileBataille.add(bataille);
		}
	}
	
	public boolean peutAvancer() {
		return !pileBataille.isEmpty() && 
				pileBataille.getLast().equals(Cartes.FEU_VERT); 
	}
	
	private boolean estDepotFeuVertAutorise() {
		return pileBataille.isEmpty() || 
				pileBataille.getLast().equals(Cartes.FEU_ROUGE) ||
				!pileBataille.getLast().equals(Cartes.FEU_VERT);
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && 
				borne.getKm()<=donnerLimitationVitesse() &&
				donnerKmParcourus()<1000 ;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if(limite instanceof DebutLimite) {
			return pileLimite.isEmpty() || pileLimite.getLast() instanceof FinLimite;
		} else {
			return pileLimite.getLast() instanceof DebutLimite;
		}
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			return peutAvancer();
		} else {
			if (bataille.equals(Cartes.FEU_VERT)) {
				return pileBataille.isEmpty() ||
						pileBataille.getLast().equals(Cartes.FEU_ROUGE) ||
						!pileBataille.getLast().equals(Cartes.FEU_VERT);
			} else {
				return !pileBataille.isEmpty() && 
						pileBataille.getLast().getType()==bataille.getType();
			}
		}
		
	}
	
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		} else if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		} else if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		} else {
			return estDepotFeuVertAutorise();
		}
	}
}
