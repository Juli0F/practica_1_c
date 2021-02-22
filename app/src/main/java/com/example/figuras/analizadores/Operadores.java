/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.figuras.analizadores;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 *
 * @author cross
 */
public class Operadores implements Serializable,Parcelable {
    
    private String operador;
    private String linea;
    private String columna;
    private String ocurrencia;

    public Operadores(String operador, String linea, String columna, String ocurrencia) {
        this.operador = operador;
        this.linea = linea;
        this.columna = columna;
        this.ocurrencia = ocurrencia;
    }

    protected Operadores(Parcel in) {
        operador = in.readString();
        linea = in.readString();
        columna = in.readString();
        ocurrencia = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(operador);
        dest.writeString(linea);
        dest.writeString(columna);
        dest.writeString(ocurrencia);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Operadores> CREATOR = new Creator<Operadores>() {
        @Override
        public Operadores createFromParcel(Parcel in) {
            return new Operadores(in);
        }

        @Override
        public Operadores[] newArray(int size) {
            return new Operadores[size];
        }
    };

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getOcurrencia() {
        return ocurrencia;
    }

    public void setOcurrencia(String ocurrencia) {
        this.ocurrencia = ocurrencia;
    }
    
    
    
    
}
