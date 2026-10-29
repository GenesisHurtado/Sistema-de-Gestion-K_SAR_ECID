package Proyecto.Vista;
import Proyecto.Modelo.SistemaKsar;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

    // Datos de ejemplo 
    private String[][] datosOeste = {
        {"Jorge Beens", "10000001", "Vigente", "Tsunami", "Border collie"},
        {"María Pérez", "10000002", "Vigente", "Rocky", "Pastor alemán"},
        {"Carlos Rojas", "10000003", "Vencida", "Luna", "Labrador"}
    };
    private String[][] datosEste = {
        {"Terremoto", "7.2", "Edificio Centro", "Cúcuta", "2026-10-12"},
        {"Deslave", "1500", "Barrio Norte", "Vargas", "2026-10-08"},
        {"Terremoto", "5.5", "Urbanización La Rivera", "Libertador", "2026-10-05"}
    };

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
        busqueda.add(new JComboBox<>(new String[]{"Perro", "Guía", "Misión"}));
        busqueda.add(new JTextField(18));
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(15, 32, 68));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        busqueda.add(btnBuscar);

        JPanel numeros = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 6));
        numeros.setBackground(Color.WHITE);
        numeros.add(new JLabel("4  equipos"));
        numeros.add(new JLabel("12  misiones"));
        numeros.add(new JLabel("2  disponibles"));

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
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        modelo.addRow(new Object[]{"DES-007", "2026-10-12", "Edificio Centro", "Equipo Alpha", "6.5", "3", "1"});
        modelo.addRow(new Object[]{"DES-006", "2026-10-08", "Barrio Norte", "Equipo Beta", "4.0", "1", "0"});
        modelo.addRow(new Object[]{"DES-005", "2026-10-05", "Urbanización La Rivera", "Equipo Gamma", "5.0", "2", "0"});

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(22);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Al seleccionar una fila, actualiza oeste y este
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int fila = tabla.getSelectedRow();
                    if (fila >= 0 && fila < datosOeste.length) {
                        lblGuia.setText("Guía: " + datosOeste[fila][0]);
                        lblCedula.setText("Cédula: " + datosOeste[fila][1]);
                        lblCert.setText("Certificación: " + datosOeste[fila][2]);
                        lblPerro.setText("Perro: " + datosOeste[fila][3]);
                        lblRaza.setText("Raza: " + datosOeste[fila][4]);

                        lblTipo.setText("Tipo: " + datosEste[fila][0]);
                        lblMagnitud.setText("Magnitud: " + datosEste[fila][1]);
                        lblLugar.setText("Lugar: " + datosEste[fila][2]);
                        lblMunicipio.setText("Municipio: " + datosEste[fila][3]);
                        lblFecha.setText("Fecha: " + datosEste[fila][4]);
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
        filaRegistro.add(btn);
        
        
        // Botones para acceder a los subFormularios
        
        if (texto.equals("Registrar perro")) {
            btn.addActionListener(e -> new JFRegistrarPerro(sistema).setVisible(true));
        }
        if (texto.equals("Registrar guía")) {
            btn.addActionListener(e -> new JFRegistrarGuia(sistema).setVisible(true));
        }
        if (texto.equals("Armar binomio")) {
            btn.addActionListener(e -> new JFArmarBinomio(sistema).setVisible(true));
        }

       filaRegistro.add(btn);
    }
     
    
    
    JPanel filaConsulta = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 4));
    filaConsulta.setBackground(Color.WHITE);
    String[] consultas = {"Listar guías y perros",
                          "Historial por fecha"};
    for (String texto : consultas) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        if (texto.equals("Historial por fecha")) {
            btn.addActionListener(e -> new JFHistorialMisiones(sistema).setVisible(true));
        }
        filaConsulta.add(btn);
    }
    
    

    sur.add(filaRegistro);
    sur.add(filaConsulta);
    return sur;
}
}
