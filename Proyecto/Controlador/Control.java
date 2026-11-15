package Proyecto.Controlador;
import Proyecto.Modelo.*;
import Proyecto.Vista.*;
import java.awt.event.*;

/**
 * Write a description of class Control here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Control
{
      private SistemaKsar modeloSistema;
      private JFKsar vistaKsar;
      private JFRegistrarPerro formularioRegistrarPerro;
      private JFRegistrarGuia  formularioRegistrarGuia;
      private JFRegistrarEquipo formularioRegistrarEquipo;
      private JFArmarBinomio formularioArmarBinomio;
      private JFResgistrarEvento formularioRegistrarEvento;
      private JFRegistrarMision formularioRegistrarMision;
      private JFListarGuia_Perro  formularioListarGuiaPerro;
      private JFFiltrarMision formularioFiltrarMision;
      private JFMayorDesempeño formularioMayorDesempeno;
      
      
      public Control (SistemaKsar modeloSistema, JFKsar vistaKsar , JFRegistrarPerro formularioRegistrarPerro,
      JFRegistrarGuia  formularioRegistrarGuia,JFRegistrarEquipo formularioRegistrarEquipo, JFArmarBinomio formularioArmarBinomio,
       JFResgistrarEvento formularioRegistrarEvento,JFRegistrarMision formularioRegistrarMision,JFListarGuia_Perro  formularioListarGuiaPerro,
       JFFiltrarMision formularioFiltrarMision,JFMayorDesempeño formularioMayorDesempeno){
        
        this.modeloSistema = modeloSistema;   
        this.vistaKsar = vistaKsar;
        //formularioRegistrarPerro = new JFRegistrarPerro () ;  
           
      }
}