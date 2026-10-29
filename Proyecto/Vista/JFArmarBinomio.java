package Proyecto.Vista;
import Proyecto.Modelo.*;
import javax.swing.*;
import java.awt.*;

/**
 * Armar binomio (Andrés).
 * Acciones en la vista. Mensaje en TextArea al armar.
 */
public class JFArmarBinomio extends JFrame
{
    private SistemaKsar sistema;
    private JComboBox<String> cmbGuia;
    private JComboBox<String> cmbPerro;
    private JComboBox<String> cmbEquipo;
    private JTextArea txtMensaje;

    public JFArmarBinomio(SistemaKsar sistema) {
        this.sistema = sistema;
        setTitle("Armar binomio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 420);
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
        JLabel sub = new JLabel("Armar binomio");
        sub.setForeground(new Color(200, 210, 230));
        JPanel t = new JPanel();
        t.setLayout(new BoxLayout(t, BoxLayout.Y_AXIS));
        t.setOpaque(false);
        t.add(titulo);
        t.add(sub);
        ImageIcon icono = new ImageIcon("imagen/logo.png");
        Image img = icono.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        JPanel izq = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        izq.setOpaque(false);
        izq.add(new JLabel(new ImageIcon(img)));
        izq.add(t);
        barra.add(izq, BorderLayout.WEST);
        return barra;
    }

    private JPanel crearCentro() {
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createTitledBorder("Seleccionar"));

        cmbGuia = new JComboBox<>();
        cmbPerro = new JComboBox<>();
        cmbEquipo = new JComboBox<>();
        cargarCombos();

        agregar(centro, 0, "Guía disponible:", cmbGuia);
        agregar(centro, 1, "Perro disponible:", cmbPerro);
        agregar(centro, 2, "Equipo:", cmbEquipo);

        txtMensaje = new JTextArea(3, 18);
        txtMensaje.setEditable(false);
        txtMensaje.setLineWrap(true);
        agregar(centro, 3, "", new JScrollPane(txtMensaje));
        return centro;
    }

    private void cargarCombos() {
        cmbGuia.removeAllItems();
        for (Guia g : sistema.getGuias()) {
            if (g.getBinomio() == null) cmbGuia.addItem(g.getCedula() + " - " + g.getNombreCompleto());
        }
        cmbPerro.removeAllItems();
        for (Perro p : sistema.getPerros()) {
            if (p.getBinomio() == null) cmbPerro.addItem(p.getCodigo() + " - " + p.getNombre());
        }
        cmbEquipo.removeAllItems();
        for (Equipo e : sistema.getEquipos()) cmbEquipo.addItem(e.getNombre());
    }

    private void agregar(JPanel p, int fila, String et, JComponent c) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridy = fila; g.insets = new Insets(5, 6, 5, 6); g.anchor = GridBagConstraints.WEST;
        g.gridx = 0; p.add(new JLabel(et), g);
        g.gridx = 1; g.weightx = 1; g.fill = GridBagConstraints.HORIZONTAL; p.add(c, g);
    }

    private JPanel crearSur() {
        JPanel sur = new JPanel(new FlowLayout());
        sur.setBackground(Color.WHITE);
        JButton btn = new JButton("Armar binomio");
        btn.setBackground(new Color(15, 32, 68));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.addActionListener(e -> armar());
        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(e -> dispose());
        sur.add(btn);
        sur.add(cancelar);
        return sur;
    }

    private void armar() {
        if (cmbGuia.getSelectedItem() == null || cmbPerro.getSelectedItem() == null || cmbEquipo.getSelectedItem() == null) {
            txtMensaje.setText("Faltan datos para armar el binomio.");
            return;
        }
        String cedula = ((String) cmbGuia.getSelectedItem()).split(" - ")[0];
        String codigo = ((String) cmbPerro.getSelectedItem()).split(" - ")[0];
        String equipo = (String) cmbEquipo.getSelectedItem();
        txtMensaje.setText(sistema.armarBinomio(cedula, codigo, equipo));
        cargarCombos();
    }
}
