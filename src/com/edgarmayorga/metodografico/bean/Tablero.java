/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodografico.bean;

import java.util.ArrayList;

/**
 *
 * @author DellMayorga
 */
public class Tablero {
    private double x;
    private double y;
    private ArrayList lstFunciones;
    
    public Tablero(){
        
    }
    
    public Tablero(double x, double y, ArrayList lstFunciones){
        setX(x);
        setY(y);
        setLstFunciones(lstFunciones);
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the lstFunciones
     */
    public ArrayList getLstFunciones() {
        return lstFunciones;
    }

    /**
     * @param lstFunciones the lstFunciones to set
     */
    public void setLstFunciones(ArrayList lstFunciones) {
        this.lstFunciones = lstFunciones;
    }
}
