package CoreStructure;

import android.content.Context;
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


    public void createSerializedClasse(Classe classe, Context context){
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try{
            File file = new File(context.getFilesDir(),"serialized_"+classe.getNomClasse());
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

    public Classe classeFromFile(String nomClasse, Context context)throws FileNotFoundException{
        FileInputStream fileInputStream=null;
        ObjectInputStream objectInputStream=null;
        Classe c = new Classe();
        try{
            File file = new File(context.getFilesDir(),"serialized_"+nomClasse);
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
    public void saveClasses(HashSet<Classe> set, Context context){
        for (Classe cl : set) {
            FileOutputStream fileOutputStream=null;
            ObjectOutputStream objectOutputStream=null;
        try{
            File file = new File(context.getFilesDir(),"serialized_"+cl.getNomClasse());
            fileOutputStream=new FileOutputStream(file,false);
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
    public void sauverDevoirs(ArrayList a,Classe classe){
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try{
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            String fileName = folder.getPath() + "/Devoirs";
            File file = new File(fileName);
            if(file.exists()){
                file.delete();
            }

            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Devoirs");
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
    public void majClasse(Classe c, Context context) throws FileNotFoundException {
        createSerializedClasse(c,context);
    }

    public static boolean freshStart(){
        if(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).listFiles().length >0)
            return false;
        else return true;
    }

    public static void deleteFile(String name){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        String fileName = folder.getPath() + "/"+name;
        File file = new File(fileName);
        if(file.exists())
            file.delete();
    }
}
