/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgarmayorga.metodosimplex_dual.gui;

import com.edgarmayorga.metodoesquinanoroeste.bean.Celda_Solucion;
import com.edgarmayorga.metodografico.gui.Pnl_Metodo_Grafico;
import com.edgarmayorga.metodosimplex_dual.bean.Celda;
import com.edgarmayorga.metodosimplex_dual.bean.Fila_Z;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author DellMayorga
 */
public class Pnl_Metodo_Simplex_Dual extends javax.swing.JPanel {
    private int numero_variables_desicion;
    private int numero_restricciones;
    private String objetivo_funcion;
    private int[] datos_matriz_inicial;
    private Celda celda;
    private JLabel[][] lblCoeficiente;
    private JLabel[] lblFuncionObjetivo;
    private JTextField[] txtFuncionObjetivo;
    private JTextField[][] txtCoeficiente;
    private JComboBox[] cbSigno;
    private JTextField[] txtTerminoIndependiente;
    private String[] funcion_objetivo;
    private String[][] funciones_restriccion;
    private String[] headers;
    //private Fila_Z[] fila_z;
    private ArrayList<Celda> lista_celda;
    private ArrayList<Fila_Z> fila_z;
    private String variable_entrada;
    private String variable_salida;
    private int valor_entrada;
    private int pivote;
    
