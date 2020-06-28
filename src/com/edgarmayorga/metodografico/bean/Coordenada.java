/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodografico.bean;

/**
 *
 * @author DellMayorga
 */
public class Coordenada {
    private double punto_x;
    private double punto_y;
    
    public Coordenada(){
        
    }
    
    public Coordenada(double punto_x, double punto_y){
        setPunto_x(punto_x);
        setPunto_y(punto_y);
    }

    /**
     * @return the punto_x
     */
    public double getPunto_x() {
        return punto_x;
    }

    /**
     * @param punto_x the punto_x to set
     */
    public void setPunto_x(double punto_x) {
        this.punto_x = punto_x;
    }

    /**
     * @return the punto_y
     */
    public double getPunto_y() {
        return punto_y;
    }

    /**
     * @param punto_y the punto_y to set
     */
    public void setPunto_y(double punto_y) {
        this.punto_y = punto_y;
    }
}
