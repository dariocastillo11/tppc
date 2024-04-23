import java.util.ArrayList;
import java.util.Random;
public class Avion {
    private final ArrayList<Asiento> asientos;
    private final Random random;

    public Avion() {
        random = new Random();
        asientos = new ArrayList<>();
        for (int i = 0; i < 186; i++) {
            Asiento asiento = new Asiento(i);
            asientos.add(asiento);
        }
    }


    public synchronized void setearAsiento(Asiento asiento, String estado) {
        if(asiento != null) {
            asiento.setEstado(estado);
            asientos.set(asiento.getNumero(), asiento);
        }
    }


    public synchronized Asiento getUnAsiento() {
        return asientos.get(random.nextInt(asientos.size()));
    }
}