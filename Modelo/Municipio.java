package Modelo;
import java.util.ArrayList;


/**
 * Write a description of class Municipio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Municipio
{
    private String nombre;
    private ArrayList <Lugar> lugares;
    
    public Municipio(){
        
    }
    
    public Municipio(String nombre){
        this.nombre=nombre;
        this.lugares=new ArrayList<>();
    }
    
    

//Start GetterSetterExtension Source Code

    /**GET Method Propertie nombre*/
    public String getNombre(){
        return this.nombre;
    }//end method getNombre

    /**SET Method Propertie nombre*/
    public void setNombre(String nombre){
        this.nombre = nombre;
    }//end method setNombre

    /**GET Method Propertie lugares*/
    public java.util.ArrayList<Modelo.Lugar> getLugares(){
        return this.lugares;
    }//end method getLugares

    /**SET Method Propertie lugares*/
    public void setLugares(java.util.ArrayList<Modelo.Lugar> lugares){
        this.lugares = lugares;
    }//end method setLugares

//End GetterSetterExtension Source Code

    // Metodos Operacionales
    
    // Obtener Descripcion
 @Override
    public String toString() {
        return "Municipio: " + nombre ;
    }

}//End class