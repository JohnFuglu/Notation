package CoreStructure;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * CLasse stockant les élèves
 */
public class Classe implements Serializable, Comparable<Classe> {

    private final ArrayList<Eleve> classEleves = new ArrayList<Eleve>();
    private final ArrayList<Devoir> devoirDonnes = new ArrayList<>();
    private String nomClasse;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Classe(String nomClasse) throws IOException {
        this.nomClasse = nomClasse;
    }

    //Constructeur temporaire pour test sans CSV
    public Classe() {

    }

    public ArrayList<Devoir> getDevoirDonnes() {
        return devoirDonnes;
    }

    public boolean devoirPresent(Devoir d) {
        for (int i = 0; i < devoirDonnes.size(); i++) {
            if (devoirDonnes.get(i).getIntitulle().equals(d.getIntitulle()))
                return true;
        }
        return false;
    }

    public void ajouterUnDevoir(Devoir d) {
        if (!devoirPresent(d))
            devoirDonnes.add(d);
    }

    public String getNomClasse() {
        return this.nomClasse;
    }

    public void setNomClasse(String nom) {
        this.nomClasse = nom;
    }

    /**
     * Va chercher un �l�ve dans la classe
     */
    public Eleve getEleve(String nomPrenom) {
        Iterator<Eleve> iterator = classEleves.iterator();
        while (iterator.hasNext()) {
            Eleve eleve;
            eleve = iterator.next();

            if (eleve.getnomPrenom().equals(nomPrenom))
                return eleve;
        }
        throw new Error("L'eleve n'est pas pr�sent dans cette classe !");
    }

    public void addEleve(Eleve e) {
        classEleves.add(e);
    }

    public ArrayList<Eleve> getClasseListEleves() {
        return this.classEleves;
    }


    public Devoir getDevoir(String intitule) {
        for (Devoir d : devoirDonnes
        ) {
            if (d.getIntitulle().equals(intitule))
                return d;
        }
        return null;
    }

    public void deleteEleve(CharSequence text) {
           classEleves.remove(getEleve(text.toString()));
    }


    @Override
    public int compareTo(Classe o) {
        return this.nomClasse.compareTo(o.nomClasse);
    }
}