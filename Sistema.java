/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layouts;

import Cliente.DatosCliente;
import Cliente.datosProductoCompra;
import civerlac.Cliente;
import civerlac.ClienteDB;
import civerlac.ConexionSQL;
import civerlac.Empleado;
import civerlac.EmpleadosDB;
import civerlac.ProductoDB;
import civerlac.ProveedorDB;
import civerlac.VentaDB;
import civerlac.Ventas;
import civerlac.VentasDB;
import civerlac.producto;
import civerlac.valoresEstaticos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author Roger Sifontes
 */
public class Sistema extends javax.swing.JFrame {

   
    
    /**
     * Creates new form Sistema
     */
    public Sistema() {
        initComponents();
        this.setLocationRelativeTo(null);
       ///
        txtIdClienteCLI.setText("Automatico");
        txtIdProductosProductos.setText("#Auto");
        txtIdClienteCLI.setEnabled(false);
       
       ///
        listaClientes();
        listaProveedores();
        listaProductos();
        listaEmpleados();
        listaHistorialVenta();
        btncancel.setVisible(false);
        btncanelProveedor.setVisible(false);
        btnCancelEmp.setVisible(false);
        //
        
        lblTotal.setText("0.00");
        
    }
    
    String estado="";
    //****MEDOTOS CLIENTES****
    
    Cliente cl = new Cliente();
    ClienteDB cliente = new ClienteDB();
    EmpleadosDB empleadodb= new EmpleadosDB();
    DefaultTableModel modeloTB = new DefaultTableModel();
    DefaultTableModel modelProveedor = new DefaultTableModel();
    DefaultTableModel modelEmpleado = new DefaultTableModel();
    DefaultTableModel modelProducto = new DefaultTableModel();
    DefaultTableModel modelVenta = new DefaultTableModel();
    DefaultTableModel modelHistorialVenta = new DefaultTableModel();
    
    public  List<datosProductoCompra> getListaProd() {
        return listaProd;
    }

    /**
     *
     */
    public  void setListaProd(List<datosProductoCompra> listaProd) {
        Sistema.listaProd = listaProd;
    }

    public static List<datosProductoCompra> listaProd=new ArrayList();
    public void listaClientes(){
        List<Cliente> listaCl = cliente.listarClientes();
        modeloTB = (DefaultTableModel) tbClientesCLI.getModel();
        
        Object[] obj = new Object[6];
        for (int i = 0; i < listaCl.size(); i++) {
            obj[0] = listaCl.get(i).getIdCliente();
            obj[1] = listaCl.get(i).getNombre();
            obj[2] = listaCl.get(i).getCorreo();
            obj[3] = listaCl.get(i).getDireccion();
            obj[4] = listaCl.get(i).getIdTelefonoCli();
            obj[5] = listaCl.get(i).getEstado();
            
            modeloTB.addRow(obj);
            
        }
        tbClientesCLI.setModel(modeloTB);
    }
    public void listaProveedores(){
        ProveedorDB cliente = new ProveedorDB();
        List<Cliente> listaCl = cliente.listarClientes();
        modelProveedor = (DefaultTableModel) tbProveedores.getModel();
        
        Object[] obj = new Object[6];
        for (int i = 0; i < listaCl.size(); i++) {
            obj[0] = listaCl.get(i).getIdCliente();
            obj[1] = listaCl.get(i).getNombre();
            obj[2] = listaCl.get(i).getCorreo();
            obj[3] = listaCl.get(i).getIdTelefonoCli();
            obj[4] = listaCl.get(i).getDireccion();
            obj[5] = listaCl.get(i).getEstado();
            
            modelProveedor.addRow(obj);
            
        }
        tbProveedores.setModel(modelProveedor);
    }
  
    
     public void listaProductos(){
        ProductoDB cliente = new ProductoDB();
        List<producto> listaCl = cliente.listarProductos();
        modelProducto = (DefaultTableModel) tbProductos.getModel();
        
        Object[] obj = new Object[6];
        for (int i = 0; i < listaCl.size(); i++) {
            obj[0] = listaCl.get(i).getId();
            obj[1] = listaCl.get(i).getNombre();
            obj[2] = listaCl.get(i).getPreciol();
            obj[3] = listaCl.get(i).getStock();
         
            
            modelProducto.addRow(obj);
            
        }
        tbProductos.setModel(modelProducto);
    }
     
     public void listaEmpleados(){
        EmpleadosDB cliente = new EmpleadosDB();
        List<Empleado> listaCl = cliente.ListEmpleados();
        modelEmpleado = (DefaultTableModel) tbEmpleados.getModel();
        
        Object[] obj = new Object[7];
        for (int i = 0; i < listaCl.size(); i++) {
            obj[0] = listaCl.get(i).getIdEmpleado();
            obj[1] = listaCl.get(i).getNombre();
            obj[2] = listaCl.get(i).getOcupacion();
            obj[3] = listaCl.get(i).getCorreo();
            obj[4] = listaCl.get(i).getDireccion();
            obj[5] = listaCl.get(i).getTelefono();
            if(listaCl.get(i).getEstado()==1){
            obj[6] = "Habilitado";
            }
            else{
            obj[6] = "Inhabilitado";
            }
            
            
            modelEmpleado.addRow(obj);
            
        }
        tbEmpleados.setModel(modelEmpleado);
    }
     public void listaHistorialVenta(){
        VentasDB cliente = new VentasDB();
        List<Ventas> listaCl = cliente.ListarHistorialVenta();
        modelHistorialVenta = (DefaultTableModel) tbVentas.getModel();
        
        Object[] obj = new Object[6];
        for (int i = 0; i < listaCl.size(); i++) {
            obj[0] = listaCl.get(i).getIdFactura();
            obj[1] = listaCl.get(i).getCliente();
            obj[2] = listaCl.get(i).getEmpleado();
            obj[3] = listaCl.get(i).getTotal();    
            modelHistorialVenta.addRow(obj);
            
        }
        tbVentas.setModel(modelHistorialVenta);
    }
     /////Limpiar tablas
    public void limpiarTabla(){
        for (int i = 0; i < modeloTB.getRowCount(); i++) {
            modeloTB.removeRow(i);
            i=i-1;
        }
    }
    
    public void limpiarTablaEmpleado(){
        for (int i = 0; i < modelEmpleado.getRowCount(); i++) {
            modelEmpleado.removeRow(i);
            i=i-1;
        }
    }
    
    public void limpiarTablaProveedor(){
        for (int i = 0; i < modelProveedor.getRowCount(); i++) {
            modelProveedor.removeRow(i);
            i=i-1;
        }
    }
    public void limpiarTablaProducto(){
        for (int i = 0; i <modelProducto.getRowCount(); i++) {
            modelProducto.removeRow(i);
            i=i-1;
        }
    }
     public void limpiarTablaHistorialV(){
        for (int i = 0; i < modelHistorialVenta.getRowCount(); i++) {
            modelHistorialVenta.removeRow(i);
            i=i-1;
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

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        panelInfo = new javax.swing.JTabbedPane();
        tabClientes = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtIdClienteCLI = new javax.swing.JTextField();
        txtNombreClienteCLI = new javax.swing.JTextField();
        txtCorreoClienteCLI = new javax.swing.JTextField();
        txtDireccionClienteCLI = new javax.swing.JTextField();
        txtTelefonoClienteCLI = new javax.swing.JTextField();
        cbEstadoCLienteCLI = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbClientesCLI = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        btnNuevoClienteCLI = new javax.swing.JButton();
        btnModificarCLI = new javax.swing.JButton();
        btnGuardarCLI = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbTipoFiltroCliente = new javax.swing.JComboBox<>();
        ClienteBuscar = new javax.swing.JButton();
        ClienteBuscar1 = new javax.swing.JButton();
        tabProveedores = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtIdProveedores = new javax.swing.JTextField();
        txtNombreProveedores = new javax.swing.JTextField();
        txtCorreoProveedores = new javax.swing.JTextField();
        txtTelefonoProveedores = new javax.swing.JTextField();
        txtubicacionProveedor = new javax.swing.JTextField();
        cbEstadoProveedores = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProveedores = new javax.swing.JTable();
        btnNuevoProveedores = new javax.swing.JButton();
        btnModificarProveedores = new javax.swing.JButton();
        btnGuardarProveedores = new javax.swing.JButton();
        btncanelProveedor = new javax.swing.JButton();
        txtsearchproovedor = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        cmbproveedor = new javax.swing.JComboBox<>();
        tabEmpleados = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtIdEmpleados = new javax.swing.JTextField();
        txtNombreEmpleados = new javax.swing.JTextField();
        txtCorreoEmpleados = new javax.swing.JTextField();
        txtDireccionEmpleados = new javax.swing.JTextField();
        txtTelefonoEmpleados = new javax.swing.JTextField();
        cbOcupacionEmpleado = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbEmpleados = new javax.swing.JTable();
        btnNuevoEmpleado = new javax.swing.JButton();
        btnModificarEmpleado = new javax.swing.JButton();
        btnGuardarEmpleados = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        btnCancelEmp = new javax.swing.JButton();
        cbEstadoEmpleados1 = new javax.swing.JComboBox<>();
        btnAddUser = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        txtsearchempleado = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        cmbempleado = new javax.swing.JComboBox<>();
        btnEliminarNV2 = new javax.swing.JButton();
        tabProductos = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtIdProductosProductos = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtNombreDelProducto = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        btnAgregarProductos = new javax.swing.JButton();
        btnEliminarProductos = new javax.swing.JButton();
        txtprecioProd = new javax.swing.JTextField();
        txtStockProd = new javax.swing.JTextField();
        btnProdModificar = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnProdCancelar = new javax.swing.JButton();
        tabHistorial = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbVentas = new javax.swing.JTable();
        btnPdfVentas = new javax.swing.JButton();
        txtIdVenta = new javax.swing.JTextField();
        pnlCompras = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtIdProductoNV1 = new javax.swing.JTextField();
        txtDescripcionProductoNV1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtCantidadNV1 = new javax.swing.JTextField();
        txtPrecioNV1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtTotalVN1 = new javax.swing.JTextField();
        btnAgregarNV1 = new javax.swing.JButton();
        btnEliminarNV1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbNuevaVenta1 = new javax.swing.JTable();
        txtNombreClienteNV1 = new javax.swing.JTextField();
        txtIdClienteNV1 = new javax.swing.JTextField();
        btnVentaNV1 = new javax.swing.JButton();
        tabNuevaVenta = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdProductoNV = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDescripcionProductoNV = new javax.swing.JTextField();
        txtCantidadNV = new javax.swing.JTextField();
        txtPrecioNV = new javax.swing.JTextField();
        txtTotalVN = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNuevaVenta = new javax.swing.JTable();
        btnVentaNV = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnAgregarNV = new javax.swing.JButton();
        btnEliminarNV = new javax.swing.JButton();
        txtIdClienteNV = new javax.swing.JTextField();
        txtNombreClienteNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnNuevaVenta = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnEmpleados = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        panelInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadEmpleados(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Id Cliente");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Nombre");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Correo");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Dirección");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Telefono");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Estado");

