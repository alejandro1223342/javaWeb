package com.krakedev.inventarios.entidades;

import java.sql.Date;

public class HistorialStock {
	private int hisId;
	private Date hisFecha;
	private String hisReferencia;
	private Producto proId;
	private int hisCantidad;

	public HistorialStock() {
		super();
	}

	public HistorialStock(int hisId, Date hisFecha, String hisReferencia, Producto proId, int hisCantidad) {
		super();
		this.hisId = hisId;
		this.hisFecha = hisFecha;
		this.hisReferencia = hisReferencia;
		this.proId = proId;
		this.hisCantidad = hisCantidad;
	}

	public int getHisId() {
		return hisId;
	}

	public void setHisId(int hisId) {
		this.hisId = hisId;
	}

	public Date getHisFecha() {
		return hisFecha;
	}

	public void setHisFecha(Date hisFecha) {
		this.hisFecha = hisFecha;
	}

	public String getHisReferencia() {
		return hisReferencia;
	}

	public void setHisReferencia(String hisReferencia) {
		this.hisReferencia = hisReferencia;
	}

	public Producto getProId() {
		return proId;
	}

	public void setProId(Producto proId) {
		this.proId = proId;
	}

	public int getHisCantidad() {
		return hisCantidad;
	}

	public void setHisCantidad(int hisCantidad) {
		this.hisCantidad = hisCantidad;
	}

	@Override
	public String toString() {
		return "HistorialStock [hisId=" + hisId + ", hisFecha=" + hisFecha + ", hisReferencia=" + hisReferencia
				+ ", proId=" + proId + ", hisCantidad=" + hisCantidad + "]";
	}

}
