package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private Carte[] cartes;
	private int nbCartes;
	private int nombreOperations = 0;
	
	
	public Sabot(Carte[] cartes) {
		super();
		this.cartes = cartes;
		nbCartes = cartes.length;
	}
	
	
	public boolean estVide() {
		return nbCartes == 0; 
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes >= cartes.length) {
			throw new IllegalStateException("Sabot plein");
		}
		cartes[nbCartes] = carte;
		nbCartes++;
		nombreOperations++;
	}
	
	public Carte piocher() {
		if(estVide()) {
			throw new IllegalStateException("Sabot vide");
		}
		
		Iterator<Carte> iter = iterator();
		Carte carte =  iter.next();
		iter.remove();
		return carte;
	}


	@Override
	public Iterator<Carte> iterator() {
		return new SabotIterateur();
	}
	
	private class SabotIterateur implements Iterator<Carte> {
		private int index = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;
		
		@Override
        public boolean hasNext() {
            return index < nbCartes;
        }

        @Override
        public Carte next() {
        	verificationConcurrence();
            if (!hasNext()) {
                throw new NoSuchElementException("Plus de cartes disponibles");
            }
            Carte carte = cartes[index];
            index++;
            nextEffectue = true;
            return carte;
        }

        @Override
        public void remove() {
            verificationConcurrence();
            if (nbCartes<1 || !nextEffectue) {
                throw new IllegalStateException("Aucune carte a supprimer");
            }
            
            for(int i = index -1; i < nbCartes - 1; i++) {
            	cartes[i] = cartes[i+1];
            }
            cartes[nbCartes-1] = null;
            nextEffectue=false;
            index--;
            nbCartes--;
            nombreOperations++;
            nombreOperationsReference++;
        }
        
        private void verificationConcurrence() {
        	if(nombreOperations != nombreOperationsReference) {
        		throw new ConcurrentModificationException("Modification concurrente");
        	}
        }


	}

}
