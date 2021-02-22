package com.example.figuras.tabla;

import android.content.Context;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.figuras.analizadores.ErroresC8;

public class Tabla {
    private TableLayout tableLayout;
    private Context context;
    private List<ErroresC8> lstErrores;
    private TableRow tableRow;
    private TextView cell;
    private int indexC;
    private int indexR;
    private String[] header;
    private ArrayList<String[]> data;
    private boolean multicolor = false;

    public Tabla(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }
    public void addHeder(String [] header){
        this.header = header;
        createHeader();

    }
    public void addData(List<ErroresC8> lstErrores){
        this.lstErrores = lstErrores;
    }

    public void addData(ArrayList<String[]> data,boolean min){
        this.data = data;
        createDataTable(min);

    }
    public void newRow(){
        this.tableRow = new TableRow(context);
    }

    public void newCell(){

        this.cell = new TextView(context);
        cell.setGravity(Gravity.CENTER);
        cell.setTextSize(25);

    }
    private  void createHeader(){
        indexC = 0;
        newRow();
        while (indexC < header.length){
            newCell();
            cell.setText(header[indexC++]);
            tableRow.addView(cell,newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable(boolean min){
        String info = "";
        for (int i = 1; i < data.size()+1 ; i++) {
            newRow();
            for (int j = 0; j <header.length ; j++) {
                newCell();
                String [] columns = data.get(i-1);
                int cont =0;
                for (String[] datos:
                     data) {
                    System.out.println("#"+cont+" -"+datos[0]+" --> "+datos[1]);
                }
                info = (j<columns.length)?columns[j]:"";
                cell.setText(info);

                if (min)
                cell.setTextSize(14);

                tableRow.addView(cell,newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }
    private  TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight =1;
        return params;
    }

    public  void backgroundHeader(int color){
        indexC = 0;

        while (indexC < header.length){
            cell = getCell(0,indexC++);
            cell.setBackgroundColor(color);

        }
       // tableLayout.addView(tableRow);
    }
    public void backgroundData(int color,int color2){

        if (data !=null)
        for (int i = 1; i < data.size()+1 ; i++) {
            multicolor = !multicolor;
            for (int j = 0; j <header.length ; j++) {
                cell = getCell(i,j);
                cell.setBackgroundColor((multicolor)?color:color2);
            }
            //tableLayout.addView(tableRow);
        }

    }

    private TextView getCell(int rowInt, int colIndex){

        tableRow = getTableRow(rowInt);
        return  (TextView) tableRow.getChildAt(colIndex);
    }
    private  TableRow getTableRow(int rowIndex){

        return(TableRow)tableLayout.getChildAt(rowIndex);
    }

}
