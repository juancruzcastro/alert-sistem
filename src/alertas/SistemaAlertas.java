package alertas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SistemaAlertas {
	private List<Usuario> usuarios;
    private List<String> temas;

    public SistemaAlertas() {
        this.usuarios = new ArrayList<>();
        this.temas = new ArrayList<>();
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void registrarTema(String tema) {
        temas.add(tema);
    }

    public void enviarAlerta(String mensaje, TipoAlerta tipo, String tema, Date fechaExpiracion, boolean paraTodos) {
        Alerta alerta = new Alerta(mensaje, fechaExpiracion, tipo, paraTodos);

        for (Usuario usuario : usuarios) {
            if (usuario.getTemasInteres().contains(tema)) {
                usuario.recibirAlerta(alerta);
            }
        }
    }

    public void enviarAlertaAUsuario(String mensaje, TipoAlerta tipo, Usuario usuario, Date fechaExpiracion, boolean paraTodos) {
        Alerta alerta = new Alerta(mensaje, fechaExpiracion, tipo, paraTodos);
        usuario.recibirAlerta(alerta);
    }

    public List<Alerta> obtenerAlertasParaTema(String tema) {
        List<Alerta> alertasParaTema = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            for (Alerta alerta : usuario.getAlertas()) {
                if (alerta.getTipo() == TipoAlerta.INFORMATIVA && alerta.isParaTodos() && alerta.getTipo().equals(tema) && !alerta.isExpirada()) {
                    alertasParaTema.add(alerta);
                }
            }
        }
        return alertasParaTema;
    }
}
