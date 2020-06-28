/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodosimplex_dual.bean;

/**
 *
 * @author DellMayorga
 */
public class Celda {
    private int valor_celda;
    private int valor_constante;
    private String variable_entrada;
    private String variable_salida;
    private double pivote;
    private boolean celda_valida;
    private int fila;
    private int columna;
    
    public Celda(){
        
    }
    
    public Celda(int valor_celda, int valor_constante, String variable_entrada, String variable_salida, double pivote, boolean celda_valida, int fila, int columna){
        setValor_celda(valor_celda);
        setValor_constante(valor_constante);
        setVariable_entrada(variable_entrada);
        setVariable_salida(variable_salida);
        setPivote(pivote);
        setCelda_valida(celda_valida);
        setFila(fila);
        setColumna(columna);
    }

    /**
     * @return the valor_celda
     */
    public int getValor_celda() {
        return valor_celda;
    }

    /**
     * @param valor_celda the valor_celda to set
     */
    public void setValor_celda(int valor_celda) {
        this.valor_celda = valor_celda;
    }

    /**
     * @return the valor_constante
     */
    public int getValor_constante() {
        return valor_constante;
    }

    /**
     * @param valor_constante the valor_constante to set
     */
    public void setValor_constante(int valor_constante) {
        this.valor_constante = valor_constante;
    }

    /**
     * @return the variable_entrada
     */
    public String getVariable_entrada() {
        return variable_entrada;
    }

    /**
     * @param variable_entrada the variable_entrada to set
     */
    public void setVariable_entrada(String variable_entrada) {
        this.variable_entrada = variable_entrada;
    }

    /**
     * @return the variable_salida
     */
    public String getVariable_salida() {
        return variable_salida;
    }

    /**
     * @param variable_salida the variable_salida to set
     */
    public void setVariable_salida(String variable_salida) {
        this.variable_salida = variable_salida;
    }

    /**
     * @return the pivote
     */
    public double getPivote() {
        return pivote;
    }

    /**
     * @param pivote the pivote to set
     */
    public void setPivote(double pivote) {
        this.pivote = pivote;
    }

    /**
     * @return the celda_valida
     */
    public boolean getCelda_valida() {
        return celda_valida;
    }

    /**
     * @param celda_valida the celda_valida to set
     */
    public void setCelda_valida(boolean celda_valida) {
        this.celda_valida = celda_valida;
    }

    /**
     * @return the fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }
}
