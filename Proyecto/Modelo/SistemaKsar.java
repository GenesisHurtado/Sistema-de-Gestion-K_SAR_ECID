package Proyecto.Modelo;
import java.util.ArrayList;


/**
 * Write a description of class SistemaKsar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    
    // ==================== MÉTODOS AUXILIARES (privados) ====================
    // Generar código de perro (CAN-###) y de misión (DES-###)
    // Buscar por clave: equipo por nombre, guía por cédula, perro por código,
    //                   misión por código, país por código
    // (devuelven el objeto o null si no existe)
     
    
    
    // ==================== REGISTROS (RF1) ====================
    // Fase 1: equipo, guía, perro
    // Fase 2: armar binomio
    // Fase 3: evento (con país, municipio y lugar)
    // Fase 4: misión
     
    
    
     // ==================== CONSULTAS (RF2, RF3) ====================
    
     
     
    // ==================== ACTUALIZACIÓN (RF4) ====================
     
    
    
    
    // ==================== FILTROS (RF5) ====================
 
}