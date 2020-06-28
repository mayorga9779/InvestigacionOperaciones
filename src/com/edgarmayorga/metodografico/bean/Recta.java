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
public class Recta {
    private double x_inicial;
    private double y_inicial;
    private double x_final;
    private double y_final;
    private double pendiente;
    
    public Recta(){
        
    }
    
    public Recta(double x_inicial, double y_inicial, double x_final, double y_final, double pendiente){
        setX_inicial(x_inicial);
        setY_inicial(y_inicial);
        setX_final(x_final);
        setY_final(y_final);
        setPendiente(pendiente);
    }

    /**
     * @return the x_incial
     */
    public double getX_inicial() {
        return x_inicial;
    }

    /**
     * @param x_incial the x_incial to set
     */
    public void setX_inicial(double x_incial) {
        this.x_inicial = x_incial;
    }

    /**
     * @return the y_inicial
     */
    public double getY_inicial() {
        return y_inicial;
    }

    /**
     * @param y_inicial the y_inicial to set
     */
    public void setY_inicial(double y_inicial) {
        this.y_inicial = y_inicial;
    }

    /**
     * @return the x_final
     */
    public double getX_final() {
        return x_final;
    }

    /**
     * @param x_final the x_final to set
     */
    public void setX_final(double x_final) {
        this.x_final = x_final;
    }

    /**
     * @return the y_final
     */
    public double getY_final() {
        return y_final;
    }

    /**
     * @param y_final the y_final to set
     */
    public void setY_final(double y_final) {
        this.y_final = y_final;
    }

    /**
     * @return the pendiente
     */
    public double getPendiente() {
        return pendiente;
    }

    /**
     * @param pendiente the pendiente to set
     */
    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }
}
