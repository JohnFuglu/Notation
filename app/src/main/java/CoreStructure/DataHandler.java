package CoreStructure;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataHandler {
    Context context;

    public DataHandler(Context activityContext) {
        context = activityContext;
    }

    public void createSerializedClasse(Classe classe) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            // File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"serialized_"+classe.getNomClasse()+".txt");
            fileOutputStream = context.openFileOutput("serialized_" + classe.getNomClasse(), MODE_PRIVATE);

            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(classe);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Classe classeFromFile(String nomClasse) throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Classe c = new Classe();
        try {
            //     File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"serialized_"+nomClasse);
            fileInputStream = context.openFileInput("serialized_" + nomClasse);
            objectInputStream = new ObjectInputStream(fileInputStream);
            c = (Classe) objectInputStream.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (objectInputStream != null) {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return c;
    }

    public Eleve getEleveFromFile(String nom, Classe classe) {
        ArrayList<Eleve> eleves = classe.getClasseListEleves();
  //      Iterator<Eleve> iterator = eleves.iterator();
        for (Eleve eleve : eleves) {
            if (eleve.getnomPrenom().equals(nom))
                return eleve;
        }
        return null;
    }

    //TODO faire une methode pour le chargment repetitif
    public void majClasse(Classe c) throws FileNotFoundException {
        createSerializedClasse(c);
    }

    public void genererEvalTxt(Classe c, Devoir d) {
        String s =d.getIntitulle() + "\n";

        for (Eleve e :c.getClasseListEleves()) {
            s+=e.getEvalDevoir(d);
        }
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {

            fileOutputStream = context.openFileOutput("Resume_"+d.getIntitulle() + c.getNomClasse()+".txt", MODE_PRIVATE);

            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
