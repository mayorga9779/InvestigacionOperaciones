/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodoesquinanoroeste.gui;

import com.edgarmayorga.bean.Celda;
import com.edgarmayorga.metodoesquinanoroeste.bean.Celda_Solucion;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author DellMayorga
 */
public class Pnl_Esquina_Noroeste extends javax.swing.JPanel {
    private int numero_filas;
    private int numero_columnas;
    private int suma_oferta;
    private int suma_demanda;
    private int contador;
    private JTextField[][] txtCostos;
    private JTextField[][] txtResultados;
    private JLabel lblOferta;
    private JLabel lblDemanda;
    private JLabel lblTitulo;
    private JLabel lblTitulo1;
    private JSeparator separador;
    private Celda celda;
    private Celda_Solucion celda_solucion;
    private ArrayList<Celda> lstCeldas;
    private ArrayList<Celda_Solucion> lstCeldas_Solucion;

    /**
     * Creates new form Pnl_Esquina_Noroeste
     */
    public Pnl_Esquina_Noroeste() {
        initComponents();
        pnlParametros.setSize(270, 950);
        pnlSolucionador.setSize(1600, 950);
        lstCeldas = new ArrayList<Celda>();
        lstCeldas_Solucion = new ArrayList<Celda_Solucion>();
        btnIterar.setVisible(false);
        setContador(0);
    }
    
