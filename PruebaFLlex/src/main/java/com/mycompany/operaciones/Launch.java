/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.operaciones;



import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cross
 */
public class Launch {
    
    public static void main(String[] args) {
        
        
        //System.out.println("Resultado "+ Math.pow(5,2));
        
        
        String entrada = "graficar circulo ( 82+43+3+2,/3, 163, rojo ) "
                + " graficar circulo ( 200 + 12 , 25, 15, verde ) "
                + "animar objeto anterior(1,1,curva)";
        StringReader reader = new StringReader(entrada);

        Operaciones lexer = new Operaciones(reader);

        
        parser p = new parser(lexer);

        try {
            p.parse();
        } catch (Exception ex) {

            Logger.getLogger(Launch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Cantidad de Errores : "+p.getContError());
        System.out.println("Cantidad de Circulos: "+ p.getContCirculo());
        
    }
    
}
