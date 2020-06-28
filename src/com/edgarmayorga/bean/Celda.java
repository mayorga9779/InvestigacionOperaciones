/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.bean;

/**
 *
 * @author DellMayorga
 * Aqui debo utilizar la interfaz Comparable, para poder sobreescribir el metodo compareTo
 * esto con el objetivo de poder ordenar el listado de celdas de menor a mayor.
 * El listado de celdas lo que contiene es un objeto de tipo Celda.
 * Para poder order la lista por el atributo costo del objeto celda
 * Se ordena la lista por el costo de menor a mayor. 
 * El metodo compareTo esta sobreescrito abajo
 * Esta clase me sirve tanto para el metodo de esquina noroeste, costo minimo y Voguel
 */
public class Celda implements Comparable<Celda>{
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
    
    @Override
    public int compareTo(Celda celda) {
        if(getCosto() < celda.costo){
            return -1;
        }
        if(getCosto() > celda.costo){
            return 1;
        }
            
        return 0;
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
