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
        // Agregar impresión para depuración
        System.out.println("CLASE USUARIO: Temas de interés de " + nombre + ": " + temasInteres);
    }
    
    public boolean es(String usuario) {
        return this.nombre.equals(usuario);
    }

    public void recibirAlerta(Alerta alerta) {
        alertas.add(alerta);
    }
    
    public String getNombre() {
		return this.nombre;
	}
    
    public List<String> getTemasInteres(){
    	return temasInteres;
    }

    public List<Alerta> obtenerAlertasNoLeidas() {
        List<Alerta> noLeidasNoExpiradas = new ArrayList<>();
        for (Alerta alerta : alertas) {
            if (!alerta.isLeida() && !alerta.isExpirada()) {
            	if(alerta.isParaTodos()) {
            		if(this.temasInteres.contains(alerta.getTema())) {
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

	public boolean elTemaEsDeSuInteres(String tema) {
	    System.out.println("Temas de interés del usuario: " + temasInteres);
	    System.out.println("Tema de la alerta: " + tema);
	    
	    boolean resultado = temasInteres.contains(tema);
	    
	    System.out.println("¿El tema es de su interés? " + resultado);
	    
	    return resultado;
	}
}