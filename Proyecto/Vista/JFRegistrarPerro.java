package Proyecto.Vista;
import javax.swing.*;
import java.awt.*;


/**
 * Write a description of class JFRegistrarPerro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JFRegistrarPerro extends JFrame
{
     private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextField txtRaza;
    private JTextField txtEdad;
    private JComboBox<String> cmbCertificacion;
    private JLabel lblAptitud;
    private boolean aptitudValidada = false;

     public JFRegistrarPerro() {
        setTitle("Registrar perro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(crearNorte(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearSur(), BorderLayout.SOUTH);
    }    
    
    
     // ==================== NORTE  ====================
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
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(12, 12, 12, 12),
                BorderFactory.createTitledBorder("Datos del perro")));

        txtCodigo = new JTextField(15);
        txtNombre = new JTextField(15);
        txtDescripcion = new JTextArea(3, 15);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtRaza = new JTextField(15);
        txtEdad = new JTextField(15);
        cmbCertificacion = new JComboBox<>(new String[]{"Vigente", "Vencida"});

        agregarFila(centro, 0, "Código:", txtCodigo);
        agregarFila(centro, 1, "Nombre:", txtNombre);
        agregarFila(centro, 2, "Descripción:", new JScrollPane(txtDescripcion));
        agregarFila(centro, 3, "Raza:", txtRaza);
        agregarFila(centro, 4, "Edad:", txtEdad);
        agregarFila(centro, 5, "Certificación:", cmbCertificacion);

               GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        centro.add(Box.createVerticalGlue(), gbc);

        return centro;
    }

    private void agregarFila(JPanel panel, int fila, String etiqueta, JComponent campo) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = fila;
        gbc.insets = new Insets(5, 6, 5, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        panel.add(new JLabel(etiqueta), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campo, gbc);
    }
    
    // ==================== SUR ====================
    private JPanel crearSur() {
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        sur.setBackground(Color.WHITE);
        sur.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 12, 12, 12),
                BorderFactory.createLineBorder(new Color(15, 32, 68), 1)));

        JButton btnRegistrar = new JButton("Registrar");
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
    
    
    //controlador .........
     private void registrar() {
        if (txtCodigo.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()
                || txtRaza.getText().trim().isEmpty() || txtEdad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos obligatorios.");
            return;
        }

        try {
            Integer.parseInt(txtEdad.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número entero.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Perro registrado correctamente.");
        dispose();
    }
    
}