package alertas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
	private String nombre;
    private List<String> temasInteres;
    private List<Alerta> alertas;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.temasInteres = new ArrayList<>();
        this.alertas = new ArrayList<>();
    }

    public void recibirAlerta(Alerta alerta) {
        alertas.add(alerta);
    }

    public List<Alerta> obtenerAlertasNoLeidas() {
        List<Alerta> noLeidas = new ArrayList<>();
        for (Alerta alerta : alertas) {
            if (!alerta.isLeida() && !alerta.isExpirada()) {
                noLeidas.add(alerta);
            }
        }
        Collections.sort(noLeidas, (a1, a2) -> {
            if (a1.getTipo() == a2.getTipo()) {
                return a2.getFechaExpiracion().compareTo(a1.getFechaExpiracion());
            } else {
                return a1.getTipo().compareTo(a2.getTipo());
            }
        });
        return noLeidas;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

	public List<String> getTemasInteres() {
		return temasInteres;
	}
}