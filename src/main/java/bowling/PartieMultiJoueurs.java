package bowling;

import java.util.HashMap;
import java.util.Map;

public class PartieMultiJoueurs implements IPartieMultiJoueurs{
	private Map<String, PartieMonoJoueur> lesParties;
	private String[] nomsDesJoueurs;
	private int nbJoueur;
	private int tourJoueurNo;
	
	public PartieMultiJoueurs(){}
	
	private boolean estTerminer(){
		return lesParties.get(nomsDesJoueurs[nbJoueur-1]).estTerminee();
	}

	public String toString(){
		return "Prochain tir: joueur " + nomsDesJoueurs[tourJoueurNo] + ", tour n° " 
			+ lesParties.get(nomsDesJoueurs[tourJoueurNo]).numeroTourCourant() 
			+ ", boule n° " + lesParties.get(nomsDesJoueurs[tourJoueurNo]).numeroProchainLancer();
	}

	public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
		if (nomsDesJoueurs.length == 0) throw new IllegalArgumentException("Il faut au minimum 1 joueur");
		String[] a = nomsDesJoueurs;
		String rep = "";
		for (int i=0 ; 1<a.length ; i++) {
			rep = "Prochain tir : joueur " + nomsDesJoueurs[i] + " tour n° 1, boule n° 1";
		}
		return toString();
	}
	
	public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
		if (estTerminer()) throw  new IllegalStateException("La partie est terminée");
		PartieMonoJoueur partieJoueur = lesParties.get(nomsDesJoueurs[tourJoueurNo]);
		partieJoueur.enregistreLancer(nombreDeQuillesAbattues);
		if (partieJoueur.numeroProchainLancer() == 1 || partieJoueur.estTerminee()) tourJoueurNo = (tourJoueurNo+1)%nbJoueur;
		if (estTerminer()) return "Partie terminée";
		return toString();
	}
	
	public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
		PartieMonoJoueur partieJoueur = lesParties.get(nomDuJoueur);
		if (partieJoueur == null) throw new IllegalArgumentException("Ce joueur ne joue pas à cette partie");
		return partieJoueur.score();
	}
}
