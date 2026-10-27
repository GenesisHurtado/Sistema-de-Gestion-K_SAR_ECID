package Proyecto.Modelo;


/**
 * Write a description of class Fecha here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fecha
{
       private int dia;
    private int mes;
    private int anio;
    
    
    public Fecha(){
        
    }
    
    public Fecha(int dia , int mes , int anio){
        this.dia=dia;
        this.mes=mes;
        this.anio=anio;
    }

//Start GetterSetterExtension Source Code

    /**GET Method Propertie dia*/
    public int getDia(){
        return this.dia;
    }//end method getDia

    /**SET Method Propertie dia*/
    public void setDia(int dia){
        this.dia = dia;
    }//end method setDia

    /**GET Method Propertie mes*/
    public int getMes(){
        return this.mes;
    }//end method getMes

    /**SET Method Propertie mes*/
    public void setMes(int mes){
        this.mes = mes;
    }//end method setMes

    /**GET Method Propertie anio*/
    public int getAnio(){
        return this.anio;
    }//end method getAnio

    /**SET Method Propertie anio*/
    public void setAnio(int anio){
        this.anio = anio;
    }//end method setAnio

//End GetterSetterExtension Source Code
  @Override
   public String toString() {
    return dia + "/" + mes + "/" + anio;
   }
 

}
