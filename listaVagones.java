
package vagones;
import java.util.Random;
import java.util.Scanner;

public class listaVagones {
    
    
    private nodoRuta primerNodoRutas;
    private nodoRuta ultimoNodoRutas;
    private nodoVagon primerNodo;
    private nodoVagon ultimoNodo;
    private int idRutaActual = 1;
    private int direccion = 1; //1 origen fin 2 fin to origen
    private int gasolina = 1500;
    private int cantidadMaximaGasolina = 1500;
    
    public listaVagones(){
        primerNodo = ultimoNodo = null;
    }
    public void agregarRutas(int piD,String pnombreRuta,int pkm){
        if(primerNodoRutas == null){
            primerNodoRutas = new nodoRuta(piD,pnombreRuta,pkm);
            ultimoNodoRutas = primerNodoRutas;
        }else{
            nodoRuta nodoTemp = primerNodoRutas;
            while(nodoTemp.ObtenerNodoRutaSiguiente() != null){
                nodoTemp = nodoTemp.ObtenerNodoRutaSiguiente();
            }
            nodoRuta nuevoNodo = new nodoRuta(piD,pnombreRuta,pkm);
            nodoTemp.agregarNuevo(nuevoNodo);
            ultimoNodoRutas = nuevoNodo;
        }
        
    }
    
    public void mostarRutas(){
        nodoRuta nodoTemp = primerNodoRutas;
        while(nodoTemp.ObtenerNodoRutaSiguiente() != null){
            System.out.println("Id De la ruta:"+ nodoTemp.getiDRuta());
            System.out.println("Nombre De la ruta:"+ nodoTemp.getnombreRuta());
            nodoTemp = nodoTemp.ObtenerNodoRutaSiguiente();
        }
        if(nodoTemp.ObtenerNodoRutaSiguiente() == null){
            System.out.println("Id De la ruta:"+ nodoTemp.getiDRuta());
            System.out.println("Nombre De la ruta:"+ nodoTemp.getnombreRuta());
        }
    }
    public void agregarNodo(int pidVagon,int pcapacidadEnUso,int pcapacidad){
        if(primerNodo == null){
            primerNodo = new nodoVagon(pidVagon,pcapacidadEnUso,pcapacidad);
            ultimoNodo = primerNodo;
        }else{
            nodoVagon nodoTemp = primerNodo;
            while(nodoTemp.obtenerNodoSiguiente() != null){
                nodoTemp = nodoTemp.obtenerNodoSiguiente();
            }
            //Crea un nuevo nodo con los datos recibidos
            nodoVagon nuevoNodo = new nodoVagon(pidVagon,pcapacidadEnUso,pcapacidad);
            //Agrega el nodo a la ultima posicion de la lista.
            nodoTemp.agregarNuevo(nuevoNodo);
            ultimoNodo = nuevoNodo;
        }
    }
    public void validarRuta(){
        // Verificamos la direccion del vagon y se comprueba que se tengan la cantidad de gasolina correcta para procesar el pedido
        boolean requerimientosCompletados = false;
        if(direccion == 1){
            //Verificamos si la direccion es de origen a fin
            while(requerimientosCompletados == false){
                requerimientosCompletados = validarGasolina(idRutaActual);
            }
            if(idRutaActual<4){
                cambiarRuta(idRutaActual+1);
            }else{
                direccion = 2;
                cambiarRuta(idRutaActual-1);
            }
        }else{
            // Verificamos si la direccion es de fin a origen
            if(idRutaActual>1){
                while(requerimientosCompletados == false){
                    requerimientosCompletados = validarGasolina(idRutaActual-1);
                }
                cambiarRuta(idRutaActual-1);
            }else{
                while(requerimientosCompletados == false){
                    requerimientosCompletados = validarGasolina(idRutaActual+1);
                }
                direccion = 1;
                cambiarRuta(idRutaActual+1);
            }
        }
        
        
    }
    
