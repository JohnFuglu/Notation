package CoreStructure;

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
 * */

public class Eleve  implements Comparable<Eleve>, Serializable{

    private String nomPrenom;
    private String prenom;
    private Classe classe;
    private String nom;
    //TEST
    private short noteTravaux;
    private short[] nTrimestres=new short[3];
    private short noteParticipation;
    private short[] nParti=new short[3];
    private short noteComportement;
    private short[] nComi=new short[3];

    private String[] appreciations=new String[3];
    private boolean noteTriRentre=false;
    private boolean notePartiRentre=false;
    private boolean noteCompRentre=false;

    private double[] notesFinales = new double[3];

    private ArrayList<Competence> compValideArrayList = new ArrayList<Competence>();
    private Set<Devoir> devoirsFaits=new HashSet<>();

    private int trimestre=0;

    /**
     * Constructeur
     * @param prenom : prenom de l'�l�ve
     * @param nom : nom de l'�l�ve
     * @param classe : classe de l'�l�ve*/
  /*  public Eleve(String prenom, Classe classe, String nom) {
        this.prenom = prenom;
        this.classe = classe;
        this.nom = nom;
    }*/
    public Eleve(String nomEtPrenom,Classe classe){//TODO casser nom et prenom en 2
       nomPrenom = nomEtPrenom;
       classe=classe;
        nTrimestres[0]=0; nTrimestres[1]=0; nTrimestres[2]=0;
        nParti[0]=0; nParti[1]=0; nParti[2]=0;
        nComi[0]=0; nComi[1]=0; nComi[2]=0;
        noteTravaux=0;
        noteParticipation=0;
        noteComportement=5;
    }
    /**Note le trimestre / 10
     * @throws error si en dehors des clous
     * @return boolean rentr�e
     * */
    public boolean noterTravaux(short note) {
        if(note<0 || note > 10)
            throw new Error("La note doit �tre comprise en 0 et 10");
        this.noteTravaux =note;
        return true;
    }

    /**Note le participation / 5
     * @throws error si en dehors des clous
     * @return boolean rentr�e
     * */
    public boolean noterParticipation(short note) {
        if(note<0 || note > 5)
            throw new Error("La note doit �tre comprise en 0 et 5");
        this.noteParticipation=note;
        return true;
    }

    /**Note le comportement / 5
     * @throws error si en dehors des clous
     * @return boolean rentr�e
     * */
    public boolean noterComportement(short note) {
        if(note<0 || note > 5)
            throw new Error("La note doit �tre comprise en 0 et 5");
        this.noteComportement=note;
        return true;
    }

    /**Note finale du trimestre par addition des 3 sous notes
     * stock�e dans un tableau
     * reset les booleans pour la saisie des notes
     * @throws error si il manque une des 3 notes � ajouter
     * @throws error si la note est < 0 ou > 20*/
	public double noteGlobale(int trimestre) {
		if((Object)nTrimestres[trimestre] ==null ||(Object)nComi[trimestre] ==  null || (Object)nParti[trimestre] ==null)
            throw new Error("Note(s) manquantes !");
		else {
				notesFinales[trimestre] = (nTrimestres[trimestre]+ nComi[trimestre] +nParti[trimestre]);
				noteCompRentre=false;
				notePartiRentre=false;
				noteTriRentre=false;
                return	notesFinales[trimestre];
		}


	}
    public short getNoteParticipation(){return noteParticipation;}
    public short getNoteComportement(){return  noteComportement;}
    /**Donne la note d'un trimestre sp�cifique
     * @param int pour trimestre voulu
     * @throws error de numero de trimestre*/
    public double donneNotePourUnTrimestre(int trimestre) {
        if(trimestre<1 || trimestre >3 )throw new Error("Ce n'est pas un trimestre valide");
        return notesFinales[trimestre-1];
    }

    public double getMoyenne(int trimestre) {
        return
            notesFinales[trimestre];}
    public String getNom() {return nom;}
    public void setNom(String nom) {}
    public String getPrenom() {return prenom;}
    public String getnomPrenom(){return nomPrenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public Classe getClasse() {return classe;}
    public void setClasse(Classe classe) {this.classe = classe;}
    @Override
    public int compareTo(Eleve o) {
        // TODO Auto-generated method stub
        return 0;
    }
    public int getTrimestre() {return trimestre;}
    public void setTrimestre(int i) { trimestre=i;}
    public double[] getNotesFinales() {return notesFinales;}
    public void setNotesFinales(double note) {this.notesFinales[trimestre]=note;}
    public void addAppreciation(String appreciation, int trimestre){appreciations[trimestre]=appreciation;}
    public String[] getAppreciations() {return appreciations;}

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

    public void augmenteParticipation() {noteParticipation++;
    }

    public void augmenteComportement() {noteComportement++;
    }

    public void diminueParticipation() {noteParticipation--;
    }

    public void diminueComportement() {
        noteComportement--;
    }
}