        txtIdClienteCLI.setText("Automatico");
        txtIdClienteCLI.setEnabled(false);
        txtIdClienteCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClienteCLIActionPerformed(evt);
            }
        });

        txtNombreClienteCLI.setEnabled(false);

        txtCorreoClienteCLI.setEnabled(false);
        txtCorreoClienteCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoClienteCLIActionPerformed(evt);
            }
        });

        txtDireccionClienteCLI.setEnabled(false);
        txtDireccionClienteCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionClienteCLIActionPerformed(evt);
            }
        });

        txtTelefonoClienteCLI.setEnabled(false);
        txtTelefonoClienteCLI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteCLIKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteCLIKeyTyped(evt);
            }
        });

        cbEstadoCLienteCLI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Habilitado", "Deshabilitado"}));
        cbEstadoCLienteCLI.setEnabled(false);
        cbEstadoCLienteCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoCLienteCLIActionPerformed(evt);
            }
        });

        tbClientesCLI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cliente", "Nombre", "Correo", "Direccion", "Telefono", "Estado"
            }
        ));
        tbClientesCLI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseClickTable(evt);
            }
        });
        jScrollPane2.setViewportView(tbClientesCLI);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Clientes");

        btnNuevoClienteCLI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ingresar productos.png"))); // NOI18N
        btnNuevoClienteCLI.setText("Nuevo Cliente");
        btnNuevoClienteCLI.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoClienteCLI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoClienteCLIMouseClicked(evt);
            }
        });
        btnNuevoClienteCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteCLIActionPerformed(evt);
            }
        });

        btnModificarCLI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/historial ventas.png"))); // NOI18N
        btnModificarCLI.setText("Modificar Cliente");
        btnModificarCLI.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificarCLI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarCLIMouseClicked(evt);
            }
        });
        btnModificarCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCLIActionPerformed(evt);
            }
        });

        btnGuardarCLI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/guardar productos.png"))); // NOI18N
        btnGuardarCLI.setText("Guardar");
        btnGuardarCLI.setEnabled(false);
        btnGuardarCLI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarCLIMouseClicked(evt);
            }
        });
        btnGuardarCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCLIActionPerformed(evt);
            }
        });

        btncancel.setText("Cancelar");
        btncancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        txtsearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Filtrar por");

        cmbTipoFiltroCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "ID" }));

        ClienteBuscar.setText("Buscar");
        ClienteBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ClienteBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteBuscarActionPerformed(evt);
            }
        });

        ClienteBuscar1.setText("Refrescar");
        ClienteBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ClienteBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabClientesLayout = new javax.swing.GroupLayout(tabClientes);
        tabClientes.setLayout(tabClientesLayout);
        tabClientesLayout.setHorizontalGroup(
            tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabClientesLayout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel17)
                .addGap(143, 143, 143)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbTipoFiltroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ClienteBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClienteBuscar1))
            .addGroup(tabClientesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(30, 30, 30)
                        .addComponent(txtNombreClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(36, 36, 36)
                        .addComponent(txtCorreoClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(22, 22, 22)
                        .addComponent(txtDireccionClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(24, 24, 24)
                        .addComponent(txtTelefonoClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(35, 35, 35)
                        .addComponent(cbEstadoCLienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addComponent(btnNuevoClienteCLI)
                        .addGap(10, 10, 10)
                        .addComponent(btnModificarCLI))
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnGuardarCLI, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        tabClientesLayout.setVerticalGroup(
            tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabClientesLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbTipoFiltroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ClienteBuscar)
                                .addComponent(ClienteBuscar1)))))
                .addGap(17, 17, 17)
                .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabClientesLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabClientesLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel11))
                            .addComponent(txtIdClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabClientesLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel12))
                            .addComponent(txtNombreClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabClientesLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel13))
                            .addComponent(txtCorreoClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabClientesLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel14))
                            .addComponent(txtDireccionClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabClientesLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel15))
                            .addComponent(txtTelefonoClienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabClientesLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel16))
                            .addComponent(cbEstadoCLienteCLI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoClienteCLI)
                            .addComponent(btnModificarCLI))
                        .addGap(11, 11, 11)
                        .addGroup(tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarCLI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btncancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelInfo.addTab("Clientes", tabClientes);

        tabProveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Nombre");
        tabProveedores.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Telefono");
        tabProveedores.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Correo");
        tabProveedores.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Direccion");
        tabProveedores.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Estado");
        tabProveedores.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        txtIdProveedores.setText("Automatico");
        txtIdProveedores.setEnabled(false);
        txtIdProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProveedoresActionPerformed(evt);
            }
        });
        tabProveedores.add(txtIdProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 201, -1));

        txtNombreProveedores.setEnabled(false);
        tabProveedores.add(txtNombreProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 201, -1));

        txtCorreoProveedores.setEnabled(false);
        txtCorreoProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoProveedoresActionPerformed(evt);
            }
        });
        tabProveedores.add(txtCorreoProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 201, -1));

        txtTelefonoProveedores.setEnabled(false);
        txtTelefonoProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoProveedoresActionPerformed(evt);
            }
        });
        tabProveedores.add(txtTelefonoProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 201, -1));

        txtubicacionProveedor.setEnabled(false);
        tabProveedores.add(txtubicacionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 201, -1));

        cbEstadoProveedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Habilitado", "Deshabilitado"}));
        cbEstadoProveedores.setEnabled(false);
        cbEstadoProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoProveedoresActionPerformed(evt);
            }
        });
        tabProveedores.add(cbEstadoProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 201, -1));

        tbProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Proveedor", "Nombre", "Correo", "Direccion", "Telefono", "Estado"
            }
        ));
        tbProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClickedtbProveedor(evt);
            }
        });
        jScrollPane3.setViewportView(tbProveedores);

        tabProveedores.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 691, -1));

        btnNuevoProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ingresar productos.png"))); // NOI18N
        btnNuevoProveedores.setText("Nuevo Proveedor");
        btnNuevoProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoProveedoresMouseClicked(evt);
            }
        });
        btnNuevoProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedoresActionPerformed(evt);
            }
        });
        tabProveedores.add(btnNuevoProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, -1));

        btnModificarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/historial ventas.png"))); // NOI18N
        btnModificarProveedores.setText("Modificar Proveedor");
        btnModificarProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificarProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarProveedoresMouseClicked(evt);
            }
        });
        btnModificarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProveedoresActionPerformed(evt);
            }
        });
        tabProveedores.add(btnModificarProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, -1, -1));

        btnGuardarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/guardar productos.png"))); // NOI18N
        btnGuardarProveedores.setText("Guardar");
        btnGuardarProveedores.setEnabled(false);
        btnGuardarProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarProveedoresMouseClicked(evt);
            }
        });
        btnGuardarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedoresActionPerformed(evt);
            }
        });
        tabProveedores.add(btnGuardarProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 135, -1));

        btncanelProveedor.setText("Cancelar");
        btncanelProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncanelProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncanelProveedorActionPerformed(evt);
            }
        });
        tabProveedores.add(btncanelProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 400, 90, 40));

        txtsearchproovedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsearchproovedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchproovedorKeyReleased(evt);
            }
        });
        tabProveedores.add(txtsearchproovedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 190, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setText("Proveedores");
        tabProveedores.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 42, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Id Cliente");
        tabProveedores.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 97, -1, -1));

        jButton2.setText("Buscar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        tabProveedores.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Filtrar por");
        tabProveedores.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        cmbproveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "ID", "Estado" }));
        tabProveedores.add(cmbproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 110, 20));

        panelInfo.addTab("Proveedores", tabProveedores);

        tabEmpleados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Nombre");
        tabEmpleados.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Direccion");
        tabEmpleados.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Correo");
        tabEmpleados.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Telefono");
        tabEmpleados.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Estado");
        tabEmpleados.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        txtIdEmpleados.setText("Automatico");
        txtIdEmpleados.setEnabled(false);
        txtIdEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEmpleadosActionPerformed(evt);
            }
        });
        tabEmpleados.add(txtIdEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 199, -1));

        txtNombreEmpleados.setEnabled(false);
        tabEmpleados.add(txtNombreEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 201, -1));

        txtCorreoEmpleados.setEnabled(false);
        txtCorreoEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoEmpleadosActionPerformed(evt);
            }
        });
        tabEmpleados.add(txtCorreoEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 201, -1));

        txtDireccionEmpleados.setEnabled(false);
        txtDireccionEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionEmpleadosActionPerformed(evt);
            }
        });
        tabEmpleados.add(txtDireccionEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 201, -1));

        txtTelefonoEmpleados.setEnabled(false);
        tabEmpleados.add(txtTelefonoEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 201, -1));

        cbOcupacionEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Vendedor"}));
        cbOcupacionEmpleado.setEnabled(false);
        cbOcupacionEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOcupacionEmpleadoActionPerformed(evt);
            }
        });
        tabEmpleados.add(cbOcupacionEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 201, -1));

        tbEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Empleado", "Nombre", "Ocupacion ", "Correo", "Direccion", "Telefono", "Estado"
            }
        ));
        tbEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClickedTbEmpleado(evt);
            }
        });
        jScrollPane6.setViewportView(tbEmpleados);

        tabEmpleados.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 675, -1));

        btnNuevoEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ingresar productos.png"))); // NOI18N
        btnNuevoEmpleado.setText("Nuevo Empleado");
        btnNuevoEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoEmpleadoMouseClicked(evt);
            }
        });
        btnNuevoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoEmpleadoActionPerformed(evt);
            }
        });
        tabEmpleados.add(btnNuevoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        btnModificarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/historial ventas.png"))); // NOI18N
        btnModificarEmpleado.setText("Modificar Empleado");
        btnModificarEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificarEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarEmpleadoMouseClicked(evt);
            }
        });
        btnModificarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEmpleadoActionPerformed(evt);
            }
        });
        tabEmpleados.add(btnModificarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, -1));

        btnGuardarEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/guardar productos.png"))); // NOI18N
        btnGuardarEmpleados.setText("Guardar");
        btnGuardarEmpleados.setEnabled(false);
        btnGuardarEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarEmpleadosMouseClicked(evt);
            }
        });
        btnGuardarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEmpleadosActionPerformed(evt);
            }
        });
        tabEmpleados.add(btnGuardarEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 135, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Ocupación ");
        tabEmpleados.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        btnCancelEmp.setText("Cancelar");
        btnCancelEmp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelEmpActionPerformed(evt);
            }
        });
        tabEmpleados.add(btnCancelEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 90, 40));

        cbEstadoEmpleados1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Habilitado", "Deshabilitado"}));
        cbEstadoEmpleados1.setEnabled(false);
        cbEstadoEmpleados1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoEmpleados1ActionPerformed(evt);
            }
        });
        tabEmpleados.add(cbEstadoEmpleados1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 201, -1));

        btnAddUser.setText("Agregar Usuario");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });
        tabEmpleados.add(btnAddUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 480, 150, 38));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel43.setText("Empleados");
        tabEmpleados.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 42, -1, -1));

        txtsearchempleado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsearchempleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchempleadoKeyReleased(evt);
            }
        });
        tabEmpleados.add(txtsearchempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 190, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Id Empleado");
        tabEmpleados.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 97, -1, -1));

        jButton3.setText("Buscar");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        tabEmpleados.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Filtrar por");
        tabEmpleados.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        cmbempleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "ID", "Estado" }));
        tabEmpleados.add(cmbempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 110, 30));

        btnEliminarNV2.setText("Refescar");
        btnEliminarNV2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarNV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNV2ActionPerformed(evt);
            }
        });
        tabEmpleados.add(btnEliminarNV2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 90, -1));

        panelInfo.addTab("Empleados", tabEmpleados);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Id Producto");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Precio");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Stock");

        txtIdProductosProductos.setEnabled(false);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Nombre del producto");

        txtNombreDelProducto.setEnabled(false);
        txtNombreDelProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreDelProductoActionPerformed(evt);
            }
        });

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Producto", "Nombre del producto", "Precio", "Stock"
            }
        ));
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseProvedorClick(evt);
            }
        });
        jScrollPane4.setViewportView(tbProductos);
        if (tbProductos.getColumnModel().getColumnCount() > 0) {
            tbProductos.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbProductos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbProductos.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        btnAgregarProductos.setText("Guardar");
        btnAgregarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarProductos.setEnabled(false);
        btnAgregarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductosActionPerformed(evt);
            }
        });

        btnEliminarProductos.setText("Eliminar");
        btnEliminarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductosActionPerformed(evt);
            }
        });

        txtprecioProd.setEnabled(false);
        txtprecioProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprecioProdActionPerformed(evt);
            }
        });

        txtStockProd.setEnabled(false);
        txtStockProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockProdActionPerformed(evt);
            }
        });

        btnProdModificar.setText("Modificar");
        btnProdModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProdModificar.setEnabled(false);
        btnProdModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdModificarActionPerformed(evt);
            }
        });

        btnInsert.setText("Ingresar");
        btnInsert.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnProdCancelar.setText("Cancelar");
        btnProdCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProdCancelar.setEnabled(false);
        btnProdCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabProductosLayout = new javax.swing.GroupLayout(tabProductos);
        tabProductos.setLayout(tabProductosLayout);
        tabProductosLayout.setHorizontalGroup(
            tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(tabProductosLayout.createSequentialGroup()
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProductosProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(txtNombreDelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(txtprecioProd, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStockProd, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(81, 81, 81)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabProductosLayout.createSequentialGroup()
                                .addComponent(btnAgregarProductos)
                                .addGap(18, 18, 18)
                                .addComponent(btnProdCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarProductos)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(tabProductosLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(btnInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(29, 29, 29)
                                .addComponent(btnProdModificar)
                                .addGap(218, 218, 218)))))
                .addContainerGap())
        );
        tabProductosLayout.setVerticalGroup(
            tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabProductosLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel27)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28)
                    .addComponent(btnProdModificar)
                    .addComponent(btnInsert))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreDelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtprecioProd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStockProd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregarProductos)
                        .addComponent(btnEliminarProductos)
                        .addComponent(btnProdCancelar))
                    .addComponent(txtIdProductosProductos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(299, Short.MAX_VALUE))
        );

        panelInfo.addTab("Productos", tabProductos);

        tbVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Vendedor", "Total"
            }
        ));
        tbVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbVentas);

        btnPdfVentas.setText("Boton generar pdf");
        btnPdfVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPdfVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabHistorialLayout = new javax.swing.GroupLayout(tabHistorial);
        tabHistorial.setLayout(tabHistorialLayout);
        tabHistorialLayout.setHorizontalGroup(
            tabHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabHistorialLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(tabHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabHistorialLayout.createSequentialGroup()
                        .addComponent(btnPdfVentas)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        tabHistorialLayout.setVerticalGroup(
            tabHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabHistorialLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(tabHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPdfVentas, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIdVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );

        panelInfo.addTab("Historial de Ventas", tabHistorial);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Id Producto");

        txtIdProductoNV1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtIdProductoNV1PropertyChange(evt);
            }
        });
        txtIdProductoNV1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdProductoNV1VentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdProductoNV1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdProductoNV1KeyTyped(evt);
            }
        });

        txtDescripcionProductoNV1.setEditable(false);
        txtDescripcionProductoNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionProductoNV1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Descripcion");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Cantidad");

        txtCantidadNV1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtCantidadNV1MouseReleased(evt);
            }
        });
        txtCantidadNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadNV1ActionPerformed(evt);
            }
        });
        txtCantidadNV1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadNV1KeyReleased(evt);
            }
        });

        txtPrecioNV1.setEditable(false);
        txtPrecioNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioNV1ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Precio");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Total");

        txtTotalVN1.setEditable(false);

        btnAgregarNV1.setText("Agregar");
        btnAgregarNV1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNV1ActionPerformed(evt);
            }
        });

        btnEliminarNV1.setText("Eliminar");
        btnEliminarNV1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNV1ActionPerformed(evt);
            }
        });

        tbNuevaVenta1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Producto", "Descripcion", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane7.setViewportView(tbNuevaVenta1);
        if (tbNuevaVenta1.getColumnModel().getColumnCount() > 0) {
            tbNuevaVenta1.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbNuevaVenta1.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbNuevaVenta1.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbNuevaVenta1.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        txtNombreClienteNV1.setEnabled(false);

        txtIdClienteNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClienteNV1ActionPerformed(evt);
            }
        });
        txtIdClienteNV1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdClienteNV1KeyReleased(evt);
            }
        });

        btnVentaNV1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/venta.png"))); // NOI18N
        btnVentaNV1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentaNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaNV1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlComprasLayout = new javax.swing.GroupLayout(pnlCompras);
        pnlCompras.setLayout(pnlComprasLayout);
        pnlComprasLayout.setHorizontalGroup(
            pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(pnlComprasLayout.createSequentialGroup()
                        .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlComprasLayout.createSequentialGroup()
                                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdProductoNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcionProductoNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addGap(18, 18, 18)
                                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCantidadNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38))
                                .addGap(18, 18, 18)
                                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39))
                                .addGap(18, 18, 18)
                                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40)
                                    .addGroup(pnlComprasLayout.createSequentialGroup()
                                        .addComponent(txtTotalVN1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(btnAgregarNV1)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminarNV1))))
                            .addGroup(pnlComprasLayout.createSequentialGroup()
                                .addComponent(txtIdClienteNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreClienteNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVentaNV1)))
                        .addGap(0, 163, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlComprasLayout.setVerticalGroup(
            pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlComprasLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalVN1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarNV1)
                            .addComponent(btnEliminarNV1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlComprasLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCantidadNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDescripcionProductoNV1)
                                .addComponent(txtIdProductoNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVentaNV1)
                    .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtIdClienteNV1)
                        .addComponent(txtNombreClienteNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );

        panelInfo.addTab("Compras", pnlCompras);

        tabNuevaVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Id Producto");
        tabNuevaVenta.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 93, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cantidad");
        tabNuevaVenta.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Precio");
        tabNuevaVenta.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Total");
        tabNuevaVenta.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, -1, -1));

        txtIdProductoNV.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtIdProductoNVPropertyChange(evt);
            }
        });
        txtIdProductoNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdProductoNVKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdProductoNVKeyTyped(evt);
            }
        });
        tabNuevaVenta.add(txtIdProductoNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 78, 28));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Descripcion");
        tabNuevaVenta.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        txtDescripcionProductoNV.setEditable(false);
        txtDescripcionProductoNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionProductoNVActionPerformed(evt);
            }
        });
        tabNuevaVenta.add(txtDescripcionProductoNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 170, 28));

        txtCantidadNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtCantidadNVMouseReleased(evt);
            }
        });
        txtCantidadNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadNVActionPerformed(evt);
            }
        });
        txtCantidadNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadNVKeyReleased(evt);
            }
        });
        tabNuevaVenta.add(txtCantidadNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 106, 28));

        txtPrecioNV.setEditable(false);
        txtPrecioNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioNVActionPerformed(evt);
            }
        });
        tabNuevaVenta.add(txtPrecioNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 121, 24));

        txtTotalVN.setEditable(false);
        tabNuevaVenta.add(txtTotalVN, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 105, 24));

        tbNuevaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Producto", "Descripcion", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane1.setViewportView(tbNuevaVenta);
        if (tbNuevaVenta.getColumnModel().getColumnCount() > 0) {
            tbNuevaVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbNuevaVenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbNuevaVenta.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbNuevaVenta.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        tabNuevaVenta.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1025, 256));

        btnVentaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/venta.png"))); // NOI18N
        btnVentaNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaNVActionPerformed(evt);
            }
        });
        tabNuevaVenta.add(btnVentaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Total a Pagar");
        tabNuevaVenta.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 400, -1, -1));

        lblTotal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTotal.setText("(ingrese total)");
        tabNuevaVenta.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 400, -1, -1));

        btnAgregarNV.setText("Agregar");
        btnAgregarNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNVActionPerformed(evt);
            }
        });
        tabNuevaVenta.add(btnAgregarNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, 40));

        btnEliminarNV.setText("Eliminar");
        btnEliminarNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNVActionPerformed(evt);
            }
        });
        tabNuevaVenta.add(btnEliminarNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, -1, 40));

        txtIdClienteNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClienteNVActionPerformed(evt);
            }
        });
        txtIdClienteNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdClienteNVKeyReleased(evt);
            }
        });
        tabNuevaVenta.add(txtIdClienteNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 82, 41));

        txtNombreClienteNV.setEnabled(false);
        tabNuevaVenta.add(txtNombreClienteNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 201, 41));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Id Cliente");
        tabNuevaVenta.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Nombre Cliente");
        tabNuevaVenta.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 93, -1));

        panelInfo.addTab("Nueva venta", tabNuevaVenta);

        btnNuevaVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/venta.png"))); // NOI18N
        btnNuevaVenta.setText("Nueva Venta");
        btnNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });

        btnClientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ususario.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClientesMouseClicked(evt);
            }
        });
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnProveedores.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ususario.png"))); // NOI18N
        btnProveedores.setText("Proveedores");
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });

        btnProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proructos.png"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnVentas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/historial ventas.png"))); // NOI18N
        btnVentas.setText("Historial de Ventas");
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        btnEmpleados.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btnEmpleados.setText("Empleados");
        btnEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevaVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProveedores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEmpleados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProductos)
                .addGap(14, 14, 14)
                .addComponent(btnVentas)
                .addGap(81, 81, 81))
            .addComponent(panelInfo, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevaVenta)
                    .addComponent(btnClientes)
                    .addComponent(btnProveedores)
                    .addComponent(btnProductos)
                    .addComponent(btnVentas)
                    .addComponent(btnEmpleados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, 1060, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentasActionPerformed

    public void LimpiarTextfield(){
        txtTelefonoClienteCLI.setText(null);
        txtIdClienteCLI.setText("Automatico");
        txtNombreClienteCLI.setText(null);
        txtCorreoClienteCLI.setText(null);
        txtDireccionClienteCLI.setText(null);
         
        
    }    
    private void btnClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMouseClicked
        panelInfo.setSelectedIndex(1);
        limpiarTabla();
        listaClientes();
    }//GEN-LAST:event_btnClientesMouseClicked

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        
    }//GEN-LAST:event_btnClientesActionPerformed

    public void ProvCancel(){
        txtIdProveedores.setEnabled(false);
        txtNombreProveedores.setEnabled(false);
        txtCorreoProveedores.setEnabled(false);
        txtTelefonoProveedores.setEnabled(false);
        txtubicacionProveedor.setEnabled(false);
        cbEstadoProveedores.setEnabled(false);
        btnModificarProveedores.setEnabled(true);
        btncanelProveedor.setVisible(false);
        btnGuardarProveedores.setEnabled(false);
        btnNuevoProveedores.setEnabled(true);
    }    public void LimpiarProveedor(){
        
        txtIdProveedores.setText("Automatico");
        txtNombreProveedores.setText(null);
        txtCorreoProveedores.setText(null);
        txtTelefonoProveedores.setText(null);
        txtubicacionProveedor.setText(null);
        cbEstadoProveedores.setEnabled(false);
        btnModificarProveedores.setEnabled(true);
        btncanelProveedor.setVisible(false);
        btnGuardarProveedores.setEnabled(false);
        btnNuevoProveedores.setEnabled(true);
        
    }        private void inActiveEmpleado(){
        txtIdEmpleados.setEnabled(false);
        txtNombreEmpleados.setEnabled(false);
        cbOcupacionEmpleado.setEnabled(false);
        txtCorreoEmpleados.setEnabled(false);
        txtDireccionEmpleados.setEnabled(false);
        txtTelefonoEmpleados.setEnabled(false);
        cbOcupacionEmpleado.setEnabled(false);
        cbEstadoEmpleados1.setEnabled(false);
    }
    private void ActiveEmpleado(){
    
        txtIdEmpleados.setEnabled(false);
        txtNombreEmpleados.setEnabled(true);
        cbOcupacionEmpleado.setEnabled(true);
        txtCorreoEmpleados.setEnabled(true);
        txtDireccionEmpleados.setEnabled(true);
        txtTelefonoEmpleados.setEnabled(true);
        cbOcupacionEmpleado.setEnabled(true);
        cbEstadoEmpleados1.setEnabled(true);
    }
    private void LimpiarEmpleado(){
        txtIdEmpleados.setText("Automatico");
        txtNombreEmpleados.setText(null);
        cbOcupacionEmpleado.setSelectedIndex(0);
        txtCorreoEmpleados.setText(null);
        txtDireccionEmpleados.setText(null);
        txtTelefonoEmpleados.setText(null);
        cbOcupacionEmpleado.setSelectedIndex(0);
    }    double total=0;
    public void limpiarVenta(){
        txtIdProductoNV.setText(null);
        txtDescripcionProductoNV.setText(null);
        txtCantidadNV.setText(null);
        txtPrecioNV.setText(null);
        txtTotalVN.setText(null);
    }
    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void loadEmpleados(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadEmpleados
        // TODO add your handling code here:

    }//GEN-LAST:event_loadEmpleados

    private void txtIdClienteNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdClienteNVKeyReleased
        // TODO add your handling code here:
        DatosCliente cliente = new DatosCliente();
        ConexionSQL cn = new ConexionSQL();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select * from `cliente` WHERE idCliente=?";
        //String sql = "INSERT INTO `cliente`(`idCliente`, `Nombre`, `Correo`, `Direccion`, `idTelefonoCli`, `Estado`) VALUES (?,?,?,?,?,?)";
        double cantidad;
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            if(txtIdClienteNV.getText()!=""  ){
                ps.setInt(1, Integer.parseInt( txtIdClienteNV.getText() )  );
                rs = ps.executeQuery();
                if(rs.next()){
                    txtNombreClienteNV.setText( rs.getString("Nombre"));
                    cliente.setDireccion(rs.getString("Direccion"));
                    cliente.setTelefono(rs.getString("idTelefonoCli"));

                }
                else{
                    txtNombreClienteNV.setText( null);

                }
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());

        }
    }//GEN-LAST:event_txtIdClienteNVKeyReleased

    private void txtIdClienteNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClienteNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClienteNVActionPerformed

    private void btnEliminarNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNVActionPerformed
        // TODO add your handling code here:
        if(tbNuevaVenta.getValueAt(tbNuevaVenta.getSelectedRow(),4).toString()!=""){
            total-=Double.parseDouble(tbNuevaVenta.getValueAt(tbNuevaVenta.getSelectedRow(),4).toString());
            lblTotal.setText( total+"" );
            DefaultTableModel dtm = (DefaultTableModel) tbNuevaVenta.getModel();
            dtm.removeRow(tbNuevaVenta.getSelectedRow());
            
            listaProd.remove(listaProd.size()-1);

        }
    }//GEN-LAST:event_btnEliminarNVActionPerformed

    private void btnAgregarNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNVActionPerformed
        // TODO add your handling code here:
        datosProductoCompra dataProductos= new datosProductoCompra();
        

        if(txtIdProductoNV.getText().length()>0 && txtCantidadNV.getText().length()>0)
        {

            try{
                int cant=Integer.parseInt(txtCantidadNV.getText());
                modelVenta = (DefaultTableModel) tbNuevaVenta.getModel();
                Object []object = new Object[5];

                object[0] = txtIdProductoNV.getText();
                object[1] = txtDescripcionProductoNV.getText();
                object[2] = cant;
                object[3] = txtPrecioNV.getText();
                object[4] = txtTotalVN.getText();

                
                dataProductos.setIdprod(txtIdProductoNV.getText());
                dataProductos.setDesc(txtDescripcionProductoNV.getText());
                dataProductos.setCant(cant+""); ;
                dataProductos.setPrecio(txtPrecioNV.getText());
                dataProductos.setTotal(txtTotalVN.getText());
                System.out.println(dataProductos.getDesc());
                
                //
                modelVenta.addRow(object);
                tbNuevaVenta.setModel(modelVenta);
                //
                listaProd.add(dataProductos);
                
                total+= Double.parseDouble( txtTotalVN.getText() )  ;
                lblTotal.setText( total+"L." );
                limpiarVenta();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null,"La cantidad debe ser un entero");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Hay vampos vacios","Error",JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnAgregarNVActionPerformed

    private void btnVentaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaNVActionPerformed

        if(txtIdClienteNV.getText().length()>0 && txtNombreClienteNV.getText().length()>2){

            try{
                valoresEstaticos vals = new valoresEstaticos();
                Ventas vnt = new Ventas();
                
                DatosCliente cliente= new DatosCliente();
                
                cliente.setCliente(txtNombreClienteNV.getText());
                cliente.setEmpleado(vals.getUsuario());
                String str = lblTotal.getText();
		str = str.substring(0, str.length()-1);
                str = str.substring(0, str.length()-1);
                cliente.setTotal(Double.parseDouble( str ));
                
                
                vnt.setCliente( txtNombreClienteNV.getText() );
                vnt.setEmpleado( vals.getUsuario()  );
                //vnt.setTotal( Double.parseDouble( lblTotal.getText() ) );

                VentasDB data = new VentasDB();
                //data.RegistrarVenta( vnt );
               // System.out.println(vnt.getCliente());
                new DetalleFactura().setVisible(true);

                limpiarTablaProducto();
                limpiarTablaHistorialV();
                listaHistorialVenta();

            }
            catch(Exception e){
                System.out.println(e);
            }

        }
        else{
            JOptionPane.showMessageDialog(null,"El campo cliente esta vacio","Error",JOptionPane.WARNING_MESSAGE);

        }

    }//GEN-LAST:event_btnVentaNVActionPerformed

    private void txtPrecioNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioNVActionPerformed

    private void txtCantidadNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadNVKeyReleased
        // TODO add your handling code here:
        double cant=Double.parseDouble( txtCantidadNV.getText());
        double precio=Double.parseDouble( txtPrecioNV.getText());
        double total= cant*precio;
        txtTotalVN.setText( total+"");
    }//GEN-LAST:event_txtCantidadNVKeyReleased

    private void txtCantidadNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadNVActionPerformed

    private void txtCantidadNVMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantidadNVMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCantidadNVMouseReleased

    private void txtDescripcionProductoNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionProductoNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionProductoNVActionPerformed

    private void txtIdProductoNVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProductoNVKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIdProductoNVKeyTyped

    private void txtIdProductoNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProductoNVKeyReleased
        // TODO add your handling code here:
        ConexionSQL cn = new ConexionSQL();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select * from `producto` WHERE idProducto=?";
        //String sql = "INSERT INTO `cliente`(`idCliente`, `Nombre`, `Correo`, `Direccion`, `idTelefonoCli`, `Estado`) VALUES (?,?,?,?,?,?)";
        double cantidad;
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            if(txtIdProductoNV.getText()!=""  ){
                ps.setInt(1, Integer.parseInt( txtIdProductoNV.getText() )  );
                rs = ps.executeQuery();
                if(rs.next()){
                    txtDescripcionProductoNV.setText( rs.getString("Nombre"));
                    txtPrecioNV.setText( rs.getString("precio"));
                }
                else{
                    txtDescripcionProductoNV.setText( null);
                    txtPrecioNV.setText(null);
                }
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());

        }
    }//GEN-LAST:event_txtIdProductoNVKeyReleased

    private void VentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VentaKeyPressed

    }//GEN-LAST:event_VentaKeyPressed

    private void txtIdProductoNVPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtIdProductoNVPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoNVPropertyChange

    private void btnPdfVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfVentasActionPerformed
        try {
            ConexionSQL con = new ConexionSQL();
            Connection conn = con.getConnection();

            JasperReport reporte = null;
            String path = "src\\Layouts\\VentasReport.jasper";

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte,null, conn);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);

        } catch (JRException ex) {
            //Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPdfVentasActionPerformed

    private void tbVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVentasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbVentasMouseClicked

    private void btnProdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdCancelarActionPerformed
        // TODO add your handling code here:
        inActivProducto();
        btnInsert.setEnabled(true);
        btnProdModificar.setEnabled(false);
        btnEliminarProductos.setEnabled(true);
        btnAgregarProductos.setEnabled(false);
        btnProdCancelar.setEnabled(false);
    }//GEN-LAST:event_btnProdCancelarActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        estado="insert";
        LimpiarProducto();
        ActiveProducto();
        btnInsert.setEnabled(false);
        btnProdModificar.setEnabled(false);
        btnEliminarProductos.setEnabled(false);
        btnAgregarProductos.setEnabled(true);
        btnProdCancelar.setEnabled(true);
        txtIdProductosProductos.setText("#Auto");

    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnProdModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdModificarActionPerformed
        // TODO add your handling code here:
        estado="modify";
        ActiveProducto();
        btnInsert.setEnabled(false);
        btnProdModificar.setEnabled(true);
        btnEliminarProductos.setEnabled(false);
        btnAgregarProductos.setEnabled(true);
        btnProdCancelar.setEnabled(true);
        txtIdProductosProductos.setEnabled(false);
    }//GEN-LAST:event_btnProdModificarActionPerformed

    private void txtStockProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockProdActionPerformed

    private void txtprecioProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprecioProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioProdActionPerformed

    private void btnEliminarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductosActionPerformed
        // TODO add your handling code here:
        producto pro= new producto();
        pro.setId( Integer.parseInt( txtIdProductosProductos.getText()  ));
        ProductoDB prod = new ProductoDB();
        prod.DeleteProducto( pro );
        limpiarTabla();
        listaProductos();
        inActivProducto();
        LimpiarProducto();
    }//GEN-LAST:event_btnEliminarProductosActionPerformed

    private void btnAgregarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductosActionPerformed
        // TODO add your handling code here:

        if(txtIdProductosProductos.getText().length()>0 && txtNombreDelProducto.getText().length()>2
            && txtprecioProd.getText().length()>0 && txtStockProd.getText().length()>0){

            double precio;
            int stock;
            try{

                precio=Double.parseDouble(txtprecioProd.getText());
                stock=Integer.parseInt(txtStockProd.getText());
                producto pro= new producto();

                pro.setNombre( txtNombreDelProducto.getText() );
                pro.setPreciol( precio );
                pro.setStock( stock );
                ProductoDB prod = new ProductoDB();
                if(estado=="insert"){
                    pro.setId( Integer.parseInt( "1" ));
                    prod.registrarProducto( pro );
                }
                else if( estado=="modify" ){
                    pro.setId( Integer.parseInt( txtIdProductosProductos.getText() ));
                    prod.UpdateProducto( pro );
                }
                else{

                }
                btnInsert.setEnabled(true);
                btnEliminarProductos.setEnabled(true);
                btnAgregarProductos.setEnabled(false);
                btnProdCancelar.setEnabled(false);

                limpiarTablaProducto();
                listaProductos();
                inActivProducto();
                LimpiarProducto();
                btnProdModificar.setEnabled(false);

            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "hay formatos incorrectos");
            }

        }
        else{
            JOptionPane.showMessageDialog(null, "Hay campos vacios");
        }
    }//GEN-LAST:event_btnAgregarProductosActionPerformed

    private void MouseProvedorClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MouseProvedorClick
        // TODO add your handling code here:
        btnProdModificar.setEnabled(true);
        txtIdProductosProductos.setText( tbProductos.getValueAt(tbProductos.getSelectedRow(),0).toString());
        txtNombreDelProducto.setText( tbProductos.getValueAt(tbProductos.getSelectedRow(),1).toString() );
        txtprecioProd.setText( tbProductos.getValueAt(tbProductos.getSelectedRow(),2).toString() );
        txtStockProd.setText( tbProductos.getValueAt(tbProductos.getSelectedRow(),3).toString() );

    }//GEN-LAST:event_MouseProvedorClick

    private void txtNombreDelProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreDelProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreDelProductoActionPerformed

    private void btnEliminarNV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNV2ActionPerformed
        // TODO add your handling code here:
        listaEmpleados();
    }//GEN-LAST:event_btnEliminarNV2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String TipoFiltro=cmbempleado.getSelectedItem().toString();
        System.out.println(TipoFiltro);
        limpiarTablaEmpleado();
        Empleado cl = new Empleado();
        List<Empleado> listaCl=null;
        String state=null;
        switch(TipoFiltro){
            case "Nombre":

            listaCl = empleadodb.listarClientesBusqueda("Nombre",txtsearchempleado.getText());
            state="true";
            break;

            case "ID":
            int id;
            try{

                id=Integer.parseInt(txtsearchempleado.getText());
                listaCl = empleadodb.listarClientesBusqueda("idEmpleado",Integer.toString(id));
                state="true";
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"ID es un valor numerico","Error",JOptionPane.WARNING_MESSAGE);
                listaClientes();
            }

            break;

            case "Estado":

            listaCl = cliente.listarClientesBusqueda("Estado",txtsearchempleado.getText());
            break;

        }
        if(state=="true"){
            modelEmpleado = (DefaultTableModel) tbEmpleados.getModel();
            Object[] obj = new Object[7];
            for (int i = 0; i < listaCl.size(); i++) {
                obj[0] = listaCl.get(i).getIdEmpleado();
                obj[1] = listaCl.get(i).getNombre();
                obj[2] = listaCl.get(i).getOcupacion();
                obj[3] = listaCl.get(i).getCorreo();
                obj[4] = listaCl.get(i).getDireccion();
                obj[5] = listaCl.get(i).getTelefono();
                obj[6] = listaCl.get(i).getEstado();

                modelEmpleado.addRow(obj);

            }
            tbEmpleados.setModel(modelEmpleado);

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtsearchempleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchempleadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchempleadoKeyReleased

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        new RegistroLayout().setVisible(true);
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void cbEstadoEmpleados1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoEmpleados1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoEmpleados1ActionPerformed

    private void btnCancelEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelEmpActionPerformed
        // TODO add your handling code here:
        inActiveEmpleado();
        LimpiarEmpleado();
        tbEmpleados.setEnabled(true);
        btnNuevoEmpleado.setEnabled(true);
        btnModificarEmpleado.setEnabled(true);
        btnGuardarEmpleados.setEnabled(false);
        btnCancelEmp.setVisible(false);
    }//GEN-LAST:event_btnCancelEmpActionPerformed

    private void btnGuardarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEmpleadosActionPerformed

        String correo = txtCorreoEmpleados.getText();

        if(correo.contains("@")){
            if (correo.contains("gmail.com") || correo.contains("yahoo.com") || correo.contains("hotmail.com") ||
                correo.contains("gmail.es") || correo.contains("yahoo.es") || correo.contains("hotmail.es")){

                String numero=txtTelefonoEmpleados.getText();

                if(numero.length()!=8){
                    JOptionPane.showMessageDialog(null, "El número de teléfono debe ser de 8 dígitos");
                } else {
                    if(numero.startsWith("2") || numero.startsWith("3") || numero.startsWith("7") ||
                        numero.startsWith("8") || numero.startsWith("9")){

                        Empleado dart = new Empleado();
                        dart.setIdEmpleado(Integer.parseInt( txtIdEmpleados.getText()));
                        dart.setOcupacion( cbOcupacionEmpleado.getSelectedItem().toString() );
                        dart.setNombre(txtNombreEmpleados.getText());
                        dart.setCorreo(txtCorreoEmpleados.getText());
                        dart.setDireccion(txtDireccionEmpleados.getText());
                        dart.setTelefono(Integer.parseInt(txtTelefonoEmpleados.getText()));

                        if(cbEstadoEmpleados1.getSelectedIndex()==0){
                            dart.setEstado(1);
                        }
                        else{
                            dart.setEstado(2);
                        }
                        EmpleadosDB ProvDB= new EmpleadosDB();
                        if(estado=="insert"){
                            ProvDB.registrarEmpleado(dart);

                        }
                        else if( estado=="update" ){
                            ProvDB.UpdateEmpleado(dart);
                        }
                        limpiarTablaEmpleado();
                        listaEmpleados();
                        LimpiarEmpleado();
                        inActiveEmpleado();

                        //
                        tbEmpleados.setEnabled(true);
                        btnNuevoEmpleado.setEnabled(true);
                        btnModificarEmpleado.setEnabled(true);
                        btnGuardarEmpleados.setEnabled(false);
                        btnCancelEmp.setVisible(false);
                    }

                    else{
                        JOptionPane.showMessageDialog(null, "El número de teléfono debe comenzar con 2, 3, 7, 8 ó 9");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Debe insertar una extensión de correo válida \n "
                    + "Las extensiones válidas son: \n "
                    + "Hotmail \n Gmail \n Yahoo");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Dirección de correo inválida");
        }

    }//GEN-LAST:event_btnGuardarEmpleadosActionPerformed

    private void btnGuardarEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarEmpleadosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarEmpleadosMouseClicked

    private void btnModificarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEmpleadoActionPerformed
        // TODO add your handling code here:
        estado="update";
        ActiveEmpleado();
        tbEmpleados.setEnabled(false);
        btnNuevoEmpleado.setEnabled(false);
        btnModificarEmpleado.setEnabled(false);
        btnGuardarEmpleados.setEnabled(true);
        btnCancelEmp.setVisible(true);
    }//GEN-LAST:event_btnModificarEmpleadoActionPerformed

    private void btnModificarEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarEmpleadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarEmpleadoMouseClicked

    private void btnNuevoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoEmpleadoActionPerformed
        // TODO add your handling code here:
        estado="insert";
        LimpiarEmpleado();
        ActiveEmpleado();

        tbEmpleados.setEnabled(false);
        btnNuevoEmpleado.setEnabled(false);
        btnModificarEmpleado.setEnabled(false);
        btnGuardarEmpleados.setEnabled(true);
        btnCancelEmp.setVisible(true);
    }//GEN-LAST:event_btnNuevoEmpleadoActionPerformed

    private void btnNuevoEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoEmpleadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoEmpleadoMouseClicked

    private void MouseClickedTbEmpleado(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MouseClickedTbEmpleado
        // TODO add your handling code here:

        txtIdEmpleados.setText( tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(),0).toString());
        txtNombreEmpleados.setText( tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(),1).toString() );
        String ocupacion=tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(),2).toString();
        txtCorreoEmpleados.setText( tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(),3).toString() );
        txtDireccionEmpleados.setText( tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(),4).toString() );
        txtTelefonoEmpleados.setText( tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(),5).toString() );
        String state=tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(),6).toString();
        if(  state=="1" ){
            cbEstadoEmpleados1.setSelectedIndex(3);
        }

        if(  ocupacion=="Vendedor" ){
            cbEstadoEmpleados1.setSelectedIndex(2);
        }
    }//GEN-LAST:event_MouseClickedTbEmpleado

    private void cbOcupacionEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOcupacionEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOcupacionEmpleadoActionPerformed

    private void txtDireccionEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionEmpleadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionEmpleadosActionPerformed

    private void txtCorreoEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoEmpleadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoEmpleadosActionPerformed

    private void txtIdEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEmpleadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEmpleadosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtsearchproovedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchproovedorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchproovedorKeyReleased

    private void btncanelProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncanelProveedorActionPerformed
        // TODO add your handling code here:
        ProvCancel();
        LimpiarProveedor();
    }//GEN-LAST:event_btncanelProveedorActionPerformed

    private void btnGuardarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedoresActionPerformed
        String correo = txtCorreoProveedores.getText();

        if(correo.contains("@")){
            if (correo.contains("gmail.com") || correo.contains("yahoo.com") || correo.contains("hotmail.com") ||
                correo.contains("gmail.es") || correo.contains("yahoo.es") || correo.contains("hotmail.es")){
                try{

                    String numero=txtTelefonoProveedores.getText();

                    if(numero.length()!=8){
                        JOptionPane.showMessageDialog(null, "El número de teléfono debe ser de 8 dígitos","Error",JOptionPane.WARNING_MESSAGE);
                    } else {
                        if(numero.startsWith("2") || numero.startsWith("3") || numero.startsWith("7") ||
                            numero.startsWith("8") || numero.startsWith("9")){

                            Cliente dart = new Cliente();
                            dart.setIdCliente( Integer.parseInt( txtIdProveedores.getText()));
                            dart.setNombre(txtNombreProveedores.getText());
                            dart.setCorreo(txtCorreoProveedores.getText());
                            dart.setDireccion(txtubicacionProveedor.getText());
                            dart.setIdTelefonoCli(Integer.parseInt(txtTelefonoProveedores.getText()));

                            if(cbEstadoProveedores.getSelectedIndex()==0){
                                dart.setEstado(1);
                            }
                            else{
                                dart.setEstado(2);
                            }
                            ProveedorDB ProvDB= new ProveedorDB();
                            if(estado=="insert"){
                                ProvDB.registrarProveedor(dart);
                            }
                            else if(estado=="update") {
                                ProvDB.UpdateProveedor(dart);
                            }
                            ProvDB.listarClientes();
                            limpiarTablaProveedor();
                            listaProveedores();
                            tbProveedores.setEnabled(true);
                            LimpiarProveedor();
                            ProvCancel();
                            LimpiarProveedor();
                        }

                        else{
                            JOptionPane.showMessageDialog(null, "El número de teléfono debe comenzar con 2, 3, 7, 8 ó 9","Error",JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }
                catch(Exception ex){

                    JOptionPane.showMessageDialog(null, "Caracteres invalidos","Error",JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Debe insertar una extensión de correo válida \n "
                    + "Las extensiones válidas son: \n "
                    + "Hotmail \n Gmail \n Yahoo","Error",JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Dirección de correo inválida","Error",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarProveedoresActionPerformed

    private void btnGuardarProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarProveedoresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarProveedoresMouseClicked

    private void btnModificarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProveedoresActionPerformed
        // TODO add your handling code here:
        estado="update";
        txtIdProveedores.setEnabled(false);
        txtNombreProveedores.setEnabled(true);
        txtCorreoProveedores.setEnabled(true);
        txtTelefonoProveedores.setEnabled(true);
        txtubicacionProveedor.setEnabled(true);
        cbEstadoProveedores.setEnabled(true);
        btnModificarProveedores.setEnabled(false);
        btncanelProveedor.setVisible(true);
        btnGuardarProveedores.setEnabled(true);
        btnNuevoProveedores.setEnabled(false);
    }//GEN-LAST:event_btnModificarProveedoresActionPerformed

    private void btnModificarProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarProveedoresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarProveedoresMouseClicked

    private void btnNuevoProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedoresActionPerformed
        // TODO add your handling code here:
        // tbClientesCLI.setEnabled(false);
        LimpiarProveedor();
        estado="insert";
        txtIdProveedores.setEnabled(false);
        txtIdProveedores.setText("Automatico");
        txtNombreProveedores.setEnabled(true);
        txtCorreoProveedores.setEnabled(true);
        txtTelefonoProveedores.setEnabled(true);
        txtubicacionProveedor.setEnabled(true);
        cbEstadoProveedores.setEnabled(true);
        btnModificarProveedores.setEnabled(false);
        btncanelProveedor.setVisible(true);
        btnGuardarProveedores.setEnabled(true);
        btnNuevoProveedores.setEnabled(false);
    }//GEN-LAST:event_btnNuevoProveedoresActionPerformed

    private void btnNuevoProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoProveedoresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoProveedoresMouseClicked

    private void MouseClickedtbProveedor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MouseClickedtbProveedor
        // TODO add your handling code here:
        txtIdProveedores.setText( tbProveedores.getValueAt(tbProveedores.getSelectedRow(),0).toString());
        txtNombreProveedores.setText( tbProveedores.getValueAt(tbProveedores.getSelectedRow(),1).toString() );
        txtCorreoProveedores.setText( tbProveedores.getValueAt(tbProveedores.getSelectedRow(),2).toString() );
        txtTelefonoProveedores.setText( tbProveedores.getValueAt(tbProveedores.getSelectedRow(),3).toString() );
        txtubicacionProveedor.setText( tbProveedores.getValueAt(tbProveedores.getSelectedRow(),4).toString() );
        String state=tbProveedores.getValueAt(tbProveedores.getSelectedRow(),5).toString();
        if(  state=="1" ){
            cbEstadoProveedores.setSelectedIndex(3);
        }
    }//GEN-LAST:event_MouseClickedtbProveedor

    private void cbEstadoProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoProveedoresActionPerformed

    private void txtTelefonoProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoProveedoresActionPerformed

    private void txtCorreoProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoProveedoresActionPerformed

    private void txtIdProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProveedoresActionPerformed

    private void ClienteBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteBuscar1ActionPerformed
        // TODO add your handling code here:
        listaClientes();
    }//GEN-LAST:event_ClienteBuscar1ActionPerformed

    private void ClienteBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteBuscarActionPerformed
        // TODO add your handling code here:
        String TipoFiltro=cmbTipoFiltroCliente.getSelectedItem().toString();
        System.out.println(TipoFiltro);
        limpiarTabla();
        ClienteDB cl = new ClienteDB();
        List<Cliente> listaCl=null;
        String state=null;
        switch(TipoFiltro){
            case "Nombre":

            listaCl = cliente.listarClientesBusqueda("Nombre",txtsearch.getText());
            state="true";
            break;

            case "ID":
            int id;
            try{

                id=Integer.parseInt(txtsearch.getText());
                listaCl = cliente.listarClientesBusqueda("idCliente",Integer.toString(id));
                state="true";
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"ID es un valor numerico","Error",JOptionPane.WARNING_MESSAGE);
                listaClientes();
            }

            break;

            case "Estado":

            listaCl = cliente.listarClientesBusqueda("Estado",txtsearch.getText());
            break;

        }
        if(state=="true"){
            modeloTB = (DefaultTableModel) tbClientesCLI.getModel();
            Object[] obj = new Object[6];
            for (int i = 0; i < listaCl.size(); i++) {
                obj[0] = listaCl.get(i).getIdCliente();
                obj[1] = listaCl.get(i).getNombre();
                obj[2] = listaCl.get(i).getCorreo();
                obj[3] = listaCl.get(i).getDireccion();
                obj[4] = listaCl.get(i).getIdTelefonoCli();
                obj[5] = listaCl.get(i).getEstado();

                modeloTB.addRow(obj);

            }
            tbClientesCLI.setModel(modeloTB);

        }

    }//GEN-LAST:event_ClienteBuscarActionPerformed

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtsearchKeyReleased

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        txtIdClienteCLI.setText("Automatico");
        CancelProcess();
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnGuardarCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCLIActionPerformed

        String correo = txtCorreoClienteCLI.getText();

        if(correo.contains("@")){
            if (correo.contains("gmail.com") || correo.contains("yahoo.com") || correo.contains("hotmail.com") ||
                correo.contains("gmail.es") || correo.contains("yahoo.es") || correo.contains("hotmail.es")){

                try{
                    String numero=txtTelefonoClienteCLI.getText();

                    if(numero.length()!=8){
                        JOptionPane.showMessageDialog(null, "El número de teléfono debe ser de 8 dígitos","Error",JOptionPane.WARNING_MESSAGE);
                    } else {
                        if(numero.startsWith("2") || numero.startsWith("3") || numero.startsWith("7") ||
                            numero.startsWith("8") || numero.startsWith("9")){

                            Cliente dart = new Cliente();
                            dart.setIdCliente( Integer.parseInt( "1"));
                            dart.setNombre(txtNombreClienteCLI.getText());
                            dart.setCorreo(txtCorreoClienteCLI.getText());
                            dart.setDireccion(txtDireccionClienteCLI.getText());
                            dart.setIdTelefonoCli(Integer.parseInt(txtTelefonoClienteCLI.getText()));

                            if(cbEstadoCLienteCLI.getSelectedIndex()==0){
                                dart.setEstado(1);
                            }
                            else{
                                dart.setEstado(2);
                            }
                            ClienteDB dbClient= new ClienteDB();
                            if(estado=="insert"){
                                dbClient.registrarClientes(dart);
                            }
                            else{
                                dbClient.UpdateClientes(dart);
                            }

                            tbClientesCLI.setEnabled(true);
                            dbClient.listarClientes();
                            LimpiarTextfield();
                            limpiarTabla();
                            listaClientes();
                            btnGuardarCLI.setEnabled(false);
                            btnNuevoClienteCLI.setEnabled(true);
                            btnModificarCLI.setEnabled(true);

                            txtIdClienteCLI.setEnabled(false);
                            txtNombreClienteCLI.setEnabled(false);
                            txtCorreoClienteCLI.setEnabled(false);
                            txtCorreoClienteCLI.setEnabled(false);
                            txtDireccionClienteCLI.setEnabled(false);
                            txtTelefonoClienteCLI.setEnabled(false);
                            cbEstadoCLienteCLI.setEnabled(false);
                            btncancel.setVisible(false);
                        }

                        else{
                            JOptionPane.showMessageDialog(null, "El número de teléfono debe comenzar con 2, 3, 7, 8 ó 9","Error",JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }
                catch(Exception ex){

                    JOptionPane.showMessageDialog(null, "Caracteres invalidos","Error",JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Debe insertar una extensión de correo válida \n "
                    + "Las extensiones válidas son: \n "
                    + "Hotmail \n Gmail \n Yahoo","Error",JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Dirección de correo inválida","Error",JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarCLIActionPerformed

    private void btnGuardarCLIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarCLIMouseClicked
        if (!"".equals(txtIdClienteCLI.getText()) && !"".equals(txtNombreClienteCLI.getText()) && !"".equals(txtCorreoClienteCLI.getText()) && !"".equals(txtDireccionClienteCLI.getText()) && !"".equals(txtTelefonoClienteCLI.getText()) && !"".equals(cbEstadoCLienteCLI.getSelectedItem())){

            cl.setIdCliente(Integer.parseInt(txtIdClienteCLI.getText()));
            cl.setNombre(txtNombreClienteCLI.getText());
            cl.setCorreo(txtCorreoClienteCLI.getText());
            cl.setDireccion(txtDireccionClienteCLI.getText());
            cl.setIdTelefonoCli(Integer.parseInt(txtTelefonoClienteCLI.getText()));
            // cl.setEstado((String) cbEstadoCLienteCLI.getSelectedItem());
            cliente.registrarClientes(cl);
            JOptionPane.showMessageDialog(null, "Cliente Registrado");

            btnGuardarCLI.setEnabled(false);
            btnNuevoClienteCLI.setEnabled(true);
            btnModificarCLI.setEnabled(true);
            txtCorreoClienteCLI.setEnabled(false);
            txtDireccionClienteCLI.setEnabled(false);
            txtTelefonoClienteCLI.setEnabled(false);
            cbEstadoCLienteCLI.setEnabled(false);
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }

        limpiarTabla();
        listaClientes();
    }//GEN-LAST:event_btnGuardarCLIMouseClicked

    private void btnModificarCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCLIActionPerformed
        // TODO add your handling code here:

        estado="update";
        btncancel.setVisible(true);

    }//GEN-LAST:event_btnModificarCLIActionPerformed

    private void btnModificarCLIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarCLIMouseClicked
        btnGuardarCLI.setEnabled(true);
        btnNuevoClienteCLI.setEnabled(false);
        btnModificarCLI.setEnabled(false);
        txtCorreoClienteCLI.setEnabled(true);
        txtDireccionClienteCLI.setEnabled(true);
        txtTelefonoClienteCLI.setEnabled(true);
        cbEstadoCLienteCLI.setEnabled(true);
        txtNombreClienteCLI.setEnabled(true);
    }//GEN-LAST:event_btnModificarCLIMouseClicked

    private void btnNuevoClienteCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteCLIActionPerformed
        tbClientesCLI.setEnabled(false);
        txtNombreClienteCLI.setEnabled(true);
        txtIdClienteCLI.setText("Automatico");
        estado="insert";
        btncancel.setVisible(true);
    }//GEN-LAST:event_btnNuevoClienteCLIActionPerformed

    private void btnNuevoClienteCLIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoClienteCLIMouseClicked
        btnGuardarCLI.setEnabled(true);
        btnNuevoClienteCLI.setEnabled(false);
        btnModificarCLI.setEnabled(false);

        txtIdClienteCLI.setText("Automatico");
        txtNombreClienteCLI.setText("");
        txtCorreoClienteCLI.setText("");
        txtCorreoClienteCLI.setEnabled(true);

        txtDireccionClienteCLI.setText("");
        txtDireccionClienteCLI.setEnabled(true);

        txtTelefonoClienteCLI.setText("");
        txtTelefonoClienteCLI.setEnabled(true);

        cbEstadoCLienteCLI.setSelectedItem("");
        cbEstadoCLienteCLI.setEnabled(true);
    }//GEN-LAST:event_btnNuevoClienteCLIMouseClicked

    private void mouseClickTable(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClickTable
        // TODO add your handling code here:
        txtIdClienteCLI.setText( tbClientesCLI.getValueAt(tbClientesCLI.getSelectedRow(),0).toString());
        txtNombreClienteCLI.setText( tbClientesCLI.getValueAt(tbClientesCLI.getSelectedRow(),1).toString() );
        txtCorreoClienteCLI.setText( tbClientesCLI.getValueAt(tbClientesCLI.getSelectedRow(),2).toString() );
        txtDireccionClienteCLI.setText( tbClientesCLI.getValueAt(tbClientesCLI.getSelectedRow(),3).toString() );
        txtTelefonoClienteCLI.setText( tbClientesCLI.getValueAt(tbClientesCLI.getSelectedRow(),4).toString() );
        String state=tbClientesCLI.getValueAt(tbClientesCLI.getSelectedRow(),5).toString();
        if(  state=="1" ){
            cbEstadoCLienteCLI.setSelectedIndex(3);
        }

    }//GEN-LAST:event_mouseClickTable

    private void cbEstadoCLienteCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoCLienteCLIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoCLienteCLIActionPerformed

    private void txtTelefonoClienteCLIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteCLIKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTelefonoClienteCLIKeyTyped

    private void txtTelefonoClienteCLIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteCLIKeyReleased

    }//GEN-LAST:event_txtTelefonoClienteCLIKeyReleased

    private void txtDireccionClienteCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionClienteCLIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionClienteCLIActionPerformed

    private void txtCorreoClienteCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoClienteCLIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoClienteCLIActionPerformed

    private void txtIdClienteCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClienteCLIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClienteCLIActionPerformed

    private void btnVentaNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaNV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentaNV1ActionPerformed

    private void txtIdClienteNV1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdClienteNV1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClienteNV1KeyReleased

    private void txtIdClienteNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClienteNV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClienteNV1ActionPerformed

    private void btnEliminarNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarNV1ActionPerformed

    private void btnAgregarNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarNV1ActionPerformed

    private void txtPrecioNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioNV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioNV1ActionPerformed

    private void txtCantidadNV1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadNV1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadNV1KeyReleased

    private void txtCantidadNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadNV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadNV1ActionPerformed

    private void txtCantidadNV1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantidadNV1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadNV1MouseReleased

    private void txtDescripcionProductoNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionProductoNV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionProductoNV1ActionPerformed

    private void txtIdProductoNV1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProductoNV1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoNV1KeyTyped

    private void txtIdProductoNV1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProductoNV1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoNV1KeyReleased

    private void txtIdProductoNV1VentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProductoNV1VentaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoNV1VentaKeyPressed

    private void txtIdProductoNV1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtIdProductoNV1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoNV1PropertyChange
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}    public void ActiveProducto(){
    
        //txtIdProductosProductos.setEnabled(true);
        txtNombreDelProducto.setEnabled(true);
        txtprecioProd.setEnabled(true);
        txtStockProd.setEnabled(true);
        
    }
      public void inActivProducto(){
        txtIdProductosProductos.setEnabled(false);
        txtNombreDelProducto.setEnabled(false);
        txtprecioProd.setEnabled(false);
        txtStockProd.setEnabled(false);
    }
  
    public void LimpiarProducto(){
        txtIdProductosProductos.setText(null);
        txtNombreDelProducto.setText(null);
        txtprecioProd.setText(null);
        txtStockProd.setText(null);
        //
        
        
    }
    public void CancelProcess(){
            tbClientesCLI.setEnabled(true);
            txtIdClienteCLI.setEnabled(false);
            txtNombreClienteCLI.setEnabled(false);
            btnGuardarCLI.setEnabled(false);
            btnNuevoClienteCLI.setEnabled(true);
            btnModificarCLI.setEnabled(true);
            txtCorreoClienteCLI.setEnabled(false);
            txtDireccionClienteCLI.setEnabled(false);
            txtTelefonoClienteCLI.setEnabled(false);
            cbEstadoCLienteCLI.setEnabled(false);
            LimpiarTextfield();
            btncancel.setVisible(false);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClienteBuscar;
    private javax.swing.JButton ClienteBuscar1;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnAgregarNV;
    private javax.swing.JButton btnAgregarNV1;
    private javax.swing.JButton btnAgregarProductos;
    private javax.swing.JButton btnCancelEmp;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnEliminarNV;
    private javax.swing.JButton btnEliminarNV1;
    private javax.swing.JButton btnEliminarNV2;
    private javax.swing.JButton btnEliminarProductos;
    private javax.swing.JButton btnEmpleados;
    private javax.swing.JButton btnGuardarCLI;
    private javax.swing.JButton btnGuardarEmpleados;
    private javax.swing.JButton btnGuardarProveedores;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnModificarCLI;
    private javax.swing.JButton btnModificarEmpleado;
    private javax.swing.JButton btnModificarProveedores;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnNuevoClienteCLI;
    private javax.swing.JButton btnNuevoEmpleado;
    private javax.swing.JButton btnNuevoProveedores;
    private javax.swing.JButton btnPdfVentas;
    private javax.swing.JButton btnProdCancelar;
    private javax.swing.JButton btnProdModificar;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnVentaNV;
    private javax.swing.JButton btnVentaNV1;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btncanelProveedor;
    private javax.swing.JComboBox<String> cbEstadoCLienteCLI;
    private javax.swing.JComboBox<String> cbEstadoEmpleados1;
    private javax.swing.JComboBox<String> cbEstadoProveedores;
    private javax.swing.JComboBox<String> cbOcupacionEmpleado;
    private javax.swing.JComboBox<String> cmbTipoFiltroCliente;
    private javax.swing.JComboBox<String> cmbempleado;
    private javax.swing.JComboBox<String> cmbproveedor;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTabbedPane panelInfo;
    private javax.swing.JPanel pnlCompras;
    private javax.swing.JPanel tabClientes;
    private javax.swing.JPanel tabEmpleados;
    private javax.swing.JPanel tabHistorial;
    private javax.swing.JPanel tabNuevaVenta;
    private javax.swing.JPanel tabProductos;
    private javax.swing.JPanel tabProveedores;
    private javax.swing.JTable tbClientesCLI;
    private javax.swing.JTable tbEmpleados;
    private javax.swing.JTable tbNuevaVenta;
    private javax.swing.JTable tbNuevaVenta1;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTable tbProveedores;
    private javax.swing.JTable tbVentas;
    private javax.swing.JTextField txtCantidadNV;
    private javax.swing.JTextField txtCantidadNV1;
    private javax.swing.JTextField txtCorreoClienteCLI;
    private javax.swing.JTextField txtCorreoEmpleados;
    private javax.swing.JTextField txtCorreoProveedores;
    private javax.swing.JTextField txtDescripcionProductoNV;
    private javax.swing.JTextField txtDescripcionProductoNV1;
    private javax.swing.JTextField txtDireccionClienteCLI;
    private javax.swing.JTextField txtDireccionEmpleados;
    private javax.swing.JTextField txtIdClienteCLI;
    private javax.swing.JTextField txtIdClienteNV;
    private javax.swing.JTextField txtIdClienteNV1;
    private javax.swing.JTextField txtIdEmpleados;
    private javax.swing.JTextField txtIdProductoNV;
    private javax.swing.JTextField txtIdProductoNV1;
    private javax.swing.JTextField txtIdProductosProductos;
    private javax.swing.JTextField txtIdProveedores;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtNombreClienteCLI;
    private javax.swing.JTextField txtNombreClienteNV;
    private javax.swing.JTextField txtNombreClienteNV1;
    private javax.swing.JTextField txtNombreDelProducto;
    private javax.swing.JTextField txtNombreEmpleados;
    private javax.swing.JTextField txtNombreProveedores;
    private javax.swing.JTextField txtPrecioNV;
    private javax.swing.JTextField txtPrecioNV1;
    private javax.swing.JTextField txtStockProd;
    private javax.swing.JTextField txtTelefonoClienteCLI;
    private javax.swing.JTextField txtTelefonoEmpleados;
    private javax.swing.JTextField txtTelefonoProveedores;
    private javax.swing.JTextField txtTotalVN;
    private javax.swing.JTextField txtTotalVN1;
    private javax.swing.JTextField txtprecioProd;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsearchempleado;
    private javax.swing.JTextField txtsearchproovedor;
    private javax.swing.JTextField txtubicacionProveedor;
    // End of variables declaration//GEN-END:variables
}
