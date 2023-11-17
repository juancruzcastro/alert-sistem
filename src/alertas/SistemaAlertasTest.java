package alertas;

import org.junit.Before;
import org.junit.Test;

public class SistemaAlertasTest {
	private SistemaAlertas sistema;
	private Usuario usuario1;
	
	@Before
	public void startUp() throws Exception {
		sistema = new SistemaAlertas();
		usuario1 = new Usuario("Usuario1");
	}
	
	// registrar usuarios
	@Test
    public void testRegistrarUsuario_ok() {
        sistema.registrarUsuario("Usuario1");
    }

	// registrar tema
    @Test
    public void testRegistrarTema_ok() {
        sistema.registrarTema("Cumplea�os");
    }
    
 // agregar tema interes
    @Test
    public void testAgregarTemaInteres_ok() {
    	sistema.agregarTemaInteres("Usuario1", "Cumplea�os");
    }
    
    @Test
    public void testAgregarTemaInteresConUsuarioNoCorrecto_generaError() {
    	sistema.agregarTemaInteres("Usuario3", "Cumplea�os");
    }
    
    // enviar alertas
    @Test
    public void testEnviarAlertaAUsuario_ok() {
    	sistema.enviarAlerta("Cumplea�os", "�Es mi cumplea�os!", "10/12/2023 12:00", TipoAlerta.URGENTE, "Usuario1");
    }
    
    @Test
    public void testEnviarAlertaParaTodos_ok() {
    	sistema.enviarAlerta("Cine", "Obt�n un 2x1 en Cines aqu�", "17/11/2023 00:00", TipoAlerta.INFORMATIVA);
    }
    
    @Test(expected = RuntimeException.class)
    public void testEnviarAlertaConTemaNoCorrecto_generaError() {
    	sistema.enviarAlerta("M�sica", "Nueva playlist disponible", "11/12/2023 15:50", TipoAlerta.INFORMATIVA, "Usuario1");
    }
    
    @Test(expected = RuntimeException.class)
    public void testEnviarAlertaConUsuarioNoCorrecto_generaError() {
    	sistema.enviarAlerta("Cumplea�os", "�Es mi cumplea�os!", "10/12/2023 12:00", TipoAlerta.URGENTE, "Usuario3");
    }
}
