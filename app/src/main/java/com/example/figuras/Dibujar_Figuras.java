package com.example.figuras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;

public class Dibujar_Figuras extends AppCompatActivity{

    private Button button;
    private DibujarFiguras fondo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujar__figuras);

        ConstraintLayout layout1 =  findViewById(R.id.layout);
         button = findViewById(R.id.btnAnimarFiguras);

        Intent intent = getIntent();
        ArrayList<String> sentencias = intent.getStringArrayListExtra("sentencias");



        Paint paint = new Paint(); // pintura o pincel
        paint.setStyle(Paint.Style.STROKE); // el tipo de trazado
        paint.setStrokeWidth(6);
        paint.setColor(Color.parseColor("#cb3234"));



        fondo = new DibujarFiguras(this,sentencias);
        layout1.addView(fondo);

        button.setOnClickListener((view) ->{

            System.out.println("Apply invalidate");
           // fondo.invalidate();


            Canvas canvas = fondo.getCanvas();
          //  canvas.drawLine(0,0,100,100,paint);
        canvas.save();

        fondo.animacion(fondo.getCanvas());



        });


        Animation animacion = AnimationUtils.loadAnimation(this,R.anim.animacion);






    }

    public void animacion(){}

}