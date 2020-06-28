/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodografico.gui;

import com.edgarmayorga.metodografico.bean.Coordenada;
import com.edgarmayorga.metodografico.bean.Funcion_Lineal;
import com.edgarmayorga.metodografico.bean.Linea;
import com.edgarmayorga.metodografico.bean.Plano_Cartesiano;
import com.edgarmayorga.metodografico.bean.Recta;
import com.edgarmayorga.metodografico.bean.Tabla_Solucion;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author DellMayorga
 */
public class Pnl_Metodo_Grafico extends javax.swing.JPanel {
    private double eje_h_x_inicial;
    private double eje_h_y_inicial;
    private double eje_h_x_final;
    private double eje_h_y_final;
    private double eje_v_x_inicial;
    private double eje_v_y_inicial;
    private double eje_v_x_final;
    private double eje_v_y_final;
    private double x_inicial;
    private double y_inicial;
    private double x_final;
    private double y_final;
    private int numero_restricciones;
    private Plano_Cartesiano plano = null;
    private Linea linea = null;
    private Recta recta = null;
    private Coordenada coordenada = null;
    private boolean inicio;
    private ArrayList<Plano_Cartesiano> lstPlano = null;
    private ArrayList<Linea> lstLinea = null;
    private ArrayList<Recta> lstRectas = null;
    private ArrayList<Coordenada> lstCoordenadas = null;
    private ArrayList<Funcion_Lineal> lstFunciones_lineales = null;
    private ArrayList<Tabla_Solucion> lstTablaSolucion = null;
    private ArrayList lado_derech_eq = new ArrayList();
    private ArrayList lstPendientes = new ArrayList();
    private XYSeriesCollection dataset = new XYSeriesCollection();
    private XYSeries[] seriesAux = new XYSeries[getNumero_restricciones()];
    private JPanel panel = new JPanel();
    private String[][] matriz ;
    /**
     * Creates new form Pnl_Metodo_Grafico
     */
    public Pnl_Metodo_Grafico() {
        initComponents();
        this.setSize(1200, 900);
        this.setVisible(true);
        //tamaño del panel del grafico
        pnlGrafico.setSize(800, 900);
        pnlGrafico.setVisible(false);
        //tamaño del panel de la tabla
        pnlTabla.setBounds(1250, 20, 650, 400); //tenia 900 de alto
        pnlTabla.setVisible(false);
        pnlParametrizacion.setSize(415, (int)this.getSize().getHeight());
        spRestricciones.setSize(400, 690);
        //oculto los controles de la parametrizacion
        lblObjetivoFuncion.setVisible(false);
        cbMaxMin.setVisible(false);
        btnNext2.setVisible(false);
        pnlFuncionObjetivo.setVisible(false);
        spRestricciones.setVisible(false);
        sp1.setVisible(false);
        sp2.setVisible(false);
        setInicio(true);
        setLstPlano(new ArrayList<>());
        setLstLinea(new ArrayList<>());
        setLstRectas(new ArrayList<Recta>());
        setLstCoordenadas(new ArrayList<Coordenada>());
        setLstFunciones_lineales(new ArrayList<Funcion_Lineal>());
        //setLstTablaSolucion(new ArrayList<Tabla_Solucion>());
    }
    /***
     * 
     * @param largo_eje_y
     * @param largo_eje_x
     * @param valor_refencia 
     * @return 
     */
    public int calcular_escala_plano(int largo_eje_y, int largo_eje_x, double valor_refencia) {
        int escala = 0;
        return escala;
    }
    
    public double obtener_dato_mayor(ArrayList dato_mayor) {
        double dato = 0.0;
        if(!dato_mayor.isEmpty()) {
            Comparator<Double> comparador = Collections.reverseOrder();
            Collections.sort(dato_mayor, comparador);
            dato = Double.valueOf(dato_mayor.get(0).toString());
        }
        return dato;
    }
    
    public double calcular_pendiente(double y2, double y1, double x2, double x1){
        double m = 0; //m es la pendiente de una recta
        m = (y2 - y1) / (x2 - x1);
        
        return m;
    }
    
    public double calcular_x_interseccion(double coeficienteYf1, double constantef2, double coeficienteYf2, double constantef1, double coeficienteXf1, double coeficienteXf2){
        double x = 0;
        x = (coeficienteYf1*constantef2 - coeficienteYf2*constantef1) / (-coeficienteYf2*coeficienteXf1 + coeficienteYf1*coeficienteXf2);
        
        return x;
    }
    
    public double calcular_y_interseccion(double constantef2, double coeficienteXf2, double x, double coeficienteYf2, double constantef1, double coeficienteXf1, double coeficienteYf1){
        double y = 0;
        if(coeficienteYf2 != 0){ //> 0
            if(coeficienteXf2 == 0){
                x = 0;
                y = (constantef2 - x) / coeficienteYf2;
            } else {
                y = (constantef2 - coeficienteXf2*x) / coeficienteYf2;  //coeficienteXf1*x
            }
        } else if(coeficienteYf2 == 0) {
            y = (constantef1 - coeficienteXf1*x) / coeficienteYf1;
        } /*else if(coeficienteYf2 < 0) {
            y = (constantef1 - coeficienteXf1*x) / coeficienteYf1;
        }*/
        return y;
    }
    
    
    
