package Proyecto.Vista;
import Proyecto.Modelo.*;
import javax.swing.*;
import java.awt.*;

/**
 * Registrar perro (Andrés).
 * Acciones en la vista. Mensaje en TextArea al registrar.
 */
public class JFRegistrarPerro extends JFrame
{
    private SistemaKsar sistema;
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextField txtRaza;
    private JTextField txtEdad;
    private JComboBox<String> cmbCertificacion;
    private JTextField txtCodigoAct;
    private JComboBox<String> cmbCertAct;
    private JTextArea txtMensaje;

    public JFRegistrarPerro(SistemaKsar sistema) {
        this.sistema = sistema;
        setTitle("Registrar perro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 560);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(crearNorte(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearSur(), BorderLayout.SOUTH);
    }

    private JPanel crearNorte() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(15, 32, 68));
        barra.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel titulo = new JLabel("K-SAR ECID");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        JLabel subtitulo = new JLabel("Registro de perro");
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
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Color.WHITE);

        JPanel datos = new JPanel(new GridBagLayout());
        datos.setBackground(Color.WHITE);
        datos.setBorder(BorderFactory.createTitledBorder("Datos del perro"));
        txtNombre = new JTextField(15);
        txtDescripcion = new JTextArea(2, 15);
        txtDescripcion.setLineWrap(true);
        txtRaza = new JTextField(15);
        txtEdad = new JTextField(15);
        cmbCertificacion = new JComboBox<>(new String[]{"VIGENTE", "VENCIDA", "EN_TRAMITE"});
        agregarFila(datos, 0, "Nombre:", txtNombre);
        agregarFila(datos, 1, "Descripción:", new JScrollPane(txtDescripcion));
        agregarFila(datos, 2, "Raza:", txtRaza);
        agregarFila(datos, 3, "Edad:", txtEdad);
        agregarFila(datos, 4, "Certificación:", cmbCertificacion);

        // RF4 (Andrés)
        JPanel act = new JPanel(new GridBagLayout());
        act.setBackground(Color.WHITE);
        act.setBorder(BorderFactory.createTitledBorder("Actualizar certificación"));
        txtCodigoAct = new JTextField(15);
        cmbCertAct = new JComboBox<>(new String[]{"VIGENTE", "VENCIDA", "EN_TRAMITE"});
        agregarFila(act, 0, "Código:", txtCodigoAct);
        agregarFila(act, 1, "Nueva certificación:", cmbCertAct);
        JButton btnAct = new JButton("Actualizar");
        btnAct.setBackground(new Color(15, 32, 68));
        btnAct.setForeground(Color.WHITE);
        btnAct.setFocusPainted(false);
        btnAct.addActionListener(e -> actualizar());
        agregarFila(act, 2, "", btnAct);

        txtMensaje = new JTextArea(3, 20);
        txtMensaje.setEditable(false);
        txtMensaje.setLineWrap(true);

        centro.add(datos);
        centro.add(act);
        centro.add(new JScrollPane(txtMensaje));
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
        JButton btnRegistrar = new JButton("Guardar perro");
        btnRegistrar.setBackground(new Color(15, 32, 68));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.addActionListener(e -> registrar());
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());
        sur.add(btnRegistrar);
        sur.add(btnCancelar);
        return sur;
    }

    private void registrar() {
        try {
            int edad = Integer.parseInt(txtEdad.getText().trim());
            Certificacion c = Certificacion.valueOf((String) cmbCertificacion.getSelectedItem());
            txtMensaje.setText(sistema.registrarPerro(txtNombre.getText().trim(),
                    txtDescripcion.getText().trim(), txtRaza.getText().trim(), edad, c));
        } catch (NumberFormatException ex) {
            txtMensaje.setText("La edad debe ser un número.");
        }
    }

    private void actualizar() {
        Certificacion c = Certificacion.valueOf((String) cmbCertAct.getSelectedItem());
        txtMensaje.setText(sistema.actualizarCertificacionPerro(txtCodigoAct.getText().trim(), c));
    }
}
