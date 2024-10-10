package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.krakedev.inventario.entidades.Categoria;
@Path("categorias")

public class ServiciosCategoria {
	
	 @Path("m1")
	    @GET
	    public String saludar() {
	        return "Hola CTM Rest Web S";
	    }
	 
	@Path("insertar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertar(Categoria categoria) {

		System.out.println("INSERTANDO PRODUCTO>>>>" + categoria);
	}
}
