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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

import CoreStructure.Classe;
import CoreStructure.DataHandler;
import CoreStructure.Eleve;

public class NotationActivity extends AppCompatActivity {
    protected Eleve eleve;
    EditText appreciationText, noteTravaux, noteTrimestre;
    private TextView nomView, classeView;
    private TextView compNumber, partNumber;
    private Classe classe;
    private DataHandler dataHandler;
    private int trimestre = 0;
    private String appreciation;
    private double travaux, trimestreNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notation);
        dataHandler = new DataHandler(getBaseContext());
        try {
            setUI();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        spinnerDevoirs();
        spinnerTrimestre();
    }

    private void spinnerTrimestre() {
        Spinner trimestreSpinner = findViewById(R.id.choix_trimestre);
        ArrayAdapter<CharSequence> adapterTri = ArrayAdapter.createFromResource(this,
                R.array.Trimestres, android.R.layout.simple_spinner_item);
        trimestreSpinner.setAdapter(adapterTri);
        trimestreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    trimestre = getTrimestreNbr(parent.getItemAtPosition(position).toString()) - 1;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private double parseStringDouble(String s) {
        StringTokenizer st = new StringTokenizer(s);
        double t = 0;
        while (st.hasMoreTokens()) {
            if (st.nextToken().equals(Double.class))
                t = Double.parseDouble(st.toString());
        }
        return t;
    }

    private int getTrimestreNbr(String s) throws IOException {
        return Integer.parseInt(String.valueOf(s.charAt(s.length() - 1)));
    }

    private void spinnerDevoirs() {
        Spinner devoirSpinner = findViewById(R.id.choix_devoir);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter.add("aucun");
        for (int i = 0; i < classe.getDevoirDonnes().size(); i++) {
            adapter.add(classe.getDevoirDonnes().get(i).getIntitulle());
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        devoirSpinner.setAdapter(adapter);
        devoirSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getItemAtPosition(position).toString().equals("aucun")) {
                    Intent evalDevoirAct = new Intent(NotationActivity.this, EvalDevoirActivity.class);
                    evalDevoirAct.putExtra("Classe", classe.getNomClasse());
                    evalDevoirAct.putExtra("Intitule", parent.getItemAtPosition(position).toString());
                    evalDevoirAct.putExtra("eleve_nom", eleve.getnomPrenom());
                    startActivity(evalDevoirAct);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void majDesTextes() {
        eleve.addAppreciation(appreciation, trimestre);
        eleve.donneNotePourUnTrimestre(trimestre, trimestreNote);
        eleve.noterTravaux(trimestre, travaux);
    }

    private void setUI() throws FileNotFoundException {
        //PAR DEFAUT TRIMESTRE = 0

        Intent intent = getIntent();
        nomView = findViewById(R.id.nom_view);
        classeView = findViewById(R.id.classe_view);
        String nomEleve = intent.getStringExtra("Nom_Eleve");
        nomView.setText(nomEleve);
        String nomClasse = intent.getStringExtra("Classe_Name");
        classeView.setText(nomClasse);
        classe = dataHandler.classeFromFile(nomClasse);
        eleve = classe.getEleve(nomEleve);

        appreciationText = findViewById(R.id.Appreciation_Text);
        noteTravaux = findViewById(R.id.noteTra_nbr);
        noteTrimestre = findViewById(R.id.noteTri_nbr);

        noteTravaux.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tmp = s.toString();
                try {
                    travaux = parseStringDouble(tmp);
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Erreur de parse", Toast.LENGTH_LONG).show();
                }
            }
        });
        noteTrimestre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tmp = s.toString();
                trimestreNote = parseStringDouble(tmp);
            }
        });
        appreciationText.setText(eleve.getAppreciation(trimestre));
        appreciationText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                appreciation = s.toString();
            }
        });
        compNumber = findViewById(R.id.comport_number);
        partNumber = findViewById(R.id.partici_number);
        compNumber.setText(Short.toString(eleve.getNoteComportement(trimestre)));
        partNumber.setText(Short.toString(eleve.getNoteParticipation(trimestre)));
        Button saveButton = findViewById(R.id.Sauver_Button);
        Button compMinusButton = findViewById(R.id.minus_comport);
        Button compPlusButton = findViewById(R.id.plus_comport);
        Button partMinusButton = findViewById(R.id.minus_partici);
        Button partPlusButton = findViewById(R.id.plus_partici);
        compMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eleve.getNoteComportement(trimestre) > 0) {
                    eleve.diminueComportement(trimestre);
                    short t = eleve.getNoteComportement(trimestre);
                    compNumber.setText(Short.toString(t));
                }
            }
        });
        compPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eleve.getNoteComportement(trimestre) < 5) {
                    eleve.augmenteComportement(trimestre);
                    short t = eleve.getNoteComportement(trimestre);
                    compNumber.setText(Short.toString(t));
                }
            }
        });
        partMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eleve.getNoteComportement(trimestre) > 0) {
                    eleve.diminueParticipation(trimestre);
                    short t = eleve.getNoteParticipation(trimestre);
                    partNumber.setText(Short.toString(t));
                }
            }
        });
        partPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eleve.getNoteComportement(trimestre) < 5) {
                    eleve.augmenteParticipation(trimestre);
                    short t = eleve.getNoteParticipation(trimestre);
                    partNumber.setText(Short.toString(t));
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                majDesTextes();
                classe.updateClasse(eleve);
                dataHandler.createSerializedClasse(classe);
                finish();
            }
        });
    }

    protected void boutonsGris() {
        //TODO bouton gris
    }

}