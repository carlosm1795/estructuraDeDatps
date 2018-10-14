/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vagones;

import java.util.*;
public class Vagones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listaVagones milista = new listaVagones();
        milista.agregarNodo(1, 50, 100);
        milista.agregarNodo(2, 50, 100);
        milista.agregarNodo(3, 50, 100);
        milista.agregarNodo(4, 50, 100);
        milista.agregarNodo(5, 50, 100);
        milista.agregarRutas(1, "Liberia - Monteverde", 83);
        milista.agregarRutas(2,"Monteverde - Alajuela",42);
        milista.agregarRutas(3,"Alajuela - San Jose",18);
        milista.agregarRutas(4,"San Jose - Cartago",26);
        
        
        while(true){
            
            Scanner in = new Scanner(System.in);  
            System.out.print("Agegar una tecla para continuar:");    
            String name = in.next();   
            milista.clearConsole();
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Gasolina Actual:"+milista.retornarGasolina());
            System.out.println(milista.retornarInfoRutaActual());
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            milista.mostarVagones();
            milista.validarRuta();
            milista.GenerarAleatoridad();
            System.out.println("**********************************");
            milista.mostarVagones();
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }
        
        
        
        
    }
    
}
