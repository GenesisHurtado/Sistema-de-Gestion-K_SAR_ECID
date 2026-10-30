package Proyecto.Vista;
import Proyecto.Modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.*;
/**
 * Write a description of class JFRegistrarMision here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JFRegistrarMision extends JFrame
{
     private SistemaKsar sistema;
    private JTextField txtCodigo;
    private JComboBox<String> cmbEvento;
    private JPanelFecha pnlFecha;
    private JComboBox<String> cmbEquipo;
    private JComboBox<String> cmbBinomio;
    private JTextField txtLugar;
    private JTextField txtMunicipio;
    private JTextField txtHoras;
    private JTextField txtSobrevivientes;
    private JTextField txtFallecidos;
    private JTextArea txtMensaje;
    
    private ArrayList<Evento> eventosLibres;
    private ArrayList<Binomio> binomiosEquipo;

    public JFRegistrarMision(SistemaKsar sistema) {
        this.sistema = sistema;
        eventosLibres = new ArrayList<>();
        binomiosEquipo = new ArrayList<>();
        setTitle("Registrar misión");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        add(crearNorte(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearSur(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }
    
    private JPanel crearNorte() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(15, 32, 68));
        barra.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel titulo = new JLabel("K-SAR ECID");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        JLabel subtitulo = new JLabel("Registro de misión");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 11));
        subtitulo.setForeground(new Color(200, 210, 230));
        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setOpaque(false);
        textos.add(titulo);
        textos.add(subtitulo);
        ImageIcon icono = new ImageIcon("imagen/logo.png");
        Image img = icono.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        izquierda.setOpaque(false);
        izquierda.add(logo);
        izquierda.add(textos);
        barra.add(izquierda, BorderLayout.WEST);
        return barra;
    }

    private JPanel crearCentro() {
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(Color.WHITE);

        // RF1 - datos de la misión
        JPanel datos = new JPanel(new GridBagLayout());
        datos.setBackground(Color.WHITE);
        datos.setBorder(BorderFactory.createTitledBorder("Datos de la misión"));

        txtCodigo = new JTextField(15);
        txtCodigo.setEditable(false);
        txtCodigo.setText(sistema.siguienteCodigoMision());
        cmbEvento = new JComboBox<>();
        pnlFecha = new JPanelFecha();
        cmbEquipo = new JComboBox<>();
        cmbBinomio = new JComboBox<>();
        txtLugar = new JTextField(15);
        txtLugar.setEditable(false);
        txtMunicipio = new JTextField(15);
        txtMunicipio.setEditable(false);
        txtHoras = new JTextField(15);
        txtSobrevivientes = new JTextField(15);
        txtFallecidos = new JTextField(15);

        cargarEventos();
        for (Equipo e : sistema.getEquipos()) cmbEquipo.addItem(e.getNombre());
        cargarBinomios();
        mostrarUbicacion();
        cmbEvento.addActionListener(e -> mostrarUbicacion());
        cmbEquipo.addActionListener(e -> cargarBinomios());

        agregarFila(datos, 0, "Código:", txtCodigo);
        agregarFila(datos, 1, "Evento atendido:", cmbEvento);
        agregarFila(datos, 2, "Fecha de la misión:", pnlFecha);
        agregarFila(datos, 3, "Equipo:", cmbEquipo);
        agregarFila(datos, 4, "Binomio:", cmbBinomio);
        agregarFila(datos, 5, "Lugar:", txtLugar);
        agregarFila(datos, 6, "Municipio:", txtMunicipio);
        agregarFila(datos, 7, "Horas de labor:", txtHoras);
        agregarFila(datos, 8, "Sobrevivientes:", txtSobrevivientes);
        agregarFila(datos, 9, "Fallecidos:", txtFallecidos);

        txtMensaje = new JTextArea(4, 20);
        txtMensaje.setEditable(false);
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setBackground(new Color(245, 245, 245));

        centro.add(datos, BorderLayout.NORTH);
        centro.add(new JScrollPane(txtMensaje), BorderLayout.CENTER);
        return centro;
    }
    
    private void agregarFila(JPanel panel, int fila, String etiqueta, JComponent campo) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = fila;
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        panel.add(new JLabel(etiqueta), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campo, gbc);
    }
    
    private JPanel crearSur() {
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        sur.setBackground(Color.WHITE);
        JButton btn = new JButton("Guardar misión");
        btn.setBackground(new Color(15, 32, 68));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.addActionListener(e -> guardar());
        JButton cancelar = new JButton("Cancelar");
        cancelar.setFocusPainted(false);
        cancelar.addActionListener(e -> dispose());
        sur.add(btn);
        sur.add(cancelar);
        return sur;
    }
    
    private void cargarEventos() {
        eventosLibres = new ArrayList<>();
        cmbEvento.removeAllItems();
        for (Evento ev : sistema.getEventos()) {
            if (!ev.tieneMision()) {
                eventosLibres.add(ev);
                cmbEvento.addItem(describirEvento(ev));
            }
        }
    }
    
    private String describirEvento(Evento ev) {
        String tipo;
        if (ev instanceof Terremoto) {
            tipo = "Terremoto " + ((Terremoto) ev).getMagnitud();
        } else if (ev instanceof Deslave) {
            tipo = "Deslave " + ((Deslave) ev).getVolumenDesplazado() + " m3";
        } else {
            tipo = ev.getDescripcion();
        }
        return tipo + " - " + ev.getFecha();
    }
    
    // Binomios del equipo elegido
    private void cargarBinomios() {
        binomiosEquipo = new ArrayList<>();
        cmbBinomio.removeAllItems();
        int i = cmbEquipo.getSelectedIndex();
        if (i < 0) {
            return;
        }
        Equipo eq = sistema.getEquipos().get(i);
        for (Binomio b : eq.getBinomios()) {
            binomiosEquipo.add(b);
            cmbBinomio.addItem(b.getGuia().getNombreCompleto() + " + " + b.getPerro().getNombre());
        }
    }
    
    // Lugar y municipio salen del evento elegido
    private void mostrarUbicacion() {
        int i = cmbEvento.getSelectedIndex();
        if (i < 0) {
            txtLugar.setText("");
            txtMunicipio.setText("");
            return;
        }
        Lugar l = eventosLibres.get(i).getLugar();
        txtLugar.setText(l.getNombre());
        txtMunicipio.setText(l.getMunicipio().obtenerDescripcion());
    }
    
    private void guardar() {
        int ie = cmbEvento.getSelectedIndex();
        int ib = cmbBinomio.getSelectedIndex();
        if (ie < 0 || ib < 0) {
            txtMensaje.setText("Seleccione un evento y un binomio.");
            return;
        }
        
        try {
            double horas = Double.parseDouble(txtHoras.getText().trim().replace(',', '.'));
            int sobrevivientes = Integer.parseInt(txtSobrevivientes.getText().trim());
            int fallecidos = Integer.parseInt(txtFallecidos.getText().trim());
            Evento evento = eventosLibres.get(ie);
            txtMensaje.setText(sistema.registrarMision(pnlFecha.getFecha(), evento,
                    binomiosEquipo.get(ib), horas, sobrevivientes, fallecidos));
            if (evento.tieneMision()) {
                cargarEventos();
                mostrarUbicacion();
                txtCodigo.setText(sistema.siguienteCodigoMision());
            }
        } catch (NumberFormatException ex) {
            txtMensaje.setText("Las horas deben ser un número (admite decimales); los sobrevivientes y los fallecidos, números enteros.");
        }
    }
    
    
    
}