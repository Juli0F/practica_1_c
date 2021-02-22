package com.example.figuras;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DibujarFiguras extends View  implements Drawable.Callback {

        private ArrayList<String> sentencias;
        private LinkedList<String[]> animaciones;
        private String actual;
        private Canvas canvas;
        private boolean animar;


        public DibujarFiguras(Context context ,  ArrayList<String> sentecias){
            super(context);

            //setContentView(R.layout.activity_figuras);
            this.sentencias = sentecias;
            this.animaciones = new LinkedList<>() ;
            this.actual = "";
            this.animar = false;



            System.out.println("Dibujar Figuras");
        }


        protected void onDraw(Canvas canvas) {

            this.canvas = canvas;
            System.out.println("OnDraw");


           // super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();


            System.out.println("Pantalla: "+ x +" , "+ y);


            if (!sentencias.isEmpty()){

                for (String instruccion: sentencias) {
                    interpretarLinea(canvas, instruccion,false);
                }
            }


        canvas.save();


        }


    private void interpretarLinea(Canvas canvas, String instruccion,boolean inv){

            System.out.println("draw : "+ instruccion);
            Paint paint = new Paint(); // pintura o pincel
            paint.setStyle(Paint.Style.STROKE); // el tipo de trazado
            paint.setStrokeWidth(6);
            Animation animacion = AnimationUtils.loadAnimation(getContext(),R.anim.animacion);


            String datos[] = instruccion.trim().split("[,]");

            switch (datos[0]){
                case "linea":

                    paint.setColor(Color.parseColor(datos[5]));
                    canvas.drawLine(Integer.parseInt(datos[1]),Integer.parseInt(datos[2]),Integer.parseInt(datos[3]),Integer.parseInt(datos[4]),paint);


                    this.actual = instruccion;

                    break;
                case "circulo":

                    paint.setColor(Color.parseColor(datos[4]));
                    canvas.drawCircle(  Integer.parseInt(datos[1]),  Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), paint);

                    this.actual = instruccion;

                    break;
                case "cuadrado":

                    paint.setColor(Color.parseColor(datos[4]));

                    Rect cuadrado = new Rect(Integer.parseInt(datos[1]),Integer.parseInt(datos[2]),Integer.parseInt(datos[1])+Integer.parseInt(datos[3]),Integer.parseInt(datos[2])+Integer.parseInt(datos[3]));
                    canvas.drawRect(cuadrado,paint);


                    this.actual = instruccion;

                    break;
                case "rectangulo":

                    paint.setColor(Color.parseColor(datos[5]));
                    Rect rect = new Rect(Integer.parseInt(datos[1]),Integer.parseInt(datos[2]),Integer.parseInt(datos[1])+Integer.parseInt(datos[3]),Integer.parseInt(datos[2])+Integer.parseInt(datos[4]));
                    canvas.drawRect(rect,paint);

                    this.actual = instruccion;

                    break;
                case "poligono":

                    paint.setColor(Color.parseColor(datos[6]));

                    //centro en x,y
                    int posX = Integer.parseInt(datos[1]);
                    int posY = Integer.parseInt(datos[2]);

                    //lados del rectangulo en el cual debe encajar el poligono
                    int alto = Integer.parseInt(datos[3]);
                    int ancho = Integer.parseInt(datos[4]);

                    //cantidad de lados que tiene el poligono
                    int cantLados = Integer.parseInt(datos[5]);

                    dibujarPoligono(canvas, paint, posX, posY, alto, ancho, cantLados);

                    canvas.save();

                    this.actual = instruccion;

                    break;
                case "animacion":
                    if (!actual.equals("")){
                        animaciones.offer(new String[]
                                {actual,//instruccion
                                 instruccion}//animacion
                        );
                        this.actual = "";
                    }
                    break;

            }





        }

        public void dibujarPoligono( Canvas canvas,Paint paint, int posX, int posY, int alto, int ancho, int cantLados ){


            //obtengo el angulo que exite entre los triangulos que estan en el poligono
            double angulo = 360/cantLados;
            //radio y lado de uno de los triangulos internos
            int radio = (alto<ancho)?alto:ancho;

            //los triangulos internos del poligono son isoceles y conozco el angulo entre dos de sus lados
            //y asi con ley de cosenos encuentro el lado opuesto,
            //lo que seria la linea que forma el poligono
            double linea = (Math.sqrt( Math.pow(radio,2) +Math.pow(radio,2)  - 2*radio*radio* (Math.cos(angulo* Math.PI/180))));

            //encuentro el resto de los angulos para encontrar otro minitriangulo interno
            double anguloInterno = (180-angulo)/2;


            canvas.save();

            double posXT = (posX+radio)-linea* Math.cos(anguloInterno*Math.PI/180);
            double posYT = (posY)+linea* Math.sin(anguloInterno*Math.PI/180);


            for (int i = 0; i < cantLados; i++) {

                canvas.rotate((float) angulo, posX,posY);//funciona no borra
                canvas.drawLine((float)(posX+radio),(float)(posY),(float) (posXT), (float) (posYT),paint);//funciona no borrar

            }
        }
        public void animacion(Canvas canvas)  {
            //[0] instruccion
            //[1] animacion

            invalidate();

            while(animaciones.peek() != null){
                String [] animacionExec = animaciones.poll();


                if (animacionExec[1].contains("linea")){


                    int contX = 0;
                    int contY = 0;
                    int cont = 0;
                    ///*

                    String instruccionSplit[] = animacionExec[0].split("[,]");;
                    String animacionSplit[] = animacionExec[1].split("[,]");


                    contX = Integer.parseInt(instruccionSplit[1]);
                    contY = Integer.parseInt(instruccionSplit[2]);

                    String paintWhiteFig = instruccionSplit[0] + ","+contX +","+contY;
                    String paintMove = animacionSplit[0] + ","+(contX+1) +","+(contY+1);

                    for (int i = 3; i < instruccionSplit.length ; i++) {


                        if (i<(instruccionSplit.length-1)){
                            paintWhiteFig = paintWhiteFig +"," +instruccionSplit[i];
                            paintMove = paintMove +","+instruccionSplit[i];
                        }else{
                            paintWhiteFig = paintWhiteFig +",#ee3a14";
                            paintMove = paintMove +","+instruccionSplit[i];
                        }
                    }

                while(cont<10){



                    System.out.println("paintNewFigAnimation : "+paintWhiteFig);
                    System.out.println("AnimacionExec : "+paintMove);



                        try {

                            interpretarLinea(canvas, paintWhiteFig,true);

                            Thread.sleep(1000);
                            interpretarLinea(canvas, paintMove,true);
                            Thread.sleep(1000);
                            invalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cont++;
                        contX++;
                        contY++;

                    paintWhiteFig = instruccionSplit[0] + ","+contX +","+contY;
                    String instruccionTemp[] = paintWhiteFig.split("[,]");

                    paintWhiteFig = instruccionSplit[0] + ","+contX +","+contY;

                    for (int i = 3; i < instruccionSplit.length ; i++) {


                        if (i<(instruccionSplit.length-1)){
                            paintWhiteFig = paintWhiteFig +"," +instruccionSplit[i];
                            paintMove = paintMove +","+instruccionSplit[i];
                        }else{
                            paintWhiteFig = paintWhiteFig +",#ffffff";
                            paintMove = paintMove +","+instruccionSplit[i];
                        }
                    }
                    }

                }else{

                }
            }
        }

    public Canvas getCanvas(){return canvas;}


    }

