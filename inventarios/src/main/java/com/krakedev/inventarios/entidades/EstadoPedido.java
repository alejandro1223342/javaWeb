package com.krakedev.inventarios.entidades;

public class EstadoPedido {

	private String estCod;
	private String estDescripcion;

	public EstadoPedido() {
		super();
	}

	public EstadoPedido(String estCod, String estDescripcion) {
		super();
		this.estCod = estCod;
		this.estDescripcion = estDescripcion;
	}

	public String getEstCod() {
		return estCod;
	}

	public void setEstCod(String estCod) {
		this.estCod = estCod;
	}

	public String getEstDescripcion() {
		return estDescripcion;
	}

	public void setEstDescripcion(String estDescripcion) {
		this.estDescripcion = estDescripcion;
	}

	@Override
	public String toString() {
		return "EstadoPedido [estCod=" + estCod + ", estDescripcion=" + estDescripcion + "]";
	}

}
