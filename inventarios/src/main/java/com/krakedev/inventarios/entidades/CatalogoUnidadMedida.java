package com.krakedev.inventarios.entidades;

public class CatalogoUnidadMedida {
	private String catIdUdm;
	private String catUdmNombre;

	public CatalogoUnidadMedida() {
		super();
	}

	public CatalogoUnidadMedida(String catIdUdm, String catUdmNombre) {
		super();
		this.catIdUdm = catIdUdm;
		this.catUdmNombre = catUdmNombre;
	}

	public String getCatIdUdm() {
		return catIdUdm;
	}

	public void setCatIdUdm(String catIdUdm) {
		this.catIdUdm = catIdUdm;
	}

	public String getCatUdmNombre() {
		return catUdmNombre;
	}

	public void setCatUdmNombre(String catUdmNombre) {
		this.catUdmNombre = catUdmNombre;
	}

	@Override
	public String toString() {
		return "CatalogoUnidadMedida [catIdUdm=" + catIdUdm + ", catUdmNombre=" + catUdmNombre + "]";
	}

}
