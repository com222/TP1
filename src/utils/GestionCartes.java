package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	public static <T> T extraire(List<T> cartes){
		 Random random = new Random();
	     int indexAleatoire = random.nextInt(cartes.size());
	     
	     return cartes.remove(indexAleatoire);
	}
	
	public static <T> T extraire2(List<T> cartes){
		 Random random = new Random();
	     int indexAleatoire = random.nextInt(cartes.size());
	     
	     ListIterator<T> iterateur = cartes.listIterator(indexAleatoire);
	     T carte = iterateur.next();
	     iterateur.remove();	     
	     
	     return carte;
	}

	
	public static <T> List<T> melanger(List<T> cartesInit) {
		List<T> cartesNew = new ArrayList<T>();
		
		while(!cartesInit.isEmpty()) {
			cartesNew.add(extraire(cartesInit));
		}
		
		return cartesNew;
	}
	
	public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2) {
		boolean verif = true;
		
		for(Object carte : liste1) {
			verif = verif && Collections.frequency(liste1, carte) == Collections.frequency(liste2, carte);
		}
		
		return verif;
	}
	
	public static <T> List<T> rassember(List<T> cartesInit){
		List<T> cartesNew = new ArrayList<T>();
		
		for(T carte : cartesInit) {
			if(Collections.frequency(cartesNew, carte)==0) {
				for(int i = 0; i < Collections.frequency(cartesInit, carte); i++) {
					cartesNew.add(carte);
				}
			}
		}
		
		return cartesNew;
	}
	
	public static <T> boolean verifierRassemblement(List <T> cartes) {
		ListIterator<T> iterateur = cartes.listIterator();
		
		while(iterateur.hasNext()) {
			T carte1 = iterateur.next();
			T carte2 = iterateur.next();
			if (!carte1.equals(carte2)) {
				ListIterator<T> iterateur2 = cartes.listIterator(iterateur.nextIndex());
				while(iterateur2.hasNext()) {
					if (carte1.equals(iterateur2.next())) {
						return false;
					}
				}
			}		
		}
		
		return true; 
	}
}
