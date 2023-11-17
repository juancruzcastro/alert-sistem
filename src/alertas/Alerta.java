package alertas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Alerta {
	private static int contadorAlertas = 0;
	private int id;
	private String tema;
    private String mensaje;
    private String fechaExpiracion;
    private TipoAlerta tipo;
    private boolean leida;
    private boolean paraTodos;
    private Usuario usuario;

    public Alerta(String tema, String mensaje, String fechaExpiracion, TipoAlerta tipo) {
    	this.id = contadorAlertas++;
    	this.tema = tema;
    	this.mensaje = mensaje;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
        this.leida = false;
        this.usuario = null;
        this.paraTodos = true;
    }
    
    public Alerta(String tema, String mensaje, String fechaExpiracion, TipoAlerta tipo, Usuario usuario) {
    	this.id = contadorAlertas++;
    	this.tema = tema;
    	this.mensaje = mensaje;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
        this.leida = false;
        this.usuario = usuario;
        this.paraTodos = false;
    }
    
    public int getId() {
        return this.id;
    }

	public boolean isLeida() {
		return this.leida;
	}

	public boolean isExpirada() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            Date fechaExpiracionDate = dateFormat.parse(fechaExpiracion);
            Date ahora = new Date();
            return fechaExpiracionDate.before(ahora) || fechaExpiracionDate.equals(ahora);
        } catch (ParseException e) {
            e.printStackTrace();  // Manejar la excepción según tu lógica
            return false;  // En caso de error, asumir que la alerta no está expirada
        }
    }

	public TipoAlerta getTipo() {
		return this.tipo;
	}
	
	public String getTema() {
		return this.tema;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public boolean isParaTodos() {
        return this.paraTodos;
    }
	
	public void leida() {
        this.leida = true;
    }

	public Usuario obtenerUsuarioDeLaAlerta() {
		return this.usuario;		
	}
	
	public String getMensaje() {
		return this.mensaje;
	}

	public void mostrarAlerta() {
		System.out.println("Mensaje: " + this.mensaje);
		System.out.println("Tema: " + this.tema);
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Fecha Expiración: " + this.fechaExpiracion);
        System.out.println("----------------------------------");
	}

	public void mostrarAlerta(Usuario usuario) {
		System.out.println("Mensaje: " + this.mensaje);
		System.out.println("Tema: " + this.tema);
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Leida: " + this.leida);
        System.out.println("Usuario: " + (usuario.getNombre() != null ? usuario.getNombre() : "N/A"));
        System.out.println("Fecha Expiración: " + this.fechaExpiracion);
        System.out.println("----------------------------------");
	}
}