    public void generar_tabla_costos(int filas, int columnas, int suma_oferta, int suma_demanda) {
        int fila = 60;
        int columna = 100;
        
        lblTitulo1 = new JLabel();
        lblTitulo1.setBounds(100, 25, 200, 25);
        lblTitulo1.setText("MATRIZ DE COSTOS");
        lblTitulo1.setForeground(Color.red);
        pnlSolucionador.add(lblTitulo1);
        lblTitulo1.setVisible(true);
        
        if(suma_oferta == suma_demanda) {   //si la suma de la oferta es igual a la suma de demanda, se crea matriz normal
            txtCostos = new JTextField[filas + 1][columnas + 1];
            txtResultados = new JTextField[filas][columnas];
            
            for(int i = 0; i < filas + 1; i++) {
                for(int j = 0; j < columnas + 1; j++) {
                    txtCostos[i][j] = new JTextField();
                    txtCostos[i][j].setBounds(columna, fila, 50, 25);
                    txtCostos[i][j].setHorizontalAlignment(JTextField.CENTER);
                    pnlSolucionador.add(txtCostos[i][j]);
                    txtCostos[i][j].setVisible(true);
                    columna = columna + 50;
                    //Cambio de color a la fila y a la columna de oferta y de demanda
                    if(j == columnas) { //columnas de oferta
                        txtCostos[i][j].setFont(txtCostos[i][j].getFont().deriveFont(16f));
                        txtCostos[i][j].setBackground(Color.yellow);
                    }
                    if(i == filas) {    //fila de demanda
                        txtCostos[i][j].setFont(txtCostos[i][j].getFont().deriveFont(16f));
                        txtCostos[i][j].setBackground(Color.yellow);
                    }
                    //Agrego la etiqueta de la columna de Oferta
                    if(j == columnas - 1) {
                        lblOferta = new JLabel();
                        lblOferta.setBounds(columna, 25, 100, 25);
                        lblOferta.setText("Oferta");
                        pnlSolucionador.add(lblOferta);
                        lblOferta.setVisible(true);
                    }
                    //Oculto el ultimo textfield que es la intersección entre la oferta y la demanda
                    if((i == filas) && j == columnas)   {
                        txtCostos[i][j].setVisible(false);
                    }
                }
                
                columna = 100;
                fila = fila + 30;
                //Agrego la etiqueta de la fila de demandas
                if(i == filas - 1) {
                    lblDemanda = new JLabel();
                    lblDemanda.setBounds(25, fila, 100, 25);
                    lblDemanda.setText("Demanda");
                    pnlSolucionador.add(lblDemanda);
                    lblDemanda.setVisible(true);
                    
                    separador = new JSeparator();
                    separador.setBounds(5, fila + 40, pnlSolucionador.getWidth() - 10, 5);
                    pnlSolucionador.add(separador);
                    separador.setVisible(true);
                    
                    lblTitulo = new JLabel();
                    lblTitulo.setBounds(100, fila + 35, 200, 50);
                    lblTitulo.setText("MATRIZ SOLUCION");
                    lblTitulo.setForeground(Color.red);
                    pnlSolucionador.add(lblTitulo);
                    lblTitulo.setVisible(true);
                }
            }
            txtCostos[0][0].requestFocus();
            //Generando la tabla en donde se van a poner los resultados (matriz de resultados)
            fila = fila + 50;
            for(int i = 0; i < getNumero_filas(); i++) {
                for(int j = 0; j < getNumero_columnas(); j++) {
                    txtResultados[i][j] = new JTextField();
                    txtResultados[i][j].setBounds(columna, fila, 50, 25);
                    txtResultados[i][j].setHorizontalAlignment(JTextField.CENTER);
                    txtResultados[i][j].setBackground(new Color(0xCEF6E3));
                    pnlSolucionador.add(txtResultados[i][j]);
                    txtResultados[i][j].setVisible(true);
                    columna = columna + 50;
                }
                columna = 100;
                fila = fila + 30;
            }
        } else if(suma_oferta > suma_demanda) {  //si la oferta es mayor que la demanda, hay que crear una fila ficticia en la demanda
            txtCostos = new JTextField[filas + 1][columnas + 2];
            txtResultados = new JTextField[filas][columnas + 1];
            
            for(int i = 0; i < filas + 1; i++) {
                for(int j = 0; j < columnas + 2; j++) {
                    txtCostos[i][j] = new JTextField();
                    txtCostos[i][j].setBounds(columna, fila, 50, 25);
                    txtCostos[i][j].setHorizontalAlignment(JTextField.CENTER);
                    pnlSolucionador.add(txtCostos[i][j]);
                    txtCostos[i][j].setVisible(true);
                    columna = columna + 50;
                    //Cambio de color a la fila y a la columna de oferta y de demanda
                    if(j == columnas + 1) { //columnas de oferta
                        txtCostos[i][j].setFont(txtCostos[i][j].getFont().deriveFont(16f));
                        txtCostos[i][j].setBackground(Color.yellow);
                    }
                    if(i == filas) {    //fila de demanda
                        txtCostos[i][j].setFont(txtCostos[i][j].getFont().deriveFont(16f));
                        txtCostos[i][j].setBackground(Color.yellow);
                    }
                    //Agrego la etiqueta de la columna de Oferta
                    if(j == columnas) {
                        lblOferta = new JLabel();
                        lblOferta.setBounds(columna, 25, 100, 25);
                        lblOferta.setText("Oferta");
                        pnlSolucionador.add(lblOferta);
                        lblOferta.setVisible(true);
                    }
                    //para la columna ficticia, porque la oferta es mayor que la demanda
                    if(j == columnas) {
                        txtCostos[i][j].setBackground(new Color(0xF781F3));
                        txtCostos[i][j].setText(String.valueOf(0));
                    }
                    //para poner el valor de la dif entre la oferta y la demanda en la celda de demanda ficticia
                    if((i == columnas) && j == filas) {
                        txtCostos[i][j].setText(String.valueOf(suma_oferta - suma_demanda));
                    }
                    //Oculto el ultimo textfield que es la intersección entre la oferta y la demanda
                    if((i == filas) && j == columnas + 1)   {
                        txtCostos[i][j].setVisible(false);
                    }
                }
                
                columna = 100;
                fila = fila + 30;
                //Agrego la etiqueta de la fila de demandas
                if(i == filas - 1) {
                    lblDemanda = new JLabel();
                    lblDemanda.setBounds(25, fila, 100, 25);
                    lblDemanda.setText("Demanda");
                    pnlSolucionador.add(lblDemanda);
                    lblDemanda.setVisible(true);
                    
                    separador = new JSeparator();
                    separador.setBounds(5, fila + 40, pnlSolucionador.getWidth() - 10, 5);
                    pnlSolucionador.add(separador);
                    separador.setVisible(true);
                    
                    lblTitulo = new JLabel();
                    lblTitulo.setBounds(100, fila + 35, 200, 50);
                    lblTitulo.setText("MATRIZ SOLUCION");
                    lblTitulo.setForeground(Color.red);
                    pnlSolucionador.add(lblTitulo);
                    lblTitulo.setVisible(true);
                }
            }
            txtCostos[0][0].requestFocus();
            //Generando la tabla en donde se van a poner los resultados (matriz de resultados)
            fila = fila + 50;
            for(int i = 0; i < getNumero_filas(); i++) { 
                for(int j = 0; j < getNumero_columnas() + 1; j++) { //se agrego 1 por la columna adicional
                    txtResultados[i][j] = new JTextField();
                    txtResultados[i][j].setBounds(columna, fila, 50, 25);
                    txtResultados[i][j].setHorizontalAlignment(JTextField.CENTER);
                    txtResultados[i][j].setBackground(new Color(0xCEF6E3));
                    pnlSolucionador.add(txtResultados[i][j]);
                    txtResultados[i][j].setVisible(true);
                    columna = columna + 50;
                }
                columna = 100;
                fila = fila + 30;
            }
        } else if(suma_oferta < suma_demanda) { //si la oferta es menor que la demanda, ha que crear una columna ficticia en la oferta
            //txtCostos = new JTextField[filas + 1][columnas + 2];
            txtCostos = new JTextField[filas + 2][columnas + 1];
            txtResultados = new JTextField[filas + 1][columnas];
            
            for(int i = 0; i < filas + 2; i++) {
                for(int j = 0; j < columnas + 1; j++) {
                    txtCostos[i][j] = new JTextField();
                    txtCostos[i][j].setBounds(columna, fila, 50, 25);
                    txtCostos[i][j].setHorizontalAlignment(JTextField.CENTER);
                    pnlSolucionador.add(txtCostos[i][j]);
                    txtCostos[i][j].setVisible(true);
                    columna = columna + 50;
                    //Cambio de color a la fila y a la columna de oferta y de demanda
                    if(j == columnas) { //columnas de oferta
                        txtCostos[i][j].setFont(txtCostos[i][j].getFont().deriveFont(16f));
                        txtCostos[i][j].setBackground(Color.yellow);
                    }
                    if(i == filas + 1) {    //fila de demanda
                        txtCostos[i][j].setFont(txtCostos[i][j].getFont().deriveFont(16f));
                        txtCostos[i][j].setBackground(Color.yellow);
                    }
                    //Agrego la etiqueta de la columna de Oferta
                    if(j == columnas + 1) {
                        lblOferta = new JLabel();
                        lblOferta.setBounds(columna, 25, 100, 25);
                        lblOferta.setText("Oferta");
                        pnlSolucionador.add(lblOferta);
                        lblOferta.setVisible(true);
                    }
                    //para la fila ficticia, porque la oferta es mayor que la demanda
                    if(i == filas) {
                        txtCostos[i][j].setBackground(new Color(0xF781F3));
                        txtCostos[i][j].setText(String.valueOf(0));
                    }
                    //para poner el valor de la dif entre la oferta y la demanda en la celda de demanda ficticia
                    if((i == filas) && j == columnas) {
                        txtCostos[i][j].setText(String.valueOf(suma_demanda - suma_oferta));
                    }
                    //Oculto el ultimo textfield que es la intersección entre la oferta y la demanda
                    if((i == filas + 1) && j == columnas)   {
                        txtCostos[i][j].setVisible(false);
                    }
                }
                
                columna = 100;
                fila = fila + 30;
                //Agrego la etiqueta de la fila de demandas
                if(i == filas) {
                    lblDemanda = new JLabel();
                    lblDemanda.setBounds(25, fila, 100, 25);
                    lblDemanda.setText("Demanda");
                    pnlSolucionador.add(lblDemanda);
                    lblDemanda.setVisible(true);
                    
                    separador = new JSeparator();
                    separador.setBounds(5, fila + 40, pnlSolucionador.getWidth() - 10, 5);
                    pnlSolucionador.add(separador);
                    separador.setVisible(true);
                    
                    lblTitulo = new JLabel();
                    lblTitulo.setBounds(100, fila + 35, 200, 50);
                    lblTitulo.setText("MATRIZ SOLUCION");
                    lblTitulo.setForeground(Color.red);
                    pnlSolucionador.add(lblTitulo);
                    lblTitulo.setVisible(true);
                }
            }
            txtCostos[0][0].requestFocus();
            //Generando la tabla en donde se van a poner los resultados (matriz resultados)
            fila = fila + 50;
            for(int i = 0; i < getNumero_filas() + 1; i++) { //se agrego +1
                for(int j = 0; j < getNumero_columnas(); j++) {
                    txtResultados[i][j] = new JTextField();
                    txtResultados[i][j].setBounds(columna, fila, 50, 25);
                    txtResultados[i][j].setHorizontalAlignment(JTextField.CENTER);
                    txtResultados[i][j].setBackground(new Color(0xCEF6E3));
                    pnlSolucionador.add(txtResultados[i][j]);
                    txtResultados[i][j].setVisible(true);
                    columna = columna + 50;
                }
                columna = 100;
                fila = fila + 30;
            }
        }
        
        repaint();
    }
    
