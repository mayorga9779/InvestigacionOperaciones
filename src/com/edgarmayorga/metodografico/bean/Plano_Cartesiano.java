/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodografico.bean;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author DellMayorga
 */
public class Plano_Cartesiano {
    private int eje_h_x_inicial;
    private int eje_h_y_incial;
    private int eje_h_x_final;
    private int eje_h_y_final;
    private int eje_v_x_inicial;
    private int eje_v_y_incial;
    private int eje_v_x_final;
    private int eje_v_y_final;
    
    public Plano_Cartesiano(int eje_h_x_inicial, int eje_h_y_incial, int eje_h_x_final, int eje_h_y_final, int eje_v_x_inicial, int eje_v_y_incial, int eje_v_x_final, int eje_v_y_final){
        this.setEje_h_x_inicial(eje_h_x_inicial);
        this.setEje_h_y_incial(eje_h_y_incial);
        this.setEje_h_x_final(eje_h_x_final);
        this.setEje_h_y_final(eje_h_y_final);
        this.setEje_v_x_inicial(eje_v_x_inicial);
        this.setEje_v_y_incial(eje_v_y_incial);
        this.setEje_v_x_final(eje_v_x_final);
        this.setEje_v_y_final(eje_v_y_final);
    }
    
    public void dibujar_plano(Graphics g, int eje_h_x_inicial, int eje_h_y_incial, int eje_h_x_final, int eje_h_y_final, int eje_v_x_inicial, int eje_v_y_incial, int eje_v_x_final, int eje_v_y_final){
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(5.0f));
        g.drawLine(getEje_v_x_inicial(), getEje_v_y_incial(), getEje_v_x_final(), getEje_v_y_final());  //eje Y
        g.drawLine(getEje_h_x_inicial(), getEje_h_y_incial(), getEje_h_x_final(), getEje_h_y_final());   //eje X
    }

    /**
     * @return the eje_h_x_inicial
     */
    public int getEje_h_x_inicial() {
        return eje_h_x_inicial;
    }

    /**
     * @param eje_h_x_inicial the eje_h_x_inicial to set
     */
    public void setEje_h_x_inicial(int eje_h_x_inicial) {
        this.eje_h_x_inicial = eje_h_x_inicial;
    }

    /**
     * @return the eje_h_y_incial
     */
    public int getEje_h_y_incial() {
        return eje_h_y_incial;
    }

    /**
     * @param eje_h_y_incial the eje_h_y_incial to set
     */
    public void setEje_h_y_incial(int eje_h_y_incial) {
        this.eje_h_y_incial = eje_h_y_incial;
    }

    /**
     * @return the eje_h_x_final
     */
    public int getEje_h_x_final() {
        return eje_h_x_final;
    }

    /**
     * @param eje_h_x_final the eje_h_x_final to set
     */
    public void setEje_h_x_final(int eje_h_x_final) {
        this.eje_h_x_final = eje_h_x_final;
    }

    /**
     * @return the eje_h_y_final
     */
    public int getEje_h_y_final() {
        return eje_h_y_final;
    }

    /**
     * @param eje_h_y_final the eje_h_y_final to set
     */
    public void setEje_h_y_final(int eje_h_y_final) {
        this.eje_h_y_final = eje_h_y_final;
    }

    /**
     * @return the eje_v_x_inicial
     */
    public int getEje_v_x_inicial() {
        return eje_v_x_inicial;
    }

    /**
     * @param eje_v_x_inicial the eje_v_x_inicial to set
     */
    public void setEje_v_x_inicial(int eje_v_x_inicial) {
        this.eje_v_x_inicial = eje_v_x_inicial;
    }

    /**
     * @return the eje_v_y_incial
     */
    public int getEje_v_y_incial() {
        return eje_v_y_incial;
    }

    /**
     * @param eje_v_y_incial the eje_v_y_incial to set
     */
    public void setEje_v_y_incial(int eje_v_y_incial) {
        this.eje_v_y_incial = eje_v_y_incial;
    }

    /**
     * @return the eje_v_x_final
     */
    public int getEje_v_x_final() {
        return eje_v_x_final;
    }

    /**
     * @param eje_v_x_final the eje_v_x_final to set
     */
    public void setEje_v_x_final(int eje_v_x_final) {
        this.eje_v_x_final = eje_v_x_final;
    }

    /**
     * @return the eje_v_y_final
     */
    public int getEje_v_y_final() {
        return eje_v_y_final;
    }

    /**
     * @param eje_v_y_final the eje_v_y_final to set
     */
    public void setEje_v_y_final(int eje_v_y_final) {
        this.eje_v_y_final = eje_v_y_final;
    }
}
