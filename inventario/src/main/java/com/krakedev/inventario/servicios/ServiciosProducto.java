package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.entidades.Producto;

@Path("productos")

public class ServiciosProducto {
	
	  @Path("m1")
	    @GET
	    public String saludar() {
	        return "Hola CTM Rest Web S";
	    }

	@Path("insertar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertar(Producto producto) {

		System.out.println("INSERTANDO PRODUCTO>>>>" + producto);
	}

	@Path("actualizar")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizar(Producto producto) {

		System.out.println("ACTUALIZANDO CLIENTE>>>>" + producto);
	}

	@Path("consultar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Producto> recuperarTodosArreglo() {
		ArrayList<Producto> productos = new ArrayList<>();
		Categoria categoria = new Categoria(1, "BEBIDAS");
		Producto productos1 = new Producto("ABC", "PEPSI", categoria, 10, 10);
		Producto productos2 = new Producto("CDE", "COCA", categoria, 60, 20);
		Producto productos3 = new Producto("EFG", "FIORA", categoria, 50, 30);
		productos.add(productos1);
		productos.add(productos2);
		productos.add(productos3);
		return productos;

	}
	
	@Path("consultar2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Producto recuperarTodos() {
	   
	        Categoria categoria = new Categoria(1, "BEBIDAS");
	        Producto productos1 = new Producto("ABC", "PEPSI", categoria, 10, 10);
	        //Producto productos2 = new Producto("CDE", "COCA", categoria, 60, 20);
	        //Producto productos3 = new Producto("EFG", "FIORA", categoria, 50, 30);
	        
	   
	    return productos1;
	}

}
