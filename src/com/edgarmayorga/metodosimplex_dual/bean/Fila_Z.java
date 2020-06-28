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
public class Fila_Z {
    private int fila;
    private int columna;
    private int valor_celda;
    
    public Fila_Z(int fila, int columna, int valor_celda){
        
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
}
