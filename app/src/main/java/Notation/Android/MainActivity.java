package Notation.Android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

import CoreStructure.Classe;
import CoreStructure.DataHandler;

public class MainActivity extends AppCompatActivity{
    private Classe classe;
    private Button creerDevoir;
    private   DataHandler dataHandler;

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String classeToFetch   = intent.getStringExtra("nom_classe");

        try {
            dataHandler = new DataHandler(getBaseContext());
            classe =dataHandler.classeFromFile(classeToFetch);

            for(int i =0;i<classe.getClasseListEleves().size();i++){
                String s = classe.getClasseListEleves().get(i).getnomPrenom();
                createButtonDynamicly(s);
            }

        }catch (FileNotFoundException e) {

        }
        TextView intitulle = findViewById(R.id.classe_nom);
        intitulle.setText(classe.getNomClasse());
        creerDevoir = findViewById(R.id.creerDevoir_button);
        creerDevoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                Intent devoirAct = new Intent(MainActivity.this,DevoirActivity.class);
                devoirAct.putExtra("Classe_Name",classe.getNomClasse());
                startActivity(devoirAct);
            }
        });
    }


    protected void createButtonDynamicly(String name){
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
                Button b = (Button)v;
                notationActivity.putExtra("Nom_Eleve",b.getText());
                notationActivity.putExtra("Classe_Name",classe.getNomClasse());
                startActivity(notationActivity);
            }
        });
        lin.addView(button);
    }
}