    /**
     * Creates new form Pnl_Metodo_Simlex_Dual
     */
    public Pnl_Metodo_Simplex_Dual() {
        initComponents();
        lblTexto1.setVisible(false);
        lista_celda = new ArrayList<Celda>();
        lblFuncionOriginal1.setVisible(false);
        lblSujetoa.setVisible(false);
        pnlInformacion.setSize(560, 700);
        pnlSolucion.setBounds(570, 10, 1590, 700);
        spMetodoSimplexDual.setVisible(false);
        Dlg_Parametros objParametros = new Dlg_Parametros(null, true);
        setNumero_variables_desicion(objParametros.getNumero_variables_desicion());
        setNumero_restricciones(objParametros.getNumero_restricciones());
        setObjetivo_funcion(objParametros.getObjetivo_funcion());
        
        if(objParametros.getMostrar_panel() == true) {
            spMetodoSimplexDual.setVisible(true);
            //Generando los controles dinámicos
            lblFuncionObjetivo = new JLabel[getNumero_variables_desicion()];
            txtFuncionObjetivo = new JTextField[getNumero_variables_desicion()];
            txtFuncionObjetivo = new JTextField[getNumero_variables_desicion()];
            lblCoeficiente = new JLabel[getNumero_restricciones()][getNumero_variables_desicion()];
            txtCoeficiente = new JTextField[getNumero_restricciones()][getNumero_variables_desicion()];
            cbSigno = new JComboBox[getNumero_restricciones()];
            txtTerminoIndependiente = new JTextField[getNumero_restricciones()];
            int fila = 75;
            int columna = 30;
            int fila2 = 30;
            int columna2 = 70;
            
            //Generando los controles para la funcion objetivo
            JLabel lblFuncion = new JLabel();
            lblFuncion.setBounds(30, 30, 100, 25);
            lblFuncion.setText("FUNCION: ");
            lblFuncion.setFont(lblFuncion.getFont().deriveFont(14f));
            pnlControlesdinamicos.add(lblFuncion);
            lblFuncion.setVisible(true);
            //Crear los controles para la función objetivo
            for(int i = 0; i < getNumero_variables_desicion(); i++) {
                txtFuncionObjetivo[i] = new JTextField();
                txtFuncionObjetivo[i].setBounds(columna2 + 35, fila2, 40, 25);
                txtFuncionObjetivo[i].setHorizontalAlignment(JTextField.CENTER);
                pnlControlesdinamicos.add(txtFuncionObjetivo[i]);
                txtFuncionObjetivo[i].setVisible(true);
                
                lblFuncionObjetivo[i] = new JLabel();
                lblFuncionObjetivo[i].setBounds(columna2 + 75, fila2, 45, 25);
                //lblFuncionObjetivo[i].setHorizontalAlignment(JTextField.CENTER);
                if(i == getNumero_variables_desicion() - 1) {    //para que no le ponga signo a la última etiqueta
                    lblFuncionObjetivo[i].setText("X"+(i+1));
                } else {
                    lblFuncionObjetivo[i].setText("X"+(i+1) + " +");
                }
                lblFuncionObjetivo[i].setFont(lblFuncionObjetivo[i].getFont().deriveFont(14f));
                pnlControlesdinamicos.add(lblFuncionObjetivo[i]);
                lblFuncionObjetivo[i].setVisible(true);
                //System.out.println("variables desicion: " + getNumero_variables_desicion());
                columna2 = columna2 + 80;
            }
            
            //crear los controles de las funciones restriccion
            for(int i = 0; i < getNumero_restricciones(); i++) {
                for(int j = 0; j < getNumero_variables_desicion(); j++) {
                    txtCoeficiente[i][j] = new JTextField();
                    txtCoeficiente[i][j].setBounds(columna, fila, 40, 25);
                    txtCoeficiente[i][j].setHorizontalAlignment(JTextField.CENTER);
                    pnlControlesdinamicos.add(txtCoeficiente[i][j]);
                    txtCoeficiente[i][j].setVisible(true);
                    
                    lblCoeficiente[i][j] = new JLabel();
                    lblCoeficiente[i][j].setBounds(columna + 40, fila, 50, 25);
                    if(j == getNumero_variables_desicion() - 1) {    //para que no le ponga signo a la última etiqueta
                        lblCoeficiente[i][j].setText("X"+(j+1));
                    } else {
                        lblCoeficiente[i][j].setText("X"+(j+1) + " + ");
                    }
                    lblCoeficiente[i][j].setFont(lblCoeficiente[i][j].getFont().deriveFont(14f));
                    pnlControlesdinamicos.add(lblCoeficiente[i][j]);
                    lblCoeficiente[i][j].setVisible(true);
                    
                    columna = columna + 75;
                }
                cbSigno[i] = new JComboBox();
                cbSigno[i].setBounds(columna + 5, fila, 50, 25);
                cbSigno[i].setFont(cbSigno[i].getFont().deriveFont(14f));
                cbSigno[i].addItem("<=");
                cbSigno[i].addItem(">=");
                cbSigno[i].addItem("=");
//                cbSigno[i].setAlignmentX(CENTER_ALIGNMENT);
//                cbSigno[i].setAlignmentY(CENTER_ALIGNMENT);
                pnlControlesdinamicos.add(cbSigno[i]);
                cbSigno[i].setVisible(true);
                
                txtTerminoIndependiente[i] = new JTextField();
                txtTerminoIndependiente[i].setBounds(columna + 60, fila, 60, 25);
                txtTerminoIndependiente[i].setHorizontalAlignment(JTextField.CENTER);
                pnlControlesdinamicos.add(txtTerminoIndependiente[i]);
                txtTerminoIndependiente[i].setVisible(true);
                columna = 30;
                fila = fila + 30;
            }
        }
    }
    
