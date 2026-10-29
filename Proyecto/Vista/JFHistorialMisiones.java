package Proyecto.Vista;
import Proyecto.Modelo.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Historial de misiones (Andrés).
 * Acciones en la vista. Filtra por equipo.
 */
public class JFHistorialMisiones extends JFrame
{
    private SistemaKsar sistema;
    private JComboBox<String> cmbEquipo;
    private DefaultTableModel modelo;
    private JTextArea txtMensaje;

    public JFHistorialMisiones(SistemaKsar sistema) {
        this.sistema = sistema;
        setTitle("Historial de misiones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(crearNorte(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearSur(), BorderLayout.SOUTH);
        mostrarTodas();
    }

    private JPanel crearNorte() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(15, 32, 68));
        barra.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel titulo = new JLabel("K-SAR ECID  -  Historial de misiones");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        barra.add(titulo, BorderLayout.WEST);
        return barra;
    }

    private JPanel crearCentro() {
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(Color.WHITE);
        String[] cols = {"Código", "Fecha", "Horas", "Sobrevivientes", "Fallecidos"};
        modelo = new DefaultTableModel(cols, 0);
        centro.add(new JScrollPane(new JTable(modelo)), BorderLayout.CENTER);
        txtMensaje = new JTextArea(2, 20);
        txtMensaje.setEditable(false);
        centro.add(txtMensaje, BorderLayout.SOUTH);
        return centro;
    }

    private JPanel crearSur() {
        JPanel sur = new JPanel(new FlowLayout());
        sur.setBackground(Color.WHITE);
        cmbEquipo = new JComboBox<>();
        for (Equipo e : sistema.getEquipos()) cmbEquipo.addItem(e.getNombre());
        JButton filtrar = new JButton("Filtrar por equipo");
        filtrar.setBackground(new Color(15, 32, 68));
        filtrar.setForeground(Color.WHITE);
        filtrar.setFocusPainted(false);
        filtrar.addActionListener(e -> filtrar());
        JButton todas = new JButton("Mostrar todas");
        todas.addActionListener(e -> mostrarTodas());
        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> dispose());
        sur.add(new JLabel("Equipo:"));
        sur.add(cmbEquipo);
        sur.add(filtrar);
        sur.add(todas);
        sur.add(cerrar);
        return sur;
    }

    private void mostrarTodas() {
        llenar(sistema.listarMisiones());
        txtMensaje.setText("");
    }

    private void filtrar() {
        if (cmbEquipo.getSelectedItem() == null) {
            txtMensaje.setText("No hay equipos.");
            return;
        }
        String nombre = (String) cmbEquipo.getSelectedItem();
        llenar(sistema.filtrarPorEquipo(nombre));
        txtMensaje.setText("Filtrado por equipo: " + nombre + ".");
    }

    private void llenar(java.util.ArrayList<Mision> lista) {
        modelo.setRowCount(0);
        for (Mision m : lista) {
            String fecha = m.getFecha() != null ? m.getFecha().getDia() + "/" + m.getFecha().getMes() + "/" + m.getFecha().getAnio() : "-";
            modelo.addRow(new Object[]{m.getCodigo(), fecha, m.getHorasLabor(), m.getSobrevivientes(), m.getFallecidos()});
        }
    }
}
