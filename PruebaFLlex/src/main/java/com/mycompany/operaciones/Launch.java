/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.operaciones;

import com.mycompany.reportes.Operadores;
import com.mycompany.reportes.ReporteDuo;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cross
 */
public class Launch {

    public static void main(String[] args) {

        //System.out.println("Resultado "+ Math.pow(5,2));
        List<ReporteDuo> lst = new ArrayList<>();

        String entrada = ""
                + "  graficar  circulo(9*2,5/3, 163, rojo ) \n"
                + " graficar circulo ( 200 + 12 , 25, 15, verde ) "
                + " graficar circulo ( 400 + 12 , 25, 15, verde ) "
                + "animar objeto anterior(1,1,curva)";
        StringReader reader = new StringReader(entrada);

        MyLexer lexer = new MyLexer(reader);

        parser p = new parser(lexer);

        try {
            p.parse();
        } catch (Exception ex) {

            Logger.getLogger(Launch.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Cantidad de Errores : " + p.getContError());
        System.out.println("Cantidad de Circulos: " + p.getContCirculo());
        System.out.println("Cantidad de Cuadrados : " + p.getContCuadrado());
        System.out.println("Cantidad de Rectangulo : " + p.getContRectangulo());
        System.out.println("Cantidad de Poligonos :" + p.getContPoligono());
        System.out.println("Cantidad de Linea : " + p.getContLinea());
        
        
        
	
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
        repObjetos.add(new ReporteDuo("Cudrado", p.getContCuadrado()+""));
        repObjetos.add(new ReporteDuo("Circulo", p.getContCirculo()+"" ));
        repObjetos.add(new ReporteDuo("Rectangulo", p.getContRectangulo()+""));
        repObjetos.add(new ReporteDuo("Poligono", p.getContPoligono()+""));
        repObjetos.add(new ReporteDuo("Linea",  p.getContLinea()+""));
        
        //reporte animaciones
        repAnimaciones.add(new ReporteDuo("Linea", p.getContAnimacionLinea()+""));
        repAnimaciones.add(new ReporteDuo("Curva", p.getContAnimacionCurva()+""));

        //reporte erroes
        for (ErroresC8 listaErrore : p.getListaErrores()) {
            System.out.println(listaErrore.getLexema() + " " + listaErrore.getLinea() + " " + listaErrore.getColumna() + " " + listaErrore.getTipo() + " " + listaErrore.getDescripcion());
        }
        
        System.out.println("");
        System.out.println("");

        //reporte operadores
        for (Operadores o : p.getOperadoresArit()) {

            System.out.println(o.getOperador() + "  -  " + o.getLinea() + "  - " + o.getColumna() + "  -  " + o.getOcurrencia());

        }

        //sentencias
        for (String sentencia : p.getLstSentencias()) {
            System.out.println("Sentencia: " + sentencia);
        }
    }

}