    /*comparamos las pendientes. Si las pendienes son iguales, entonces son rectas paralelas || y si las pendientes 
    son distintas, entonces encontramos el punto de interseccion*/
    public void calculando_puntos_interseccion(){
        double coeficienteYf1 = 0;
        double constantef2 = 0; 
        double coeficienteYf2 = 0; 
        double constantef1 = 0; 
        double coeficienteXf1 = 0; 
        double coeficienteXf2 = 0;
        double x = 0;
        double y = 0;
        int aux = 0;
        int fin_lista_pendientes = 0;
        
        for(int i = 0; i <= getNumero_restricciones() - 2; i++){    //le resto 2, porque no quiero comparar la ultima pendiente, puesto que ya fue comparada con las pendientes anteriores
            double pendiente = lstRectas.get(i).getPendiente();
            for(int j = aux; j <= getNumero_restricciones() - 1; j++){ //voy a comparar la primer pendiente con cada una de las pendientes
                if(j >= 1){ //empiezo a partir de la segunda pendiente
                    if(pendiente != lstRectas.get(j).getPendiente()){  
                        coeficienteYf1 = lstFunciones_lineales.get(i).getCoeficienteY();
                        constantef2 = lstFunciones_lineales.get(j).getConstante();
                        coeficienteYf2 = lstFunciones_lineales.get(j).getCoeficienteY();
                        constantef1 = lstFunciones_lineales.get(i).getConstante();
                        coeficienteXf1 = lstFunciones_lineales.get(i).getCoeficienteX();
                        coeficienteXf2 = lstFunciones_lineales.get(j).getCoeficienteX();
                        x = calcular_x_interseccion(coeficienteYf1, constantef2, coeficienteYf2, constantef1, coeficienteXf1, coeficienteXf2);
                        y = calcular_y_interseccion(constantef2, coeficienteXf2, x, coeficienteYf2, constantef1, coeficienteXf1, coeficienteYf1);

                        if(x >= 0 && y >= 0){   //solo tomo los puntos positivos o cero que son del cuadrante positivo
                            getLstCoordenadas().add(new Coordenada(x, y));
                        }
                        if(j == getNumero_restricciones()-1)   //si llego hasta el último elemento
                        {
                            aux = i + 1;
                        }
                    }
                }
            }
        }
    }
    
    public String calcular_factibilidad(double coeficiente_x, double coeficiente_y, double coordenada_x, double coordenada_y, double constante, String operador_relacional, String tipo_funcion){
        String factibilidad = "";
        double evaluacion = 0;
        
        if(tipo_funcion.equals("fn_restriccion")) {
            evaluacion = (coeficiente_x * coordenada_x) + (coeficiente_y * coordenada_y);
            switch (operador_relacional) {
                case "<=":
                    if(evaluacion <= constante){
                        factibilidad = "FACTIBLE";
                    } else {
                        factibilidad = "NO FACTIBLE";
                    }
                    break;
                case ">=":
                    if(evaluacion <= constante){
                        factibilidad = "FACTIBLE";
                    } else {
                        factibilidad = "NO FACTIBLE";
                    }
                    break;
                case "=":
                    if(evaluacion <= constante){
                        factibilidad = "FACTIBLE";
                    } else {
                        factibilidad = "NO FACTIBLE";
                    }
                    break;
            }
        }
        return factibilidad;
    }
    
