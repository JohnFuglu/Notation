package Notation.Android;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import CoreStructure.Classe;
import CoreStructure.CompArtsPlast;
import CoreStructure.DataHandler;
import CoreStructure.Devoir;

public class DevoirActivity extends AppCompatActivity {
    private Spinner competence1;
    private Spinner competence2;

    private Classe classe;
    private String classeNom,comp1,comp2,intitulle;
    private ArrayList<Devoir> devoirs;
    private Devoir devoir;
    EditText TBText ,BText,MoyText,EchecText;
    private TextView date;
    private TextView intituleTv;
    private final String[] criteres=new String[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devoir);
        Intent intent = getIntent();
        classeNom =intent.getStringExtra("Classe_Name");
         TextView classeAffiche=  findViewById(R.id.classe_aff);
        classeAffiche.setText(classeNom);
        prepareUi();
        liaisonText();
        if (intent.getBooleanExtra("Creation_Devoir", true)) {
            Button save = findViewById(R.id.sauver_button);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        sauverDevoir();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            TBText.setClickable(false);
            BText.setClickable(false);
            MoyText.setClickable(false);
            EchecText.setClickable(false);
            //TODO empecher de toucher à ce qu'il faut pas
        }

    }
    private void prepareUi() {

        createSpinners();

    }

    private void createSpinners() {
        competence1 = findViewById(R.id.compet_Spinner);
        competence2 = findViewById(R.id.compet_Spinner2);
        ArrayAdapter<String> adaptater = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        adaptater.addAll(CompArtsPlast.getCompetences());
        competence1.setAdapter(adaptater);
        competence2.setAdapter(adaptater);
        competence1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                comp1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        competence2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                comp2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setDate (TextView view){
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formating according to my need
        String date = formatter.format(today);
        view.setText(date);
    }
    private void liaisonText() {
        intituleTv=findViewById(R.id.Intitule_Text);
        intituleTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               intitulle=s.toString();
            }
        });


        date=findViewById(R.id.date_print);
       setDate(date);
        TBText = findViewById(R.id.TbReussi_Text);
        BText= findViewById(R.id.BReussi_Text);
        MoyText= findViewById(R.id.MoyReussi_Text);
        EchecText=findViewById(R.id.Echec_Text);
        TBText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                criteres[0]=s.toString();
            }
        });
        BText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                criteres[1]=s.toString();
            }
        });
        MoyText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                criteres[2]=s.toString();
            }
        });
        EchecText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                criteres[3]=s.toString();
            }
        });
    }

    public void sauverDevoir() throws FileNotFoundException {
        rempliDevoir();
        DataHandler dh = new DataHandler(getBaseContext());
        classe= dh.classeFromFile(classeNom);
        classe.ajouterUnDevoir(devoir);
        dh.majClasse(classe);
    }
    private void rempliDevoir(){
        devoir = new Devoir(intitulle," ",(String) date.getText());
        for(int i=0;i<3;i++)
            devoir.rempliCriteres(criteres[i],i);
        devoir.ajouteCompetenceauDevoir(comp1);
        devoir.ajouteCompetenceauDevoir(comp2);
        devoir.setClasse(classeNom);
    }

}