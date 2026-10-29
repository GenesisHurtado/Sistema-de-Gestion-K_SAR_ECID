package Proyecto.Modelo;
import java.util.ArrayList;

/**
 * Contenedora del sistema K-SAR.
 * Andrés: registrarGuia, registrarPerro, armarBinomio (RF1),
 * actualizarCertificacionGuia/Perro (RF4), filtrarPorEquipo y listarMisiones (RF5).
 * La contenedora llama a los métodos de cada clase; no contiene su lógica.
 */
public class SistemaKsar
{
    private ArrayList<Equipo> equipos;
    private ArrayList<Guia> guias;
    private ArrayList<Perro> perros;
    private ArrayList<Binomio> binomios;
    private ArrayList<Evento> eventos;
    private ArrayList<Mision> misiones;
    private ArrayList<Pais> paises;

    private int consecutivoPerro;
    private int consecutivoMision;

    public SistemaKsar() {
        this.equipos = new ArrayList<>();
        this.guias = new ArrayList<>();
        this.perros = new ArrayList<>();
        this.binomios = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.misiones = new ArrayList<>();
        this.paises = new ArrayList<>();
        this.consecutivoPerro = 0;
        this.consecutivoMision = 0;
    }

    // ==================== AUXILIARES (Andrés) ====================

    public Guia buscarGuia(String cedula) {
        for (Guia g : guias) {
            if (g.getCedula().equals(cedula)) return g;
        }
        return null;
    }

    public Perro buscarPerro(String codigo) {
        for (Perro p : perros) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    public Equipo buscarEquipo(String nombre) {
        for (Equipo e : equipos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        }
        return null;
    }

    private String generarCodigoPerro() {
        consecutivoPerro++;
        return String.format("CAN-%03d", consecutivoPerro);
    }

    // ==================== REGISTROS RF1 (Andrés) ====================

    /** RF1 - Registrar guía. Devuelve mensaje de resultado. */
    public String registrarGuia(String cedula, String nombre, Certificacion cert) {
        if (cedula == null || !cedula.matches("\\d+")) {
            return "La cédula debe tener solo números.";
        }
        if (buscarGuia(cedula) != null) {
            return "Ya existe un guía con esa cédula.";
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            return "El nombre es obligatorio.";
        }
        guias.add(new Guia(cedula, nombre.trim(), cert));
        return "Guía registrado correctamente.";
    }

    /** RF1 - Registrar perro. Código automático CAN-###. */
    public String registrarPerro(String nombre, String descripcion, String raza, int edad, Certificacion cert) {
        if (nombre == null || nombre.trim().isEmpty() || raza == null || raza.trim().isEmpty()) {
            return "Nombre y raza son obligatorios.";
        }
        if (edad < 0) {
            return "La edad debe ser mayor o igual a 0.";
        }
        String codigo = generarCodigoPerro();
        perros.add(new Perro(codigo, nombre.trim(), descripcion, raza.trim(), edad, cert));
        return "Perro registrado. Código: " + codigo;
    }

    /** RF1 - Armar binomio. Solo guía y perro sin binomio. */
    public String armarBinomio(String cedulaGuia, String codigoPerro, String nombreEquipo) {
        Guia guia = buscarGuia(cedulaGuia);
        Perro perro = buscarPerro(codigoPerro);
        Equipo equipo = buscarEquipo(nombreEquipo);
        if (guia == null) return "El guía no existe.";
        if (perro == null) return "El perro no existe.";
        if (equipo == null) return "El equipo no existe.";
        if (guia.getBinomio() != null) return "Ese guía ya tiene binomio.";
        if (perro.getBinomio() != null) return "Ese perro ya tiene binomio.";

        boolean disponible = guia.estaVigente() && perro.estaVigente();
        Binomio b = new Binomio(disponible, 0, guia, perro);
        b.setEquipo(equipo);
        b.actualizarEstado();
        guia.setBinomio(b);
        perro.setBinomio(b);
        equipo.getBinomios().add(b);
        binomios.add(b);
        return disponible ? "Binomio armado y disponible." : "Binomio armado (certificación no vigente).";
    }

    // ==================== ACTUALIZACIÓN RF4 (Andrés) ====================

    /** RF4 - Actualizar certificación del guía (Director). */
    public String actualizarCertificacionGuia(String cedula, Certificacion nueva) {
        Guia g = buscarGuia(cedula);
        if (g == null) return "No se encontró el guía.";
        g.setCertificacion(nueva);
        return "Certificación del guía actualizada.";
    }

    /** RF4 - Actualizar certificación del perro (Médico veterinario). */
    public String actualizarCertificacionPerro(String codigo, Certificacion nueva) {
        Perro p = buscarPerro(codigo);
        if (p == null) return "No se encontró el perro.";
        p.setCertificacion(nueva);
        return "Certificación del perro actualizada.";
    }

    // ==================== FILTROS RF5 (Andrés) ====================

    /** RF5 - Filtrar misiones por equipo. */
    public ArrayList<Mision> filtrarPorEquipo(String nombreEquipo) {
        ArrayList<Mision> resultado = new ArrayList<>();
        Equipo eq = buscarEquipo(nombreEquipo);
        if (eq == null) return resultado;
        for (Binomio b : eq.getBinomios()) {
            if (b.getMisiones() != null) {
                resultado.addAll(b.getMisiones());
            }
        }
        return resultado;
    }

    /** RF5 - Todas las misiones (base del historial). */
    public ArrayList<Mision> listarMisiones() {
        return misiones;
    }

    // getters de listas (para llenar combos en la vista)
    public ArrayList<Guia> getGuias() { return guias; }
    public ArrayList<Perro> getPerros() { return perros; }
    public ArrayList<Equipo> getEquipos() { return equipos; }
    public ArrayList<Binomio> getBinomios() { return binomios; }
}
