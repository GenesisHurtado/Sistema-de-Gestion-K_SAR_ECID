package Proyecto.Vista;
import Proyecto.Modelo.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Write a description of class JFFiltrarMision here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JFFiltrarMision extends JFrame
{
      private static final String TODOS = "Todos";
 
    private SistemaKsar sistema;
    private JComboBox<String> cmbEquipo;
    private JComboBox<String> cmbMunicipio;
    private DefaultTableModel modelo;
    private JLabel lblResumen;
 
    public JFFiltrarMision(SistemaKsar sistema) {
        this.sistema = sistema;
        setTitle("Filtrar misiones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(820, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(crearNorte(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearSur(), BorderLayout.SOUTH);
        filtrar();
    }
 
    private JPanel crearNorte() {
        JPanel norte = new JPanel();
        norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
 
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(15, 32, 68));
        barra.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel titulo = new JLabel("K-SAR ECID");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        JLabel subtitulo = new JLabel("Filtrar misiones por equipo y municipio");
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
 
        // Filtros
        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filtros.setBackground(Color.WHITE);
        filtros.setBorder(BorderFactory.createTitledBorder("Filtros"));
 
        cmbEquipo = new JComboBox<>();
        cmbEquipo.addItem(TODOS);
        for (Equipo e : sistema.getEquipos()) {
            cmbEquipo.addItem(e.getNombre());
        }
 
        cmbMunicipio = new JComboBox<>();
        cmbMunicipio.addItem(TODOS);
        for (String m : sistema.listarNombresMunicipios()) {
            cmbMunicipio.addItem(m);
        }
 
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBackground(new Color(15, 32, 68));
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setFocusPainted(false);
        btnFiltrar.addActionListener(e -> filtrar());
 
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(e -> limpiar());
 
        filtros.add(Estilo.etiqueta("Equipo:"));
        filtros.add(cmbEquipo);
        filtros.add(Estilo.etiqueta("Municipio:"));
        filtros.add(cmbMunicipio);
        filtros.add(btnFiltrar);
        filtros.add(btnLimpiar);
 
        norte.add(barra);
        norte.add(filtros);
        return norte;
    }
 
    private JPanel crearCentro() {
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(Color.WHITE);
        String[] cols = {"Código", "Fecha", "Equipo", "Binomio", "Municipio", "Lugar",
                         "Horas", "Sobrevivientes", "Fallecidos"};
        modelo = new DefaultTableModel(cols, 0);
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(22);
        centro.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return centro;
    }
 
    private JPanel crearSur() {
        JPanel sur = new JPanel(new BorderLayout());
        sur.setBackground(Color.WHITE);
        sur.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        lblResumen = new JLabel(" ");
        JButton cerrar = new JButton("Cerrar");
        cerrar.setFocusPainted(false);
        cerrar.addActionListener(e -> dispose());
        sur.add(lblResumen, BorderLayout.CENTER);
        sur.add(cerrar, BorderLayout.EAST);
        return sur;
    }
 
    private void filtrar() {
        String equipo = (String) cmbEquipo.getSelectedItem();
        String municipio = (String) cmbMunicipio.getSelectedItem();
        if (equipo == null || equipo.equals(TODOS)) equipo = "";
        if (municipio == null || municipio.equals(TODOS)) municipio = "";
 
        ArrayList<Mision> lista = sistema.filtrarMisiones(equipo, municipio);
        llenar(lista);
 
        if (lista.isEmpty()) {
            lblResumen.setText("No hay misiones que cumplan los filtros.");
        } else {
            lblResumen.setText(lista.size() + " misión(es) encontrada(s).");
        }
    }
 
    private void limpiar() {
        cmbEquipo.setSelectedIndex(0);
        cmbMunicipio.setSelectedIndex(0);
        filtrar();
    }
 
    private void llenar(ArrayList<Mision> lista) {
        modelo.setRowCount(0);
        for (Mision m : lista) {
            Municipio mun = m.obtenerMunicipio();
            String lugar = (m.getEvento() != null && m.getEvento().getLugar() != null)
                    ? m.getEvento().getLugar().getNombre() : "-";
            modelo.addRow(new Object[]{
                m.getCodigo(),
                m.getFecha() != null ? m.getFecha().toString() : "-",
                m.getEquipo() != null ? m.getEquipo().getNombre() : "-",
                m.getBinomio() != null ? m.getBinomio().resumen() : "-",
                mun != null ? mun.getNombre() : "-",
                lugar,
                m.getHorasLabor(),
                m.getSobrevivientes(),
                m.getFallecidos()
            });
        }
    }
}