package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.CatalogoBDD;
import com.krakedev.inventarios.bdd.ProductosBDD;
import com.krakedev.inventarios.bdd.TipoDocumentoBDD;
import com.krakedev.inventarios.entidades.Catalogo;
import com.krakedev.inventarios.entidades.CatalogoTipoDocumentos;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("catalogo")

public class ServicioCatalogos {
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Catalogo catalogo) {

		System.out.println(">>>>" + catalogo);
		CatalogoBDD ctl = new CatalogoBDD();
		try {
			ctl.insertarCatalogo(catalogo);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	@Path("actualizar")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Catalogo catalogo) {

		System.out.println("ACTUALIZANDO CLIENTE>>>>" + catalogo);
		CatalogoBDD ctl = new CatalogoBDD();
		try {
			ctl.actualizar(catalogo);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("recuperar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerDocumentos() {
		CatalogoBDD catalogo = new CatalogoBDD();
		ArrayList<Catalogo> catTipo = null;
		try {
			catTipo = catalogo.recuperarTodos();
			return Response.ok(catTipo).build();
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
