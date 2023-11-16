package alertas;

import java.util.Date;
import java.util.List;

public class Main {
	public static void main(String[] args) {
        SistemaAlertas sistema = new SistemaAlertas();

        Usuario usuario1 = new Usuario("Usuario1");
        Usuario usuario2 = new Usuario("Usuario2");

        sistema.registrarUsuario(usuario1);
        sistema.registrarUsuario(usuario2);

        sistema.registrarTema("Cumpleaños");
        sistema.registrarTema("Fotos");

        sistema.enviarAlerta("¡Es mi cumpleaños!", TipoAlerta.URGENTE, "Cumpleaños", new Date(), false);
        sistema.enviarAlerta("Nuevo álbum de fotos disponible", TipoAlerta.INFORMATIVA, "Fotos", new Date(), true);

        List<Alerta> alertasNoLeidasUsuario1 = usuario1.obtenerAlertasNoLeidas();
        List<Alerta> alertasParaTemaFotos = sistema.obtenerAlertasParaTema("Fotos");
        List<Alerta> alertasParaTemaCumpleaños = sistema.obtenerAlertasParaTema("Cumpleaños");
    }
}
