package alertas;

import java.util.Date;

public class Alerta {
    private String mensaje;
    private Date fechaExpiracion;
    private TipoAlerta tipo;
    private boolean leida;
    private boolean paraTodos;

    public Alerta(String mensaje, Date fechaExpiracion, TipoAlerta tipo, boolean paraTodos) {
        this.mensaje = mensaje;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
        this.leida = false;
        this.paraTodos = paraTodos;
    }

	public boolean isLeida() {
		return leida;
	}

	public boolean isExpirada() {
        Date ahora = new Date();
        return fechaExpiracion.before(ahora) || fechaExpiracion.equals(ahora);
    }

	public TipoAlerta getTipo() {
		return tipo;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public boolean isParaTodos() {
        return paraTodos;
    }
}
