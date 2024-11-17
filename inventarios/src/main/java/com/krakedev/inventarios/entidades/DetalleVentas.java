package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class DetalleVentas {

	private int detId;
	private CabeceraVentas cabvId;
	private Producto prodId;
	private int detCantidad;
	private BigDecimal detPrecioVenta;
	private BigDecimal detSubtotal;
	private BigDecimal detSubtotalConIva;

	public DetalleVentas() {
		super();
	}

	public DetalleVentas(int detId, CabeceraVentas cabvId, Producto prodId, int detCantidad, BigDecimal detPrecioVenta,
			BigDecimal detSubtotal, BigDecimal detSubtotalConIva) {
		super();
		this.detId = detId;
		this.cabvId = cabvId;
		this.prodId = prodId;
		this.detCantidad = detCantidad;
		this.detPrecioVenta = detPrecioVenta;
		this.detSubtotal = detSubtotal;
		this.detSubtotalConIva = detSubtotalConIva;
	}

	public int getDetId() {
		return detId;
	}

	public void setDetId(int detId) {
		this.detId = detId;
	}

	public CabeceraVentas getCabvId() {
		return cabvId;
	}

	public void setCabvId(CabeceraVentas cabvId) {
		this.cabvId = cabvId;
	}

	public Producto getProdId() {
		return prodId;
	}

	public void setProdId(Producto prodId) {
		this.prodId = prodId;
	}

	public int getDetCantidad() {
		return detCantidad;
	}

	public void setDetCantidad(int detCantidad) {
		this.detCantidad = detCantidad;
	}

	public BigDecimal getDetPrecioVenta() {
		return detPrecioVenta;
	}

	public void setDetPrecioVenta(BigDecimal detPrecioVenta) {
		this.detPrecioVenta = detPrecioVenta;
	}

	public BigDecimal getDetSubtotal() {
		return detSubtotal;
	}

	public void setDetSubtotal(BigDecimal detSubtotal) {
		this.detSubtotal = detSubtotal;
	}

	public BigDecimal getDetSubtotalConIva() {
		return detSubtotalConIva;
	}

	public void setDetSubtotalConIva(BigDecimal detSubtotalConIva) {
		this.detSubtotalConIva = detSubtotalConIva;
	}

	@Override
	public String toString() {
		return "DetalleVentas [detId=" + detId + ", cabvId=" + cabvId + ", prodId=" + prodId + ", detCantidad="
				+ detCantidad + ", detPrecioVenta=" + detPrecioVenta + ", detSubtotal=" + detSubtotal
				+ ", detSubtotalConIva=" + detSubtotalConIva + "]";
	}

}
