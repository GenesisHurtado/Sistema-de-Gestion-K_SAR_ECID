package Proyecto.Modelo;
import java.util.ArrayList;


/**
 * Write a description of class Lugar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lugar
{
  private String nombre;
  private String calle;
  private String avenida;
  private String numero;
  private ArrayList <Evento> eventos;

 public Lugar (){
     
 }
 
 
 public Lugar(String nombre, String calle, String avenida, String numero){
     this.nombre=nombre;
     this.calle=calle;
     this.avenida=avenida;
     this.numero=numero;
     this.eventos=new ArrayList<>();
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

    /**GET Method Propertie calle*/
    public String getCalle(){
        return this.calle;
    }//end method getCalle

    /**SET Method Propertie calle*/
    public void setCalle(String calle){
        this.calle = calle;
    }//end method setCalle

    /**GET Method Propertie avenida*/
    public String getAvenida(){
        return this.avenida;
    }//end method getAvenida

    /**SET Method Propertie avenida*/
    public void setAvenida(String avenida){
        this.avenida = avenida;
    }//end method setAvenida

    /**GET Method Propertie numero*/
    public String getNumero(){
        return this.numero;
    }//end method getNumero

    /**SET Method Propertie numero*/
    public void setNumero(String numero){
        this.numero = numero;
    }//end method setNumero

    /**GET Method Propertie eventos*/
    public java.util.ArrayList<Proyecto.Modelo.Evento> getEventos(){
        return this.eventos;
    }//end method getEventos

    /**SET Method Propertie eventos*/
    public void setEventos(java.util.ArrayList<Proyecto.Modelo.Evento> eventos){
        this.eventos = eventos;
    }//end method setEventos

//End GetterSetterExtension Source Code
    
 // Metodos Operacionales
    
    // hacer metodo mostrar informacion
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
               "Calle: " + calle+ "\n" +
               "Avenida" + avenida+ "\n" +
               "Numero "+ numero ;
    }

}//End class
