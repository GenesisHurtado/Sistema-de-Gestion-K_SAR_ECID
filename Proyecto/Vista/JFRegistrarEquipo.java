package Proyecto.Vista;
import Proyecto.Modelo.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


/**
 * Write a description of class JFRegistrarEquipo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JFRegistrarEquipo extends JFrame
{
    private SistemaKsar sistema;
    private JTextField txtNombre;
    private JPanelFecha pnlFecha;
    private DefaultTableModel modelo;
    private JTextArea txtMensaje;

    public JFRegistrarEquipo(SistemaKsar sistema) {
        this.sistema = sistema;
        setTitle("Registrar equipo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(520, 560);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(crearNorte(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearSur(), BorderLayout.SOUTH);
        cargarTabla();
    }

    private JPanel crearNorte() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(15, 32, 68));
        barra.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel titulo = new JLabel("K-SAR ECID");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        JLabel subtitulo = new JLabel("Registro de equipo");
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

        // RF1 - datos del equipo
        JPanel datos = new JPanel(new GridBagLayout());
        datos.setBackground(Color.WHITE);
        datos.setBorder(BorderFactory.createTitledBorder("Datos del equipo"));
        txtNombre = new JTextField(15);
        pnlFecha = new JPanelFecha();
        agregarFila(datos, 0, "Nombre del equipo:", txtNombre);
        agregarFila(datos, 1, "Fecha de creación:", pnlFecha);

        // Equipos registrados
        JPanel lista = new JPanel(new BorderLayout());
        lista.setBackground(Color.WHITE);
        lista.setBorder(BorderFactory.createTitledBorder("Equipos registrados"));
        String[] cols = {"Nombre", "Fecha de creación", "Binomios"};
        modelo = new DefaultTableModel(cols, 0);
        lista.add(new JScrollPane(new JTable(modelo)), BorderLayout.CENTER);

        txtMensaje = new JTextArea(3, 20);
        txtMensaje.setEditable(false);
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setBackground(new Color(245, 245, 245));

        centro.add(datos);
        centro.add(lista);
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
        JButton btn = new JButton("Guardar equipo");
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

    private void guardar() {
        txtMensaje.setText(sistema.registrarEquipo(txtNombre.getText().trim(), pnlFecha.getFecha()));
        cargarTabla();
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        for (Equipo e : sistema.getEquipos()) {
            modelo.addRow(new Object[]{e.getNombre(), e.getFechaCreacion(), e.getBinomios().size()});
        }
    }
}