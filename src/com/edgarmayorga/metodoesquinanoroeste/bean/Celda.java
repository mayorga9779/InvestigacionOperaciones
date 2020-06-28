/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodoesquinanoroeste.bean;

/**
 *
 * @author DellMayorga
 */
public class Celda {
    private int costo;
    private int oferta;
    private int demanda;
    private int fila;
    private int columna;
    private char celda_valida;
    
    public Celda() {
        
    }
    
    public Celda(int costo, int oferta, int demanda, int fila, int columna, char celda_valida) {
        setCosto(costo);
        setOferta(oferta);
        setDemanda(demanda);
        setFila(fila);
        setColumna(columna);
        setCelda_valida(celda_valida);
    }

    /**
     * @return the costo
     */
    public int getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }

    /**
     * @return the oferta
     */
    public int getOferta() {
        return oferta;
    }

    /**
     * @param oferta the oferta to set
     */
    public void setOferta(int oferta) {
        this.oferta = oferta;
    }

    /**
     * @return the demanda
     */
    public int getDemanda() {
        return demanda;
    }

    /**
     * @param demanda the demanda to set
     */
    public void setDemanda(int demanda) {
        this.demanda = demanda;
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
     * @return the celda_valida
     */
    public char getCelda_valida() {
        return celda_valida;
    }

    /**
     * @param celda_valida the celda_valida to set
     */
    public void setCelda_valida(char celda_valida) {
        this.celda_valida = celda_valida;
    }
}
