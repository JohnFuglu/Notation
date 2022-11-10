package CoreStructure;

import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * CoreStructure.Classe eleve :
 * note de trimestre sur 10
 * note de participation sur 5
 * note de comportement sur 5
 * tableau de 3 notes sur 20
 * arraylist de Competences
 */

public class Eleve implements Comparable<Eleve>, Serializable {

    private String nomPrenom;
    private String prenom;
    private Classe classe;
    private String nom;
    //TEST
    private double[] nTrimestreTravaux = new double[3];
    private short[] nParti = new short[3];
    private short[] nCompor = new short[3];
    private String[] appreciations = new String[3];
    private double[] notesFinales = new double[3];

    private ArrayList<Competence> compValideArrayList = new ArrayList<Competence>();

    public Set<Devoir> getDevoirsFaits() {
        return devoirsFaits;
    }

    private Set<Devoir> devoirsFaits = new HashSet<>();
    private int trimestre = 1;

    public Eleve(String nomEtPrenom, Classe classe) {//TODO casser nom et prenom en 2
        nomPrenom = nomEtPrenom;
        classe = classe;
        nTrimestreTravaux[0] = 0;
        nTrimestreTravaux[1] = 0;
        nTrimestreTravaux[2] = 0;
        nParti[0] = 0;
        nParti[1] = 0;
        nParti[2] = 0;
        nCompor[0] = 5;
        nCompor[1] = 5;
        nCompor[2] = 5;
        appreciations = new String[]{"aucune", "aucune", "aucune"};
    }
    public String getNoteTravaux(int trimestre){
        return Double.toString(nTrimestreTravaux[trimestre-1]);
    }
    public String getNoteTrimestre(int trimestre){
        notesFinales[trimestre-1]= nTrimestreTravaux[trimestre-1]+nParti[trimestre-1]+nCompor[trimestre-1];
        return Double.toString(notesFinales[trimestre-1]);
    }
    public void ajoutDevoirFait(Devoir d) {
        if (!devoirsFaits.contains(d))
            devoirsFaits.add(d);
    }
    public void creeNoteTravaux(){
        if(devoirsFaits.isEmpty())
            return;
        else{
            for(Devoir d :devoirsFaits){
                if(!d.isDirty()){
                switch (d.getSmiley()){
                    case"Très content +":
                        nTrimestreTravaux[d.getTrimestre()-1]+=1.5d;
                        d.setDirty(true);
                        break;
                    case"Très content -":
                        nTrimestreTravaux[d.getTrimestre()-1]+=0.95d;
                        d.setDirty(true);
                        break;
                    case"Très content":
                        nTrimestreTravaux[d.getTrimestre()-1]+=1.3d;
                        d.setDirty(true);
                        break;
                    case"Content +":
                        nTrimestreTravaux[d.getTrimestre()-1]+=0.85d;
                        d.setDirty(true);
                        break;
                    case"Content":
                        nTrimestreTravaux[d.getTrimestre()-1]+=1d;
                        d.setDirty(true);
                        break;
                    case"Content -":
                        nTrimestreTravaux[d.getTrimestre()-1]+=0.80d;
                        d.setDirty(true);
                        break;
                        case"Bof +":
                            nTrimestreTravaux[d.getTrimestre()-1]+=0.75d;
                            d.setDirty(true);
                            break;
                    case"Bof":
                        nTrimestreTravaux[d.getTrimestre()-1]+=0.65d;
                        d.setDirty(true);
                        break;
                    case"Bof -":
                        nTrimestreTravaux[d.getTrimestre()-1]+=0.55d;
                        d.setDirty(true);
                        break;
                    case"Pas content +":
                        nTrimestreTravaux[d.getTrimestre()-1]+=0.45d;
                        d.setDirty(true);
                        break;
                    case"Pas content":
                        nTrimestreTravaux[d.getTrimestre()-1]+=0.40d;
                        d.setDirty(true);
                        break;
                    case"Pas content -":
                        nTrimestreTravaux[d.getTrimestre()-1]+=0.10d;
                        d.setDirty(true);
                        break;
                }
            }}
        }

    }
    public void creeNoteFinale(int trimestre){
            notesFinales[trimestre-1]=nTrimestreTravaux[trimestre-1]+nCompor[trimestre-1]+nParti[trimestre-1];
    }
    public String getEvalDevoir(Devoir d) {
        for(Devoir dd : devoirsFaits){
            if (dd.getIntitulle().equals(d.getIntitulle()))
                return nomPrenom + "\n " + dd.resumer();
        }
      return nomPrenom +" :" +" Devoir Absent" + "\n";
    }

    /**
     * Note le trimestre / 10
     *
     * @return boolean rentr�e
     * @throws error si en dehors des clous
     */
    public boolean noterTravaux(int trimestre, double note) {
        if (note < 0 || note > 10)
            throw new Error("La note doit �tre comprise en 0 et 10");
        this.nTrimestreTravaux[trimestre] = note;
        return true;
    }

    /**
     * Note le participation / 5
     *
     * @return boolean rentr�e
     * @throws error si en dehors des clous
     */
    public boolean noterParticipation(int trimestre, short note) {
        if (note < 0 || note > 5)
            throw new Error("La note doit �tre comprise en 0 et 5");
        this.nParti[trimestre] = note;
        return true;
    }

    public short getNoteParticipation(int trimestre) {
        return nParti[trimestre-1];
    }

    public short getNoteComportement(int trimestre) {
        return nCompor[trimestre-1];
    }

    /**
     * Donne la note d'un trimestre sp�cifique
     *
     * @param int pour trimestre voulu
     * @throws error de numero de trimestre
     */
    public void donneNotePourUnTrimestre(int trimestre, double note) {
        nTrimestreTravaux[trimestre] = note;
    }

    public double getMoyenne(int trimestre) {
        return
                notesFinales[trimestre];
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getnomPrenom() {
        return nomPrenom;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public int compareTo(Eleve o) {
        return this.nomPrenom.compareTo(o.getnomPrenom());
    }

    public int getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(int i) {
        trimestre = i;
    }

    public double[] getNotesFinales() {
        return notesFinales;
    }

    public void setNotesFinales(double note) {
        this.notesFinales[trimestre] = note;
    }

    public void addAppreciation(String appreciation, int trimestre) {
        appreciations[trimestre-1] = appreciation;
    }

    public String[] getAppreciations() {
        return appreciations;
    }

    public String getAppreciation(int trimestre) {
        return appreciations[trimestre-1];
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

    public void augmenteParticipation(int trimestre) {
        nParti[trimestre-1]++;
    }

    public void augmenteComportement(int trimestr) {
        nCompor[trimestr-1]++;
    }

    public void diminueParticipation(int trimestr) {
        nParti[trimestr-1]--;
    }

    public void diminueComportement(int trimestr) {
        nCompor[trimestr-1]--;
    }

    public void setNoteComportement(int trimestre, TextView compNumber) {
        nCompor[trimestre-1]=Short.parseShort(compNumber.getText().toString());
    }

    public void setNoteParticipation(int trimestre, TextView partNumber) {
        nParti[trimestre-1]=Short.parseShort(partNumber.getText().toString());
    }

    public void deleteDevoir(Devoir devoir) {
        for(Devoir d : devoirsFaits){
            if(d.getIntitulle().equals(devoir.getIntitulle())){
                devoirsFaits.remove(d);
            }
        }
    }
}