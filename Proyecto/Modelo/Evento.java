package Modelo;


/**
 * Write a description of class Evento here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Evento
{
    protected String descripcion ;
    protected Fecha fecha;
    protected Mision mision;
    
    
    public Evento(){
        
    }
    
    public Evento(String descripcion , Fecha fecha , Mision mision){
       this.descripcion=descripcion;
       this.fecha=fecha;
       this.mision=mision;
    }

//Start GetterSetterExtension Source Code

    /**GET Method Propertie descripcion*/
    public String getDescripcion(){
        return this.descripcion;
    }//end method getDescripcion

    /**SET Method Propertie descripcion*/
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }//end method setDescripcion

    /**GET Method Propertie fecha*/
    public Fecha getFecha(){
        return this.fecha;
    }//end method getFecha

    /**SET Method Propertie fecha*/
    public void setFecha(Fecha fecha){
        this.fecha = fecha;
    }//end method setFecha

    /**GET Method Propertie mision*/
    public Mision getMision(){
        return this.mision;
    }//end method getMision

    /**SET Method Propertie mision*/
    public void setMision(Mision mision){
        this.mision = mision;
    }//end method setMision

//End GetterSetterExtension Source Code
    // Metodos Operacionales
    
    // hacer metodo mostrar informacion
    
    @Override
    public String toString() {
        return "Descripción: " + descripcion +
               "Fecha: " + fecha+
               "Misión" + mision;
    }

}//End class