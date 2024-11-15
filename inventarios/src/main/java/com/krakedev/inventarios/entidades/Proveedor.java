package com.krakedev.inventarios.entidades;

public class Proveedor {

	private String provIdentificador;
	private String catIdTipodoc;
	private String provNombre;
	private String provTelefono;
	private String provCorreo;
	private String provDireccion;

	public Proveedor() {

	}

	public Proveedor(String provIdentificador, String catIdTipodoc, String provNombre, String provTelefono,
			String provCorreo, String provDireccion) {
		super();
		this.provIdentificador = provIdentificador;
		this.catIdTipodoc = catIdTipodoc;
		this.provNombre = provNombre;
		this.provTelefono = provTelefono;
		this.provCorreo = provCorreo;
		this.provDireccion = provDireccion;
	}

	public String getProvIdentificador() {
		return provIdentificador;
	}

	public void setProvIdentificador(String provIdentificador) {
		this.provIdentificador = provIdentificador;
	}

	public String getCatIdTipodoc() {
		return catIdTipodoc;
	}

	public void setCatIdTipodoc(String catIdTipodoc) {
		this.catIdTipodoc = catIdTipodoc;
	}

	public String getProvNombre() {
		return provNombre;
	}

	public void setProvNombre(String provNombre) {
		this.provNombre = provNombre;
	}

	public String getProvTelefono() {
		return provTelefono;
	}

	public void setProvTelefono(String provTelefono) {
		this.provTelefono = provTelefono;
	}

	public String getProvCorreo() {
		return provCorreo;
	}

	public void setProvCorreo(String provCorreo) {
		this.provCorreo = provCorreo;
	}

	public String getProvDireccion() {
		return provDireccion;
	}

	public void setProvDireccion(String provDireccion) {
		this.provDireccion = provDireccion;
	}

	@Override
	public String toString() {
		return "Proveedor [provIdentificador=" + provIdentificador + ", catIdTipodoc=" + catIdTipodoc + ", provNombre="
				+ provNombre + ", provTelefono=" + provTelefono + ", provCorreo=" + provCorreo + ", provDireccion="
				+ provDireccion + "]";
	}

}
