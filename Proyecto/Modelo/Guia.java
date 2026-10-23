package Modelo;


/**
 * Write a description of class Guia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Guia
{
    private String cedula ;
    private String nombreCompleto;
    private Certificacion certificacion;
    
    public Guia(){
        
    }
    
    public Guia(String cedula , String nombreCompleto , Certificacion certificacion){
        this.cedula=cedula;
        this.nombreCompleto=nombreCompleto;
        this.certificacion=certificacion;
    }
    
    

//Start GetterSetterExtension Source Code

    /**GET Method Propertie cedula*/
    public String getCedula(){
        return this.cedula;
    }//end method getCedula

    /**SET Method Propertie cedula*/
    public void setCedula(String cedula){
        this.cedula = cedula;
    }//end method setCedula

    /**GET Method Propertie nombreCompleto*/
    public String getNombreCompleto(){
        return this.nombreCompleto;
    }//end method getNombreCompleto

    /**SET Method Propertie nombreCompleto*/
    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto = nombreCompleto;
    }//end method setNombreCompleto

    /**GET Method Propertie certificacion*/
    public Certificacion getCertificacion(){
        return this.certificacion;
    }//end method getCertificacion

    /**SET Method Propertie certificacion*/
    public void setCertificacion(Certificacion certificacion){
        this.certificacion = certificacion;
    }//end method setCertificacion

//End GetterSetterExtension Source Code
    // Metodos Operacionales
    
    // hacer metodo estadoCertificado
    
    @Override
    public String toString() {
        return "Cedula: " + cedula+
               "Nombre Completo : " + nombreCompleto +
               "Certificación :"+ certificacion;
               
    }

}//End class