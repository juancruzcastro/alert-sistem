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

    public void registrarUsuario(String user) {
    	Usuario usuario = new Usuario(user);
        usuarios.add(usuario);
    }

    public void registrarTema(String tema) {
        temas.add(tema);
    }
    
    public void agregarTemaInteres(String user, String tema) {
    	Usuario usuario = obtenerUsuario(user);
    	usuario.agregarTemaInteres(tema);
    }
    
    public void enviarAlerta(String tema, String mensaje, String fechaExpiracion, TipoAlerta tipo) {
    	Alerta alerta = new Alerta(tema, mensaje, fechaExpiracion, tipo);
    	alertas.add(alerta);
    }
    
    public void enviarAlerta(String tema, String mensaje, String fechaExpiracion, TipoAlerta tipo, String usuario) {
    	Usuario user = obtenerUsuario(usuario);
    	if(!user.getTemasInteres().contains(tema)) {
    		throw new RuntimeException("El usuario fue encontrado, pero la alerta no corresponde con sus temas de interés.");
    	}
    	
    	Alerta alerta = new Alerta(tema, mensaje, fechaExpiracion, tipo, user);
    	alertas.add(alerta);
    	user.recibirAlerta(alerta);
    }
    
    private boolean elUsuarioEsta(String usuario) {
    	boolean usuarioEncontrado = false;
    	for(Usuario user : usuarios) {
			usuarioEncontrado |= user.getNombre().equals(usuario);
    	}
    	return usuarioEncontrado;
    }
    
    public List<Alerta> obtenerAlertasNoLeidasDe(String user) {
    	List<Alerta> alertasDelUsuario = new ArrayList<>();
		for (Alerta alerta : alertas) {
            if (alerta.isParaTodos()) {
            	Usuario usuarioEncontrado = new Usuario();
            	usuarioEncontrado = obtenerUsuario(user);
            	if(usuarioEncontrado == null) {
            		throw new RuntimeException("El usuario no es válido.");
            	} else if(usuarioEncontrado.getTemasInteres().contains(alerta.getTema())) {
        			alertasDelUsuario.add(alerta);
        		}
            } else if(!alerta.isExpirada() && alerta.obtenerUsuarioDeLaAlerta().getNombre().equals(user)) {
            	alertasDelUsuario.add(alerta);
            }
        }
		
		ordenarAlertas(alertasDelUsuario);
        return alertasDelUsuario;
	}
    
    private Usuario obtenerUsuario(String usuario) {
    	Usuario usuarioEncontrado = null;
    	for(Usuario user : usuarios) {
    		if(user.es(usuario)) {
    			usuarioEncontrado = user;
    		}
    	}
    	
    	if(usuarioEncontrado == null) {
    		throw new RuntimeException("El usuario no fue encontrado.");
    	}
    	
    	return usuarioEncontrado;
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
