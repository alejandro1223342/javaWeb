package com.krakedev.inventarios.entidades;

public class UnidadesMedida {
	private int udmId;
	private String udmNombre;
	private String udmDescripcion;
	private CatalogoUnidadMedida catalogoUnidadMedida;

	public UnidadesMedida() {
		super();
	}

	public UnidadesMedida(int udmId, String udmNombre, String udmDescripcion,
			CatalogoUnidadMedida catalogoUnidadMedida) {
		super();
		this.udmId = udmId;
		this.udmNombre = udmNombre;
		this.udmDescripcion = udmDescripcion;
		this.catalogoUnidadMedida = catalogoUnidadMedida;
	}

	public int getUdmId() {
		return udmId;
	}

	public void setUdmId(int udmId) {
		this.udmId = udmId;
	}

	public String getUdmNombre() {
		return udmNombre;
	}

	public void setUdmNombre(String udmNombre) {
		this.udmNombre = udmNombre;
	}

	public String getUdmDescripcion() {
		return udmDescripcion;
	}

	public void setUdmDescripcion(String udmDescripcion) {
		this.udmDescripcion = udmDescripcion;
	}

	public CatalogoUnidadMedida getCatalogoUnidadMedida() {
		return catalogoUnidadMedida;
	}

	public void setCatalogoUnidadMedida(CatalogoUnidadMedida catalogoUnidadMedida) {
		this.catalogoUnidadMedida = catalogoUnidadMedida;
	}

	@Override
	public String toString() {
		return "UnidadesMedida [udmId=" + udmId + ", udmNombre=" + udmNombre + ", udmDescripcion=" + udmDescripcion
				+ ", catalogoUnidadMedida=" + catalogoUnidadMedida + "]";
	}

}
