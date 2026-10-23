package Modelo;
import java.util.ArrayList;


/**
 * Write a description of class Equipo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Equipo
{
    
    private String nombre;
    private Fecha fechaCreacion;
    private ArrayList <Binomio> binomios;
    private ArrayList <Mision> misiones ;

//Start GetterSetterExtension Source Code

    /**GET Method Propertie nombre*/
    public String getNombre(){
        return this.nombre;
    }//end method getNombre

    /**SET Method Propertie nombre*/
    public void setNombre(String nombre){
        this.nombre = nombre;
    }//end method setNombre

    /**GET Method Propertie fechaCreacion*/
    public Fecha getFechaCreacion(){
        return this.fechaCreacion;
    }//end method getFechaCreacion

    /**SET Method Propertie fechaCreacion*/
    public void setFechaCreacion(Fecha fechaCreacion){
        this.fechaCreacion = fechaCreacion;
    }//end method setFechaCreacion

    /**GET Method Propertie binomios*/
    public java.util.ArrayList<Modelo.Binomio> getBinomios(){
        return this.binomios;
    }//end method getBinomios

    /**SET Method Propertie binomios*/
    public void setBinomios(java.util.ArrayList<Modelo.Binomio> binomios){
        this.binomios = binomios;
    }//end method setBinomios

    /**GET Method Propertie misiones*/
    public java.util.ArrayList<Modelo.Mision> getMisiones(){
        return this.misiones;
    }//end method getMisiones

    /**SET Method Propertie misiones*/
    public void setMisiones(java.util.ArrayList<Modelo.Mision> misiones){
        this.misiones = misiones;
    }//end method setMisiones

//End GetterSetterExtension Source Code

    @Override
    public String toString() {
        return "Nombre : " + nombre +
               "Fecha Creación: " + fechaCreacion;
    }

}//End class