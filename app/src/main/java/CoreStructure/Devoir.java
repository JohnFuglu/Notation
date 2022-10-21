package CoreStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Classe de devoir
 * avec consigne, date et remaques d'�valuation.
 * un hashmap sert � stocker la grille d'eval
 * les competences travaill�es sont stock�es dans une arraylist
 */

public class Devoir implements Serializable {
    private String consigne;

    public String getConsigne() {
        return consigne;
    }

    public String getDateDevoir() {
        return dateDevoir;
    }

    private final String dateDevoir;
    private final String[] critereStrings = new String[4];
    private final HashMap<String, Smileys> grilleEval = new HashMap<>();
    ArrayList<Competence> competencesEvalues = new ArrayList<>();
    ArrayList<String> competencesEvaluesString = new ArrayList<>();

    public void setIntitulle(String intitulle) {
        this.intitulle = intitulle;
    }

    private String intitulle;
    private String remarques;
    private String smiley;
    private String classe;
    public Devoir(String intitulle, String consigne, String dateDevoir) {
        this.intitulle = intitulle;
        this.consigne = consigne;
        this.dateDevoir = dateDevoir;
    }

    public void setSmiley(String smiley) {
        this.smiley = smiley;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classeNom) {
        this.classe = classeNom;
    }

    /**
     * Commente le devoir de l'�l�ve
     *
     * @param remarques sur le travail
     */
    public void commenterDevoir(String remarques) {
        this.remarques = remarques;
    }



    public void ajouteCompetenceauDevoir(String comp) {
        competencesEvaluesString.add(comp);
    }

    /**
     * Rempli les crit�res d'�valuation
     *
     * @param string d'un crit�re
     */
    public void rempliCriteres(String texte, int i) {
        critereStrings[i] = texte;
    }

    public String getCritere(int i) {
        switch (i) {
            case 0:
                return critereStrings[0];
            case 1:
                return critereStrings[1];
            case 2:
                return critereStrings[2];
            case 3:
                return critereStrings[3];
        }
        return "pas de critère";
    }

    public String getIntitulle() {
        return intitulle;
    }


    public String resumer() {
        String s = remarques + " // " + smiley + "\n";
        return s;
    }

    public void setConsigne(String consigne) {this.consigne=consigne;
    }
}