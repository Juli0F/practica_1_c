package com.example.figuras.reportes;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.figuras.R;
import com.example.figuras.analizadores.ReporteDuo;
import com.example.figuras.tabla.Tabla;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reporte_Objetos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reporte_Objetos extends Fragment {


    private TableLayout tableLayoutAritmeticos;
    private ArrayList<String[]> rowsAritmetics;
    private String[] header = {"Lexema","Linea","Columna","Tipo","Descripcion"};
    private ArrayList<String[]> rows;
    private TableLayout tableLayout;
    private String[] header2 = {"Objeto","Cantidad"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Reporte_Objetos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reporte_Objetos.
     */
    // TODO: Rename and change types and number of parameters
    public static Reporte_Objetos newInstance(String param1, String param2) {
        Reporte_Objetos fragment = new Reporte_Objetos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        tableLayout = (TableLayout) tableLayout;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflar la vista
        View v = inflater.inflate(R.layout.fragment_reporte__objetos,container,false);

        //obtengo la tabla donde se mostraran los datos
        tableLayout = (TableLayout)v.findViewById(R.id.tableObjetos);
        rows = new ArrayList<>();

        //obtengo el parametro que el activity le envia al fragmento
        ArrayList<ReporteDuo> repObjetos = (ArrayList<ReporteDuo>) getArguments().getSerializable("rep_objetos");

        Tabla tabla = new Tabla(tableLayout,getContext());
        tabla.addHeder(header2);

        //verifico que el argumento no sea null, esto para que no se crashee la app
        if (repObjetos != null) {
            for (ReporteDuo r : repObjetos) {
                rows.add(new String[]{r.getItem(), r.getCantidad()});
            }
            tabla.addData(rows,false);
        }


        tabla.backgroundHeader(Color.CYAN);
        tabla.backgroundData(Color.DKGRAY,Color.LTGRAY);


        // Inflate the layout for this fragment
        return v;


    }
}