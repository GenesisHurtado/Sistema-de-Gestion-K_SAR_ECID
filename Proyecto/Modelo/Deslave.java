package Proyecto.Modelo;


/**
 * Write a description of class Deslave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deslave extends Evento
{
    
    private int volumenDesplazado;
    private String zonaAfectada;
    
    
    public Deslave(){
        
    }
    
    public Deslave(String descripcion , Fecha fecha , Mision mision , int volumenDesplazado, String zonaAfectada){
     super(descripcion,fecha,mision);
     this.volumenDesplazado=volumenDesplazado;
     this.zonaAfectada=zonaAfectada;
     
    }

//Start GetterSetterExtension Source Code

    /**GET Method Propertie volumenDesplazado*/
    public int getVolumenDesplazado(){
        return this.volumenDesplazado;
    }//end method getVolumenDesplazado

    /**SET Method Propertie volumenDesplazado*/
    public void setVolumenDesplazado(int volumenDesplazado){
        this.volumenDesplazado = volumenDesplazado;
    }//end method setVolumenDesplazado

    /**GET Method Propertie zonaAfectada*/
    public String getZonaAfectada(){
        return this.zonaAfectada;
    }//end method getZonaAfectada

    /**SET Method Propertie zonaAfectada*/
    public void setZonaAfectada(String zonaAfectada){
        this.zonaAfectada = zonaAfectada;
    }//end method setZonaAfectada

//End GetterSetterExtension Source Code

     // Metodos operacionales
     // calcular aporte
    @Override
public String toString() {
    
    return super.toString() + 
           "Volumen Desplazado : " + this.volumenDesplazado + 
           " Zona Afectada: " + this.zonaAfectada;
}
}//End class
