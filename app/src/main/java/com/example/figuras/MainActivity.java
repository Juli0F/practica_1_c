package com.example.figuras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.figuras.analizadores.MyLexer;
import com.example.figuras.analizadores.MyLexer_test;
import com.example.figuras.analizadores.ReporteDuo;
import com.example.figuras.analizadores.parser;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtInput;
    private EditText txtInstrucciones;
    private Button btnGraficar;
    private AutoCompleteTextView autoCompleteTextView;
    private String[] autoCompleteText = {"graficar","cuadrado (","circulo (","rectangulo (","linea","poligono (",
            "animar objeto anterior (", "objeto","animar","anterior ("};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput = findViewById(R.id.txtInPut);
    //    txtInstrucciones = findViewById(R.id.txtShow);
        String instrucciones = "Puede Crear las Siguientes \"Figuras:\" \n" +
                "Cuadrado, Rectangulo, Circulo y una Linea\n" +
                "de la Sigueinte Manera\n" +
                "graficar figura (numero,numero)";

        btnGraficar = findViewById(R.id.btnGraficar);






        btnGraficar.setOnClickListener((view) -> {

            try {

            String txtTest =  ""
                    + "  graficar  circulo( 9*2,5/3, 163, rojo ) \n"
                    + " graficar circulo ( 200 + 12 , 25, 15, verde ) "
                    + " graficar circulo ( 400 + 12 , 25, 15, verde ) "
                    + " graficar rectangulo ( 180 , 20, 220,80, negro ) "
                    + " graficar cuadrado ( 300 , 500, 220, negro )"
                    + "animar objeto anterior(1,1,linea)"
                    + "graficar poligono ( 400, 500, 300, 200, 8, negro)\n ";

                    //180, 20, 220, 80
            txtInput.setText(txtTest);


            String txtIn = txtInput.getText().toString();
            System.out.println(txtIn);

//            txtInstrucciones.setText(instrucciones);

            StringReader reader = new StringReader(txtIn);
            MyLexer lexer = new MyLexer(reader);
            parser p = new parser(lexer);
            p.parse();

            List<ReporteDuo> repColor  = new ArrayList<>();
            List<ReporteDuo> repObjetos= new ArrayList<>();;
            List<ReporteDuo> repAnimaciones= new ArrayList<>();;

            //reporte de colores
            repColor.add(new ReporteDuo("Azul", p.getContAzul()+""));
            repColor.add(new ReporteDuo("Amarillo", p.getContAmarillo()+""));
            repColor.add(new ReporteDuo("Rojo", p.getContRojo()+""));
            repColor.add(new ReporteDuo("Verde", p.getContVerde()+""));
            repColor.add(new ReporteDuo("Naranja", p.getContNaranja()+""));
            repColor.add(new ReporteDuo("Morado", p.getContMorado()+""));
            repColor.add(new ReporteDuo("Cafe", p.getContCafe()+""));
            repColor.add(new ReporteDuo("Negro", p.getContNegro()+""));

            //reporte de figuras
            repObjetos.add(new ReporteDuo("Cuadrado", p.getContCuadrado()+""));
            repObjetos.add(new ReporteDuo("Circulo", p.getContCirculo()+"" ));
            repObjetos.add(new ReporteDuo("Rectangulo", p.getContRectangulo()+""));
            repObjetos.add(new ReporteDuo("Poligono", p.getContPoligono()+""));
            repObjetos.add(new ReporteDuo("Linea",  p.getContLinea()+""));

            //reporte animaciones
            repAnimaciones.add(new ReporteDuo("Linea", p.getContAnimacionLinea()+""));
            repAnimaciones.add(new ReporteDuo("Curva", p.getContAnimacionCurva()+""));

            //sentencias
            List<String> sentencias = p.getLstSentencias();

                Intent intent = new Intent(this, Log_View.class);

                Bundle bundle = new Bundle();

                if (!p.getListaErrores().isEmpty()){
                    bundle.putSerializable("rep_errores", (Serializable) p.getListaErrores());
                    intent = new Intent(this, Errores.class);
                }else{
                    //datos que se graficaran
                    System.out.println("Enviando Datos  Ident");

                    bundle.putSerializable("rep_objetos", (Serializable) repObjetos);
                    bundle.putSerializable("rep_aritmeticos", (Serializable) p.getOperadoresArit() );
                    bundle.putSerializable("rep_animaciones", (Serializable) repAnimaciones);
                    bundle.putSerializable("rep_colores", (Serializable) repColor);



                    bundle.putSerializable("sentencias", (Serializable) sentencias);


                }

                intent.putExtras(bundle); //empaquetado de datos
                startActivity(intent); //Start de la nueva actividad


            } catch (Exception e) {
                e.printStackTrace();
            }


        });
    }
}