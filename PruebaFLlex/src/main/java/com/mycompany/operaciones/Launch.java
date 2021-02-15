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
        
        String entrada = "$ \n"
                + "  graficar  circulo( 82+2*3,5/3, 163, rojo ) \n"
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
        
        System.out.println("Cantidad de Errores : "+p.getContError());
        System.out.println("Cantidad de Circulos: "+ p.getContCirculo());
        
        
        System.out.println("Lexema ");
        for (ErroresC8 listaErrore : p.getListaErrores()) {
            
        
            
        
            System.out.println(listaErrore.getLexema()+" "+listaErrore.getLinea()+" "+listaErrore.getColumna()+" "+listaErrore.getTipo()+" "+listaErrore.getDescripcion() );
        }
        System.out.println("");
        System.out.println("");
        
        for (Operadores o : p.getOperadoresArit()) {
            
            System.out.println(o.getOperador()+"  -  "+o.getLinea()+"  - "+o.getColumna()+"  -  "+o.getOcurrencia());
            
        }
        
    }
    
}
