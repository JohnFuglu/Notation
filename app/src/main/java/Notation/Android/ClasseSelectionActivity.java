package Notation.Android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import CoreStructure.Classe;
import CoreStructure.DataHandler;
import CoreStructure.Eleve;

public class ClasseSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    private final ArrayList<Classe> classes = new ArrayList<>();
    private DataHandler dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe_selection);
        try {
            dh = new DataHandler(getApplicationContext());
            if (getFilesDir().listFiles().length == 0) {
                Toast.makeText(this, "XLS", Toast.LENGTH_SHORT).show();
                xlsReader();
            } else {
                Toast.makeText(this, "LOAD", Toast.LENGTH_SHORT).show();
                loadClasses();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void createButtonDynamicly(String name) {
        Button button = new Button(this);
        int currId = 1000;
        button.setId(currId++);
        button.setText(name);
        LinearLayout lin = findViewById(R.id.classes_linearLay);
        button.setLayoutParams(lin.getLayoutParams());
        button.setOnClickListener(this);
        lin.addView(button);
    }

    @SuppressLint("SuspiciousIndentation")
    protected void xlsReader() throws IOException {

        try {
            final AssetManager as = getBaseContext().getAssets();
            InputStream file = as.open("Classes.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Set<String> set = new HashSet<>();
                HSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.rowIterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (!cell.getStringCellValue().isEmpty())
                            set.add(cell.getStringCellValue());
                    }
                }
                createButtonDynamicly(sheet.getSheetName());
                classes.add(createClasse(set, sheet.getSheetName()));

            }
            Collections.sort(classes);
            saveClassesData(classes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Classe createClasse(Set<String> set, String name) throws IOException {
        Classe c = new Classe(name);
        for (String s : set) {
            Eleve e = new Eleve(s, c);
            c.addEleve(e);
        }
        return c;

    }

    protected void saveClassesData(ArrayList<Classe> classesHashset) {
        for (Classe c : classes) {
            dh.createSerializedClasse(c);
        }
        Toast.makeText(getBaseContext(), "Save to " + getBaseContext().getFilesDir(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        Intent notationActivity = new Intent(ClasseSelectionActivity.this, MainActivity.class);
        Button b = (Button) v;
        notationActivity.putExtra("nom_classe", b.getText());
        startActivity(notationActivity);
    }

    private void loadClasses() throws FileNotFoundException {
        File[] file = getBaseContext().getFilesDir().listFiles();
        ArrayList<File> ar = new ArrayList<>();
        for (File f : file) {
            ar.add(f);
        }
        Collections.sort(ar);
        for (int i = 0; i < file.length; i++) {
            if (file[i].getName().contains("serialized_")) {
                String tmp = file[i].getName();
                String[] s = tmp.split("_");
                classes.add(dh.classeFromFile(s[1]));
                createButtonDynamicly(s[1]);
            }
        }

    }
}