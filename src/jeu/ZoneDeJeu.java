package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
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
	private Set<Botte> pileBotte = new HashSet<>();
	
	// début TP3
	public int donnerLimitationVitesse() {
		//TP4
		if(estPrioritaire() || pileLimite.isEmpty()) {
			return 200; 
		}

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
			// TP4
		} else if (c instanceof Botte botte) {
			pileBotte.add(botte);
		}
	}
	
	public boolean peutAvancer() {
		// TP4
		if (pileBataille.isEmpty() )
			return estPrioritaire();
		
		Bataille lastBataille = pileBataille.getLast();
		if (lastBataille.equals(Cartes.FEU_VERT)) 
			return true;
		if (!estPrioritaire()){
			return false;
		} else {
			return lastBataille instanceof Parade ||
					lastBataille.equals(Cartes.FEU_ROUGE) ||
					avoirBotte(lastBataille.getType()); 
		}
	}
	
	private boolean estDepotFeuVertAutorise() {
		// TP4
	    if (estPrioritaire()) return false;
	    if (pileBataille.isEmpty()) return true;
	    Bataille sommet = pileBataille.getLast();
	    return sommet.equals(Cartes.FEU_ROUGE)
	        || (sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT))
	        || (sommet instanceof Attaque && avoirBotte(sommet.getType()));
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && 
				borne.getKm()<=donnerLimitationVitesse() &&
				donnerKmParcourus()<1000 ;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		// TP4
		if(estPrioritaire()) return false;
		if(limite instanceof DebutLimite) {
			return pileLimite.isEmpty() || pileLimite.getLast() instanceof FinLimite;
		} else {
			return !pileLimite.isEmpty() && pileLimite.getLast() instanceof DebutLimite;
		}
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) { 
		// TP4
		if (avoirBotte(bataille.getType())) return false; 
		if (bataille instanceof Attaque) {
			return peutAvancer();
		} else {
			if (bataille.equals(Cartes.FEU_VERT)) {
				return estDepotFeuVertAutorise();
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
		} else if (carte instanceof Botte ){
			return true; 
		} else {
			return estDepotFeuVertAutorise();
		}
	}
	// fin TP3
	
	// TP4
	public boolean estPrioritaire() {
		return pileBotte.contains(Cartes.PRIORITAIRE);
	}
	
	private boolean avoirBotte(Type type) {
		for (Botte botte : pileBotte) {
			if (botte.getType() == type) return true;
		}
		return false;
	}
	
	public Set<Botte> getPileBotte(){
		return pileBotte;
	}
	
	public Bataille getSommetBataille() {
	    return pileBataille.isEmpty() ? null : pileBataille.getLast();
	}
}
