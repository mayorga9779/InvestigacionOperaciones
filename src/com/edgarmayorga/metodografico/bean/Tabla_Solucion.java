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
public class Tabla_Solucion {
    private double punto_x;
    private double punto_y;
    private String fn_restriccion;
    private String resultado;
    private String fn_objetivo;
    private double valor_fn_objetivo;
    
    public Tabla_Solucion(){
        
    }
    
    public Tabla_Solucion(double punto_x, double punto_y, String fn_restriccion, String resultado, String fn_objetivo, double valor_fn_objetivo){
        setPunto_x(punto_x);
        setPunto_y(punto_y);
        setFn_restriccion(fn_restriccion);
        setResultado(resultado);
        setFn_objetivo(fn_objetivo);
        setValor_fn_objetivo(valor_fn_objetivo);
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

    /**
     * @return the fn_restriccion
     */
    public String getFn_restriccion() {
        return fn_restriccion;
    }

    /**
     * @param fn_restriccion the fn_restriccion to set
     */
    public void setFn_restriccion(String fn_restriccion) {
        this.fn_restriccion = fn_restriccion;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the fn_objetivo
     */
    public String getFn_objetivo() {
        return fn_objetivo;
    }

    /**
     * @param fn_objetivo the fn_objetivo to set
     */
    public void setFn_objetivo(String fn_objetivo) {
        this.fn_objetivo = fn_objetivo;
    }

    /**
     * @return the valor_fn_objetivo
     */
    public double getValor_fn_objetivo() {
        return valor_fn_objetivo;
    }

    /**
     * @param valor_fn_objetivo the valor_fn_objetivo to set
     */
    public void setValor_fn_objetivo(double valor_fn_objetivo) {
        this.valor_fn_objetivo = valor_fn_objetivo;
    }
}
