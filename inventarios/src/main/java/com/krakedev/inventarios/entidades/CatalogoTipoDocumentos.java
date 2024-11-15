package com.krakedev.inventarios.entidades;

public class CatalogoTipoDocumentos {

	private String catIdTipoDc;
	private String catNombre;

	public CatalogoTipoDocumentos() {

	}

	public CatalogoTipoDocumentos(String catIdTipoDc, String catNombre) {
		super();
		this.catIdTipoDc = catIdTipoDc;
		this.catNombre = catNombre;
	}

	public String getCatIdTipoDc() {
		return catIdTipoDc;
	}

	public void setCatIdTipoDc(String catIdTipoDc) {
		this.catIdTipoDc = catIdTipoDc;
	}

	public String getCatNombre() {
		return catNombre;
	}

	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
	}

	@Override
	public String toString() {
		return "CatalogoTipoDocumentos [catIdTipoDc=" + catIdTipoDc + ", catNombre=" + catNombre + "]";
	}

}