    public boolean validarGasolina(int pidRutaDestino){
        //Obtener el nodo con la ruta a verificar
        Scanner sc = new Scanner(System.in);
        boolean cantidadDeGasolinaCorrecta = false;
        nodoRuta nodoRutaDestino = obtenerRutaByID(pidRutaDestino);
        if(gasolina>= nodoRutaDestino.getkm()){
            restarGasolina(nodoRutaDestino.getkm());
            return true;
        }else{
            System.out.println("Desafortunadamente no se tiene la cantidad de gasolina necesaria por favor ingrese la cantidad de gasolina a depositar:");
            int gasolinaExtra = sc.nextInt();
            while(cantidadDeGasolinaCorrecta == false){
                if(gasolinaExtra + gasolina <= cantidadMaximaGasolina){
                    cantidadDeGasolinaCorrecta = true;
                    sumarGasolina(gasolinaExtra);
                }else{
                    System.out.println("Lo sentimos pero el maximo de gasolina permitido a depositar en este momento es de:"+ (cantidadMaximaGasolina-gasolina));
                    System.out.println("Desafortunadamente no se tiene la cantidad de gasolina necesaria por favor ingrese la cantidad de gasolina a depositar:");
                    gasolinaExtra = sc.nextInt();
                }
            }
            
            return false;
        }
    }
    public nodoRuta obtenerRutaByID(int pId){
        nodoRuta nodoTemp = primerNodoRutas;
        while(nodoTemp.ObtenerNodoRutaSiguiente() != null){
            if(nodoTemp.getiDRuta() == pId){
                return nodoTemp;
            }else{
                nodoTemp = nodoTemp.ObtenerNodoRutaSiguiente();
            }
        }
        if(ultimoNodoRutas.getiDRuta() == pId){
            return ultimoNodoRutas;
        }
        return null;
        
    }
    public void cambiarDireccion(){
        if (direccion==1){
            direccion = 2;
        }else{
            direccion = 1;
        }
    }
    public void restarGasolina(int pGasolina){
        gasolina -= pGasolina;
    }
    public void sumarGasolina(int pGasolina){
        gasolina += pGasolina;
    }
    public void cambiarRuta(int pRuta){
        idRutaActual = pRuta;
    }
    
    
    public int generarRandomIdVagon(){
        Random rand = new Random();
        int  idVagon = rand.nextInt(5) + 1;
        return idVagon;
    }
    public nodoVagon buscarVagonPorId(int pIdVagon){
        nodoVagon nodoTemp = primerNodo;
        while(nodoTemp.obtenerNodoSiguiente() != null){
            if(nodoTemp.getidVagon() == pIdVagon){
                return nodoTemp;
            }else{
                nodoTemp = nodoTemp.obtenerNodoSiguiente();
            }
        }
        if(ultimoNodo.getidVagon() == pIdVagon){
            return ultimoNodo;
        }
        return null;
    }
    public int randomCajas(nodoVagon pnodoVagon){
        //Funcion que crea cierta cantidad de cajas dependiendo de la cantidad de cajas disponibles en el vagon
        int cantidadDisponible = pnodoVagon.obtenercapacidad() - pnodoVagon.obtenercapacidadEnUso();
        Random rand = new Random();
        int  randomCajas = 0;
        if(cantidadDisponible <= 0){
            randomCajas = rand.nextInt(100) + 1;
        }else{
            randomCajas = rand.nextInt(cantidadDisponible) + 1;
        }
        
        return randomCajas;
        
    }
    public int randomCajasParaVaciar(nodoVagon pnodoVagon){
        // Funcion que va a generar un numero de cajas aleatorio para restar
        Random rand = new Random();
        int randomCajas = 0;
        int capacidadEnUso = pnodoVagon.obtenercapacidadEnUso();
        
        if (capacidadEnUso != 0){
            randomCajas = rand.nextInt(capacidadEnUso) + 1;
        }else{
            System.out.println("Intentamos Restar algun producto al vagon:"+pnodoVagon.getidVagon()+", sin embargo no tenia ningun producto.");
        }
        
        return randomCajas;
    }
    public void generarRandomInsertCajas(){
        int idVagon = generarRandomIdVagon();
        nodoVagon nodoTemp = buscarVagonPorId(idVagon);
        if (nodoTemp == null){
            
        }else{
            int numeroDeCajasAInsertar = randomCajas(nodoTemp);
        nodoTemp.llenarVagon(numeroDeCajasAInsertar);
        }
        
        
    }
    public void generarRandomVaciarCajas(){
        int idVagon = generarRandomIdVagon();
        nodoVagon nodoTemp = buscarVagonPorId(idVagon);
        int numeroDeCajasARestar= randomCajasParaVaciar(nodoTemp);
        nodoTemp.vaciarVagon(numeroDeCajasARestar);
    }
    public void mostarVagones(){
        nodoVagon nodoTemp = primerNodo;
        while(nodoTemp.obtenerNodoSiguiente() != null){
            System.out.println("Id Del nodo:"+ nodoTemp.getidVagon());
            System.out.println("Cantidad de cajas en uso:"+ nodoTemp.obtenercapacidadEnUso());
            nodoTemp = nodoTemp.obtenerNodoSiguiente();
        }
        if(nodoTemp.obtenerNodoSiguiente() == null){
            System.out.println("Id Del nodo:"+ nodoTemp.getidVagon());
            System.out.println("Cantidad de cajas en uso:"+ nodoTemp.obtenercapacidadEnUso());
        }
    }
    public void GenerarAleatoridad(){
        //1- Solo llenarVagon
        //2 Solo Vaciar Vagon
        //3 llenar y vaciar vagon
        Random rand = new Random();
        int  randomCajas = rand.nextInt(3) + 1;
        switch (randomCajas){
            case 1:
                generarRandomInsertCajas();
                break;
            case 2:
                generarRandomVaciarCajas();
                break;
            case 3:
                generarRandomInsertCajas();
                generarRandomVaciarCajas();
                break;
        }
    }
    
    public int retornarGasolina(){
        return gasolina;
    }
    
    
    public String retornarInfoRutaActual(){
        nodoRuta nodoTemp = obtenerRutaByID(idRutaActual);
        String msg = "";
        msg = "ID ruta:" + nodoTemp.getiDRuta() + ", nombre de Ruta:" + nodoTemp.getnombreRuta();
        return msg;
    }
    
    public void clearConsole(){
        //Funcion para limpiar la consola
        for(int x = 0; x <= 50; x++){
            System.out.println();
        }
    }
    
    
}
