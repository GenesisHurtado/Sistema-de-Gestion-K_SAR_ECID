package Proyecto.Vista;
import Proyecto.Modelo.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;

/**
 * Ventana principal.
 * Andrés: recibe el controlador y abre los formularios de guía, perro, binomio e historial.
 */
public class JFKsar extends JFrame
{
      // Etiquetas del oeste
    private JLabel lblGuia;
    private JLabel lblCedula;
    private JLabel lblCert;
    private JLabel lblPerro;
    private JLabel lblRaza;
 
    // Etiquetas del este
    private JLabel lblTipo;
    private JLabel lblMagnitud;
    private JLabel lblLugar;
    private JLabel lblMunicipio;
    private JLabel lblFecha;
 
    // Búsqueda y contadores
    private JComboBox<String> cmbCriterio;
    private JTextField txtBuscar;
    private JLabel lblEquipos;
    private JLabel lblMisiones;
    private JLabel lblDisponibles;
    private JLabel lblMensaje;
 
    // Tabla (cada fila corresponde a la misión de la misma posición en "mostradas")
    private JTable tabla;
    private DefaultTableModel modelo;
    private ArrayList<Mision> mostradas = new ArrayList<>();
 
    private SistemaKsar sistema;
 
    public JFKsar() {
        this(new SistemaKsar());
    }
 
    public JFKsar(SistemaKsar sistema) {
        this.sistema = sistema;
        setTitle("K-SAR ECID - Sistema de Gestión de Equipos Caninos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
 
        add(crearNorte(), BorderLayout.NORTH);
        add(crearOeste(), BorderLayout.WEST);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearEste(), BorderLayout.EAST);
        add(crearSur(), BorderLayout.SOUTH);
 
        refrescar();
        setVisible(true);
    }
 
    // ==================== NORTE ====================
    private JPanel crearNorte() {
        JPanel norte = new JPanel();
        norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
        norte.setBackground(Color.WHITE);
 
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(15, 32, 68));
        barra.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
 
        JLabel titulo = new JLabel("K-SAR ECID");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        JLabel subtitulo = new JLabel("Sistema de Gestión de Equipos Caninos");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitulo.setForeground(new Color(200, 210, 230));
 
        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setOpaque(false);
        textos.add(titulo);
        textos.add(subtitulo);
 
