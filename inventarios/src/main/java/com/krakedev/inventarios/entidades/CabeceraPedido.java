package com.krakedev.inventarios.entidades;

import java.sql.Date;
import java.util.ArrayList;

public class CabeceraPedido {
	private int cabpId;
	private Proveedor provIdentificador;
	private Date cabpFecha;
	private EstadoPedido estCod;

	private ArrayList<DetallePedido> detalles;

	public CabeceraPedido() {
		super();
	}

	public CabeceraPedido(int cabpId, Proveedor provIdentificador, Date cabpFecha, EstadoPedido estCod) {
		super();
		this.cabpId = cabpId;
		this.provIdentificador = provIdentificador;
		this.cabpFecha = cabpFecha;
		this.estCod = estCod;
	}

	public int getCabpId() {
		return cabpId;
	}

	public void setCabpId(int cabpId) {
		this.cabpId = cabpId;
	}

	public Proveedor getProvIdentificador() {
		return provIdentificador;
	}

	public void setProvIdentificador(Proveedor provIdentificador) {
		this.provIdentificador = provIdentificador;
	}

	public Date getCabpFecha() {
		return cabpFecha;
	}

	public void setCabpFecha(Date cabpFecha) {
		this.cabpFecha = cabpFecha;
	}

	public EstadoPedido getEstCod() {
		return estCod;
	}

	public void setEstCod(EstadoPedido estCod) {
		this.estCod = estCod;
	}

	public ArrayList<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "CabeceraPedido [cabpId=" + cabpId + ", provIdentificador=" + provIdentificador + ", cabpFecha="
				+ cabpFecha + ", estCod=" + estCod + "]";
	}

}
