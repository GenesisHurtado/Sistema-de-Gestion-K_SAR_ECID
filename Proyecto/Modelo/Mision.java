package Proyecto.Modelo;


/**
 * Write a description of class Mision here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mision
{
    private String codigo;
    private double horasLabor;
    private int sobrevivientes;
    private int fallecidos;
    private Fecha fecha;
    
    
    
    public Mision(){
        
    }
    
     public Mision(String codigo, double horasLabor , int sobrevivientes , int fallecidos, Fecha fecha ){
     this.codigo=codigo;
     this.horasLabor=horasLabor;
     this.sobrevivientes=sobrevivientes;
     this.fecha=fecha;
    
     
     
    }
    
    
    

//Start GetterSetterExtension Source Code

    /**GET Method Propertie codigo*/
    public String getCodigo(){
        return this.codigo;
    }//end method getCodigo

    /**SET Method Propertie codigo*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }//end method setCodigo

    /**GET Method Propertie horasLabor*/
    public double getHorasLabor(){
        return this.horasLabor;
    }//end method getHorasLabor

    /**SET Method Propertie horasLabor*/
    public void setHorasLabor(double horasLabor){
        this.horasLabor = horasLabor;
    }//end method setHorasLabor

    /**GET Method Propertie sobrevivientes*/
    public int getSobrevivientes(){
        return this.sobrevivientes;
    }//end method getSobrevivientes

    /**SET Method Propertie sobrevivientes*/
    public void setSobrevivientes(int sobrevivientes){
        this.sobrevivientes = sobrevivientes;
    }//end method setSobrevivientes

    /**GET Method Propertie fallecidos*/
    public int getFallecidos(){
        return this.fallecidos;
    }//end method getFallecidos

    /**SET Method Propertie fallecidos*/
    public void setFallecidos(int fallecidos){
        this.fallecidos = fallecidos;
    }//end method setFallecidos

    /**GET Method Propertie fecha*/
    public Fecha getFecha(){
        return this.fecha;
    }//end method getFecha

    /**SET Method Propertie fecha*/
    public void setFecha(Fecha fecha){
        this.fecha = fecha;
    }//end method setFecha

    
//End GetterSetterExtension Source Code
    // Metodos Operacionales
    
    // hacer metodo calcularDesempeño esDeMunicipio
    
    @Override
    public String toString() {
        return "Código: " + codigo+
               "Horas de Labor: " + horasLabor +
               "Sobrevivientes  : "+ sobrevivientes+
               "Fallecidos : " + fallecidos+
               "Fecha "+ fecha;
              
               
    }

}//End class
