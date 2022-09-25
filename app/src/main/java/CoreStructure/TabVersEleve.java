package CoreStructure;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class TabVersEleve {
    //TODO mettre un tri avec les majuscules = cas des noms composés
    //TODO le coup des accents
    public TabVersEleve(Classe c) throws IOException {
        String line="";
        String spliter=";";
        Eleve e ;
       String path = Environment.DIRECTORY_DOCUMENTS;
        BufferedReader buff=new BufferedReader(new InputStreamReader(new FileInputStream(""), StandardCharsets.UTF_8));
        short s=0 ;
        while((line=buff.readLine()) != null) {
            String[]eleve= line.split(spliter);
            String string=eleve[0];

            if(s > 0) {
                StringReader reader = new StringReader(string);
                int i = reader.read();
                int fin=0;
                String nomMaj =" ";
                while(i!=-1 && !Character.isLowerCase(i)) {
                    char temp=(char)i;
                    if(Character.isUpperCase(temp) || Character.isWhitespace(temp)) {
                        nomMaj +=temp;
                        fin++;
                    }
                    i= (char)reader.read();
                }

                String nom= nomMaj.substring(1, nomMaj.length()-2);//String nom= string.substring(1, string.indexOf(' '));
                String prenom= string.substring(fin, string.length()-1);

                e=new Eleve(prenom,c);
                c.addEleve(e);
                reader.close();
            }
            s++;

        }
        buff.close();

    }
    private String prepareNomPrenom() {
        return "";
    }
}