import java.util.ArrayList;
import java.lang.Thread;
import java.util.Random;

public class Proceso4Verif implements Runnable{
    private Random random;
    private Listas listas;
    private Avion avion;

    public Proceso4Verif(Avion avion, Listas listas){
        this.listas= listas;
        this.avion = avion;
        random = new Random();
    }

    @Override
    public void run(){
        while(true) {
            Asiento asiento = listas.getUnAsientoCualquiera(listas.getRegConfirmadas());
            if(asiento != null) {
                if (asiento.getEstado().equals("checked")) {
                    listas.eliminarConfirmada(asiento);
                    listas.registrarVerificacion(asiento);
                }
            }

            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}