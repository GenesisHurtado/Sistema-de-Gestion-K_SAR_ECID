package Proyecto.Modelo;


/**
 * Misión. Andrés agregó el atributo evento (bidireccional) y calcularDesempeno().
 */
public class Mision
{
    private String codigo;
    private double horasLabor;
    private int sobrevivientes;
    private int fallecidos;
    private Fecha fecha;
    private Evento evento;
    
    
    
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

    /**GET Method Propertie evento*/
    public Evento getEvento(){
        return this.evento;
    }//end method getEvento

    /**SET Method Propertie evento*/
    public void setEvento(Evento evento){
        this.evento = evento;
    }//end method setEvento

    
//End GetterSetterExtension Source Code
    // Metodos Operacionales (Andrés)

    /** RF6 - Desempeño puntual. Entero truncado. */
    public int calcularDesempeno() {
        int base = (sobrevivientes * 10) + (int) horasLabor;
        int extra = 0;
        if (evento instanceof Terremoto) {
            extra = (int) (((Terremoto) evento).getMagnitud() * 5);
        } else if (evento instanceof Deslave) {
            extra = ((Deslave) evento).getVolumenDesplazado() / 100;
        }
        return base + extra;
    }
    
    @Override
    public String toString() {
        return "Código: " + codigo+ "\n" +
               "Horas de Labor: " + horasLabor + "\n" +
               "Sobrevivientes  : "+ sobrevivientes+ "\n" +
               "Fallecidos : " + fallecidos+ "\n" +
               "Fecha "+ fecha+ "\n" +
               "Evento: "+ (evento != null ? evento.getDescripcion() : "ninguno");
              
               
    }


}//End class
