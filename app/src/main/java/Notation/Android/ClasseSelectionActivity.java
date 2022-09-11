package Notation.Android;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import CoreStructure.Classe;
import CoreStructure.DataHandler;
import CoreStructure.Eleve;

public class ClasseSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    private HashSet<Classe> classes = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe_selection);
        try {
            xlsReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    protected void xlsReader() throws IOException {

        try{
            final AssetManager as= getBaseContext().getAssets();
            InputStream file = as.open("Classes.xls");
            HSSFWorkbook workbook = new  HSSFWorkbook(file);
            for(int i=0;i<workbook.getNumberOfSheets();i++){
                Set<String> set = new HashSet<>();
                HSSFSheet sheet =workbook.getSheetAt(i);
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
                createButtonDynamicly(sheet.getSheetName());
                classes.add(createClasse(set,sheet.getSheetName()));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    protected Classe createClasse(Set<String> set,String name) throws IOException {
        Classe c = new Classe(name);
        for (String s : set) {
            Eleve e = new Eleve(s, c);
            c.addEleve(e);
        }
        return c;

    }
    protected void saveClassesData(HashSet<Classe> classesHashset){
        DataHandler dataHandler =new DataHandler();
        dataHandler.saveClasses(classesHashset);
    }
    @Override
    public void onClick(View v) {
        Intent notationActivity = new Intent(ClasseSelectionActivity.this, MainActivity.class);
        Button b = (Button)v;
        //TODO faire le passage des classe S à classe
        startActivity(notationActivity);
    }
}