    public void generar_informacion_solucion(){
        JLabel[] lblFuncionObjetivo = new JLabel[getNumero_restricciones()];
        JLabel[] lblFuncionRestriccion = new JLabel[getNumero_restricciones()];
        JLabel[] lblFuncionRestriccionH = new JLabel[getNumero_restricciones()];
        String[] funcion_objetivo = new String[getNumero_variables_desicion() + getNumero_restricciones()];
        
        lblFuncionOriginal1.setText(getObjetivo_funcion() + ": ");
        lblSujetoa.setText("SUJETO A:");
        String funcion_restriccion = "";
        String funcion_objetivoH = "";  //esta es la funcion objetivo con holguras
        String funcion_restricionH = "";
        int columna = 0;
        int fila = 115;
        boolean continuar = true;
        //Recorriendo los controles de la funcion objetivo
        for(int i = 0; i < getNumero_variables_desicion(); i++) {
            if(!txtFuncionObjetivo[i].getText().trim().equals("")){ //Validando que los controles de la función objetivo estén llenos
                if(i == getNumero_variables_desicion() - 1){
                    funcion_objetivo[i] = txtFuncionObjetivo[i].getText() + "X" + (i+1);
                } else {
                    funcion_objetivo[i] = txtFuncionObjetivo[i].getText() + "X" + (i+1) + " + ";
                }
                lblFuncionOriginal1.setText(lblFuncionOriginal1.getText() + funcion_objetivo[i]);
            } else {
                JOptionPane.showMessageDialog(Pnl_Metodo_Simplex_Dual.this, "Debe igresar todos los coeficientes de las variables y la parte derecha de la ecuación", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        lblFuncionOriginal1.setVisible(true);
        lblSujetoa.setVisible(true);
        
        //Recorriendo los controles de las funciones restriccion
        for(int i = 0; i < getNumero_restricciones(); i++){
            funcion_restriccion = "";
            lblFuncionRestriccion[i] = new JLabel();
            
            for(int j = 0; j < getNumero_variables_desicion(); j++){
                if(!txtCoeficiente[i][j].getText().trim().equals("")){  //validando que todos los controles de los coeficientes de las funciones restriccion esten llengos
                    if(j == getNumero_variables_desicion() - 1){
                        funcion_restriccion = funcion_restriccion + txtCoeficiente[i][j].getText()+"X"+(j+1) + " " + cbSigno[i].getSelectedItem() + " " + txtTerminoIndependiente[i].getText();
                    } else {
                        funcion_restriccion = funcion_restriccion + txtCoeficiente[i][j].getText()+"X"+(j+1) + " + ";
                    }
                } else {
                    continuar = false;
                    break;
                }
            }
            if(continuar == true){
                lblFuncionRestriccion[i].setText(funcion_restriccion);
                lblFuncionRestriccion[i].setBounds(10, fila, 600, 40);
                pnlInformacion.add(lblFuncionRestriccion[i]);
                lblFuncionRestriccion[i].setVisible(true);
                fila = fila + 25;
                //Agregamos las etiquetas que detalla como se pasa a la forma estandar el problema
                lblTexto1.setBounds(10, fila+50, 550, 40);
                lblTexto1.setText("<html>Se pasa el problema a forma estándar, añadiendo variables de exceso, holgura, y artificiales según corresponda</html>"); 
                lblTexto1.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(Pnl_Metodo_Simplex_Dual.this, "Debe igresar todos los coeficientes de las variables y la parte derecha de la ecuación", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        //Recorriendo los controles de las funciones restriccion
        for(int i = 0; i < getNumero_restricciones(); i++) {
            lblFuncionObjetivo[i] = new JLabel();
            
            for(int j = 0; j < getNumero_variables_desicion(); j++){
                if(txtCoeficiente[i][j].getText().trim().equals("")){
                    continuar = false;
                    break;
                }
            }
            if(continuar == true){
                if(cbSigno[i].getSelectedItem().equals("<=")){
                    lblFuncionObjetivo[i].setText("* Como la restricción " + (i+1) + " es del tipo " + cbSigno[i].getSelectedItem().toString() + " se agrega la variable de holgura X" + (getNumero_variables_desicion() + (i+1)));
                } else if(cbSigno[i].getSelectedItem().equals(">=")){
                    //lblFuncionObjetivo[i].setText("* Como la restricción " + i+1 + " es del tipo " + cbSigno[i].getSelectedItem().toString() + " se agrega la variable de holgura X" + getNumero_variables_desicion() + 1);
                } else if(cbSigno[i].getSelectedItem().equals("=")){
                    //lblFuncionObjetivo[i].setText("* Como la restricción " + i+1 + " es del tipo " + cbSigno[i].getSelectedItem().toString() + " se agrega la variable de holgura X" + getNumero_variables_desicion() + 1);
                }
                lblFuncionObjetivo[i].setBounds(25, fila+100, 550, 25);
                pnlInformacion.add(lblFuncionObjetivo[i]);
                lblFuncionObjetivo[i].setVisible(true);
                fila = fila + 25;
            } else {
                JOptionPane.showMessageDialog(Pnl_Metodo_Simplex_Dual.this, "Debe igresar todos los coeficientes de las variables y la parte derecha de la ecuación", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        //recorriendo la funcion objetivo y agregandole las holguras
        for(int i = 0; i < (getNumero_restricciones()); i++){
            if(i == (getNumero_restricciones()) - 1){
                funcion_objetivoH = funcion_objetivoH + "0X" + (getNumero_restricciones() + i);
            } else {
                funcion_objetivoH = funcion_objetivoH + "0X" + (getNumero_restricciones() + i) + " + ";
            }
        }
        lblFOHolguras.setText(lblFuncionOriginal1.getText() + " + " + funcion_objetivoH);
        lblFOHolguras.setBounds(10, fila + 125, 550, 25);
        pnlInformacion.add(lblFOHolguras);
        lblFOHolguras.setVisible(true);
        fila = fila + 25;
        //recorriendo las funciones restriccion y agregandoles las holguras
        for(int i = 0; i < getNumero_restricciones(); i++){
            funcion_restriccion = "";
            lblFuncionRestriccionH[i] = new JLabel();
            
            for(int j = 0; j < getNumero_variables_desicion(); j++){
                if(!txtCoeficiente[i][j].getText().trim().equals("")){  //validando que todos los controles de los coeficientes de las funciones restriccion esten llengos
                    if(j == getNumero_variables_desicion() - 1){
                        funcion_restriccion = funcion_restriccion + txtCoeficiente[i][j].getText()+"X"+(j+1) + " + " + "1X" + (getNumero_restricciones() + i) + " = " + txtTerminoIndependiente[i].getText();
                    } else {
                        funcion_restriccion = funcion_restriccion + txtCoeficiente[i][j].getText()+"X"+(j+1) + " + ";
                    }
                } else {
                    continuar = false;
                    break;
                }
            }
            if(continuar == true){
                /*if(i == getNumero_restricciones() - 1){
                    JLabel lblSujetoa2 = new JLabel();
                    lblSujetoa2.setText("SUJETO A:");
                    lblSujetoa2.setBounds(10, fila + 75, 100, 40);
                    pnlInformacion.add(lblSujetoa2);
                    lblSujetoa2.setVisible(true);
                }*/
                //fila = fila + 25;
                lblFuncionRestriccionH[i].setText(funcion_restriccion);
                lblFuncionRestriccionH[i].setBounds(10, fila + 125, 600, 40);
                pnlInformacion.add(lblFuncionRestriccionH[i]);
                lblFuncionRestriccionH[i].setVisible(true);
                fila = fila + 25;
            } else {
                JOptionPane.showMessageDialog(Pnl_Metodo_Simplex_Dual.this, "Debe igresar todos los coeficientes de las variables y la parte derecha de la ecuación", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        this.repaint();
    }
    
    public void agregar_datos_tabla_inicial(ArrayList<Celda> lista_celda, int numero_filas, int numero_columnas){
        if(lista_celda.size() > 0){
            DefaultTableModel modelo = (DefaultTableModel)tblGridSolucion.getModel();
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            tcr.setVerticalAlignment(SwingConstants.CENTER);
            Object[] fila = new Object[numero_columnas];
            int contador = 0;
            int contador_headers = 0;
            //genero los encabezados del JTable
            //modelo.setRowCount(numero_filas);
            modelo.setColumnCount(numero_columnas);
            headers = new String[numero_columnas];
            //Agrego los encabezados al JTable
            for(contador_headers = 0; contador_headers < getNumero_variables_desicion(); contador_headers++){
                TableColumn column = tblGridSolucion.getTableHeader().getColumnModel().getColumn(contador_headers);
                headers[contador_headers] = "X"+(contador_headers+1);
                column.setHeaderValue(headers[contador_headers]);
            }
            for(int k = 0; k < getNumero_restricciones() + 1; k++){
                if(k == getNumero_restricciones()){
                    TableColumn column = tblGridSolucion.getTableHeader().getColumnModel().getColumn(contador_headers);
                    headers[contador_headers] = "Solucion";
                    column.setHeaderValue(headers[contador_headers]);
                } else {
                    TableColumn column = tblGridSolucion.getTableHeader().getColumnModel().getColumn(contador_headers);
                    headers[contador_headers] = "S"+(contador_headers+1);
                    column.setHeaderValue(headers[contador_headers]);
                }
                contador_headers++;
            }
            //Lleno el JTable con las datos de lista_celda
            for(int i = 0; i < numero_filas; i++){
                for(int j = 0; j < numero_columnas; j++){
                    tblGridSolucion.getColumnModel().getColumn(j).setCellRenderer(tcr);
                    fila[j] = lista_celda.get(contador).getValor_celda();
                    contador++;
                }
                modelo.addRow(fila);
            }
        }
        //repaint();
    }
    
    public void generar_tabla_inicial(){
        int numero_filas_restriccion = (getNumero_restricciones());
        int numero_columnas_restriccion = (getNumero_variables_desicion() + getNumero_restricciones() + 1);
        int contador_filas = 0;
        int contador_columnas = 0;
        String metodo = "";
        //hay 3 casos, cuando las restricciones son <=, >= o =
        for(int i = 0; i < getNumero_restricciones(); i++){
            if(cbSigno[i].getSelectedItem().equals("<=")){
                metodo = "simplex";
            } else if(!cbSigno[i].getSelectedItem().equals("<=")){
                metodo = "dual";
                break;
            }
        }
        
        if(metodo.equals("simplex")){
            //Agrego la matriz de funciones restriccion a la lista de celdas
            for(int i = 0; i < numero_filas_restriccion; i++){
                for(int j = 0; j < numero_columnas_restriccion; j++){
                    //agregando la matriz normal
                    if(j < getNumero_variables_desicion()){
                        celda = new Celda(Integer.parseInt(txtCoeficiente[i][j].getText().trim()), Integer.parseInt(txtTerminoIndependiente[i].getText().trim()), "", "", 0, true, i, j);
                        lista_celda.add(celda);
                        contador_columnas++;
                    } else {    //agregando la matriz identidad
                        if(contador_filas == i && contador_columnas == j + i){    //si es la primera columna de matriz identidad, que le ponga 1 al valor de la celda
                            celda = new Celda(1, Integer.parseInt(txtTerminoIndependiente[i].getText().trim()), "", "", 0, true, i, j);
                            lista_celda.add(celda);
                        } else {    //si no, que le ponga 0 al valor de la celda
                            if(j == numero_columnas_restriccion - 1){
                                celda = new Celda(Integer.parseInt(txtTerminoIndependiente[i].getText().trim()), Integer.parseInt(txtTerminoIndependiente[i].getText().trim()), "", "", 0, true, i, j);
                                lista_celda.add(celda);
                            } else {
                                celda = new Celda(0, Integer.parseInt(txtTerminoIndependiente[i].getText().trim()), "", "", 0, true, i, j);
                                lista_celda.add(celda);
                            }
                        }
                    }
                }
                contador_filas++;
            }
            //Agregando la la funcion Z a la lista de celdas
            for(int j = 0; j < numero_columnas_restriccion; j++){
                if(j < getNumero_variables_desicion()){ //guarda los valores de la funcion objetivo Z
                    celda = new Celda((-1)*Integer.parseInt(txtFuncionObjetivo[j].getText().trim()), 0, "Z", "Z", 0, true, numero_filas_restriccion, j);
                    lista_celda.add(celda);
                } else {    //agregando la matriz identidad para la funcion objetivo Z
                    celda = new Celda(0, 0, "Z", "Z", 0, true, numero_filas_restriccion, j);
                    lista_celda.add(celda);
                }
            }
            agregar_datos_tabla_inicial(lista_celda, numero_filas_restriccion + 1, numero_columnas_restriccion);
        } else if(metodo.equals("dual")){
            //aqui voy a crear una lista para el metodo dual
        }
        //Agregando la información de la lista al JTable
        
        //imprimo la lista de celdas
        for(int i = 0; i < lista_celda.size(); i++){  //recorro las filas (restricciones + fila Z)
            System.out.println("valor_celda: "+lista_celda.get(i).getValor_celda()+
                    " valor_constante: "+lista_celda.get(i).getValor_constante()+
                    " var_entrada: "+lista_celda.get(i).getVariable_entrada()+
                    " var_salida: "+lista_celda.get(i).getVariable_salida()+
                    " pivote: "+lista_celda.get(i).getPivote()+
                    " valida: "+lista_celda.get(i).getCelda_valida()+
                    " fila: "+lista_celda.get(i).getFila()+
                    " columna: "+lista_celda.get(i).getColumna());
        }
    }
    
    public void obtener_fila_z(ArrayList<Celda> lista_celda){
        fila_z = new ArrayList<Fila_Z>(); //Fila_Z[getNumero_variables_desicion()+getNumero_restricciones()+1];
        Fila_Z celda;
        for(int i = 0; i < lista_celda.size(); i++){
            if(lista_celda.get(i).getVariable_entrada().equals("Z") && lista_celda.get(i).getVariable_salida().equals("Z")){    //obtengo la fila Z
                celda = new Fila_Z(lista_celda.get(i).getFila(), lista_celda.get(i).getColumna(), lista_celda.get(i).getValor_celda());
                fila_z.add(celda);
            }
        }
        //imprimiendo la Fila Z
        for(int i = 0; i < fila_z.size(); i++){
            System.out.println("fila: "+fila_z.get(i).getFila() + " columna: " + fila_z.get(i).getColumna() + " valor: " + fila_z.get(i).getValor_celda());
        }
    }
    
    public int columna_entrada(Fila_Z[] fila_z){
        int columna = 0;
        if(fila_z.length > 0){
            
        }
        return columna;
    }
    
    public int fila_entrada(ArrayList<Celda> lista_celda){
        int fila = 0;
        return fila;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spMetodoSimplexDual = new javax.swing.JSplitPane();
        pnlControlesdinamicos = new javax.swing.JPanel();
        pnlContenedorGeneral = new javax.swing.JPanel();
        pnlInformacion = new javax.swing.JPanel();
        btnGenerarTabla = new javax.swing.JButton();
        lblTexto1 = new javax.swing.JLabel();
        lblFuncionOriginal1 = new javax.swing.JLabel();
        lblSujetoa = new javax.swing.JLabel();
        lblFOHolguras = new javax.swing.JLabel();
        pnlSolucion = new javax.swing.JPanel();
        pnlGridSolucion = new javax.swing.JPanel();
        spSolucionGrid = new javax.swing.JScrollPane();
        tblGridSolucion = new javax.swing.JTable();
        btnIterar = new javax.swing.JButton();
        spSolucionGrid2 = new javax.swing.JScrollPane();
        tblGridSolucion2 = new javax.swing.JTable();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        spMetodoSimplexDual.setDividerLocation(200);
        spMetodoSimplexDual.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        pnlControlesdinamicos.setAutoscrolls(true);
        pnlControlesdinamicos.setLayout(null);
        spMetodoSimplexDual.setLeftComponent(pnlControlesdinamicos);

        pnlContenedorGeneral.setLayout(null);

        pnlInformacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información solución", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        pnlInformacion.setLayout(null);

        btnGenerarTabla.setText("Generar tabla");
        btnGenerarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarTablaActionPerformed(evt);
            }
        });
        pnlInformacion.add(btnGenerarTabla);
        btnGenerarTabla.setBounds(220, 30, 120, 25);

        lblTexto1.setText("lblTexto1");
        pnlInformacion.add(lblTexto1);
        lblTexto1.setBounds(10, 260, 530, 30);

        lblFuncionOriginal1.setText("FUNCION OBJETIVO:");
        pnlInformacion.add(lblFuncionOriginal1);
        lblFuncionOriginal1.setBounds(10, 70, 540, 20);

        lblSujetoa.setText("SUJETO A:");
        pnlInformacion.add(lblSujetoa);
        lblSujetoa.setBounds(10, 100, 70, 16);

        lblFOHolguras.setText("jLabel1");
        pnlInformacion.add(lblFOHolguras);
        lblFOHolguras.setBounds(10, 130, 540, 16);

        pnlContenedorGeneral.add(pnlInformacion);
        pnlInformacion.setBounds(10, 10, 560, 460);

        pnlSolucion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Solución", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        pnlSolucion.setLayout(null);

        pnlGridSolucion.setLayout(null);

        tblGridSolucion.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        tblGridSolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblGridSolucion.setColumnSelectionAllowed(true);
        tblGridSolucion.setEnabled(false);
        tblGridSolucion.getTableHeader().setReorderingAllowed(false);
        spSolucionGrid.setViewportView(tblGridSolucion);

        pnlGridSolucion.add(spSolucionGrid);
        spSolucionGrid.setBounds(60, 10, 550, 170);

        btnIterar.setText("Siguiente paso...");
        pnlGridSolucion.add(btnIterar);
        btnIterar.setBounds(60, 390, 150, 25);

        tblGridSolucion2.setFont(new java.awt.Font("Consolas", 1, 16)); // NOI18N
        tblGridSolucion2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblGridSolucion2.setEnabled(false);
        tblGridSolucion2.getTableHeader().setReorderingAllowed(false);
        spSolucionGrid2.setViewportView(tblGridSolucion2);

        pnlGridSolucion.add(spSolucionGrid2);
        spSolucionGrid2.setBounds(60, 200, 550, 170);

        pnlSolucion.add(pnlGridSolucion);
        pnlGridSolucion.setBounds(6, 19, 690, 430);

        pnlContenedorGeneral.add(pnlSolucion);
        pnlSolucion.setBounds(580, 10, 700, 460);

        spMetodoSimplexDual.setRightComponent(pnlContenedorGeneral);

        add(spMetodoSimplexDual);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarTablaActionPerformed
        // TODO add your handling code here:
        generar_informacion_solucion();
        generar_tabla_inicial();
        obtener_fila_z
    }//GEN-LAST:event_btnGenerarTablaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarTabla;
    private javax.swing.JButton btnIterar;
    private javax.swing.JLabel lblFOHolguras;
    private javax.swing.JLabel lblFuncionOriginal1;
    private javax.swing.JLabel lblSujetoa;
    private javax.swing.JLabel lblTexto1;
    private javax.swing.JPanel pnlContenedorGeneral;
    private javax.swing.JPanel pnlControlesdinamicos;
    private javax.swing.JPanel pnlGridSolucion;
    private javax.swing.JPanel pnlInformacion;
    private javax.swing.JPanel pnlSolucion;
    private javax.swing.JSplitPane spMetodoSimplexDual;
    private javax.swing.JScrollPane spSolucionGrid;
    private javax.swing.JScrollPane spSolucionGrid2;
    private javax.swing.JTable tblGridSolucion;
    private javax.swing.JTable tblGridSolucion2;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the numero_variables_desicion
     */
    public int getNumero_variables_desicion() {
        return numero_variables_desicion;
    }

    /**
     * @param numero_variables_desicion the numero_variables_desicion to set
     */
    public void setNumero_variables_desicion(int numero_variables_desicion) {
        this.numero_variables_desicion = numero_variables_desicion;
    }

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
     * @return the objetivo_funcion
     */
    public String getObjetivo_funcion() {
        return objetivo_funcion;
    }

    /**
     * @param objetivo_funcion the objetivo_funcion to set
     */
    public void setObjetivo_funcion(String objetivo_funcion) {
        this.objetivo_funcion = objetivo_funcion;
    }

    /**
     * @return the jButton1
     */
    public javax.swing.JButton getjButton1() {
        return btnGenerarTabla;
    }

    /**
     * @param jButton1 the jButton1 to set
     */
    public void setjButton1(javax.swing.JButton jButton1) {
        this.btnGenerarTabla = jButton1;
    }

    /**
     * @return the jPanel1
     */
    public javax.swing.JPanel getjPanel1() {
        return pnlGridSolucion;
    }

    /**
     * @param jPanel1 the jPanel1 to set
     */
    public void setjPanel1(javax.swing.JPanel jPanel1) {
        this.pnlGridSolucion = jPanel1;
    }

    /**
     * @return the jScrollPane1
     */
    public javax.swing.JScrollPane getjScrollPane1() {
        return spSolucionGrid;
    }

    /**
     * @param jScrollPane1 the jScrollPane1 to set
     */
    public void setjScrollPane1(javax.swing.JScrollPane jScrollPane1) {
        this.spSolucionGrid = jScrollPane1;
    }

    /**
     * @return the jTable1
     */
    public javax.swing.JTable getjTable1() {
        return tblGridSolucion;
    }

    /**
     * @param jTable1 the jTable1 to set
     */
    public void setjTable1(javax.swing.JTable jTable1) {
        this.tblGridSolucion = jTable1;
    }

    /**
     * @return the pnlContenedorGeneral
     */
    public javax.swing.JPanel getPnlContenedorGeneral() {
        return pnlContenedorGeneral;
    }

    /**
     * @param pnlContenedorGeneral the pnlContenedorGeneral to set
     */
    public void setPnlContenedorGeneral(javax.swing.JPanel pnlContenedorGeneral) {
        this.pnlContenedorGeneral = pnlContenedorGeneral;
    }

    /**
     * @return the pnlControlesdinamicos
     */
    public javax.swing.JPanel getPnlControlesdinamicos() {
        return pnlControlesdinamicos;
    }

    /**
     * @param pnlControlesdinamicos the pnlControlesdinamicos to set
     */
    public void setPnlControlesdinamicos(javax.swing.JPanel pnlControlesdinamicos) {
        this.pnlControlesdinamicos = pnlControlesdinamicos;
    }

    /**
     * @return the pnlInformacion
     */
    public javax.swing.JPanel getPnlInformacion() {
        return pnlInformacion;
    }

    /**
     * @param pnlInformacion the pnlInformacion to set
     */
    public void setPnlInformacion(javax.swing.JPanel pnlInformacion) {
        this.pnlInformacion = pnlInformacion;
    }

    /**
     * @return the pnlSolucion
     */
    public javax.swing.JPanel getPnlSolucion() {
        return pnlSolucion;
    }

    /**
     * @param pnlSolucion the pnlSolucion to set
     */
    public void setPnlSolucion(javax.swing.JPanel pnlSolucion) {
        this.pnlSolucion = pnlSolucion;
    }

    /**
     * @return the spMetodoSimplexDual
     */
    public javax.swing.JSplitPane getSpMetodoSimplexDual() {
        return spMetodoSimplexDual;
    }

    /**
     * @param spMetodoSimplexDual the spMetodoSimplexDual to set
     */
    public void setSpMetodoSimplexDual(javax.swing.JSplitPane spMetodoSimplexDual) {
        this.spMetodoSimplexDual = spMetodoSimplexDual;
    }
}
