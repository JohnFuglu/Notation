package CoreStructure;

import android.text.Editable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**Classe de devoir
 * avec consigne, date et remaques d'�valuation.
 * un hashmap sert � stocker la grille d'eval
 * les competences travaill�es sont stock�es dans une arraylist
 * */

public class Devoir implements Serializable {
    private String intitulle;
    private final String consigne;
    private final String dateDevoir;
    private String remarques;
    private final String[] critereStrings=new String[4];

    private final HashMap<String, Smileys> grilleEval=new HashMap<>();
    ArrayList<Competence>competencesEvalues=new ArrayList<>();
    ArrayList<String>competencesEvaluesString=new ArrayList<>();

    public String getClasse() {
        return classe;
    }

    private String classe;

    public Devoir(String intitulle,String consigne, String dateDevoir) {
        this.intitulle =intitulle;
        this.consigne = consigne;
        this.dateDevoir = dateDevoir;
    }

    /**Commente le devoir de l'�l�ve
     * @param remarques sur le travail*/
    public void commenterDevoir(String remarques) {this.remarques=remarques;}
    /**Ajoute une competence au devoir
     * @param comp du cycle*/
    public void ajouteCompetenceauDevoir(Competence comp) {
        competencesEvalues.add(comp);
    }
    public void ajouteCompetenceauDevoir(String comp) {
        competencesEvaluesString.add(comp);
    }
    /**Rempli les crit�res d'�valuation
     * @param string d'un crit�re*/
    public void rempliCriteres(String texte,int i) {
        critereStrings[i]=texte;
    }
    public String getCritere(int i ){
        switch (i){
            case 0:
                return critereStrings[0];
            case 1 :
                return critereStrings[1];
            case 2 :
                return critereStrings[2];
            case 3 :
                    return critereStrings[3];
        }
        return "pas de critère";
    }

    public String getIntitulle() {
        return intitulle;
    }

    public void setIntitulle(Editable s) {
        intitulle=s.toString();
    }

    public void setClasse(String classeNom) {this.classe=classeNom;
    }
}