    public double obtener_valor_funcion_objetivo(double coeficiente_x, double coeficiente_y, double coordenada_x, double coordenada_y, String tipo_funcion) {
        double valor_fn = 0;
        if(tipo_funcion.equals("fn_objetivo")) {
            valor_fn = (coeficiente_x * coordenada_x) + (coeficiente_y * coordenada_y);
        }
        
        return valor_fn;
    }
    //el siguiente metodo genera una tabla HTML, pero solo para efectos de visualizacion y ver como llenar la matriz bidimensional
    public void generar_tabla_html(){
        FileWriter fw = null;
        PrintWriter pw = null;
        boolean encabezado = false;
        boolean detalle = false;
        double coeficiente_x = 0; 
        double coeficiente_y = 0;
        double constante = 0;
        double coordenada_x = 0;
        double coordenada_y = 0;
        double valor_funcion_objetivo = 0;
        String operador_relacional = "";
        String tipo_funcion = "";
        String funcion = "";
        String factibilidad = "";
        String color = "";
        int contador_fila = 0;
        int contador_columna = 0;
        //matriz = new String[lstCoordenadas.size() + 1][lstFunciones_lineales.size() + 2];
        matriz = new String[lstCoordenadas.size() + 1][lstFunciones_lineales.size() + 3];
        
        try{
            fw = new FileWriter("solucion.html");
            pw = new PrintWriter(fw);
            pw.println("<html>");
            pw.println("<head><title>SOLUCION</title></head>");
            pw.println("<body>");
            pw.println("<div id =\"divC\">");
            pw.println("<table border>");
            //encabezado de la tabla
            for(int i = 0; i <= lstFunciones_lineales.size() - 1; i++) {    
                if(i == 0) {
                    pw.println("<tr>");
                    pw.println("<th>X</th>");
                    pw.println("<th>Y</th>");
                    matriz[i][0] = "X";
                    matriz[i][1] = "Y";
                    matriz[i][lstFunciones_lineales.size() + 2] = "CONCLUSION";
                } else if(i > 1 && encabezado == false) {
                    contador_columna = 2;
                    for(int f = 0; f <= lstFunciones_lineales.size() - 1; f++) {    //encabezado funciones en la tabla
                        if(lstFunciones_lineales.get(f).getOperador_relaciona() == "=" && lstFunciones_lineales.get(f).getConstante() == 0) {
                            funcion = cbMaxMin.getSelectedItem().toString() + " F(x,y) = " + lstFunciones_lineales.get(f).getCoeficienteX() + "X + " + lstFunciones_lineales.get(f).getCoeficienteY() + "Y " /*+ lstFunciones_lineales.get(f).getOperador_relaciona() + " " + lstFunciones_lineales.get(f).getConstante()*/;
                        } else {
                            funcion = lstFunciones_lineales.get(f).getCoeficienteX() + "X + " + lstFunciones_lineales.get(f).getCoeficienteY() + "Y " + lstFunciones_lineales.get(f).getOperador_relaciona() + " " + lstFunciones_lineales.get(f).getConstante();
                        }
                        
                        pw.println("<th>"+funcion+"</th>");
                        matriz[0][contador_columna] = funcion;
                        contador_columna++;
                    }
                    pw.println("<th>R</th>");
                    pw.println("</tr>");
                    encabezado = true;
                }
            }
            //cuerpo de la tabla
            for(int j = 0; j <= lstCoordenadas.size() - 1; j++) {
                    pw.println("<tr>");
                    pw.println("<td>"+lstCoordenadas.get(j).getPunto_x()+"</td>");
                    pw.println("<td>"+lstCoordenadas.get(j).getPunto_y()+"</td>");
                    matriz[j + 1][0] = String.valueOf(lstCoordenadas.get(j).getPunto_x()) ;
                    matriz[j + 1][1] = String.valueOf(lstCoordenadas.get(j).getPunto_y());
                    
                    int contador_columna_detalle = 2;
                    for(int i = 0; i <= lstFunciones_lineales.size() - 1; i++){
                        coeficiente_x = lstFunciones_lineales.get(i).getCoeficienteX();
                        coeficiente_y = lstFunciones_lineales.get(i).getCoeficienteY();
                        constante = lstFunciones_lineales.get(i).getConstante();
                        operador_relacional = lstFunciones_lineales.get(i).getOperador_relaciona();
                        tipo_funcion = lstFunciones_lineales.get(i).getTipo_funcion();
                        coordenada_x = lstCoordenadas.get(j).getPunto_x();
                        coordenada_y = lstCoordenadas.get(j).getPunto_y();
                        factibilidad = calcular_factibilidad(coeficiente_x, coeficiente_y, coordenada_x, coordenada_y, constante, operador_relacional, tipo_funcion);
                        valor_funcion_objetivo = obtener_valor_funcion_objetivo(coeficiente_x, coeficiente_y, coordenada_x, coordenada_y, tipo_funcion);
                        //Calculando los colores de las celdas
                        switch(factibilidad) {
                            case "FACTIBLE":
                                color = "LightGreen";
                                break;
                            case "NO FACTIBLE":
                                color = "LightSalmon";
                        }
                        
                        if(tipo_funcion.equals("fn_restriccion")) {
                            pw.println("<td BGCOLOR = \""+color+"\">"+factibilidad+"</td>");
                            matriz[j + 1][contador_columna_detalle] = factibilidad;
                            if(factibilidad == "NO FACTIBLE")   {
                                matriz[j + 1][lstFunciones_lineales.size() + 2] = factibilidad;
                            }
                            contador_columna_detalle++;
                        } else if (tipo_funcion.equals("fn_objetivo")) {
                            pw.println("<td>"+valor_funcion_objetivo+"</td>");
                            matriz[j + 1][contador_columna_detalle] = String.valueOf(valor_funcion_objetivo);
                            contador_columna_detalle++;
                        }
                    }
                    pw.println("<tr>");
            }
            
            pw.println("</table>");
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
            pw.close();
            //pinto la matriz en la consola, para ver los resultados
            /*String valor = "";
             for(int i=0; i< lstCoordenadas.size() + 1; i++){
                 int columnas = lstFunciones_lineales.size();
                for(int j=0; j<lstFunciones_lineales.size() + 3; j++){
                    System.out.print(matriz[i][j] + "\t");
                }
                System.out.println();
            }*/
        } catch(Exception ex){
            
        }
    }
    
    public void mostrar_matriz(){
        DefaultTableModel model = (DefaultTableModel)tblSolucion.getModel();
        tblSolucion.setPreferredScrollableViewportSize(tblSolucion.getPreferredSize());
        
        int cantidad_columnas = 0;
        int cantidad_filas = 0;
        cantidad_columnas = lstFunciones_lineales.size() + 3;
        cantidad_filas = lstCoordenadas.size();
        String[] headers = new String[cantidad_columnas];
        int contador = 2;
        model.setRowCount(cantidad_filas);
        model.setColumnCount(cantidad_columnas);
        //tblSolucion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblSolucion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblSolucion.setSize(1800, 500);
        //Agrego los valores de la primera fila de la matriz(datos X, Y, f1, f2,...fn y funcion objetivo) al vector de encabezados "header"
        for(int i = 0; i <= cantidad_columnas - 1; i++) {
            headers[0] = "X";
            headers[1] = "Y";
            
            if(i >= 2) {
                headers[i] = matriz[0][contador];
                contador++;
            }
        }
        //Agrego los titulos a los encabezados del JTable
        for(int i = 0; i <= headers.length - 1; i++){
            if(i == 0 || i == 1) {
                TableColumn column = tblSolucion.getTableHeader().getColumnModel().getColumn(i);
                tblSolucion.getColumnModel().getColumn(i).setPreferredWidth(100);
                column.setHeaderValue(headers[i]);
            } else if(i > 1) {
                TableColumn column = tblSolucion.getTableHeader().getColumnModel().getColumn(i);
                tblSolucion.getColumnModel().getColumn(i).setPreferredWidth(350);
                column.setHeaderValue(headers[i]);
            }
        }
        //Lleno el JTable con la matriz de datos
        for(int i = 0; i < cantidad_filas; i++) {
            for(int j = 0; j < cantidad_columnas; j++) {
                if(matriz[i+1][j] == null){ //sobreescribo la ultima columna, si trae nulo, que le ponga factible
                    matriz[i+1][j] = "FACTIBLE";
                }
                tblSolucion.setValueAt(matriz[i+1][j], i, j);
            }
        }
        tblSolucion.setDefaultRenderer(Object.class, new FortmatoTablaSolucion(cantidad_filas, cantidad_columnas));       //colores del grid
        tblSolucion.setEnabled(false);
        pnlTabla.setVisible(true);
    }
    
