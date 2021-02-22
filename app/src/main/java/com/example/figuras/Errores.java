package com.example.figuras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.figuras.analizadores.ErroresC8;
import com.example.figuras.tabla.Tabla;

import java.util.ArrayList;

public class Errores extends AppCompatActivity {

    private TableLayout tableLayout;
    private ArrayList<String[]> rows;
    private String[] header2 = {"Lexema","Linea","Columna","Tipo","Descripcion"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errores);

        Intent intent = getIntent();

        ArrayList<ErroresC8> repObjetos = intent.getParcelableArrayListExtra("rep_errores");

        //obtengo la tabla donde se mostraran los datos
        tableLayout = (TableLayout)findViewById(R.id.tableError);
        rows = new ArrayList<>();

        //obtengo el parametro que el activity le envia al fragmento
        ArrayList<ErroresC8> repAnimaciones = intent.getParcelableArrayListExtra("rep_errores");

        Tabla tabla = new Tabla(tableLayout,this);
        tabla.addHeder(header2);

        //verifico que el argumento no sea null, esto para que no se crashee la app
        if (repAnimaciones != null) {
            for (ErroresC8 r : repAnimaciones) {
                rows.add(new String[]{r.getLexema(),r.getLinea(),r.getColumna(),r.getTipo(),r.getDescripcion()});
            }
            tabla.addData(rows,false);
        }


        tabla.backgroundHeader(Color.CYAN);
        tabla.backgroundData(Color.DKGRAY,Color.LTGRAY);

    }
}