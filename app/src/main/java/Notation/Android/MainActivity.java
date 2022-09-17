package Notation.Android;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import CoreStructure.Classe;
import CoreStructure.DataHandler;
import CoreStructure.Eleve;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Classe classe;
    private Button creerDevoir;


//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String classeToFetch   = intent.getStringExtra("button_message");
        try {
            DataHandler dataHandler = new DataHandler();
            classe =dataHandler.classeFromFile(classeToFetch,getApplicationContext());

            for(int i =0;i<classe.getClasseListEleves().size();i++){
                String s = classe.getClasseListEleves().get(i).getnomPrenom();
                createButtonDynamicly(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        TextView intitulle = findViewById(R.id.classe_nom);
        intitulle.setText(classe.getNomClasse());
        creerDevoir = findViewById(R.id.creerDevoir_button);
        creerDevoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                Intent devoirAct = new Intent(MainActivity.this,DevoirActivity.class);
                boolean estCreation=true;
                devoirAct.putExtra("Creation_Devoir",estCreation);
                devoirAct.putExtra("Classe_Name",classe.getNomClasse());
                startActivity(devoirAct);
            }
        });
    }
    protected  Set<String> xlsReader() throws IOException {
        Set<String> set = new HashSet<>();
        try{
            final AssetManager as= getBaseContext().getAssets();
            InputStream file = as.open("5e.xls");
            HSSFWorkbook workbook = new  HSSFWorkbook(file);
            HSSFSheet sheet =workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while(rowIterator.hasNext()){
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator= row.cellIterator();
                    while(cellIterator.hasNext()){
                        Cell cell = cellIterator.next();
                        if(!cell.getStringCellValue().isEmpty())
                            set.add(cell.getStringCellValue());
                    }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return set;
    }

    protected void createEleve(Set<String> set) throws IOException {
        for (String s : set) {
            Eleve e = new Eleve(s, classe);
            classe.addEleve(e);
        }
            saveData(classe,getApplicationContext());
    }
    protected void createButtonDynamicly(String name){
        Button button = new Button(this);
        int currId = 1000;
        button.setId(currId++);
        button.setText(name);
        LinearLayout lin = findViewById(R.id.classes_linearLay);
        button.setLayoutParams(lin.getLayoutParams());
        button.setOnClickListener(this);
        lin.addView(button);
    }

    @Override
    public void onClick(View v) {
        Intent notationActivity = new Intent(MainActivity.this, NotationActivity.class);
        Button b = (Button)v;
        notationActivity.putExtra("button_message",b.getText());
        notationActivity.putExtra("button_message2",classe.getNomClasse());
        startActivity(notationActivity);
    }
    protected void saveData(Classe c, Context context){
        DataHandler dataHandler =new DataHandler();
        dataHandler.createSerializedClasse(c,context);
    }
    //TODO rassembler les chargements ici
    private void persistentDataUpdate(){}
}