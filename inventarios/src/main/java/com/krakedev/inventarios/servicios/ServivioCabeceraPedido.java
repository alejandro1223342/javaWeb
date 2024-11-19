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

import com.krakedev.inventarios.bdd.PedidosBDD;
import com.krakedev.inventarios.entidades.CabeceraPedido;
import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("pedidos")

public class ServivioCabeceraPedido {
	@Path("registrar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(CabeceraPedido cabeceraPedido) {

		System.out.println(">>>>" + cabeceraPedido);
		PedidosBDD pro = new PedidosBDD();
		try {
			pro.insertar(cabeceraPedido);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	@Path("recibir")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(CabeceraPedido cabeceraPedido) {

		System.out.println("ACTUALIZANDO CLIENTE>>>>" + cabeceraPedido);
		PedidosBDD pro = new PedidosBDD();
		try {
			pro.actualizar(cabeceraPedido);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("buscarPorProveedor/{proveedorParam}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorProveedor(@PathParam("proveedorParam") String prov_identificador) {
		PedidosBDD pedidos = new PedidosBDD();
	    ArrayList<DetallePedido> detalles = null;

		System.out.println("Ingresa>>>>" + prov_identificador);

		try {
			detalles = pedidos.buscarPorProveedor(prov_identificador);
			return Response.ok(detalles).build();
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
