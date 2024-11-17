package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class DetallePedido {

	private int detId;
	private CabeceraPedido cabpId;
	private Producto prodId;
	private int detCantidadSolicitada;
	private BigDecimal detSubtotal;
	private int detCantidadRecibida;

	public DetallePedido() {
		super();
	}

	public DetallePedido(int detId, CabeceraPedido cabpId, Producto prodId, int detCantidadSolicitada,
			BigDecimal detSubtotal, int detCantidadRecibida) {
		super();
		this.detId = detId;
		this.cabpId = cabpId;
		this.prodId = prodId;
		this.detCantidadSolicitada = detCantidadSolicitada;
		this.detSubtotal = detSubtotal;
		this.detCantidadRecibida = detCantidadRecibida;
	}

	public int getDetId() {
		return detId;
	}

	public void setDetId(int detId) {
		this.detId = detId;
	}

	public CabeceraPedido getCabpId() {
		return cabpId;
	}

	public void setCabpId(CabeceraPedido cabpId) {
		this.cabpId = cabpId;
	}

	public Producto getProdId() {
		return prodId;
	}

	public void setProdId(Producto prodId) {
		this.prodId = prodId;
	}

	public int getDetCantidadSolicitada() {
		return detCantidadSolicitada;
	}

	public void setDetCantidadSolicitada(int detCantidadSolicitada) {
		this.detCantidadSolicitada = detCantidadSolicitada;
	}

	public BigDecimal getDetSubtotal() {
		return detSubtotal;
	}

	public void setDetSubtotal(BigDecimal detSubtotal) {
		this.detSubtotal = detSubtotal;
	}

	public int getDetCantidadRecibida() {
		return detCantidadRecibida;
	}

	public void setDetCantidadRecibida(int detCantidadRecibida) {
		this.detCantidadRecibida = detCantidadRecibida;
	}

	@Override
	public String toString() {
		return "DetallePedido [detId=" + detId + ", cabpId=" + cabpId + ", prodId=" + prodId
				+ ", detCantidadSolicitada=" + detCantidadSolicitada + ", detSubtotal=" + detSubtotal
				+ ", detCantidadRecibida=" + detCantidadRecibida + "]";
	}

}
