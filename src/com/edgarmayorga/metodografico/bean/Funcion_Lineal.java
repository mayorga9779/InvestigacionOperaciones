/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodografico.bean;

/**
 *
 * @author DellMayorga 23/09/2017
 * Aqui se van a guardar todas las funciones lineales que se generen.
 * aX + bY = c
 * donde: 
 * a = coeficienteX
 * b = coeficienteY
 * c = constante
 */
public class Funcion_Lineal {
    private double coeficienteX;
    private double coeficienteY;
    private double constante;
    private String operador_relaciona;
    private String tipo_funcion;    //si es restriccion o funcion objetivo
    
    public Funcion_Lineal(){
        
    }
    
    public Funcion_Lineal(double coeficienteX, double coeficienteY, double constante, String operador_relaciona, String tipo_funcion){
        setCoeficienteX(coeficienteX);
        setCoeficienteY(coeficienteY);
        setConstante(constante);
        setOperador_relaciona(operador_relaciona);
        setTipo_funcion(tipo_funcion);
    }

    /**
     * @return the coeficienteX
     */
    public double getCoeficienteX() {
        return coeficienteX;
    }

    /**
     * @param coeficienteX the coeficienteX to set
     */
    public void setCoeficienteX(double coeficienteX) {
        this.coeficienteX = coeficienteX;
    }

    /**
     * @return the coeficienteY
     */
    public double getCoeficienteY() {
        return coeficienteY;
    }

    /**
     * @param coeficienteY the coeficienteY to set
     */
    public void setCoeficienteY(double coeficienteY) {
        this.coeficienteY = coeficienteY;
    }

    /**
     * @return the constante
     */
    public double getConstante() {
        return constante;
    }

    /**
     * @param constante the constante to set
     */
    public void setConstante(double constante) {
        this.constante = constante;
    }

    /**
     * @return the operador_relaciona
     */
    public String getOperador_relaciona() {
        return operador_relaciona;
    }

    /**
     * @param operador_relaciona the operador_relaciona to set
     */
    public void setOperador_relaciona(String operador_relaciona) {
        this.operador_relaciona = operador_relaciona;
    }

    /**
     * @return the tipo_funcion
     */
    public String getTipo_funcion() {
        return tipo_funcion;
    }

    /**
     * @param tipo_funcion the tipo_funcion to set
     */
    public void setTipo_funcion(String tipo_funcion) {
        this.tipo_funcion = tipo_funcion;
    }
}
