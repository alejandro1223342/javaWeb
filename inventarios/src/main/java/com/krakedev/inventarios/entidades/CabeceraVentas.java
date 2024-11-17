package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class CabeceraVentas {
	private int cabvId;
	private Date cabvFecha;
	private BigDecimal cabvTotalsiniva;
	private BigDecimal cabvIva;
	private BigDecimal cabvTotal;
	
	private ArrayList<DetalleVentas> detalles;

	public CabeceraVentas() {
		super();
	}

	public CabeceraVentas(int cabvId, Date cabvFecha, BigDecimal cabvTotalsiniva, BigDecimal cabvIva,
			BigDecimal cabvTotal) {
		super();
		this.cabvId = cabvId;
		this.cabvFecha = cabvFecha;
		this.cabvTotalsiniva = cabvTotalsiniva;
		this.cabvIva = cabvIva;
		this.cabvTotal = cabvTotal;
	}

	public int getCabvId() {
		return cabvId;
	}

	public void setCabvId(int cabvId) {
		this.cabvId = cabvId;
	}

	public Date getCabvFecha() {
		return cabvFecha;
	}

	public void setCabvFecha(Date cabvFecha) {
		this.cabvFecha = cabvFecha;
	}

	public BigDecimal getCabvTotalsiniva() {
		return cabvTotalsiniva;
	}

	public void setCabvTotalsiniva(BigDecimal cabvTotalsiniva) {
		this.cabvTotalsiniva = cabvTotalsiniva;
	}

	public BigDecimal getCabvIva() {
		return cabvIva;
	}

	public void setCabvIva(BigDecimal cabvIva) {
		this.cabvIva = cabvIva;
	}

	public BigDecimal getCabvTotal() {
		return cabvTotal;
	}

	public void setCabvTotal(BigDecimal cabvTotal) {
		this.cabvTotal = cabvTotal;
	}

	public ArrayList<DetalleVentas> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<DetalleVentas> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "CabeceraVentas [cabvId=" + cabvId + ", cabvFecha=" + cabvFecha + ", cabvTotalsiniva=" + cabvTotalsiniva
				+ ", cabvIva=" + cabvIva + ", cabvTotal=" + cabvTotal + "]";
	}

}
