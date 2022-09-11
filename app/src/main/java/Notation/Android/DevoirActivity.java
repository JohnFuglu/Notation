package Notation.Android;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import CoreStructure.Devoir;
import CoreStructure.Smileys;

public class DevoirActivity extends AppCompatActivity {
    private Spinner competence1;
    private Spinner competence2;
    private Spinner smileys;

    private Devoir devoir;
    Smileys[] smileyArray = {Smileys.TRESCONTENT,Smileys.TRESCONTENTplus,Smileys.TRESCONTENTmoins,Smileys.CONTENT,Smileys.CONTENTplus, Smileys.CONTENTmoins,
            Smileys.MOYENmoins, Smileys.MOYENplus,Smileys.MOYEN, Smileys.PASCONTENT, Smileys.PASCONTENTplus,Smileys.PASCONTENTmoins};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devoir);
        competence1 = (Spinner) findViewById(R.id.compet_Spinner);
        competence2 = (Spinner) findViewById(R.id.compet_Spinner2);
        smileys = (Spinner) findViewById(R.id.smiley_spinner);

        ArrayAdapter<Smileys> adapter = new ArrayAdapter<Smileys>(this, android.R.layout.simple_spinner_dropdown_item, smileyArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smileys.setAdapter(adapter);

    }


}