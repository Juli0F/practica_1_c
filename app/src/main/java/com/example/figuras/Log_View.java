package com.example.figuras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.figuras.analizadores.Operadores;
import com.example.figuras.analizadores.ReporteDuo;
import com.example.figuras.reportes.Reporte_Animacion;
import com.example.figuras.reportes.Reporte_Objetos;
import com.example.figuras.reportes.Reporte_Operadores;
import com.example.figuras.reportes.colores;

import java.io.Serializable;
import java.util.ArrayList;

public class Log_View extends AppCompatActivity  {

    FragmentTransaction transaction;
    Fragment inicio, objetos, colores, operadores, animaciones;
    Button button;
    private TableLayout tableLayout;
    private ArrayList<String[]> rows;
    private String[] header2 = {"Objeto","Cantidad"};
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__view);
     //   tableLayout = (TableLayout)findViewById(R.id.);
        button = findViewById(R.id.graficar);


        rows = new ArrayList<>();
        objetos = new Reporte_Objetos();
        colores = new colores();
        animaciones = new Reporte_Animacion();
        operadores = new Reporte_Operadores();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentos,objetos).commit();


        Bundle bundle = new Bundle();
        Intent intent = getIntent();

        ArrayList<ReporteDuo> repObjetos = intent.getParcelableArrayListExtra("rep_objetos");
        ArrayList<ReporteDuo> repAnimaciones = intent.getParcelableArrayListExtra("rep_animaciones");
        ArrayList<Operadores> repOperadores = intent.getParcelableArrayListExtra("rep_aritmeticos");
        ArrayList<ReporteDuo> repColores = intent.getParcelableArrayListExtra("rep_colores");
        ArrayList<String> sentencias = intent.getStringArrayListExtra("sentencias");

        System.out.println("Imprimiendo desde Log_View");
        if (sentencias != null){
            for (String string :
                    sentencias) {

                System.out.println("-->"+ string);

            }
        }
        //aca se lo envio al fragmento para que el fragmento lo pueda usar
        Bundle paraFragmento = new Bundle();
        Bundle operdor = new Bundle();
        Bundle bundleColores = new Bundle();


        paraFragmento.putSerializable("rep_objetos" , repObjetos);
        bundle.putSerializable("rep_animaciones",repAnimaciones);
        operdor.putSerializable("rep_operadores" , repOperadores);
        bundleColores.putSerializable("rep_colores" , repColores);

        animaciones.setArguments(bundle);
        objetos.setArguments(paraFragmento);
        operadores.setArguments(operdor);
        colores.setArguments(bundleColores);



        button.setOnClickListener((view)->{

            Intent intentSentencias = new Intent(this, Dibujar_Figuras.class);
            Bundle bundleSentencias = new Bundle();

            bundleSentencias.putSerializable("sentencias",(Serializable) sentencias);

            intentSentencias.putExtras(bundleSentencias); //empaquetado de datos
            startActivity(intentSentencias); //Start de la nueva actividad

        });

    }

    public void onCkick(View view) {

        transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.btnObjetos:
                transaction.replace(R.id.contenedorFragmentos,objetos);
                transaction.addToBackStack(null);
            break;
            case R.id.btnAnimacion:
                transaction.replace(R.id.contenedorFragmentos,animaciones);
                transaction.addToBackStack(null);
                break;
            case R.id.btnColores:
                transaction.replace(R.id.contenedorFragmentos,colores);
                transaction.addToBackStack(null);
                break;
            case R.id.btnOperadores:
                transaction.replace(R.id.contenedorFragmentos,operadores);
                transaction.addToBackStack(null);
                break;


        }

        transaction.commit();
    }




}