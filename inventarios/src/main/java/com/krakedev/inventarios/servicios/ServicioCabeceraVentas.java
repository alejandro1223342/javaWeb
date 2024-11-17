package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.VentasBDD;
import com.krakedev.inventarios.entidades.CabeceraVentas;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("ventas")

public class ServicioCabeceraVentas {
	@Path("guardar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(CabeceraVentas cabeceraVentas) {

		System.out.println(">>>>" + cabeceraVentas);
		VentasBDD ventas = new VentasBDD();
		try {
			ventas.insertar(cabeceraVentas);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
}
