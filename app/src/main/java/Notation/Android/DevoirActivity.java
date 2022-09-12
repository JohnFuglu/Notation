package Notation.Android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import CoreStructure.Classe;
import CoreStructure.DataHandler;
import CoreStructure.Devoir;

public class DevoirActivity extends AppCompatActivity {
    private Spinner competence1;
    private Spinner competence2;
    private Spinner smileys;
    private Classe classe;
    private String classeNom;
    private ArrayList<Devoir> devoirs;
    private Devoir devoir;
    EditText TBText ,BText,MoyText,EchecText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devoir);
        Intent intent =getIntent();

       classeNom =intent.getStringExtra("Classe_Name");
       TextView classeAffiche=      findViewById(R.id.classe_aff);
       classeAffiche.setText(classeNom);

      // TODO separer devoir classe et devoir à remplir
       /*si (activite prec == mainActivity) alors

        * sinon alors
                chercher devoir qui correspond dans L'Eleve -> il est dans sa classe
                remplir
                sauver
       * */
        competence1 = (Spinner) findViewById(R.id.compet_Spinner);
        competence2 = (Spinner) findViewById(R.id.compet_Spinner2);
        smileys = (Spinner) findViewById(R.id.smiley_spinner);
/*
        competence1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO competence en objet et non en text placeholder
                devoir.ajouteCompetenceauDevoir(view.getTransitionName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/

        TBText = findViewById(R.id.TbReussi_Text);
       /* TBText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                devoir.rempliCriteres(s.toString(),0);
            }
        });*/
        BText = findViewById(R.id.BReussi_Text);
        MoyText = findViewById(R.id.MoyReussi_Text);
        EchecText = findViewById(R.id.Echec_Text);

    }
    public void sauverDevoir() throws FileNotFoundException {
        DataHandler dh = new DataHandler();
        classe= dh.classeFromFile(classeNom);
        classe.ajouterUnDevoir(devoir);
        dh.majClasse(classe);
    }

}