package Notation.Android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

import CoreStructure.Classe;
import CoreStructure.DataHandler;
import CoreStructure.Eleve;

public class NotationActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView nomView,classeView;
    private TextView compNumber,partNumber;
    private Classe classe;
    protected Eleve eleve;
    private final DataHandler dataHandler = new DataHandler();
    private Spinner devoirSpinner;
    private Button saveButton,
            compPlusButton,compMinusButton,
    partPlusButton,partMinusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notation);

        nomView=findViewById(R.id.nom_view);
        classeView=findViewById(R.id.classe_view);

        try {
            affichageEleve();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DataHandler dh = new DataHandler();
        try {
            dh.classeFromFile(classe.getNomClasse());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        createButtons();

        devoirSpinner=findViewById(R.id.choix_devoir);

        String[] strings = new String[classe.getDevoirDonnes().size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       devoirSpinner.setAdapter(adapter);
        devoirSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
}

    private void createButtons() {
        compNumber=findViewById(R.id.comport_number);
        partNumber=findViewById(R.id.partici_number);
        compNumber.setText(Short.toString(eleve.getNoteComportement()));
        partNumber.setText(Short.toString(eleve.getNoteParticipation()));
        saveButton = findViewById(R.id.Sauver_Button);
        compMinusButton = findViewById(R.id.minus_comport);
        compPlusButton = findViewById(R.id.plus_comport);
        partMinusButton = findViewById(R.id.minus_partici);
        partPlusButton = findViewById(R.id.plus_partici);
        compMinusButton.setOnClickListener(this);
        compPlusButton.setOnClickListener(this);
        partMinusButton.setOnClickListener(this);
        partPlusButton.setOnClickListener(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classe.updateClasse(eleve);
              DataHandler dh = new DataHandler();
              dh.createSerializedClasse(classe);
            }
        });
    }

    private void ajouteAppreciation(){

    }
    private void affichageEleve() throws FileNotFoundException {
        Intent intent =getIntent();
        String nomEleve =  intent.getStringExtra("button_message");
        nomView.setText(nomEleve);
        String nomClasse= intent.getStringExtra("button_message2");
        classeView.setText( nomClasse);
        classe = dataHandler.classeFromFile(nomClasse);
        eleve= dataHandler.getEleveFromFile(nomEleve,classe);
    }

    @Override
    public void onClick(View v) {
     //   boutonsGris();
        Button b = (Button)v;
        switch(b.getId()){
            case R.id.minus_comport:
                eleve.diminueComportement();
                compNumber.setText(Short.toString(eleve.getNoteComportement()));
                 break;
            case R.id.plus_comport:
                eleve.augmenteComportement();
                partNumber.setText(Short.toString(eleve.getNoteComportement()));
                break;
            case R.id.minus_partici:
                eleve.diminueParticipation();
                compNumber.setText(Short.toString(eleve.getNoteParticipation()));

        case    R.id.plus_partici:
            eleve.augmenteParticipation();
            partNumber.setText(Short.toString(eleve.getNoteParticipation()));
        }
    }
    protected void boutonsGris(){
      if(eleve.getNoteComportement()==0)
          compMinusButton.setEnabled(false);
      if(eleve.getNoteComportement()==5)
          compPlusButton.setEnabled(false);
        if(eleve.getNoteParticipation()==0)
            partMinusButton.setEnabled(false);
        if(eleve.getNoteParticipation()==5)
            partPlusButton.setEnabled(false);
    }

}