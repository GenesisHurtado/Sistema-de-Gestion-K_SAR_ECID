package Proyecto.Modelo;
import java.util.ArrayList;


/**
 * Write a description of class Binomio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Binomio
{
    
    private boolean estado;
    private int cantidadMisiones;
    private Guia guia;
    private Perro perro;
    private ArrayList <Mision> misiones;
    
    public Binomio(){
        
    
    }
    
    public Binomio(boolean estado , int cantidadMisiones, Guia guia , Perro perro ){
        this.estado=estado;
        this.cantidadMisiones=cantidadMisiones;
        this:guia=guia;
        this.perro=perro;
        this.misiones=new ArrayList<>();
    }

//Start GetterSetterExtension Source Code

    /**GET Method Propertie estado*/
    public boolean getEstado(){
        return this.estado;
    }//end method getEstado

    /**SET Method Propertie estado*/
    public void setEstado(boolean estado){
        this.estado = estado;
    }//end method setEstado

    /**GET Method Propertie cantidadMisiones*/
    public int getCantidadMisiones(){
        return this.cantidadMisiones;
    }//end method getCantidadMisiones

    /**SET Method Propertie cantidadMisiones*/
    public void setCantidadMisiones(int cantidadMisiones){
        this.cantidadMisiones = cantidadMisiones;
    }//end method setCantidadMisiones

    /**GET Method Propertie guia*/
    public Guia getGuia(){
        return this.guia;
    }//end method getGuia

    /**SET Method Propertie guia*/
    public void setGuia(Guia guia){
        this.guia = guia;
    }//end method setGuia

    /**GET Method Propertie perro*/
    public Perro getPerro(){
        return this.perro;
    }//end method getPerro

    /**SET Method Propertie perro*/
    public void setPerro(Perro perro){
        this.perro = perro;
    }//end method setPerro

    /**GET Method Propertie misiones*/
    public java.util.ArrayList<Modelo.Mision> getMisiones(){
        return this.misiones;
    }//end method getMisiones

    /**SET Method Propertie misiones*/
    public void setMisiones(java.util.ArrayList<Modelo.Mision> misiones){
        this.misiones = misiones;
    }//end method setMisiones

//End GetterSetterExtension Source Code
     
    // Metodos Operacionales
    
    // hacer metodos verificacion certificacion , actualizar estado, , incrementar misiones
    
    @Override
    public String toString() {
        return "Estado: " + estado +
               "Cantidad Misiones: " + cantidadMisiones+
               "Guia: " + guia+
               "Perro :"+ perro;
               
    }

}//End class
