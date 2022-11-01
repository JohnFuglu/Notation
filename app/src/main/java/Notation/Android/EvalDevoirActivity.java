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
import CoreStructure.Devoir;
import CoreStructure.Eleve;

public class EvalDevoirActivity extends AppCompatActivity {
    DataHandler dh;
    Eleve eleve;
    Classe classe;
    Devoir devoir;
    String smiley;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eval_devoir);
        try {
            recupInfo();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    void recupInfo() throws FileNotFoundException {

        Intent intent = getIntent();
        dh = new DataHandler(getBaseContext());

        TextView intitulle = findViewById(R.id.intiltulle_view);
        TextView classeView = findViewById(R.id.cl_view);
        TextView tBView = findViewById(R.id.TB_View);
        TextView bView = findViewById(R.id.B_View);
        TextView moyView = findViewById(R.id.Moy_View);
        TextView nonView = findViewById(R.id.Non_View);

        spinnerSmiley();
        Button sauver = (Button) findViewById(R.id.save_button);
        Button delete = (Button) findViewById(R.id.delete_button);
        TextView nomView = findViewById(R.id.nom_viewEval);
        String t1 = intent.getStringExtra("Classe");
        String t2 = intent.getStringExtra("Intitule");
        String t3 = intent.getStringExtra("eleve_nom");
        TextView commentaire = findViewById(R.id.comm_EvalDevoir);

        classeView.setText(t1);
        intitulle.setText(t2);
        nomView.setText(t3);

        classe=dh.classeFromFile(t1);
        eleve=dh.getEleveFromFile(t3,classe);
        devoir =classe.getDevoir(t2);


        tBView.setText(devoir.getCritere(0));
        bView.setText(devoir.getCritere(1));
        moyView.setText(devoir.getCritere(2));
        nonView.setText(devoir.getCritere(3));

        sauver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Devoir devEleve = new Devoir(devoir.getIntitulle(),devoir.getConsigne(),devoir.getDateDevoir(),devoir.getTrimestre());
                devEleve.commenterDevoir(commentaire.getText().toString());
                devEleve.setSmiley(smiley);
                eleve.ajoutDevoirFait(devEleve);
                eleve.creeNoteTravaux();
                try {
                    dh.majClasse(classe);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                finish();
            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eleve.deleteDevoir(devoir);
                try {
                    dh.majClasse(classe);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
    private void spinnerSmiley() {
        Spinner spinner = findViewById(R.id.evaluer_devoir);
        ArrayAdapter<CharSequence> adapterTri = ArrayAdapter.createFromResource(this,
                R.array.smileys, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapterTri);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    smiley = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}