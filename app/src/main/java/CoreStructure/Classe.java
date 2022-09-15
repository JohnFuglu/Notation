package CoreStructure;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**CLasse stockant les élèves
 * */
public class Classe implements Serializable {

    private String nomClasse;
    private ArrayList<Eleve> classEleves = new ArrayList<Eleve>();

    public ArrayList<Devoir> getDevoirDonnes() {
        return devoirDonnes;
    }
    public boolean devoirPresent(Devoir d){
       for(int i=0;i<devoirDonnes.size();i++){
           if(devoirDonnes.get(i).getIntitulle().equals(d.getIntitulle()))
               return true;
       }
        return false;
    }
    private ArrayList<Devoir> devoirDonnes=new ArrayList<>();
    public void ajouterUnDevoir(Devoir d){
        if(!devoirPresent(d))
            devoirDonnes.add(d);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public	Classe (String nomClasse) throws IOException {
        this.nomClasse=nomClasse;
    }
    //Constructeur temporaire pour test sans CSV
    public Classe(){

    }


    public String getNomClasse() {return this.nomClasse;}
    public void setNomClasse(String nom){this.nomClasse=nom;}
    /**Va chercher un �l�ve dans la classe*/
    public Eleve getEleve(String nomPrenom) {
        Iterator<Eleve> iterator =  classEleves.iterator();
        while(iterator.hasNext()) {
            Eleve eleve;
            eleve =(Eleve)iterator.next();

            if(eleve.getnomPrenom().equals(nomPrenom))
                return eleve;
        }
        throw new Error("L'eleve n'est pas pr�sent dans cette classe !");
    }


    public Eleve getEleve(String nom, String prenom) {
        Iterator<Eleve> iterator =  classEleves.iterator();
        while(iterator.hasNext()) {
            Eleve eleve;
            eleve =(Eleve)iterator.next();

            if(eleve.getPrenom().equals(prenom) && eleve.getNom().equals(nom) )
                return eleve;
        }
        throw new Error("L'eleve n'est pas pr�sent dans cette classe !");
    }
    public void addEleve(Eleve e) {classEleves.add(e);}

    public ArrayList<Eleve> getClasseListEleves(){return this.classEleves;}
    /**Donne un devoir � la classe, verif s'il existe d�j�
     * @param devoir
     * @throws Error si devoir d�j� donn�*/
    public void donneUnDevoir(Devoir devoir) {
        boolean ok=false;
        for(Devoir d : devoirDonnes) {
            if(devoir.getIntitulle()==d.getIntitulle())
                throw new Error("Ce devoir existe d�j� dans cette classe !");
            ok=true;
        }
        if(ok)
            devoirDonnes.add(devoir);
    }
   public void updateClasse(Eleve e){
       for (Eleve eleve : classEleves) {
           if(eleve.getnomPrenom()==e.getnomPrenom()){
               eleve=e;
               return;
           }
       }
   }

    public Devoir getDevoir(String intitule) {
        for (Devoir d :devoirDonnes
             ) {
            if(d.getIntitulle().equals(intitule))
                return d;
        }
        return null;
    }
}