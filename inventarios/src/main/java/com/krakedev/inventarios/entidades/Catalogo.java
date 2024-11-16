package com.krakedev.inventarios.entidades;

public class Catalogo {
	private int catId;
	private String catNombre;
	private String catDescripcion;
	private Catalogo catPadre;

	public Catalogo() {
		super();
	}

	public Catalogo(int catId, String catNombre, String catDescripcion, Catalogo catPadre) {
		super();
		this.catId = catId;
		this.catNombre = catNombre;
		this.catDescripcion = catDescripcion;
		this.catPadre = catPadre;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatNombre() {
		return catNombre;
	}

	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
	}

	public String getCatDescripcion() {
		return catDescripcion;
	}

	public void setCatDescripcion(String catDescripcion) {
		this.catDescripcion = catDescripcion;
	}

	public Catalogo getCatPadre() {
		return catPadre;
	}

	public void setCatPadre(Catalogo catPadre) {
		this.catPadre = catPadre;
	}

	@Override
	public String toString() {
		return "Catalogo [catId=" + catId + ", catNombre=" + catNombre + ", catDescripcion=" + catDescripcion
				+ ", catPadre=" + catPadre + "]";
	}

}
