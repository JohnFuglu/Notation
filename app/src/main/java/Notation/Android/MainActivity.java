package Notation.Android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.util.Collections;

import CoreStructure.Classe;
import CoreStructure.DataHandler;
import CoreStructure.Devoir;

public class MainActivity extends AppCompatActivity {
    private Classe classe;
    private Button creerDevoir, genererEvals, ajouterEleve;
    private DataHandler dataHandler;
    private Devoir d;
    String classeToFetch;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    try {
                        spinnerDevoirs();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        ajouterEleve=findViewById(R.id.ajoutEleveButton);

        classeToFetch = intent.getStringExtra("nom_classe");
        genererEvals = findViewById(R.id.generer_Evals_Button);

        creerDevoir = findViewById(R.id.creerDevoir_button);
        TextView intitulle = findViewById(R.id.classe_nom);


        try {
            dataHandler = new DataHandler(getBaseContext());
            classe = dataHandler.classeFromFile(classeToFetch);
           Collections.sort(classe.getClasseListEleves());
            for (int i = 0; i < classe.getClasseListEleves().size(); i++) {
                String s = classe.getClasseListEleves().get(i).getnomPrenom();
                createButtonDynamicly(s);
            }

        } catch (FileNotFoundException e) {

        }
        try {
            spinnerDevoirs();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        intitulle.setText(classe.getNomClasse());

        creerDevoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                Intent devoirAct = new Intent(MainActivity.this, DevoirActivity.class);
                devoirAct.putExtra("Classe_Name", classe.getNomClasse());
                activityResultLauncher.launch(devoirAct);
            }
        });

        genererEvals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (d != null){
                    dataHandler = new DataHandler(getBaseContext());
                    try {
                        classe=  dataHandler.classeFromFile(classeToFetch);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    dataHandler.genererEvalTxt(classe, d);
                }
            }
        });

        ajouterEleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                Intent ajouterEleve = new Intent(MainActivity.this, AjoutEleveActivity.class);
                ajouterEleve.putExtra("Classe_Name", classe.getNomClasse());
                activityResultLauncher.launch(ajouterEleve);
            }
        });
    }

    private void spinnerDevoirs() throws FileNotFoundException {
        classe=dataHandler.classeFromFile(classeToFetch);
        Spinner devoirs = findViewById(R.id.Devoir_Selection);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter.add("aucun");
        for (int i = 0; i < classe.getDevoirDonnes().size(); i++) {
            adapter.add(classe.getDevoirDonnes().get(i).getIntitulle());
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        devoirs.setAdapter(adapter);
        devoirs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!parent.getItemAtPosition(position).toString().equals("aucun")) {
                    d = classe.getDevoir(parent.getItemAtPosition(position).toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected void createButtonDynamicly(String name) {
        Button button = new Button(this);
        int currId = 1000;
        button.setId(currId++);
        button.setText(name);
        LinearLayout lin = findViewById(R.id.classes_linearLay);
        button.setLayoutParams(lin.getLayoutParams());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notationActivity = new Intent(MainActivity.this, NotationActivity.class);
                Button b = (Button) v;
                notationActivity.putExtra("Nom_Eleve", b.getText());
                notationActivity.putExtra("Classe_Name", classe.getNomClasse());
                startActivity(notationActivity);
            }
        });
        lin.addView(button);
    }
}