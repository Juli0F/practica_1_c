
package com.mycompany.operaciones;

import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*;
import java.io.Reader;

/*
Agregar la importacion del paquete
*/

%%

%public 
%class MyLexer
%cup
%unicode
%line
%column

/*MACROS */

DIGITO = [0-9]

SALTO_LINEA = [ \r\t\b\f\n]
//[ \r\t\b\f\n]

%{
	private List<ErroresC8> err;
%}



%init{
    err = new ArrayList<>();
%init}

%{

public List<ErroresC8> getListaErroresLexicos(){
	return this.err;
	}
%}

%%

/*Reglas Lexicas*/
<YYINITIAL> {


{DIGITO}+ {return new Symbol (sym.ENTERO, yyline+1,yycolumn+1,Integer.parseInt(yytext())); }

"+" {return new Symbol(sym.SUMA,yyline+1, yycolumn+1,(yytext()));}
"-" {return new Symbol(sym.RESTA,yyline+1, yycolumn+1,(yytext()));}

"*" {return new Symbol(sym.MULTIPLICACION,yyline+1, yycolumn+1,(yytext()));}
"/" {return new Symbol(sym.DIVISION,yyline+1, yycolumn+1,(yytext()));}
"," {return new Symbol(sym.COMA,yyline+1, yycolumn+1, yytext());}
//";" {return new Symbol(sym.PUNTO_COMA,yyline+1, yycolumn+1);}
//"**" {return new Symbol(sym.POTENCIA,yyline+1, yycolumn+1);}
//"^" {return new Symbol(sym.POTENCIA,yyline+1, yycolumn+1);}

"(" {return new Symbol(sym.PAR_ABIERTO,yyline+1, yycolumn+1,(yytext()));}
")" {return new Symbol(sym.PAR_CERRADO,yyline+1, yycolumn+1,(yytext()));}
//
"graficar" {return new Symbol(sym.GRAFICAR,yyline+1, yycolumn+1,(yytext()));}
"animar" {return new Symbol(sym.ANIMAR,yyline+1, yycolumn+1,(yytext()));}
"objeto" {return new Symbol(sym.OBJETO,yyline+1, yycolumn+1,(yytext()));}
"anterior" {return new Symbol(sym.ANTERIOR,yyline+1, yycolumn+1,(yytext()));}
//FIGURAS
"circulo" {return new Symbol(sym.CIRCULO,yyline+1, yycolumn+1,(yytext()));}
"cuadrado" {return new Symbol(sym.CUADRADO,yyline+1, yycolumn+1,(yytext()));}
"rectangulo" {return new Symbol(sym.RECTANGULO,yyline+1, yycolumn+1,(yytext()));}
"linea" {return new Symbol(sym.LINEA,yyline+1, yycolumn+1,(yytext()));}
"poligono" {return new Symbol(sym.POLIGONO,yyline+1, yycolumn+1,(yytext()));}
//COLORES
"azul" {return new Symbol(sym.AZUL,yyline+1, yycolumn+1,("#1E90FF"));}
"rojo" {return new Symbol(sym.ROJO,yyline+1, yycolumn+1,("#FF0000"));}
"verde" {return new Symbol(sym.VERDE,yyline+1, yycolumn+1,("#008000"));}
"amarillo" {return new Symbol(sym.AMARILLO,yyline+1, yycolumn+1,("#FFFF99"));}
"naranja" {return new Symbol(sym.NARANJA,yyline+1, yycolumn+1,("#FFA500"));}
"morado" {return new Symbol(sym.MORADO,yyline+1, yycolumn+1,("#800080"));}
"cafe" {return new Symbol(sym.CAFE,yyline+1, yycolumn+1,("#F4A460"));}
"negro" {return new Symbol(sym.NEGRO,yyline+1, yycolumn+1,("#000000"));}
//tipo de animacion

"curva" {return new Symbol(sym.CURVA,yyline+1, yycolumn+1,(yytext()));}


//";" {return new Symbol(sym.SEMICOLON,yyline+1, yycolumn+1,(yytext()));}

//"," {return new Symbol(sym.COMA,yyline+1, yycolumn+1,(yytext()));}

{SALTO_LINEA} {/*IGNORAR	*/}

//
[^] 	 {err.add(new ErroresC8(yytext(),String.valueOf((yyline+1)),String.valueOf((yycolumn+1)),"Lexico","No se reconoce en el Lenguaje")) ;}
<<EOF>>             { return new Symbol(sym.EOF); }

}
/*
s ::=  ENTERO SUMA ENTERO {: System.out.println("ENTERO SUMA ENTERO");:} 
	| ENTERO SUMA ENTERO COMA s {: System.out.println("ENTERO SUMA ENTERO,COMA s");:} 
	;
*/	


