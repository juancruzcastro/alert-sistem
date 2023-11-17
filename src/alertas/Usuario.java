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
    
    public Usuario() {}
    
    public void agregarTemaInteres(String tema) {
        temasInteres.add(tema);
    }
    
    public boolean es(String usuario) {
		if(this.nombre.equals(usuario))
			return true;
		return false;
	}

    public void recibirAlerta(Alerta alerta) {
        alertas.add(alerta);
    }
    
    public String getNombre() {
		return this.nombre;
	}

    public List<Alerta> obtenerAlertasNoLeidas() {
        List<Alerta> noLeidasNoExpiradas = new ArrayList<>();
        for (Alerta alerta : alertas) {
            if (!alerta.isLeida() && !alerta.isExpirada()) {
            	if(alerta.isParaTodos()) {
            		if(this.getTemasInteres().contains(alerta.getTema())) {
            			noLeidasNoExpiradas.add(alerta);
            		}
            	} else {
            		noLeidasNoExpiradas.add(alerta);
            	}
            }
        }
        ordenarAlertas(noLeidasNoExpiradas);  // Aplicar el ordenamiento
        return noLeidasNoExpiradas;
    }

    public List<Alerta> getAlertas() {
        return this.alertas;
    }

	public List<String> getTemasInteres() {
		return this.temasInteres;
	}
	/*
	private void ordenarAlertas(List<Alerta> alertas) {
        Collections.sort(alertas, (a1, a2) -> {
            if (a1.getTipo() == TipoAlerta.URGENTE && a2.getTipo() == TipoAlerta.URGENTE) {
                return Long.compare(a2.getId(), a1.getId());  // LIFO para Urgentes
            } else if (a1.getTipo() == TipoAlerta.INFORMATIVA && a2.getTipo() == TipoAlerta.INFORMATIVA) {
                return Long.compare(a1.getId(), a2.getId());  // FIFO para Informativas
            } else {
            	// Si los tipos son diferentes, ordenar por tipo (Urgente primero)
            	return a1.getTipo().ordinal() - a2.getTipo().ordinal();
            }
        });
    }
	*/
	private void ordenarAlertas(List<Alerta> alertas) {
	    Collections.sort(alertas, (a1, a2) -> Long.compare(a2.getId(), a1.getId()));
	}
	
	public void marcarAlertaComoLeida(String notificacion) {
    	for(Alerta alerta : alertas) {
    		if(alerta.getMensaje().equals(notificacion)) {
    			alerta.leida();
    		}
    	}
    }
}