package Proyecto;
import Proyecto.Modelo.*;
import Proyecto.Vista.*;
import Proyecto.Controlador.*;

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
      private static final boolean CARGAR_DATOS_PRUEBA = true;

    public static void main(String[] args){
        SistemaKsar modelo = new SistemaKsar();
        if(CARGAR_DATOS_PRUEBA){
            DatosPrueba.cargar(modelo);
        }
        JFKsar vista = new JFKsar();
        Control control = new Control(modelo, vista);
        vista.setVisible(true);
    }
}