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
public class ErroresC8 implements Parcelable, Serializable {
    private String lexema;
    private String linea;
    private String columna;
    private String tipo;
    private String descripcion;

    public ErroresC8(String lexema, String linea, String columna, String tipo, String descripcion) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    protected ErroresC8(Parcel in) {
        lexema = in.readString();
        linea = in.readString();
        columna = in.readString();
        tipo = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<ErroresC8> CREATOR = new Creator<ErroresC8>() {
        @Override
        public ErroresC8 createFromParcel(Parcel in) {
            return new ErroresC8(in);
        }

        @Override
        public ErroresC8[] newArray(int size) {
            return new ErroresC8[size];
        }
    };

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lexema);
        dest.writeString(linea);
        dest.writeString(columna);
        dest.writeString(tipo);
        dest.writeString(descripcion);
    }
}
