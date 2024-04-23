import java.util.ArrayList;
import java.util.Random;

public class Listas {
    private final ArrayList<Asiento> pendientes;
    private final ArrayList<Asiento> confirmadas;
    private final ArrayList<Asiento> canceladas;
    private final ArrayList<Asiento> verificadas;
    private final Random random;

    public Listas() {
        pendientes = new ArrayList<>();
        confirmadas = new ArrayList<>();
        canceladas = new ArrayList<>();
        verificadas = new ArrayList<>();
        random = new Random();
    }

    /**
     * Con este método se accede al array correspondiente
     */
    public synchronized ArrayList<Asiento> getRegPendientes(){
        return pendientes;
    }

    public synchronized ArrayList<Asiento> getRegConfirmadas(){
        return confirmadas;
    }

    public synchronized ArrayList<Asiento> getRegCanceladas(){
        return canceladas;
    }

    public synchronized ArrayList<Asiento> getRegVerificadas(){
        return verificadas;
    }

    /**
     * Estos métodos agregan un asiento al array correspondiente
     */
    public synchronized void registrarReserva(Asiento asiento){
        if(!pendientes.contains(asiento)) {
            pendientes.add(asiento);
            System.out.println(Thread.currentThread().getName() + " reservó el " + asiento.getNumero());
        }
    }

    public synchronized void registrarPago(Asiento asiento){
        if(!confirmadas.contains(asiento)) {
            confirmadas.add(asiento);
            System.out.println(Thread.currentThread().getName() + " pagó el " + asiento.getNumero());
        }
    }

    public synchronized void registrarCancelacion(Asiento asiento){
        if(!canceladas.contains(asiento) && !verificadas.contains(asiento)) {
            canceladas.add(asiento);
            System.out.println(Thread.currentThread().getName() + " canceló el " + asiento.getNumero());
        }
    }

    public synchronized void registrarVerificacion(Asiento asiento) {
        if (!verificadas.contains(asiento) && !canceladas.contains(asiento)) {
            verificadas.add(asiento);
            System.out.println(Thread.currentThread().getName() + " verificó el " + asiento.getNumero());
        }
    }


    /**
     * Este método elimina una asiento del array correspondiente
     */
    public synchronized void eliminarPendiente(Asiento asiento){
        pendientes.remove(asiento);
    }

    public synchronized void eliminarConfirmada(Asiento asiento){
        confirmadas.remove(asiento);
    }

    /**
     * Este método se utiliza para obtener una de las reservas de cualquiera
     * de las listas que necesite. Es decir de la lista que pase por parámetro
     */

    public synchronized Asiento getUnAsientoCualquiera(ArrayList<Asiento> registro){
        if(!registro.isEmpty()){
            int nroAleatorio = random.nextInt(registro.size());
            return registro.get(nroAleatorio);
        }
        return null;
    }

    public synchronized String info(){
        int nCancelados = 0;
        for(Asiento asiento: canceladas){
            nCancelados++;
        }
        int nVerificados = 0;
        for(Asiento asiento: verificadas){
            nVerificados++;
        }
        return "Verificados: " + nVerificados  + "\nCancelados: " + nCancelados;
    }

    public String printMatriz() {
        int totalOcupados = 0;
        for (Asiento asiento : verificadas) {
            totalOcupados++;
        }
        return " Ocupación final: " + totalOcupados + " asientos";
    }
}