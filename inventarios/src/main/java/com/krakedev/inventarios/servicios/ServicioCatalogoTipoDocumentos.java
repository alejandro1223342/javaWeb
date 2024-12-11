package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.TipoDocumentoBDD;
import com.krakedev.inventarios.entidades.CatalogoTipoDocumentos;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("tiposdocumento")

public class ServicioCatalogoTipoDocumentos {
	
	@Path("recuperar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerDocumentos() {
		TipoDocumentoBDD catalogoDoc = new TipoDocumentoBDD();
		ArrayList<CatalogoTipoDocumentos> tipoDoc = null;
		try {
			tipoDoc = catalogoDoc.recuperarTodos();
			return Response.ok(tipoDoc).build();
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(CatalogoTipoDocumentos tipoDoc) {

		System.out.println(">>>>" + tipoDoc);
		TipoDocumentoBDD ctl = new TipoDocumentoBDD();
		try {
			ctl.insertarTipoDocumento(tipoDoc);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

}
