package alertas;

import java.util.List;

public class Main {
	public static void main(String[] args) {
        SistemaAlertas sistema = new SistemaAlertas();

        sistema.registrarUsuario("Usuario1");
        sistema.registrarUsuario("Usuario2");

        sistema.registrarTema("M�sica");
        sistema.registrarTema("Cumplea�os");
        sistema.registrarTema("Cine");
        
        sistema.agregarTemaInteres("Usuario1", "Cumplea�os");
        sistema.agregarTemaInteres("Usuario1", "Cine");
        sistema.agregarTemaInteres("Usuario2", "Cumplea�os");
        sistema.agregarTemaInteres("Usuario2", "Cine");
        sistema.agregarTemaInteres("Usuario2", "M�sica");
        
        sistema.enviarAlerta("Cumplea�os", "�Es mi cumplea�os!", "10/12/2023 12:00", TipoAlerta.URGENTE, "Usuario1");
        sistema.enviarAlerta("Cine", "�S�lo por hoy! 25% OFF con MODO", "11/12/2023 12:00", TipoAlerta.URGENTE, "Usuario1");
        sistema.enviarAlerta("M�sica", "Nueva playlist disponible", "11/12/2023 15:50", TipoAlerta.INFORMATIVA);
        sistema.enviarAlerta("Cine", "Se estrena una nueva pelicula de Marvel", "11/11/2023 09:20", TipoAlerta.INFORMATIVA);
        sistema.enviarAlerta("Cine", "Obt�n un 2x1 en Cines aqu�", "17/11/2023 00:00", TipoAlerta.INFORMATIVA);
        sistema.enviarAlerta("M�sica", "Suscribete ahora a YouTube Premium", "21/12/2023 15:50", TipoAlerta.INFORMATIVA);
        sistema.enviarAlerta("Cumplea�os", "Cumplea�os de pap�", "09/10/2024 07:20", TipoAlerta.URGENTE, "Usuario2");
        sistema.enviarAlerta("Deporte", "Batacazo en la bombonera", "17/11/2023 10:00", TipoAlerta.INFORMATIVA);

        List<Alerta> alertasNoLeidasDeUsuario1 = sistema.obtenerAlertasNoLeidasDe("Usuario1");
        List<Alerta> alertasNoLeidasDeUsuario2 = sistema.obtenerAlertasNoLeidasDe("Usuario2");
        List<Alerta> alertasDeCine = sistema.obtenerAlertasDe("Cine");
        
        Usuario usuario1 = sistema.getUsuario("Usuario1");
        Usuario usuario2 = sistema.getUsuario("Usuario2");
        
        usuario1.marcarAlertaComoLeida("�Es mi cumplea�os!");
        usuario2.marcarAlertaComoLeida("Obt�n un 2x1 en Cines aqu�");
        
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
