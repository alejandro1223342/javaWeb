package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProductosBDD;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("productos")

public class ServicioProductos {
	@Path("buscar/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("sub")String subcadena) {

		ProductosBDD provBDD = new ProductosBDD();
		ArrayList<Producto> productos = null;
		try {
			productos = provBDD.buscarP(subcadena);
			return Response.ok(productos).build();
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Producto productos) {

		System.out.println(">>>>" + productos);
		ProductosBDD pro = new ProductosBDD();
		try {
			pro.insertarProducto(productos);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	@Path("actualizar")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Producto productos) {

		System.out.println("ACTUALIZANDO CLIENTE>>>>" + productos);
		ProductosBDD pro = new ProductosBDD();
		try {
			pro.actualizar(productos);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("buscarPorId/{prodParam}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("prodParam") int prod_id) {
		ProductosBDD pro = new ProductosBDD();
	    Producto productos = null;

		System.out.println("Ingresa>>>>" + prod_id);

		try {
			productos = pro.buscarPorId(prod_id);
			return Response.ok(productos).build();
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
