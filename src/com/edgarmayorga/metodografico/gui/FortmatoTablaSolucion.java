/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodografico.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author DellMayorga
 */
public class FortmatoTablaSolucion extends DefaultTableCellRenderer {
    private boolean encontrado_columna = false;
    private boolean encontrado_fila = false;
    private int filas;
    private int columnas;
//    private int contador_fila = 0;
//    private int contador_columna = 0;
    
    public FortmatoTablaSolucion(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
        String valor = String.valueOf(table.getValueAt(row, columnas-1));   //columns-1: obtengo la columna que tiene como titulo "CONCLUSION"
        
        if(String.valueOf(table.getValueAt(row, columnas-1)).equals("NO FACTIBLE")) {   //Si el contenido de la celda es "NO FACTIBLE"
            setBackground(new Color(0xFE899B)); //pinta color rojo
        } else if(String.valueOf(table.getValueAt(row, columnas-1)).equals("FACTIBLE")) {   //Si el contenido de la celda es "FACTIBLE"
            setBackground(new Color(0x00FF00)); //pinta color verde
        }
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);  //Repinto la tabla, para que se vean los cambios
        return this;
    }
}
