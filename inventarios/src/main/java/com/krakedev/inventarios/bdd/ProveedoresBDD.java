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
					"select prov_identificador,cat_doc.cat_id_tipodoc,cat_doc.cat_nombre,prov_nombre,prov_telefono, prov_correo, prov_direccion "
							+ "from proveedores, catalogo_tipo_documentos as cat_doc where proveedores.cat_id_tipodoc = cat_doc.cat_id_tipodoc "
							+ "and upper(prov_nombre) like ?");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%"); // Convierto a mayusculas
			rs = ps.executeQuery();

			while (rs.next()) {

				String identificador = rs.getString("prov_identificador");
				String codigoTipoDocumento = rs.getString("cat_id_tipodoc");
				String nombreTipoDocumento = rs.getString("cat_nombre");
				String nombre = rs.getString("prov_nombre");
				String telefono = rs.getString("prov_telefono");
				String correo = rs.getString("prov_correo");
				String direccion = rs.getString("prov_direccion");

				CatalogoTipoDocumentos td = new CatalogoTipoDocumentos(codigoTipoDocumento, nombreTipoDocumento);
				proveedor = new Proveedor(identificador, td, nombre, telefono, correo, direccion);
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

	

	public void insertar(Proveedor proveedor) throws KrakeDevException {

		Connection con = null;

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO proveedores (prov_identificador, cat_id_tipodoc, prov_nombre, prov_telefono, prov_correo, prov_direccion) VALUES (?, ?, ?, ?, ?, ?);");
			ps.setString(1, proveedor.getProvIdentificador());
			ps.setString(2, proveedor.getTipoDocumento().getCatIdTipoDc());
			ps.setString(3, proveedor.getProvNombre());
			ps.setString(4, proveedor.getProvTelefono());
			ps.setString(5, proveedor.getProvCorreo());
			ps.setString(6, proveedor.getProvDireccion());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar clientes. Detalle: " + e.getMessage());
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	
}
