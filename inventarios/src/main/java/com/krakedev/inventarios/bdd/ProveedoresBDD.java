package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.CatalogoTipoDocumentos;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {

	public ArrayList<Proveedor> buscar(String subcadena) throws KrakeDevException {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor proveedor = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select prov_identificador,cat_id_tipodoc,prov_nombre,prov_telefono, prov_correo, prov_direccion "
							+ "from proveedores " + "where upper(prov_nombre) like ?");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%"); // Convierto a mayusculas
			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("prov_identificador");
				String tipoDocumento = rs.getString("cat_id_tipodoc");
				String nombre = rs.getString("prov_nombre");
				String telefono = rs.getString("prov_telefono");
				String correo = rs.getString("prov_correo");
				String direccion = rs.getString("prov_direccion");

				proveedor = new Proveedor(identificador, tipoDocumento, nombre, telefono, correo, direccion);
				proveedores.add(proveedor);
			}

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}

		return proveedores;
	}

	public ArrayList<CatalogoTipoDocumentos> recuperarTodos() throws KrakeDevException {
		ArrayList<CatalogoTipoDocumentos> catalagoDoc = new ArrayList<CatalogoTipoDocumentos>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CatalogoTipoDocumentos catDoc = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("select cat_id_tipodoc,cat_nombre from catalogo_tipo_documentos ");
			rs = ps.executeQuery();

			while (rs.next()) {
				String tipoDoc = rs.getString("cat_id_tipodoc");
				String nombre = rs.getString("cat_nombre");

				catDoc = new CatalogoTipoDocumentos(tipoDoc, nombre);
				catalagoDoc.add(catDoc);
			}

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}

		return catalagoDoc;
	}

}
