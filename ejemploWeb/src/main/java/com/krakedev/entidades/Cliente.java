package com.krakedev.entidades;

public class Cliente {

	private String cedula;
	private String nombre;
	private String apellido;
	private int edad;
	private int num_hijos;

	public Cliente() {

	}


	public Cliente(String cedula, String nombre, String apellido, int edad,int num_hijos) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.num_hijos=num_hijos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	


	public int getNum_hijos() {
		return num_hijos;
	}


	public void setNum_hijos(int num_hijos) {
		this.num_hijos = num_hijos;
	}


	@Override
	public String toString() {
		return "Cliente [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + "]";
	}

	

}
