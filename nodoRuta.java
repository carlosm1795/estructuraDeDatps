
package vagones;


public class nodoRuta {
    private int iDRuta;
    private String nombreRuta;
    private int km;
    private nodoRuta siguiente;
    
    public nodoRuta(int piDRuta,String pnombreRuta,int pkm){
        iDRuta = piDRuta;
        nombreRuta = pnombreRuta;
        km = pkm;
        siguiente = null;
    }
    
    public int getiDRuta(){
        return iDRuta;
    }
    public String getnombreRuta(){
        return nombreRuta;
    }
    public int getkm(){
        return km;
    }
    public nodoRuta ObtenerNodoRutaSiguiente(){
        return siguiente;
    }
    public void agregarNuevo(nodoRuta pNuevo){
	siguiente = pNuevo;
    }
}
