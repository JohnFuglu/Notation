package CoreStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**Classe de devoir
 * avec consigne, date et remaques d'�valuation.
 * un hashmap sert � stocker la grille d'eval
 * les competences travaill�es sont stock�es dans une arraylist
 * */

public class Devoir implements Serializable {
    private String nom;
    private String consigne;
    private Date dateDevoir;
    private String remarques;
    private String[] critereStrings=new String[4];
    private HashMap<String, Smileys> grilleEval=new HashMap<>();
    ArrayList<Competence>competencesEvalues=new ArrayList<>();

    public Devoir(String nom,String consigne, Date dateDevoir) {
        this.nom=nom;
        this.consigne = consigne;
        this.dateDevoir = dateDevoir;
    }

    /**Cr�e une grille d'�val*/
    public void creeGrilleEval() {
        for(int i =0;i<4;i++)
            grilleEval.put(critereStrings[i], Smileys.VIDE);
    }
    /**Evalue la grille*/
    public void evalueGrille(Smileys s,String key) {
        grilleEval.put(key, s);
    }
    /**Commente le devoir de l'�l�ve
     * @param remarques sur le travail*/
    public void commenterDevoir(String remarques) {this.remarques=remarques;}
    /**Ajoute une competence au devoir
     * @param comp du cycle*/
    public void ajouteCompetenceauDevoir(Competence comp) {
        competencesEvalues.add(comp);
    }
    /**Rempli les crit�res d'�valuation
     * @param string d'un crit�re*/
    public void rempliCriteres(String texte,int i) {
        critereStrings[i]=texte;
    }


    public String getNom() {
        return nom;
    }
}