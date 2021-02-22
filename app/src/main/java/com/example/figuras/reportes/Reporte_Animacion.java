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
 * Use the {@link Reporte_Animacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reporte_Animacion extends Fragment  {
    private TableLayout tableLayout;
    private ArrayList<String[]> rows;
    private String[] header2 = {"Animacion","Cantidad"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Reporte_Animacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reporte_Animacion.
     */
    // TODO: Rename and change types and number of parameters
    public static Reporte_Animacion newInstance(String param1, String param2) {
        Reporte_Animacion fragment = new Reporte_Animacion();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflar la vista
        View v = inflater.inflate(R.layout.fragment_reporte__animacion,container,false);

        //obtengo la tabla donde se mostraran los datos
        tableLayout = (TableLayout)v.findViewById(R.id.tableAnimaciones);
        rows = new ArrayList<>();

        //obtengo el parametro que el activity le envia al fragmento
        ArrayList<ReporteDuo> repAnimaciones = (ArrayList<ReporteDuo>) getArguments().getSerializable("rep_animaciones");



        if (repAnimaciones != null){
            System.out.println("repAnimaciones no es null");
            for (ReporteDuo r:
                    repAnimaciones) {

                System.out.println("#: "+r.getItem() + "  , "+r.getCantidad());

            }
        }

        Tabla tabla = new Tabla(tableLayout,getContext());
        tabla.addHeder(header2);

        //verifico que el argumento no sea null, esto para que no se crashee la app
        if (repAnimaciones != null) {
            for (ReporteDuo r : repAnimaciones) {
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