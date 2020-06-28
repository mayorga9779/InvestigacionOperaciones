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
public class Celda_Solucion {
    private int costo;
    private int valor_celda;    //aqui se va a guardar la cantidad que se debe mover del origen al destino en el problema
    
    public Celda_Solucion(){
        
    }
    
    public Celda_Solucion(int costo, int valor_celda) {
        setCosto(costo);
        setValor_celda(valor_celda);
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
