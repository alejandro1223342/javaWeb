package com.krakedev.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.krakedev.entidades.Cliente;

@Path("customers")
public class ServicioClientes {
    @Path("m1")
    @GET
    public String saludar() {
        return "Hola CTM Rest Web S";
    }
    
    @Path("mbuscar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente buscar() {
        Cliente cliente = new Cliente("1050518594", "Alejandro");
        return cliente;
    }
    
    @Path("insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertar(Cliente cliente) {
    	
    	System.out.println(">>>>"+cliente);
    }
    
    @Path("actualizar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizar(Cliente cliente) {
    	
    	System.out.println("ACTUALIZANDO CLIENTE>>>>"+cliente);
    }
}
