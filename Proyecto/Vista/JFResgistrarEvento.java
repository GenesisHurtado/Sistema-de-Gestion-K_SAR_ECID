package Proyecto.Vista;
import Proyecto.Modelo.*;
import Proyecto.Modelo.SistemaKsar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Write a description of class JFResgistrarEvento here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JFResgistrarEvento extends JFrame
{
    private SistemaKsar sistema;

    // Datos del evento
    private JComboBox<String> cmbTipo;
    private JPanelFecha pnlFecha;
    private JTextField txtMagnitud;
    private JTextField txtEpicentro;
    private JTextField txtVolumen;
    private JTextField txtZona;

    // Ubicacion del evento
    private JTextField txtPais;
    private JTextField txtCodigo;
    private JTextField txtMunicipio;
    private JTextField txtLugar;
    private JTextField txtCalle;
    private JTextField txtNumero;
    private JTextField txtBarrio;

    private JTextArea txtMensaje;
    
     public JFResgistrarEvento(SistemaKsar sistema) {
        this.sistema = sistema;
        setTitle("Registrar evento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        add(crearNorte(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearSur(), BorderLayout.SOUTH);
        cambiarTipo();
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
        JLabel subtitulo = new JLabel("Registro de evento");
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

        // Tipo y fecha del evento
        JPanel general = new JPanel(new GridBagLayout());
        general.setBackground(Color.WHITE);
        general.setBorder(BorderFactory.createTitledBorder("Datos del evento"));
        cmbTipo = new JComboBox<>(new String[]{"Terremoto", "Deslave"});
        cmbTipo.addActionListener(e -> cambiarTipo());
        pnlFecha = new JPanelFecha();
        agregarFila(general, 0, "Tipo de evento:", cmbTipo);
        agregarFila(general, 1, "Fecha del evento:", pnlFecha);

        // Datos propios de cada tipo (izquierda) y ubicacion (derecha)
        JPanel medio = new JPanel(new GridLayout(1, 2));
        medio.setBackground(Color.WHITE);

        JPanel tipos = new JPanel(new GridLayout(2, 1));
        tipos.setBackground(Color.WHITE);

        JPanel terremoto = new JPanel(new GridBagLayout());
        terremoto.setBackground(Color.WHITE);
        terremoto.setBorder(BorderFactory.createTitledBorder("Datos del terremoto"));
        txtMagnitud = new JTextField(15);
        txtEpicentro = new JTextField(15);
        agregarFila(terremoto, 0, "Magnitud (Richter, 1 decimal):", txtMagnitud);
        agregarFila(terremoto, 1, "Epicentro:", txtEpicentro);

        JPanel deslave = new JPanel(new GridBagLayout());
        deslave.setBackground(Color.WHITE);
        deslave.setBorder(BorderFactory.createTitledBorder("Datos del deslave"));
        txtVolumen = new JTextField(15);
        txtZona = new JTextField(15);
        agregarFila(deslave, 0, "Volumen (m³, entero):", txtVolumen);
        agregarFila(deslave, 1, "Zona afectada:", txtZona);

        tipos.add(terremoto);
        tipos.add(deslave);

        JPanel ubicacion = new JPanel(new GridBagLayout());
        ubicacion.setBackground(Color.WHITE);
        ubicacion.setBorder(BorderFactory.createTitledBorder("Ubicación del evento"));
        txtPais = new JTextField(15);
        txtCodigo = new JTextField(15);
        txtMunicipio = new JTextField(15);
        txtLugar = new JTextField(15);
        txtCalle = new JTextField(15);
        txtNumero = new JTextField(15);
        txtBarrio = new JTextField(15);
        agregarFila(ubicacion, 0, "País:", txtPais);
        agregarFila(ubicacion, 1, "Código del país:", txtCodigo);
        agregarFila(ubicacion, 2, "Municipio:", txtMunicipio);
        agregarFila(ubicacion, 3, "Lugar:", txtLugar);
        agregarFila(ubicacion, 4, "Calle / avenida:", txtCalle);
        agregarFila(ubicacion, 5, "Número:", txtNumero);
        agregarFila(ubicacion, 6, "Barrio:", txtBarrio);
        JButton btnUbicacion = new JButton("Registrar ubicación");
        btnUbicacion.setFocusPainted(false);
        btnUbicacion.addActionListener(e -> registrarUbicacion());
        agregarFila(ubicacion, 7, "", btnUbicacion);

        
        txtCodigo.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                completarPais();
            }
        });
        
        txtLugar.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                completarLugar();
            }
        });

        medio.add(tipos);
        medio.add(ubicacion);

        txtMensaje = new JTextArea(4, 20);
        txtMensaje.setEditable(false);
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setBackground(new Color(245, 245, 245));

        centro.add(general, BorderLayout.NORTH);
        centro.add(medio, BorderLayout.CENTER);
        centro.add(new JScrollPane(txtMensaje), BorderLayout.SOUTH);
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
        JButton btn = new JButton("Guardar evento");
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

    
    private void cambiarTipo() {
        boolean esTerremoto = cmbTipo.getSelectedItem().equals("Terremoto");
        txtMagnitud.setEnabled(esTerremoto);
        txtEpicentro.setEnabled(esTerremoto);
        txtVolumen.setEnabled(!esTerremoto);
        txtZona.setEnabled(!esTerremoto);
        if (esTerremoto) {
            txtVolumen.setText("");
            txtZona.setText("");
        } else {
            txtMagnitud.setText("");
            txtEpicentro.setText("");
        }
    }

    private void completarPais() {
        Pais p = sistema.buscarPais(txtCodigo.getText().trim());
        if (p != null) {
            txtPais.setText(p.getNombre());
        }
    }

    private void completarLugar() {
        Lugar l = sistema.buscarLugar(txtCodigo.getText().trim(), txtMunicipio.getText().trim(), txtLugar.getText().trim());
        if (l != null) {
            txtCalle.setText(l.getCalle());
            txtNumero.setText(l.getNumero());
            txtBarrio.setText(l.getBarrio());
        }
    }

    private String registrarUbicacionDelFormulario() {
        return sistema.registrarUbicacion(txtPais.getText().trim(), txtCodigo.getText().trim(),
                txtMunicipio.getText().trim(), txtLugar.getText().trim(),
                txtCalle.getText().trim(), txtNumero.getText().trim(), txtBarrio.getText().trim());
    }

    private void registrarUbicacion() {
        txtMensaje.setText(registrarUbicacionDelFormulario());
    }

    private void guardar() {
   
        String mensajeUbicacion = registrarUbicacionDelFormulario();
        Lugar lugar = sistema.buscarLugar(txtCodigo.getText().trim(), txtMunicipio.getText().trim(), txtLugar.getText().trim());
        if (lugar == null) {
            txtMensaje.setText(mensajeUbicacion);
            return;
        }
        Fecha fecha = pnlFecha.getFecha();
        String mensajeEvento;
        if (cmbTipo.getSelectedItem().equals("Terremoto")) {
            try {
              
                double magnitud = txtMagnitud.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtMagnitud.getText().trim().replace(',', '.'));
                mensajeEvento = sistema.registrarTerremoto(fecha, magnitud, txtEpicentro.getText().trim(), lugar);
            } catch (NumberFormatException ex) {
                mensajeEvento = "La magnitud debe ser un número con un decimal (por ejemplo, 7.2).";
            }
        } else {
            try {
                int volumen = txtVolumen.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtVolumen.getText().trim());
                mensajeEvento = sistema.registrarDeslave(fecha, volumen, txtZona.getText().trim(), lugar);
            } catch (NumberFormatException ex) {
                mensajeEvento = "El volumen debe ser un número entero.";
            }
        }
        txtMensaje.setText(mensajeUbicacion + "\n" + mensajeEvento);
    }

}