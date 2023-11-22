package alertas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SistemaAlertasTest {
	private SistemaAlertas sistema;
	private Usuario usuario1;
	
	@Before
	public void startUp() throws Exception {
		sistema = new SistemaAlertas();
		usuario1 = sistema.registrarUsuario("Usuario1");
	}
	
	@Test
    public void testRegistrarUsuario_ok() {
        assertNotNull(usuario1);
        assertEquals("Usuario1", usuario1.getNombre());
    }

	@Test
    public void testRegistrarTema_ok() {
        sistema.registrarTema("Cumpleaños");
        assertTrue(sistema.getTemas().contains("Cumpleaños"));
    }
    
    @Test
    public void testAgregarTemaInteres_ok() {
        Usuario usuario = sistema.registrarUsuario("Usuario1");
        sistema.agregarTemaInteres("Usuario1", "Cumpleaños");

        // Verificar que el usuario tenga el tema de interés
        assertTrue(usuario.getTemasInteres().contains("Cumpleaños"));
    }
    
    @Test
    public void testEnviarAlertaAUsuario_ok() {
    	Usuario usuarioo = sistema.registrarUsuario("Usuario1");
        sistema.agregarTemaInteres("Usuario1", "Cumpleaños");
        Alerta alerta = sistema.enviarAlerta("Cumpleaños", "¡Es mi cumpleaños!", "10/12/2023 12:00", TipoAlerta.URGENTE, "Usuario1");
        assertNotNull(alerta);
        assertTrue(usuarioo.getAlertas().contains(alerta));
    }
    
    @Test
    public void testEnviarAlertaParaTodos_ok() {
        Alerta alerta = sistema.enviarAlerta("Cine", "Obtén un 2x1 en Cines aquí", "17/11/2023 00:00", TipoAlerta.INFORMATIVA);
        assertNotNull(alerta);
        assertTrue(sistema.getAlertas().contains(alerta));
    }
    
    @Test(expected = RuntimeException.class)
    public void testEnviarAlertaConTemaNoCorrecto_generaError() {
        sistema.enviarAlerta("Música", "Nueva playlist disponible", "11/12/2023 15:50", TipoAlerta.INFORMATIVA, "Usuario1");
    }
    
    @Test(expected = RuntimeException.class)
    public void testEnviarAlertaConUsuarioNoCorrecto_generaError() {
        sistema.enviarAlerta("Cumpleaños", "¡Es mi cumpleaños!", "10/12/2023 12:00", TipoAlerta.URGENTE, "Usuario3");
    }
}