        ImageIcon icono = new ImageIcon("imagen/logo.png");
        Image img = icono.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 14));
 
        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        izquierda.setOpaque(false);
        izquierda.add(logo);
        izquierda.add(textos);
 
        JLabel rol = new JLabel("Rol: Director");
        rol.setForeground(Color.WHITE);
        rol.setFont(new Font("Arial", Font.PLAIN, 13));
 
        barra.add(izquierda, BorderLayout.WEST);
        barra.add(rol, BorderLayout.EAST);
 
        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        busqueda.setBackground(Color.WHITE);
        busqueda.add(new JLabel("Buscar por"));
        cmbCriterio = new JComboBox<>(new String[]{"Perro", "Guía", "Misión"});
        busqueda.add(cmbCriterio);
        txtBuscar = new JTextField(18);
        txtBuscar.setToolTipText("Perro: código o nombre | Guía: cédula o nombre | Misión: código");
        txtBuscar.addActionListener(e -> buscar());
        busqueda.add(txtBuscar);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(15, 32, 68));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(e -> buscar());
        busqueda.add(btnBuscar);
        JButton btnTodas = new JButton("Actualizar");
        btnTodas.setFocusPainted(false);
        btnTodas.addActionListener(e -> refrescar());
        busqueda.add(btnTodas);
        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(new Color(120, 40, 40));
        busqueda.add(lblMensaje);
 
        JPanel numeros = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 6));
        numeros.setBackground(Color.WHITE);
        lblEquipos = new JLabel("0  equipos");
        lblMisiones = new JLabel("0  misiones");
        lblDisponibles = new JLabel("0  disponibles");
        numeros.add(lblEquipos);
        numeros.add(lblMisiones);
        numeros.add(lblDisponibles);
 
        norte.add(barra);
        norte.add(busqueda);
        norte.add(numeros);
        return norte;
    }
 
    // ==================== OESTE ====================
    private JPanel crearOeste() {
        JPanel oeste = new JPanel();
        oeste.setLayout(new BoxLayout(oeste, BoxLayout.Y_AXIS));
        oeste.setBackground(Color.WHITE);
        oeste.setBorder(BorderFactory.createTitledBorder("Binomio seleccionado"));
        oeste.setPreferredSize(new Dimension(220, 0));
 
        lblGuia = new JLabel("Guía: —");
        lblCedula = new JLabel("Cédula: —");
        lblCert = new JLabel("Certificación: —");
        lblPerro = new JLabel("Perro: —");
        lblRaza = new JLabel("Raza: —");
 
        oeste.add(Box.createVerticalStrut(10));
        oeste.add(lblGuia);
        oeste.add(lblCedula);
        oeste.add(lblCert);
        oeste.add(Box.createVerticalStrut(15));
        oeste.add(lblPerro);
        oeste.add(lblRaza);
        oeste.add(Box.createVerticalGlue());
        return oeste;
    }
 
    // ==================== CENTRO ====================
    private JPanel crearCentro() {
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createTitledBorder("Historial de misiones"));
 
        String[] columnas = {"Código", "Fecha", "Lugar", "Equipo", "Horas", "Sobrevivientes", "Fallecidos"};
        modelo = new DefaultTableModel(columnas, 0);
 
        tabla = new JTable(modelo);
        tabla.setRowHeight(22);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 
        // Al seleccionar una fila, actualiza oeste (binomio) y este (evento)
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int fila = tabla.getSelectedRow();
                    if (fila >= 0 && fila < mostradas.size()) {
                        Mision m = mostradas.get(fila);
                        mostrarBinomio(m.getBinomio());
                        mostrarEvento(m.getEvento());
                    }
                }
            }
        });
 
        centro.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return centro;
    }
 
    // ==================== ESTE ====================
    private JPanel crearEste() {
        JPanel este = new JPanel();
        este.setLayout(new BoxLayout(este, BoxLayout.Y_AXIS));
        este.setBackground(Color.WHITE);
        este.setBorder(BorderFactory.createTitledBorder("Evento"));
        este.setPreferredSize(new Dimension(220, 0));
 
        lblTipo = new JLabel("Tipo: —");
        lblMagnitud = new JLabel("Magnitud: —");
        lblLugar = new JLabel("Lugar: —");
        lblMunicipio = new JLabel("Municipio: —");
        lblFecha = new JLabel("Fecha: —");
 
        este.add(Box.createVerticalStrut(10));
        este.add(lblTipo);
        este.add(lblMagnitud);
        este.add(lblLugar);
        este.add(lblMunicipio);
        este.add(lblFecha);
        este.add(Box.createVerticalGlue());
        return este;
    }
 
    // ==================== SUR ====================
     private JPanel crearSur() {
        JPanel sur = new JPanel();
        sur.setLayout(new BoxLayout(sur, BoxLayout.Y_AXIS));
        sur.setBackground(Color.WHITE);
        sur.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
 
        String[] registros = {"Registrar equipo", "Registrar guía", "Registrar perro",
                              "Armar binomio", "Registrar evento", "Registrar misión"};
 
        JPanel filaRegistro = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 4));
        filaRegistro.setBackground(Color.WHITE);
        for (String texto : registros) {
            JButton btn = new JButton(texto);
            btn.setBackground(new Color(15, 32, 68));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
 
            // Botones para acceder a los subFormularios
 
            if (texto.equals("Registrar equipo")) {
                btn.addActionListener(e -> new JFRegistrarEquipo(sistema).setVisible(true));
            }
 
            if (texto.equals("Registrar guía")) {
                btn.addActionListener(e -> new JFRegistrarGuia(sistema).setVisible(true));
            }
 
            if (texto.equals("Registrar perro")) {
                btn.addActionListener(e -> new JFRegistrarPerro(sistema).setVisible(true));
            }
 
            if (texto.equals("Armar binomio")) {
                btn.addActionListener(e -> new JFArmarBinomio(sistema).setVisible(true));
            }
 
            if (texto.equals("Registrar evento")) {
                btn.addActionListener(e -> new JFResgistrarEvento(sistema).setVisible(true));
            }
 
            if (texto.equals("Registrar misión")) {
                btn.addActionListener(e -> new JFRegistrarMision(sistema).setVisible(true));
            }
 
            filaRegistro.add(btn);
        }
 
        JPanel filaConsulta = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 4));
        filaConsulta.setBackground(Color.WHITE);
        String[] consultas = {"Listar guías y perros", "Filtrar misiones", "Historial por fecha", "Mayor desempeño"};
        for (String texto : consultas) {
            JButton btn = new JButton(texto);
            btn.setFocusPainted(false);
 
            if (texto.equals("Listar guías y perros")) {
                btn.addActionListener(e -> new JFListarGuia_Perro(sistema).setVisible(true));
            }
 
            if (texto.equals("Filtrar misiones")) {
                btn.addActionListener(e -> new JFFiltrarMision(sistema).setVisible(true));
            }
 
            if (texto.equals("Historial por fecha")) {
                btn.addActionListener(e -> new JFHistorialMisiones(sistema).setVisible(true));
            }
 
            if (texto.equals("Mayor desempeño")) {
                btn.addActionListener(e -> new JFMayorDesempeño(sistema).setVisible(true));
            }
 
            filaConsulta.add(btn);
        }
 
        sur.add(filaRegistro);
        sur.add(filaConsulta);
        return sur;
    }
 
    // ==================== LÓGICA DE LA VENTANA ====================
 
    // Recalcula contadores y vuelve a cargar la tabla con todas las misiones
    private void refrescar() {
        lblEquipos.setText(sistema.getEquipos().size() + "  equipos");
        lblMisiones.setText(sistema.getMisiones().size() + "  misiones");
        lblDisponibles.setText(sistema.contarBinomiosDisponibles() + "  disponibles");
        txtBuscar.setText("");
        lblMensaje.setText(" ");
        mostrarMisiones(sistema.listarMisiones());
        limpiarDetalle();
    }
 
    // Botón Buscar: Perro/Guía/Misión según el combo
    private void buscar() {
        String criterio = (String) cmbCriterio.getSelectedItem();
        String texto = txtBuscar.getText().trim();
 
        if (texto.isEmpty()) {
            mostrarMisiones(sistema.listarMisiones());
            limpiarDetalle();
            lblMensaje.setText(" ");
            return;
        }
 
        if (criterio.equals("Misión")) {
            ArrayList<Mision> lista = sistema.buscarMisionesPorCodigo(texto);
            mostrarMisiones(lista);
            if (lista.isEmpty()) {
                limpiarDetalle();
                lblMensaje.setText("No se encontró ninguna misión con \"" + texto + "\".");
            } else {
                lblMensaje.setText(lista.size() + " misión(es) encontrada(s).");
                tabla.setRowSelectionInterval(0, 0);
            }
            return;
        }
 
        ArrayList<Binomio> binomios = criterio.equals("Perro")
                ? sistema.buscarBinomiosPorPerro(texto)
                : sistema.buscarBinomiosPorGuia(texto);
 
        if (binomios.isEmpty()) {
            mostrarMisiones(new ArrayList<Mision>());
            limpiarDetalle();
            lblMensaje.setText("No se encontró un binomio con ese " + criterio.toLowerCase() + ".");
            return;
        }
 
        ArrayList<Mision> lista = new ArrayList<>();
        for (Binomio b : binomios) {
            lista.addAll(b.getMisiones());
        }
        mostrarMisiones(lista);
 
        if (lista.isEmpty()) {
            
            mostrarBinomio(binomios.get(0));
            mostrarEvento(null);
            lblMensaje.setText("Binomio encontrado, sin misiones registradas.");
        } else {
            lblMensaje.setText(lista.size() + " misión(es) encontrada(s).");
            tabla.setRowSelectionInterval(0, 0);
        }
    }
 
    private void mostrarMisiones(ArrayList<Mision> lista) {
        mostradas = new ArrayList<>(lista);
        modelo.setRowCount(0);
        for (Mision m : mostradas) {
            String lugar = (m.getEvento() != null && m.getEvento().getLugar() != null)
                    ? m.getEvento().getLugar().getNombre() : "-";
            modelo.addRow(new Object[]{
                m.getCodigo(),
                m.getFecha() != null ? m.getFecha().toString() : "-",
                lugar,
                m.getEquipo() != null ? m.getEquipo().getNombre() : "-",
                m.getHorasLabor(),
                m.getSobrevivientes(),
                m.getFallecidos()
            });
        }
    }
 
    // Panel oeste
    private void mostrarBinomio(Binomio b) {
        if (b == null) {
            lblGuia.setText("Guía: —");
            lblCedula.setText("Cédula: —");
            lblCert.setText("Certificación: —");
            lblPerro.setText("Perro: —");
            lblRaza.setText("Raza: —");
            return;
        }
        Guia g = b.getGuia();
        Perro p = b.getPerro();
        lblGuia.setText("Guía: " + (g != null ? g.getNombreCompleto() : "—"));
        lblCedula.setText("Cédula: " + (g != null ? g.getCedula() : "—"));
        lblCert.setText("Certificación: " + (g != null ? g.getCertificacion() : "—"));
        lblPerro.setText("Perro: " + (p != null ? p.getNombre() : "—"));
        lblRaza.setText("Raza: " + (p != null ? p.getRaza() : "—"));
    }
 
    // Panel este
    private void mostrarEvento(Evento ev) {
        if (ev == null) {
            lblTipo.setText("Tipo: —");
            lblMagnitud.setText("Dato: —");
            lblLugar.setText("Lugar: —");
            lblMunicipio.setText("Municipio: —");
            lblFecha.setText("Fecha: —");
            return;
        }
        lblTipo.setText("Tipo: " + ev.getTipo());
        lblMagnitud.setText(ev.getNombreDato() + ": " + ev.getValorDato());
        lblLugar.setText("Lugar: " + (ev.getLugar() != null ? ev.getLugar().getNombre() : "—"));
        lblMunicipio.setText("Municipio: " + (ev.getLugar() != null && ev.getLugar().getMunicipio() != null
                ? ev.getLugar().getMunicipio().getNombre() : "—"));
        lblFecha.setText("Fecha: " + (ev.getFecha() != null ? ev.getFecha().toString() : "—"));
    }
 
    private void limpiarDetalle() {
        mostrarBinomio(null);
        mostrarEvento(null);
    }

}

