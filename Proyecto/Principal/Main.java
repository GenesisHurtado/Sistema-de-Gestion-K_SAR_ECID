package Proyecto.Principal;
import Proyecto.Modelo.SistemaKsar;
import Proyecto.Vista.JFKsar;
import javax.swing.SwingUtilities;


/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
    private static final boolean CARGAR_DATOS_PRUEBA = true;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaKsar sistema = new SistemaKsar();
            if (CARGAR_DATOS_PRUEBA) {
                DatosPrueba.cargar(sistema);
            }
            new JFKsar(sistema);
        });
    }
}