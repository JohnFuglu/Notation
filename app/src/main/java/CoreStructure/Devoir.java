package CoreStructure;

import java.io.Serializable;
import java.util.ArrayList;


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

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    private boolean dirty;
    private final String dateDevoir;
    private final String[] critereStrings = new String[4];
  //  ArrayList<Competence> competencesEvalues = new ArrayList<>();
       ArrayList<String> competencesEvaluesString = new ArrayList<>();

    public void setIntitulle(String intitulle) {
        this.intitulle = intitulle;
    }

    public short getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(short trimestre) {
        this.trimestre = trimestre;
    }

    private short trimestre=0;
    private String intitulle;
    private String remarques;

    public String getRemarques() {
        return remarques;
    }

    public String getSmiley() {
        return smiley;
    }
    private String smiley;
    private String classe;
    public Devoir(String intitulle, String consigne, String dateDevoir, short trimestre) {
        this.intitulle = intitulle;
        this.consigne = consigne;
        this.dateDevoir = dateDevoir;
        this.trimestre=trimestre;
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