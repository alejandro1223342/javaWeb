package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Catalogo;
import com.krakedev.inventarios.entidades.CatalogoTipoDocumentos;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class CatalogoBDD {
	public void insertarCatalogo(Catalogo catalogo) throws KrakeDevException {

		Connection con = null;

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO catalogo (cat_nombre, cat_descripcion, cat_padre) VALUES (?, ?, ?)");
			ps.setString(1, catalogo.getCatNombre());
			ps.setString(2, catalogo.getCatDescripcion());
			if (catalogo.getCatPadre() == null || catalogo.getCatPadre().getCatId() <= 0) {
				ps.setNull(3, Types.INTEGER);
			} else {
				ps.setInt(3, catalogo.getCatPadre().getCatId());
			}

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar catalogos. Detalle: " + e.getMessage());
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

	public void actualizar(Catalogo catalogo) throws KrakeDevException {

		Connection con = null;

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"UPDATE catalogo " + "	SET cat_nombre=?, cat_descripcion=?, cat_padre=? WHERE cat_id= ?;");
			ps.setString(1, catalogo.getCatNombre());
			ps.setString(2, catalogo.getCatDescripcion());
			if (catalogo.getCatPadre() == null || catalogo.getCatPadre().getCatId() <= 0) {
				ps.setNull(3, Types.INTEGER);
			} else {
				ps.setInt(3, catalogo.getCatPadre().getCatId());
			}
			ps.setInt(4, catalogo.getCatId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualizar catalogo. Detalle: " + e.getMessage());
		} catch (KrakeDevException e) {
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
	
	public class TipoDocumentoBDD {
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
}
