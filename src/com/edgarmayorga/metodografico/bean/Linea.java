/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodografico.bean;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author DellMayorga
 */
public class Linea {
    private int x_inicial;
    private int y_inicial;
    private int x_final;
    private int y_final;
    
    public Linea(){
        
    }
    
    public Linea(int x_inicial, int y_inicial, int x_final, int y_final){
        this.setX_inicial(x_inicial);
        this.setY_inicial(y_inicial);
        this.setX_final(x_final);
        this.setY_final(y_final);
    }
    
    public void dibujar_linea(Graphics g, int x_inicial, int y_inicial, int x_final, int y_final){
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2.0f));
        g2.setColor(Color.blue);
        g.drawLine(getX_inicial(), getY_inicial(), getX_final(), getY_final());
    }

    /**
     * @return the x_inicial
     */
    public int getX_inicial() {
        return x_inicial;
    }

    /**
     * @param x_inicial the x_inicial to set
     */
    public void setX_inicial(int x_inicial) {
        this.x_inicial = x_inicial;
    }

    /**
     * @return the y_inicial
     */
    public int getY_inicial() {
        return y_inicial;
    }

    /**
     * @param y_inicial the y_inicial to set
     */
    public void setY_inicial(int y_inicial) {
        this.y_inicial = y_inicial;
    }

    /**
     * @return the x_final
     */
    public int getX_final() {
        return x_final;
    }

    /**
     * @param x_final the x_final to set
     */
    public void setX_final(int x_final) {
        this.x_final = x_final;
    }

    /**
     * @return the y_final
     */
    public int getY_final() {
        return y_final;
    }

    /**
     * @param y_final the y_final to set
     */
    public void setY_final(int y_final) {
        this.y_final = y_final;
    }
}