    public void resolver_matriz(ArrayList<Celda> lstCeldas) {
        boolean evaluar = false;
        int cantidad_celdas = lstCeldas.size();
        for(int i = 0; i < lstCeldas.size(); i++) { //recorro el array para verificar si todos las celdas estan en N
            if(lstCeldas.get(i).getCelda_valida() == 'N') {
                evaluar = false;
            } else if (lstCeldas.get(i).getCelda_valida() == 'S') {
                evaluar = true;
                break;
            }
        }
        
        if(evaluar == true) {   //nos sirve para realizar paso por paso la solucion
            if(lstCeldas.size() > 0) {
                int costo = 0;
                int oferta = 0;
                int demanda = 0;
                int fila = 0;
                int columna = 0;
                int posicion = 0;
                int col = 0;
                int fil = 0;

                for(int i = 0; i < lstCeldas.size(); i++) { //evaluamos solo los nodos que tienen propiedad S
                    if(lstCeldas.get(i).getCelda_valida() == 'S') {
                        costo = lstCeldas.get(i).getCosto();
                        oferta = lstCeldas.get(i).getOferta();
                        demanda = lstCeldas.get(i).getDemanda();
                        fila = lstCeldas.get(i).getFila();
                        columna = lstCeldas.get(i).getColumna();
                        posicion = i;
                        i = lstCeldas.size();   //con esto rompo el ciclo, para que cuente cada click en el boton
                        break;
                    }
                }
                if(oferta == demanda) { //cuando la oferta es igual que la demanda
                    lstCeldas.get(posicion).setOferta(0);
                    lstCeldas.get(posicion).setDemanda(0);
                    
                    for(int j = 0; j < lstCeldas.size(); j++) { //actualizamos las filas y columnas que tienen indice 0
                        if(lstCeldas.get(j).getCelda_valida() == 'S') { //que busque solo los que tienen S
                            if(lstCeldas.get(j).getColumna() == columna) {
                                lstCeldas.get(j).setCelda_valida('N');
                            }
                            if(lstCeldas.get(j).getFila() == fila) {
                                lstCeldas.get(j).setCelda_valida('N');
                            }
                            if(lstCeldas.get(j).getColumna() == columna && lstCeldas.get(j).getFila() == fila) {
                                txtResultados[fila][columna].setText(String.valueOf(oferta)); //agregamos el valor a la matriz solucion
                                celda_solucion = new Celda_Solucion(costo, oferta);
                                lstCeldas_Solucion.add(celda_solucion);
                                //col = getNumero_columnas();
                                //fil = getNumero_filas();
                                //para cambiar el valor que muestra los controles de oferta y demanda
                                if(txtSumaOferta.getText().trim().equals(txtSumaDemanda.getText().trim())) { 
                                    txtCostos[columna][getNumero_columnas()].setText(String.valueOf(0));
                                    txtCostos[getNumero_filas()][fila].setText(String.valueOf(0));
                                } else if(Integer.parseInt(txtSumaOferta.getText().trim()) > Integer.parseInt(txtSumaDemanda.getText().trim())) {
                                    txtCostos[fila][getNumero_columnas() + 1].setText(String.valueOf(0));
                                    txtCostos[getNumero_filas()][columna].setText(String.valueOf(0));
                                } else if(Integer.parseInt(txtSumaOferta.getText().trim()) < Integer.parseInt(txtSumaDemanda.getText().trim())) {
                                    //numero_filas = getNumero_filas() + 1;
                                    //numero_columnas = getNumero_columnas();
                                    txtCostos[fila][getNumero_columnas()].setText(String.valueOf(0));
                                    if(columna == fila - 1/*getNumero_filas() == getNumero_columnas() - 1*/) {
                                      txtCostos[getNumero_filas()+1][fila - 1].setText(String.valueOf(0));  
                                    } else {
                                      txtCostos[getNumero_filas() + 1][fila + 1].setText(String.valueOf(0));  
                                    }
                                }
                            }
                        }
                    }
                } else if(oferta > demanda) {   //cuando la oferta es mayor que la demanda
                    lstCeldas.get(posicion).setOferta(oferta - demanda);   //si la demanda es 0, la columna se tacha o cambia de color
                    lstCeldas.get(posicion).setDemanda(0);
                    for(int j = 0; j < lstCeldas.size(); j++) { //actualizamos las filas y columnas que tienen indice 0
                        if(lstCeldas.get(j).getCelda_valida() == 'S') { //que busque solo los que tienen S
                            if(lstCeldas.get(j).getColumna() == columna) {  //para llevar el control de las celdas a pintar
                                lstCeldas.get(j).setCelda_valida('N');
                            }
                            if(lstCeldas.get(j).getFila() == fila) {    //para modificar la oferta
                                lstCeldas.get(j).setOferta(oferta - demanda);
                            }
                            if(lstCeldas.get(j).getColumna() == columna && lstCeldas.get(j).getFila() == fila) {    //para asignar valor a la matriz solucion
                                txtResultados[fila][columna].setText(String.valueOf(demanda));
                                celda_solucion = new Celda_Solucion(costo, demanda);
                                lstCeldas_Solucion.add(celda_solucion);
                                col = getNumero_columnas();
                                fil = getNumero_filas();
                                 //para cambiar el valor que muestra los controles de oferta y demanda
                                if(txtSumaOferta.getText().trim().equals(txtSumaDemanda.getText().trim())) {    //esto es nuevo
                                    txtCostos[fila][getNumero_columnas()].setText(String.valueOf(oferta - demanda));
                                    txtCostos[getNumero_filas()][columna].setText(String.valueOf(0));
                                } else if(Integer.parseInt(txtSumaOferta.getText().trim()) > Integer.parseInt(txtSumaDemanda.getText().trim())) {
                                    //numero_filas = getNumero_filas();
                                    //numero_columnas = getNumero_columnas() + 1;
                                    txtCostos[fila][getNumero_columnas() + 1].setText(String.valueOf(oferta - demanda));
                                    txtCostos[getNumero_filas()][columna /*+ 1*/].setText(String.valueOf(0));
                                } else if(Integer.parseInt(txtSumaOferta.getText().trim()) < Integer.parseInt(txtSumaDemanda.getText().trim())) {
                                    //numero_filas = getNumero_filas() + 1;
                                    //numero_columnas = getNumero_columnas();
                                    txtCostos[fila /*+ 1*/][getNumero_columnas()].setText(String.valueOf(oferta - demanda));
                                    txtCostos[getNumero_filas() + 1][columna].setText(String.valueOf(0));
                                }
                                //txtCostos[getNumero_filas()][columna].setText(String.valueOf(0));
                            }
                        }
                    }
                } else if(oferta < demanda) {   //cuando la demanda es mayor que la oferta
                    lstCeldas.get(posicion).setOferta(0);   //si la oferta es 0, la fila se tacha o cambia de color
                    lstCeldas.get(posicion).setDemanda(demanda - oferta);
                    for(int j = 0; j < lstCeldas.size(); j++) { //actualizamos las filas y columnas que tienen indice 0
                        if(lstCeldas.get(j).getCelda_valida() == 'S') { //que busque solo los que tienen S
                            if(lstCeldas.get(j).getFila() == fila) {
                                lstCeldas.get(j).setCelda_valida('N');
                            }
                            if(lstCeldas.get(j).getColumna() == columna) {
                                lstCeldas.get(j).setDemanda(demanda - oferta);
                            }
                            if(lstCeldas.get(j).getColumna() == columna && lstCeldas.get(j).getFila() == fila) {//en posicion era j
                                txtResultados[fila][columna].setText(String.valueOf(oferta));
                                celda_solucion = new Celda_Solucion(costo, oferta);
                                lstCeldas_Solucion.add(celda_solucion);
                                col = getNumero_columnas();
                                fil = getNumero_filas();
                                //txtCostos[fila][getNumero_columnas()].setText(String.valueOf(0));
                                //para cambiar el valor que muestra los controles de oferta y demanda
                                if(txtSumaOferta.getText().trim().equals(txtSumaDemanda.getText().trim())) {    //esto es nuevo
                                    //numero_filas = getNumero_filas();
                                    //numero_columnas = getNumero_columnas();
                                    txtCostos[fila][getNumero_columnas()].setText(String.valueOf(0));
                                    txtCostos[getNumero_columnas()][columna].setText(String.valueOf(demanda - oferta));
                                } else if(Integer.parseInt(txtSumaOferta.getText().trim()) > Integer.parseInt(txtSumaDemanda.getText().trim())) {
                                    //txtCostos[fila][getNumero_columnas() + 1].setText(String.valueOf(0));
                                    //txtCostos[getNumero_columnas() + 1][columna + 1].setText(String.valueOf(oferta - demanda));
                                    
                                    txtCostos[fila][getNumero_columnas() + 1].setText(String.valueOf(0));
                                    txtCostos[getNumero_columnas()][columna].setText(String.valueOf(demanda - oferta));
                                } else if(Integer.parseInt(txtSumaOferta.getText().trim()) < Integer.parseInt(txtSumaDemanda.getText().trim())) {
                                    //numero_filas = getNumero_filas() + 1;
                                    //numero_columnas = getNumero_columnas();
                                    txtCostos[fila][getNumero_columnas()].setText(String.valueOf(0));
                                    txtCostos[getNumero_columnas()+1][columna].setText(String.valueOf(demanda - oferta));
                                }
                                //txtCostos[getNumero_columnas()][columna].setText(String.valueOf(demanda - oferta));
                            }
                        }
                    }
                }
                //ciclo para pintar los textfield que se van utilizando cuando se coloca la ofera o demanda en la casilla
                for(int i = 0; i < lstCeldas.size(); i++) {
                    if(lstCeldas.get(i).getCelda_valida() == 'N') {
                        int filap = lstCeldas.get(i).getFila();
                        int columnap = lstCeldas.get(i).getColumna();
                        txtCostos[filap][columnap].setBackground(new Color(0x00BFFF));
                    }
                }
                //recorriendo el array list para ver los resultados
                /*for(int i = 0; i < lstCeldas.size(); i++) {
                    System.out.println("costo: " +lstCeldas.get(i).getCosto()+
                            " oferta: " + lstCeldas.get(i).getOferta() + 
                            " demanda: " + lstCeldas.get(i).getDemanda() +
                            " fila: " + lstCeldas.get(i).getFila() + 
                            " columna: " + lstCeldas.get(i).getColumna() + 
                            " estado: " + lstCeldas.get(i).getCelda_valida());
                }
                System.out.println("***************************************************************************************");*/
            }
        } else if(evaluar == false) {   //que mueste el resultado
            //recorro el array de celdas solucion para ver su contenido
            JLabel lblResultado = new JLabel();
            lblResultado.setText("<html>COSTO TOTAL = ");   //se pone html para que el label acepte multi linea
            int resultado = 0;
            for(int i = 0; i < lstCeldas_Solucion.size(); i++) {
                //System.out.println("Costo: " + lstCeldas_Solucion.get(i).getCosto() + " Valor celda: " + lstCeldas_Solucion.get(i).getValor_celda());
                if(i == lstCeldas_Solucion.size() - 1) {
                    resultado = resultado + (lstCeldas_Solucion.get(i).getValor_celda() * lstCeldas_Solucion.get(i).getCosto());
                    lblResultado.setText(lblResultado.getText() + lstCeldas_Solucion.get(i).getValor_celda() + "(" + lstCeldas_Solucion.get(i).getCosto() + ") = " + resultado + "</html>");
                    
                } else {
                    resultado = resultado + (lstCeldas_Solucion.get(i).getValor_celda() * lstCeldas_Solucion.get(i).getCosto());
                    lblResultado.setText(lblResultado.getText() + lstCeldas_Solucion.get(i).getValor_celda() + "(" + lstCeldas_Solucion.get(i).getCosto() + ") + ");
                }
            }
            lblResultado.setBounds(10, 500, pnlSolucionador.getWidth(), 50);
            lblResultado.setFont(lblResultado.getFont().deriveFont(20f));
            pnlSolucionador.add(lblResultado);
            lblResultado.setVisible(true);
            repaint();
            JOptionPane.showMessageDialog(this, "¡Iteraciones realizadas con éxito!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlParametros = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNoFilas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNoColumnas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSumaOferta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSumaDemanda = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnAceptar = new javax.swing.JButton();
        btnIterar = new javax.swing.JButton();
        pnlSolucionador = new javax.swing.JPanel();

        setLayout(null);

        pnlParametros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parametros ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        pnlParametros.setLayout(null);

        jLabel1.setText("No. filas:");
        pnlParametros.add(jLabel1);
        jLabel1.setBounds(60, 110, 60, 16);

        txtNoFilas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlParametros.add(txtNoFilas);
        txtNoFilas.setBounds(120, 110, 110, 22);

        jLabel2.setText("No. columnas:");
        pnlParametros.add(jLabel2);
        jLabel2.setBounds(30, 140, 90, 16);

        txtNoColumnas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlParametros.add(txtNoColumnas);
        txtNoColumnas.setBounds(120, 140, 110, 22);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("INFORMACION OFERTA Y DEMANDA");
        pnlParametros.add(jLabel3);
        jLabel3.setBounds(10, 220, 250, 16);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("INFORMACION DE LOS COSTOS");
        pnlParametros.add(jLabel4);
        jLabel4.setBounds(10, 80, 250, 16);

        jLabel5.setText("Suma oferta:");
        pnlParametros.add(jLabel5);
        jLabel5.setBounds(50, 250, 80, 16);

        txtSumaOferta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlParametros.add(txtSumaOferta);
        txtSumaOferta.setBounds(130, 250, 110, 22);

        jLabel6.setText("Suma demanda:");
        pnlParametros.add(jLabel6);
        jLabel6.setBounds(30, 280, 100, 16);

        txtSumaDemanda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlParametros.add(txtSumaDemanda);
        txtSumaDemanda.setBounds(130, 280, 110, 22);
        pnlParametros.add(jSeparator1);
        jSeparator1.setBounds(10, 190, 250, 2);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        pnlParametros.add(btnAceptar);
        btnAceptar.setBounds(130, 310, 110, 25);

        btnIterar.setText("Iterar...");
        btnIterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIterarActionPerformed(evt);
            }
        });
        pnlParametros.add(btnIterar);
        btnIterar.setBounds(130, 340, 110, 25);

