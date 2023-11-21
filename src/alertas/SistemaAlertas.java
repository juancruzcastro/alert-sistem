package alertas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SistemaAlertas {
	private List<Usuario> usuarios;
    private List<String> temas;
    private List<Alerta> alertas;

    public SistemaAlertas() {
        this.usuarios = new ArrayList<>();
        this.temas = new ArrayList<>();
        this.alertas = new ArrayList<>();
    }

    public Usuario registrarUsuario(String user) {
    	Usuario usuario = new Usuario(user);
        usuarios.add(usuario);
        return usuario;
    }

    public List<String> registrarTema(String tema) {
        temas.add(tema);
        return temas;
    }
    
    public List<String> getTemas(){
    	return temas;
    }
    
    public void agregarTemaInteres(String user, String tema) {
    	Usuario usuario = obtenerUsuario(user);
    	if(usuario == null) {
    		throw new RuntimeException("El usuario no fue encontrado.");
    	}
    	
    	usuario.agregarTemaInteres(tema);
    	// Actualizar la instancia del usuario en la lista
        usuarios.remove(usuario);
        usuarios.add(usuario);
    }
    /*
    public void agregarTemaInteres(String user, String tema) {
        Usuario usuario = obtenerUsuario(user);
        if (usuario != null) {
            usuario.agregarTemaInteres(tema);
        } else {
            throw new RuntimeException("El usuario no fue encontrado: " + user);
        }
    }
    
    public void agregarTemaInteres(String user, String tema) {
        Usuario usuario = obtenerUsuario(user);

        if (usuario == null) {
            throw new RuntimeException("El usuario no fue encontrado.");
        }

        usuario.agregarTemaInteres(tema);

        // Agregar impresión para depuración
        System.out.println("Temas de interés de " + user + ": " + usuario.getTemasInteres());
    }
    */
    public Alerta enviarAlerta(String tema, String mensaje, String fechaExpiracion, TipoAlerta tipo) {
    	Alerta alerta = new Alerta(tema, mensaje, fechaExpiracion, tipo);
    	alertas.add(alerta);
    	return alerta;
    }
    
    public Alerta enviarAlerta(String tema, String mensaje, String fechaExpiracion, TipoAlerta tipo, String usuario) {
        Usuario user = obtenerUsuario(usuario);

        if (user == null) {
            throw new RuntimeException("El usuario '" + usuario + "' no fue encontrado.");
        }

        // Agregar impresión para depuración
        System.out.println("Temas de interés del usuario antes de verificar la alerta: " + user.getTemasInteres());

        if (!user.elTemaEsDeSuInteres(tema)) {
            throw new RuntimeException("El usuario '" + usuario + "' fue encontrado, pero la alerta no corresponde con sus temas de interés.");
        }

        Alerta alerta = new Alerta(tema, mensaje, fechaExpiracion, tipo, user);
        user.recibirAlerta(alerta);
        alertas.add(alerta);
        return alerta;
    }
    
    public List<Alerta> getAlertas(){
    	return alertas;
    }
    
    public List<Alerta> obtenerAlertasNoLeidasDe(String user) {
    	List<Alerta> alertasDelUsuario = new ArrayList<>();
		for (Alerta alerta : alertas) {
			
            if (alerta.isParaTodos()) {
            	Usuario usuarioEncontrado = new Usuario();
            	usuarioEncontrado = obtenerUsuario(user);
            	
            	if(usuarioEncontrado == null) {
            		throw new RuntimeException("El usuario no es válido.");
            	} else if(usuarioEncontrado.elTemaEsDeSuInteres(alerta.getTema())) {
        			alertasDelUsuario.add(alerta);
        		}
            } else if(!alerta.isExpirada() && alerta.obtenerUsuarioDeLaAlerta().getNombre().equals(user)) {
            	alertasDelUsuario.add(alerta);
            }
            
        }
		
		ordenarAlertas(alertasDelUsuario);
        return alertasDelUsuario;
	}

    private Usuario obtenerUsuario(String nombreUsuario) {
    	Usuario usuario = null;
    	for(Usuario user : usuarios) {
    		if(user.es(nombreUsuario)) {
    			usuario = user;
    		}
    	}
    	
    	return usuario;
    }
    
    private void ordenarAlertas(List<Alerta> alertas) {
        Collections.sort(alertas, (a1, a2) -> {
            if (a1.getTipo() == TipoAlerta.URGENTE && a2.getTipo() == TipoAlerta.URGENTE) {
            	if(a1.getFechaExpiracion().compareTo(a2.getFechaExpiracion()) > 0) {
            		return Long.compare(a1.getId(), a2.getId());  // LIFO para Urgentes
            	} else {
            		return Long.compare(a2.getId(), a1.getId());  // LIFO para Urgentes
            	}
            } else if (a1.getTipo() == TipoAlerta.INFORMATIVA && a2.getTipo() == TipoAlerta.INFORMATIVA) {
            	if(a1.getFechaExpiracion().compareTo(a2.getFechaExpiracion()) > 0) {
            		return Long.compare(a1.getId(), a2.getId());  // FIFO para Informativas
            	} else {
            		return Long.compare(a2.getId(), a1.getId());  // FIFO para Informativas
            	}
            } else {
            	// Si los tipos son diferentes, ordenar por tipo (Urgente primero)
            	return a1.getTipo().ordinal() - a2.getTipo().ordinal();
            }
        });
    }
    
    public List<Alerta> obtenerAlertasDe(String tema) {
        List<Alerta> alertasParaTema = new ArrayList<>();
        for (Alerta alerta : alertas) {
            if (alerta.getTema().equals(tema) && !alerta.isExpirada()) {
                alertasParaTema.add(alerta);
            }
        }
        return alertasParaTema;
    }
    
    public Usuario getUsuario(String nombre) {
    	Usuario user = new Usuario();
    	for(Usuario usuario : usuarios) {
    		if(usuario.es(nombre)) {
    			user = usuario;
    		}
    	}
    	return user;
    }
}