    /*public void encontrar_solucion(String objetivo, String[][] matriz){   //con este metodo busco dentro de las soluciones factibles, el mayor o menor, según sea la condición
        
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlParametrizacion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNoRestricciones = new javax.swing.JTextField();
        lblObjetivoFuncion = new javax.swing.JLabel();
        cbMaxMin = new javax.swing.JComboBox<>();
        sp2 = new javax.swing.JSeparator();
        sp1 = new javax.swing.JSeparator();
        pnlFuncionObjetivo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCoeX = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCoeY = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnNext3 = new javax.swing.JButton();
        btnNext2 = new javax.swing.JButton();
        btnNext1 = new javax.swing.JButton();
        spRestricciones = new javax.swing.JScrollPane();
        pnlRestricciones = new javax.swing.JPanel();
        pnlGrafico = new javax.swing.JPanel();
        pnlTabla = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSolucion = new javax.swing.JTable();

        setLayout(null);

        pnlParametrizacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Parametrización", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 0, 51))); // NOI18N
        pnlParametrizacion.setLayout(null);

        jLabel1.setText("¿No. de restricciones?");
        pnlParametrizacion.add(jLabel1);
        jLabel1.setBounds(20, 30, 130, 20);
        pnlParametrizacion.add(txtNoRestricciones);
        txtNoRestricciones.setBounds(160, 30, 90, 25);

        lblObjetivoFuncion.setText("¿Objetivo de la función?");
        pnlParametrizacion.add(lblObjetivoFuncion);
        lblObjetivoFuncion.setBounds(18, 76, 136, 16);

        cbMaxMin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maximizar", "Minimizar" }));
        pnlParametrizacion.add(cbMaxMin);
        cbMaxMin.setBounds(166, 73, 85, 22);
        pnlParametrizacion.add(sp2);
        sp2.setBounds(10, 190, 400, 10);
        pnlParametrizacion.add(sp1);
        sp1.setBounds(10, 100, 400, 10);

        pnlFuncionObjetivo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Función objetivo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 0, 51))); // NOI18N
        pnlFuncionObjetivo.setForeground(new java.awt.Color(255, 0, 102));
        pnlFuncionObjetivo.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Z = ");
        pnlFuncionObjetivo.add(jLabel3);
        jLabel3.setBounds(10, 30, 30, 20);

        txtCoeX.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlFuncionObjetivo.add(txtCoeX);
        txtCoeX.setBounds(40, 30, 50, 25);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("X + ");
        pnlFuncionObjetivo.add(jLabel4);
        jLabel4.setBounds(100, 30, 26, 20);

        txtCoeY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlFuncionObjetivo.add(txtCoeY);
        txtCoeY.setBounds(130, 30, 50, 25);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Y");
        pnlFuncionObjetivo.add(jLabel5);
        jLabel5.setBounds(190, 30, 20, 20);

        btnNext3.setText("Continuar...");
        btnNext3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext3ActionPerformed(evt);
            }
        });
        pnlFuncionObjetivo.add(btnNext3);
        btnNext3.setBounds(270, 30, 110, 25);

        pnlParametrizacion.add(pnlFuncionObjetivo);
        pnlFuncionObjetivo.setBounds(10, 110, 390, 70);

        btnNext2.setText("Continuar...");
        btnNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext2ActionPerformed(evt);
            }
        });
        pnlParametrizacion.add(btnNext2);
        btnNext2.setBounds(280, 70, 110, 25);

        btnNext1.setText("Continuar...");
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });
        pnlParametrizacion.add(btnNext1);
        btnNext1.setBounds(280, 30, 110, 25);

        spRestricciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restricciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 0, 51))); // NOI18N

        pnlRestricciones.setAutoscrolls(true);
        pnlRestricciones.setOpaque(false);
        pnlRestricciones.setPreferredSize(new java.awt.Dimension(100, 5000));
        pnlRestricciones.setLayout(null);
        spRestricciones.setViewportView(pnlRestricciones);

        pnlParametrizacion.add(spRestricciones);
        spRestricciones.setBounds(10, 200, 390, 470);

        add(pnlParametrizacion);
        pnlParametrizacion.setBounds(10, 10, 420, 680);

        pnlGrafico.setLayout(null);
        add(pnlGrafico);
        pnlGrafico.setBounds(440, 20, 720, 670);

        pnlTabla.setOpaque(false);
        pnlTabla.setLayout(new javax.swing.OverlayLayout(pnlTabla));

        tblSolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSolucion.setRowSelectionAllowed(false);
        jScrollPane3.setViewportView(tblSolucion);

        pnlTabla.add(jScrollPane3);

        add(pnlTabla);
        pnlTabla.setBounds(1170, 20, 370, 670);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        // TODO add your handling code here:
        if(!txtNoRestricciones.getText().trim().equals("")) {
            lblObjetivoFuncion.setVisible(true);
            cbMaxMin.setVisible(true);
            btnNext2.setVisible(true);
            sp1.setVisible(true);
            btnNext1.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "¡Ingrese el numero de restricciones!", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext2ActionPerformed
        // TODO add your handling code here:
        String objetivo_funcion = cbMaxMin.getSelectedItem().toString();
        pnlFuncionObjetivo.setVisible(true);
        btnNext2.setVisible(false);
        txtCoeX.requestFocus();
    }//GEN-LAST:event_btnNext2ActionPerformed

    private void btnNext3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext3ActionPerformed
        // TODO add your handling code here:
        if(!txtCoeX.getText().trim().equals("") && !txtCoeY.getText().trim().equals("")) {
            setNumero_restricciones(Integer.parseInt(txtNoRestricciones.getText().trim()));
            int numero_restricciones = getNumero_restricciones();
            spRestricciones.setVisible(true);
            sp2.setVisible(true);
            btnNext3.setVisible(false);
            //construccion dinamico de los controles que van a servir para restricciones de la funcion objetivo
            JTextField[] textfieldX = new JTextField[numero_restricciones];
            JTextField[] textfieldY = new JTextField[numero_restricciones];
            JTextField[] textfieldVal = new JTextField[numero_restricciones];
            JComboBox[] comboboxSigno = new JComboBox[numero_restricciones];
            JLabel[] labelX = new JLabel[numero_restricciones];
            JLabel[] labelY = new JLabel[numero_restricciones];
            XYSeries[] series = new XYSeries[numero_restricciones]; //este es nuevo
            int columna = 30;
            int fila = 20;
            
            for(int i = 0; i <= getNumero_restricciones() - 1; i++) {
                textfieldX[i] = new JTextField();   //textfield coeficientes X
                textfieldX[i].setBounds(columna, fila, 50, 25);
                textfieldX[i].setHorizontalAlignment(JTextField.CENTER);
                pnlRestricciones.add(textfieldX[i]);
                textfieldX[i].setVisible(true);
                textfieldX[0].requestFocus();
                
                labelX[i] = new JLabel();   //labels de X
                labelX[i].setBounds(columna + 60, fila, 25, 25);
                labelX[i].setFont(labelX[i].getFont().deriveFont(14f));
                labelX[i].setText("X + ");
                pnlRestricciones.add(labelX[i]);
                labelX[i].setVisible(true);
                
                textfieldY[i] = new JTextField();   //textfield coeficientes Y
                textfieldY[i].setBounds(columna + 90, fila, 50, 25);
                textfieldY[i].setHorizontalAlignment(JTextField.CENTER);
                pnlRestricciones.add(textfieldY[i]);
                textfieldY[i].setVisible(true);
                
                labelY[i] = new JLabel();   //labels de X
                labelY[i].setBounds(columna + 150, fila, 25, 25);
                labelY[i].setFont(labelY[i].getFont().deriveFont(14f));
                labelY[i].setText("Y");
                pnlRestricciones.add(labelY[i]);
                labelY[i].setVisible(true);
                
                comboboxSigno[i] = new JComboBox(); //Combo box que contiene el signo de =, >=, <= de la restricción
                comboboxSigno[i].setBounds(columna + 185, fila, 50, 25);
                comboboxSigno[i].setFont(comboboxSigno[i].getFont().deriveFont(14f));
                comboboxSigno[i].addItem("<=");
                comboboxSigno[i].addItem(">=");
                comboboxSigno[i].addItem("=");
                pnlRestricciones.add(comboboxSigno[i]);
                comboboxSigno[i].setVisible(true);
                
                textfieldVal[i] = new JTextField(); //textfield que va a contener el miembro derecho de la ecuación de restricción
                textfieldVal[i].setBounds(columna + 245, fila, 50, 25);
                textfieldVal[i].setHorizontalAlignment(JTextField.CENTER);
                pnlRestricciones.add(textfieldVal[i]);
                textfieldVal[i].setVisible(true);
                
                if(i == getNumero_restricciones() - 1) {    //si el la ultima posicion, que cree un botón 
                    JButton btnProcesar = new JButton();
                    btnProcesar.setText("Continuar");
                    btnProcesar.setBounds(columna, fila + 30, 100, 25);
                    pnlRestricciones.add(btnProcesar);
                    btnProcesar.setVisible(true);
                    
                    JButton btnReiniciar = new JButton();
                    btnReiniciar.setText("Reiniciar");
                    btnReiniciar.setBounds(columna, fila + 30, 100, 25);
                    pnlRestricciones.add(btnReiniciar);
                    btnReiniciar.setVisible(false);
                    
                    btnReiniciar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            txtNoRestricciones.setText("");
                            btnNext1.setVisible(true);
                            lblObjetivoFuncion.setVisible(false);
                            cbMaxMin.setVisible(false);
                            btnNext2.setVisible(false);
                            txtCoeX.setText("");
                            txtCoeY.setText("");
                            sp1.setVisible(false);
                            sp2.setVisible(false);
                            pnlFuncionObjetivo.setVisible(false);
                            btnNext3.setVisible(true);
                            spRestricciones.setVisible(false);
                            pnlGrafico.setVisible(false);
                            if(textfieldX.length > 0){  //vacio el vector de controles, para limpiar el panel, con uno que tenga control, es porque los demas controles existen, y hay que limpiarlos
                                for(int i = 0; i <= getNumero_restricciones() -1; i++){
                                    pnlRestricciones.remove(textfieldX[i]);
                                    pnlRestricciones.remove(textfieldY[i]);
                                    pnlRestricciones.remove(textfieldVal[i]);
                                    pnlRestricciones.remove(labelX[i]);
                                    pnlRestricciones.remove(labelY[i]);
                                    pnlRestricciones.remove(comboboxSigno[i]);
                                    pnlRestricciones.remove(btnProcesar);
                                    pnlRestricciones.remove(btnReiniciar);
                                    series[i].clear();  //limpio todos los puntos
                                    dataset.removeAllSeries();  //limpio todas las gráficas
                                }
                            }
                        }
                    });
                    
                    btnProcesar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            double dato_mayor = 0.0;
                            boolean procede = false;
                            
                            //obteniendo los valores miembro de la ecuación para determinar que numero es mayor
                            //tambien me sirve para guardar todas las funciones generadas en un array de funciones
                            for(int j = 0; j <= getNumero_restricciones() -1; j++){
                                if(!textfieldX[j].getText().trim().equals("") && !textfieldY[j].getText().trim().equals("") && !textfieldVal[j].getText().trim().equals("")) {  //evaluo si se ingresaron los coeficientes de las variables
                                    lado_derech_eq.add(Integer.parseInt(textfieldVal[j].getText()));
                                    //agrego funciones restriccion
                                    lstFunciones_lineales.add(new Funcion_Lineal(Double.parseDouble(textfieldX[j].getText()), Double.parseDouble(textfieldY[j].getText()), Double.parseDouble(textfieldVal[j].getText()), comboboxSigno[j].getSelectedItem().toString(), "fn_restriccion"));
                                    procede = true;
                                } else {
                                    procede = false;
                                    JOptionPane.showMessageDialog(Pnl_Metodo_Grafico.this, "Debe igresar todos los coeficientes de las variables y la parte derecha de la ecuación", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                    break;
                                }
                            }
                            //agrego funcion objetivo
                            lstFunciones_lineales.add(new Funcion_Lineal(Double.parseDouble(txtCoeX.getText().trim()), Double.parseDouble(txtCoeY.getText().trim()), 0, "=", "fn_objetivo"));   //agrego la funcion objetivo a la lista de funciones lineales
                            dato_mayor = obtener_dato_mayor(lado_derech_eq);
                            
                            if(procede == true){
                                //obtengo el valor de los datos ingresados en las ecuaciones de restriccion
                                for(int i = 0; i <= getNumero_restricciones() - 1; i++) {   
                                    if(!textfieldX[i].getText().trim().equals("") && !textfieldY[i].getText().trim().equals("") && !textfieldVal[i].getText().trim().equals("")) {  //evaluo si se ingresaron los coeficientes de las variables
                                        double coeficiente_x = Integer.valueOf(textfieldX[i].getText());
                                        double coeficiente_y = Integer.valueOf(textfieldY[i].getText());
                                        double miembro = Integer.valueOf(textfieldVal[i].getText());
                                        series[i] = new XYSeries(coeficiente_x + "X + " + coeficiente_y + "Y = " + miembro);
                                        
                                        /***************CALCULANDO EL PUNTO INICIAL DE UNA RECTA**********************/
                                        //encontrando el valor de X, cuando Y es 0
                                        if(coeficiente_x != 0 && coeficiente_y != 0){   //si las dos variables tienen coeficiente
                                            setY_inicial(0); //hago Y = 0 y calculo X
                                            setX_inicial((miembro - (coeficiente_y * getY_inicial())) / coeficiente_x);
                                        } else if(coeficiente_y == 0){
                                            setY_inicial(0);
                                            setX_inicial(miembro / coeficiente_x);
                                        } else if(coeficiente_x == 0){
                                            setY_inicial(miembro / coeficiente_y);
                                            setX_inicial(0);
                                        }
                                        /***************CALCULANDO EL PUNTO FINAL DE UNA RECTA**********************/
                                        //encontrando el valor de Y, cuando X es 0
                                        if(coeficiente_x != 0 && coeficiente_y != 0){
                                            setX_final(0);
                                            setY_final((miembro - (coeficiente_x * getX_final())) / coeficiente_y); //esta es la ecuacion despejada y
                                        } else if(coeficiente_y == 0){
                                            setY_final(dato_mayor);
                                            setX_final(miembro / coeficiente_x);
                                        } else if(coeficiente_x == 0){
                                            setY_final(miembro / coeficiente_y);
                                            setX_final(dato_mayor);
                                        }
                                        series[i].add(getX_inicial(), getY_inicial());  //agrego los puntos iniciales al objeto series, que luego serán graficados
                                        series[i].add(getX_final(), getY_final());  //agrego los puntos finales al objeto series, que luego serán graficados
                                        
