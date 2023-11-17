package alertas;

import java.util.List;

public class Main {
	public static void main(String[] args) {
        SistemaAlertas sistema = new SistemaAlertas();

        sistema.registrarUsuario("Usuario1");
        sistema.registrarUsuario("Usuario2");

        sistema.registrarTema("Música");
        sistema.registrarTema("Cumpleaños");
        sistema.registrarTema("Cine");
        
        sistema.agregarTemaInteres("Usuario1", "Cumpleaños");
        sistema.agregarTemaInteres("Usuario1", "Cine");
        sistema.agregarTemaInteres("Usuario2", "Cumpleaños");
        sistema.agregarTemaInteres("Usuario2", "Cine");
        sistema.agregarTemaInteres("Usuario2", "Música");
        
        sistema.enviarAlerta("Cumpleaños", "¡Es mi cumpleaños!", "10/12/2023 12:00", TipoAlerta.URGENTE, "Usuario1");
        sistema.enviarAlerta("Cine", "¡Sólo por hoy! 25% OFF con MODO", "11/12/2023 12:00", TipoAlerta.URGENTE, "Usuario1");
        sistema.enviarAlerta("Música", "Nueva playlist disponible", "11/12/2023 15:50", TipoAlerta.INFORMATIVA);
        sistema.enviarAlerta("Cine", "Se estrena una nueva pelicula de Marvel", "11/11/2023 09:20", TipoAlerta.INFORMATIVA);
        sistema.enviarAlerta("Cine", "Obtén un 2x1 en Cines aquí", "17/11/2023 00:00", TipoAlerta.INFORMATIVA);
        sistema.enviarAlerta("Música", "Suscribete ahora a YouTube Premium", "21/12/2023 15:50", TipoAlerta.INFORMATIVA);
        sistema.enviarAlerta("Cumpleaños", "Cumpleaños de papá", "09/10/2024 07:20", TipoAlerta.URGENTE, "Usuario2");
        sistema.enviarAlerta("Deporte", "Batacazo en la bombonera", "17/11/2023 10:00", TipoAlerta.INFORMATIVA);

        List<Alerta> alertasNoLeidasDeUsuario1 = sistema.obtenerAlertasNoLeidasDe("Usuario1");
        List<Alerta> alertasNoLeidasDeUsuario2 = sistema.obtenerAlertasNoLeidasDe("Usuario2");
        List<Alerta> alertasDeCine = sistema.obtenerAlertasDe("Cine");
        
        Usuario usuario1 = sistema.getUsuario("Usuario1");
        Usuario usuario2 = sistema.getUsuario("Usuario2");
        
        usuario1.marcarAlertaComoLeida("¡Es mi cumpleaños!");
        usuario2.marcarAlertaComoLeida("Obtén un 2x1 en Cines aquí");
        
        for(Alerta alerta : alertasNoLeidasDeUsuario1) {
            alerta.mostrarAlerta(usuario1);
        }
        
        System.out.println("");
        System.out.println("");
        
        for(Alerta alerta : alertasNoLeidasDeUsuario2) {
            alerta.mostrarAlerta(usuario2);
        }
    }
}
