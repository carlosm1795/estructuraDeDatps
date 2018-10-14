
package vagones;

public class nodoVagon {
    private int idVagon;
    private int capacidadEnUso;
    private int capacidad;
    private nodoVagon siguiente;
    
    public nodoVagon(int pidVagon,int pcapacidadEnUso,int pcapacidad){
		idVagon = pidVagon;
                capacidadEnUso = pcapacidadEnUso;
                capacidad = pcapacidad;
		siguiente = null;
	}
    public void setCapacidadEnUso(int pCantidad){
        capacidadEnUso = pCantidad;
    }
    public int getidVagon(){
        return idVagon;
    }
    public void agregarNuevo(nodoVagon pNuevo){
	siguiente = pNuevo;
    }
    public void llenarVagon(int pValor){
        capacidadEnUso += pValor;
    }
    public void vaciarVagon(int pValor){
        capacidadEnUso -= pValor;
    }
    public nodoVagon obtenerNodoSiguiente(){
        return siguiente;
    }
    public int obtenercapacidadEnUso(){
        return capacidadEnUso;
    }
    public int obtenercapacidad(){
        return capacidad;
    }
}
