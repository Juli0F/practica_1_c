/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.figuras.analizadores;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author cross
 */
public class ReporteDuo implements Parcelable {
    
    private String item;
    private String cantidad;
    
    
    public ReporteDuo(String item, String cantidad) {
        this.item = item;
        this.cantidad = cantidad;
    }

    protected ReporteDuo(Parcel in) {
        item = in.readString();
        cantidad = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item);
        dest.writeString(cantidad);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReporteDuo> CREATOR = new Creator<ReporteDuo>() {
        @Override
        public ReporteDuo createFromParcel(Parcel in) {
            return new ReporteDuo(in);
        }

        @Override
        public ReporteDuo[] newArray(int size) {
            return new ReporteDuo[size];
        }
    };

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
