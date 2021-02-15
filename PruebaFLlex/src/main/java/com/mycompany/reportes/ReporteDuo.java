/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import java.util.List;

/**
 *
 * @author cross
 */
public class ReporteDuo {
    
    private String item;
    private String cantidad;
    
    
    public ReporteDuo(String item, String cantidad) {
        this.item = item;
        this.cantidad = cantidad;
    }

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
