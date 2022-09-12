package CoreStructure;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class DataHandler {


    public void createSerializedClasse(Classe classe){
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"serialized_"+classe.getNomClasse());
            fileOutputStream=new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(classe);
            fileOutputStream.flush();
            fileOutputStream.close();
            objectOutputStream. flush();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Classe classeFromFile(String nomClasse)throws FileNotFoundException{
        FileInputStream fileInputStream=null;
        ObjectInputStream objectInputStream=null;
        Classe c = new Classe();
        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"serialized_"+nomClasse);
            fileInputStream=new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            c= (Classe) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }
    public void saveClasses(HashSet<Classe> set){
        for (Classe cl : set) {
            FileOutputStream fileOutputStream=null;
            ObjectOutputStream objectOutputStream=null;
        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"serialized_"+cl.getNomClasse());
            fileOutputStream=new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(cl);
            fileOutputStream.flush();
            fileOutputStream.close();
            objectOutputStream. flush();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
    public Eleve getEleveFromFile(String nom,Classe classe){
        ArrayList<Eleve> eleves = classe.getClasseListEleves();
        Iterator<Eleve> iterator = eleves.iterator();
        for (Eleve eleve : eleves) {
            if(eleve.getnomPrenom().equals(nom))
                return  eleve;
        }
        return null;
    }
    public void sauverDevoirs(ArrayList a){
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Devoirs");
            fileOutputStream=new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(a);
            fileOutputStream.flush();
            fileOutputStream.close();
            objectOutputStream. flush();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Devoir trouverLeDevoir(ArrayList<Devoir> devoirs,Devoir element){
       for(int i =0;i< devoirs.size();i++){
           if(devoirs.get(i).getIntitulle()==element.getIntitulle()){
               return devoirs.get(i);
           }
          }
        return null;
    }
    //TODO faire une methode pour le chargment repetitif
    public void majClasse(Classe c) throws FileNotFoundException {
        createSerializedClasse(c);

    }
}
