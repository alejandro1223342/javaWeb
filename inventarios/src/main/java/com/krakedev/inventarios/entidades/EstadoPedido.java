package com.krakedev.inventarios.entidades;

public class EstadoPedido {

	private int estCod;
	private String estDescripcion;

	public EstadoPedido() {
		super();
	}

	public EstadoPedido(int estCod, String estDescripcion) {
		super();
		this.estCod = estCod;
		this.estDescripcion = estDescripcion;
	}

	public int getEstCod() {
		return estCod;
	}

	public void setEstCod(int estCod) {
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