                                        /****************GUARDANDO LOS PUNTOS INICIALES Y FINALES, Y LA PENDIENTE EN UNA ESTRUCTURA DE DATOS*************/
                                        double pendiente = calcular_pendiente(getY_final(), getY_inicial(), getX_final(), getX_inicial());
                                        lstRectas.add(new Recta(getX_inicial(), getY_inicial(), getX_final(), getY_final(), pendiente));
                                        //if(coeficiente_x > 0 && coeficiente_y > 0){ //cuando la funcion tiene los 2 coeficientes, guardo los 2 puntos
                                        if(coeficiente_x != 0 && coeficiente_y != 0){ //cuando la funcion tiene los 2 coeficientes (sean negativos o positivos), guardo los 2 puntos
                                            getLstCoordenadas().add(new Coordenada(getX_inicial(), getY_inicial()));
                                            getLstCoordenadas().add(new Coordenada(getX_final(), getY_final()));
                                        } else if(coeficiente_x == 0) { //si el coeficiente en x es 0, solo guardo el punto final
                                            getLstCoordenadas().add(new Coordenada(0, getY_final()));
                                        } else if(coeficiente_y == 0){  //si el coeficiente en y e 0, solo guardo el punto inicial
                                            getLstCoordenadas().add(new Coordenada(getX_final(), 0));
                                        }
                                        dataset.addSeries(series[i]);   //se agregan al data set todos los puntos, para luego ser graficados
                                    } else {
                                        JOptionPane.showMessageDialog(Pnl_Metodo_Grafico.this, "Debe igresar todos los coeficientes de las variables y la parte derecha de la ecuación", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                        break;
                                    }
                                }
                                JFreeChart chart = ChartFactory.createXYLineChart("SOLUCION GRAFICA", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
                                chart.setBackgroundPaint(Color.WHITE);
                                ChartPanel chpanel = new ChartPanel(chart);
                                chpanel.setMouseWheelEnabled(true);
                                chpanel.setMouseZoomable(true);
                                chpanel.setPreferredSize(new Dimension(pnlGrafico.getWidth(), pnlGrafico.getHeight()));
                                XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
                                for(int i = 0; i <= getNumero_restricciones() - 1; i++){    //recorro todas las lineas y les cambio el grosor
                                    render.setSeriesStroke(i, new BasicStroke(3.0f));
                                    render.setSeriesShapesVisible(i, false);    //muestra circulos en los puntos de la recta  
                                }
                                
                                getLstCoordenadas().add(new Coordenada(0, 0));  //agrego el punto 0,0 a la lista de coordenadas también
                                calculando_puntos_interseccion();   //agrego los puntos de intersección de las rectas
                                generar_tabla_html();   //aqui esta contenido la forma en que creo la matriz bidimensional
                                
                                XYPlot plot = (XYPlot)chart.getPlot();
                                plot.setBackgroundPaint(Color.WHITE);   //cambio el color de fondo de la imagen
                                plot.setRangeGridlinePaint(Color.LIGHT_GRAY);   //pongo color a las lineas verticales del grafico
                                plot.setDomainGridlinePaint(Color.LIGHT_GRAY);  //pongo color a las lineas horitontales del grafico
                                plot.setRenderer(render);   //se renderiza el grafico
                                panel.setSize(pnlGrafico.getWidth(), pnlGrafico.getHeight());
                                panel.add(chpanel);
                                pnlGrafico.add(panel);
                                panel.repaint();
                                panel.setVisible(true);
                                pnlGrafico.setVisible(true);
                                btnProcesar.setVisible(false);
                                btnReiniciar.setVisible(true);
                                mostrar_matriz(); //Lleno el JTable y pinto sus filas, según la condición de la ultima columna "RESULTAOD" si es factible verde, si no es factible rojo
                            }
                        }
                    });
                }
                fila = fila + 30;   //cambio de fila, para posicionar los controles dinamicos
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese los coeficientes de la funcion objetivo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnNext3ActionPerformed

    /**
     * @return the objPlano
     */