        add(pnlParametros);
        pnlParametros.setBounds(20, 20, 270, 650);

        pnlSolucionador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SOLUCIONADOR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        javax.swing.GroupLayout pnlSolucionadorLayout = new javax.swing.GroupLayout(pnlSolucionador);
        pnlSolucionador.setLayout(pnlSolucionadorLayout);
        pnlSolucionadorLayout.setHorizontalGroup(
            pnlSolucionadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        pnlSolucionadorLayout.setVerticalGroup(
            pnlSolucionadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );

        add(pnlSolucionador);
        pnlSolucionador.setBounds(300, 20, 860, 650);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        try {
            if(!txtNoFilas.getText().trim().equals("") && !txtNoColumnas.getText().trim().equals("") && !txtSumaOferta.getText().trim().equals("") && !txtSumaDemanda.getText().trim().equals("")) {
                /*if(txtSumaOferta.getText().trim().equals(txtSumaDemanda.getText().trim())) {    //si suma oferta = suma demanda
                    setNumero_filas(Integer.parseInt(txtNoFilas.getText().trim()));
                    setNumero_columnas(Integer.parseInt(txtNoColumnas.getText().trim()));
                    setSuma_oferta(Integer.parseInt(txtSumaOferta.getText().trim()));
                    setSuma_demanda(Integer.parseInt(txtSumaDemanda.getText().trim()));
                } else if(Integer.parseInt(txtSumaOferta.getText().trim()) > Integer.parseInt(txtSumaDemanda.getText().trim())) {
                    setNumero_filas(Integer.parseInt(txtNoFilas.getText().trim()) + 1);
                    setNumero_columnas(Integer.parseInt(txtNoColumnas.getText().trim()) + 2);
                    setSuma_oferta(Integer.parseInt(txtSumaOferta.getText().trim()));
                    setSuma_demanda(Integer.parseInt(txtSumaDemanda.getText().trim()));
                } else if(Integer.parseInt(txtSumaOferta.getText().trim()) < Integer.parseInt(txtSumaDemanda.getText().trim())) {
                    setNumero_filas(Integer.parseInt(txtNoFilas.getText().trim()) + 2);
                    setNumero_columnas(Integer.parseInt(txtNoColumnas.getText().trim()) + 1);
                    setSuma_oferta(Integer.parseInt(txtSumaOferta.getText().trim()));
                    setSuma_demanda(Integer.parseInt(txtSumaDemanda.getText().trim()));
                }*/
                setNumero_filas(Integer.parseInt(txtNoFilas.getText().trim()));
                setNumero_columnas(Integer.parseInt(txtNoColumnas.getText().trim()));
                setSuma_oferta(Integer.parseInt(txtSumaOferta.getText().trim()));
                setSuma_demanda(Integer.parseInt(txtSumaDemanda.getText().trim()));
                
                generar_tabla_costos(getNumero_filas(), getNumero_columnas(), getSuma_oferta(), getSuma_demanda());
                btnIterar.setVisible(true);
                btnAceptar.setVisible(false);
            }
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnIterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIterarActionPerformed
        // TODO add your handling code here:
        boolean continuar = true;   //variable que me va a servir para control el mensaje de que estan vacias los textfield
        int fila = 0;
        int numero_filas = 0;
        int numero_columnas = 0;
        
        if(txtSumaOferta.getText().trim().equals(txtSumaDemanda.getText().trim())) {    //esto es nuevo
            numero_filas = getNumero_filas();
            numero_columnas = getNumero_columnas();
        } else if(Integer.parseInt(txtSumaOferta.getText().trim()) > Integer.parseInt(txtSumaDemanda.getText().trim())) {
            numero_filas = getNumero_filas();
            numero_columnas = getNumero_columnas() + 1;
        } else if(Integer.parseInt(txtSumaOferta.getText().trim()) < Integer.parseInt(txtSumaDemanda.getText().trim())) {
            numero_filas = getNumero_filas() + 1;
            numero_columnas = getNumero_columnas();
        }
        
        try {
            if(getContador() == 0) {
                //recorro la matriz de textfield para validar que todos los controles están llenos y guardo los datos en un arraylist
                for(int i = 0; i < numero_filas/*getNumero_filas()*/; i++) {
                    for(int j = 0; j < numero_columnas/*getNumero_columnas()*/; j++) {
                        if(!txtCostos[i][j].getText().trim().equals("")) {
                            //guardos los datos introducidos en el arraylist
                            int costo = Integer.parseInt(txtCostos[i][j].getText().trim());
                            int oferta = Integer.parseInt(txtCostos[i][numero_columnas].getText());   //obtengo la oferta
                            int demanda = Integer.parseInt(txtCostos[numero_filas][j].getText());
                            int fil = i;  //esta variable es el indice de la fila
                            int col = j;  //esta variable es el indice de la columna
                            char celda_valida = 'S';
                            celda = new Celda(costo, oferta, demanda, fil, col, celda_valida);
                            lstCeldas.add(celda);   //despues de que la matriz de costos esta llena, los guardo en un arraylist
                        } else {
                            continuar = false;
                            break;
                        }
                    }
                    if(continuar == false) {
                        JOptionPane.showMessageDialog(this, "Debe ingresar los costos, las ofertas y demandas en la matriz", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                }
                setContador(1);
            }
            //int lento = getContador();
            //llamo al metodo resolver matriz
            resolver_matriz(lstCeldas);
            //recorriendo el array list
            /*for(int i = 0; i < lstCeldas.size(); i++) {
                System.out.println("costo: " +lstCeldas.get(i).getCosto()+
                        " oferta: " + lstCeldas.get(i).getOferta() + 
                        " demanda: " + lstCeldas.get(i).getDemanda() +
                        " fila: " + lstCeldas.get(i).getFila() + 
                        " columna: " + lstCeldas.get(i).getColumna() + 
                        " estado: " + lstCeldas.get(i).getCelda_valida());
            }*/
        } catch(Exception ex) {
            System.out.println("Error 1: " + ex.getStackTrace() + " " + ex.getMessage());
        }
    }//GEN-LAST:event_btnIterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnIterar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlParametros;
    private javax.swing.JPanel pnlSolucionador;
    private javax.swing.JTextField txtNoColumnas;
    private javax.swing.JTextField txtNoFilas;
    private javax.swing.JTextField txtSumaDemanda;
    private javax.swing.JTextField txtSumaOferta;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the numero_filas
     */
    public int getNumero_filas() {
        return numero_filas;
    }

    /**
     * @param numero_filas the numero_filas to set
     */
    public void setNumero_filas(int numero_filas) {
        this.numero_filas = numero_filas;
    }

    /**
     * @return the numero_columnas
     */
    public int getNumero_columnas() {
        return numero_columnas;
    }

    /**
     * @param numero_columnas the numero_columnas to set
     */
    public void setNumero_columnas(int numero_columnas) {
        this.numero_columnas = numero_columnas;
    }

    /**
     * @return the suma_oferta
     */
    public int getSuma_oferta() {
        return suma_oferta;
    }

    /**
     * @param suma_oferta the suma_oferta to set
     */
    public void setSuma_oferta(int suma_oferta) {
        this.suma_oferta = suma_oferta;
    }

    /**
     * @return the suma_demanda
     */
    public int getSuma_demanda() {
        return suma_demanda;
    }

    /**
     * @param suma_demanda the suma_demanda to set
     */
    public void setSuma_demanda(int suma_demanda) {
        this.suma_demanda = suma_demanda;
    }

    /**
     * @return the contador
     */
    public int getContador() {
        return contador;
    }

    /**
     * @param contador the contador to set
     */
    public void setContador(int contador) {
        this.contador = contador;
    }
}
