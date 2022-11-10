package Notation.Android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

import CoreStructure.Classe;
import CoreStructure.DataHandler;
import CoreStructure.Eleve;

public class AjoutEleveActivity extends AppCompatActivity {

    TextView nom;
    Button ajout,suppression;
    String quelleClasse;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_eleve);
        Intent intent = getIntent();
        quelleClasse= intent.getStringExtra("Classe_Name");
        TextView classe=findViewById(R.id.nouv_classe);
        classe.setText(quelleClasse);
        nom=findViewById(R.id.nouv_Nom);
        suppression=findViewById(R.id.supprimeEleve);
        suppression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHandler data = new DataHandler(getBaseContext());
                try {
                    Classe cetteClasse = data.classeFromFile(quelleClasse);
                    cetteClasse.deleteEleve(nom.getText());
                    data.majClasse(cetteClasse);
                    finish();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        ajout=findViewById(R.id.nouv_ajout_button);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHandler data = new DataHandler(getBaseContext());
                try {
                    Classe cetteClasse = data.classeFromFile(quelleClasse);
                    cetteClasse.addEleve(new Eleve(nom.getText().toString(),cetteClasse));
                    data.createSerializedClasse(cetteClasse);
                    finish();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}