//    public Plano_Cartesiano getObjPlano() {
//        return objPlano;
//    }

    /**
     * @param objPlano the objPlano to set
     */
//    public void setObjPlano(Plano_Cartesiano objPlano) {
//        this.objPlano = objPlano;
//    }

    /**
     * @return the eje_h_x_inicial
     */
    public double getEje_h_x_inicial() {
        return eje_h_x_inicial;
    }

    /**
     * @param eje_h_x_inicial the eje_h_x_inicial to set
     */
    public void setEje_h_x_inicial(double eje_h_x_inicial) {
        this.eje_h_x_inicial = eje_h_x_inicial;
    }

    /**
     * @return the eje_h_y_inicial
     */
    public double getEje_h_y_inicial() {
        return eje_h_y_inicial;
    }

    /**
     * @param eje_h_y_inicial the eje_h_y_inicial to set
     */
    public void setEje_h_y_inicial(double eje_h_y_inicial) {
        this.eje_h_y_inicial = eje_h_y_inicial;
    }

    /**
     * @return the eje_h_x_final
     */
    public double getEje_h_x_final() {
        return eje_h_x_final;
    }

    /**
     * @param eje_h_x_final the eje_h_x_final to set
     */
    public void setEje_h_x_final(double eje_h_x_final) {
        this.eje_h_x_final = eje_h_x_final;
    }

    /**
     * @return the eje_h_y_final
     */
    public double getEje_h_y_final() {
        return eje_h_y_final;
    }

    /**
     * @param eje_h_y_final the eje_h_y_final to set
     */
    public void setEje_h_y_final(double eje_h_y_final) {
        this.eje_h_y_final = eje_h_y_final;
    }

    /**
     * @return the eje_v_x_inicial
     */
    public double getEje_v_x_inicial() {
        return eje_v_x_inicial;
    }

    /**
     * @param eje_v_x_inicial the eje_v_x_inicial to set
     */
    public void setEje_v_x_inicial(double eje_v_x_inicial) {
        this.eje_v_x_inicial = eje_v_x_inicial;
    }

    /**
     * @return the eje_v_y_inicial
     */
    public double getEje_v_y_inicial() {
        return eje_v_y_inicial;
    }

    /**
     * @param eje_v_y_inicial the eje_v_y_inicial to set
     */
    public void setEje_v_y_inicial(double eje_v_y_inicial) {
        this.eje_v_y_inicial = eje_v_y_inicial;
    }

    /**
     * @return the eje_v_x_final
     */
    public double getEje_v_x_final() {
        return eje_v_x_final;
    }

    /**
     * @param eje_v_x_final the eje_v_x_final to set
     */
    public void setEje_v_x_final(double eje_v_x_final) {
        this.eje_v_x_final = eje_v_x_final;
    }

    /**
     * @return the eje_v_y_final
     */
    public double getEje_v_y_final() {
        return eje_v_y_final;
    }

    /**
     * @param eje_v_y_final the eje_v_y_final to set
     */
    public void setEje_v_y_final(double eje_v_y_final) {
        this.eje_v_y_final = eje_v_y_final;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnNext2;
    private javax.swing.JButton btnNext3;
    private javax.swing.JComboBox<String> cbMaxMin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblObjetivoFuncion;
    private javax.swing.JPanel pnlFuncionObjetivo;
    private javax.swing.JPanel pnlGrafico;
    private javax.swing.JPanel pnlParametrizacion;
    private javax.swing.JPanel pnlRestricciones;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JSeparator sp1;
    private javax.swing.JSeparator sp2;
    private javax.swing.JScrollPane spRestricciones;
    private javax.swing.JTable tblSolucion;
    private javax.swing.JTextField txtCoeX;
    private javax.swing.JTextField txtCoeY;
    private javax.swing.JTextField txtNoRestricciones;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the numero_restricciones
     */
    public int getNumero_restricciones() {
        return numero_restricciones;
    }

    /**
     * @param numero_restricciones the numero_restricciones to set
     */
    public void setNumero_restricciones(int numero_restricciones) {
        this.numero_restricciones = numero_restricciones;
    }

    /**
     * @return the x_inicial
     */
    public double getX_inicial() {
        return x_inicial;
    }

    /**
     * @param x_inicial the x_inicial to set
     */
    public void setX_inicial(double x_inicial) {
        this.x_inicial = x_inicial;
    }

    /**
     * @return the y_inicial
     */
    public double getY_inicial() {
        return y_inicial;
    }

    /**
     * @param y_inicial the y_inicial to set
     */
    public void setY_inicial(double y_inicial) {
        this.y_inicial = y_inicial;
    }

    /**
     * @return the x_final
     */
    public double getX_final() {
        return x_final;
    }

    /**
     * @param x_final the x_final to set
     */
    public void setX_final(double x_final) {
        this.x_final = x_final;
    }

    /**
     * @return the y_final
     */
    public double getY_final() {
        return y_final;
    }

    /**
     * @param y_final the y_final to set
     */
    public void setY_final(double y_final) {
        this.y_final = y_final;
    }

    /**
     * @return the plano
     */
    public Plano_Cartesiano getPlano() {
        return plano;
    }

    /**
     * @param plano the plano to set
     */
    public void setPlano(Plano_Cartesiano plano) {
        this.plano = plano;
    }

    /**
     * @return the linea
     */
    public Linea getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    /**
     * @return the lstPlano
     */
    public ArrayList<Plano_Cartesiano> getLstPlano() {
        return lstPlano;
    }

    /**
     * @param lstPlano the lstPlano to set
     */
    public void setLstPlano(ArrayList<Plano_Cartesiano> lstPlano) {
        this.lstPlano = lstPlano;
    }

    /**
     * @return the lstLinea
     */
    public ArrayList<Linea> getLstLinea() {
        return lstLinea;
    }

    /**
     * @param lstLinea the lstLinea to set
     */
    public void setLstLinea(ArrayList<Linea> lstLinea) {
        this.lstLinea = lstLinea;
    }

    /**
     * @return the inicio
     */
    public boolean getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }
    /**
     * @return the lstRectas
     */
    public ArrayList<Recta> getLstRectas() {
        return lstRectas;
    }

    /**
     * @param lstRectas the lstRectas to set
     */
    public void setLstRectas(ArrayList<Recta> lstRectas) {
        this.lstRectas = lstRectas;
    }

    /**
     * @return the lstCoordenadas
     */
    public ArrayList<Coordenada> getLstCoordenadas() {
        return lstCoordenadas;
    }

    /**
     * @param lstCoordenadas the lstCoordenadas to set
     */
    public void setLstCoordenadas(ArrayList<Coordenada> lstCoordenadas) {
        this.lstCoordenadas = lstCoordenadas;
    }

    /**
     * @return the lstFunciones_lineales
     */
    public ArrayList<Funcion_Lineal> getLstFunciones_lineales() {
        return lstFunciones_lineales;
    }

    /**
     * @param lstFunciones_lineales the lstFunciones_lineales to set
     */
    public void setLstFunciones_lineales(ArrayList<Funcion_Lineal> lstFunciones_lineales) {
        this.lstFunciones_lineales = lstFunciones_lineales;
    }

    /**
     * @return the lstTablaSolucion
     */
    public ArrayList<Tabla_Solucion> getLstTablaSolucion() {
        return lstTablaSolucion;
    }

    /**
     * @param lstTablaSolucion the lstTablaSolucion to set
     */
    public void setLstTablaSolucion(ArrayList<Tabla_Solucion> lstTablaSolucion) {
        this.lstTablaSolucion = lstTablaSolucion;
    }
}
